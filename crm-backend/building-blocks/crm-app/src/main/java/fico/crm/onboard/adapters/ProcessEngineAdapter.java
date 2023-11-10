package fico.crm.onboard.adapters;

import fico.crm.configuration.exception.BadExecuteProcessException;
import fico.crm.onboard.application.port.out.ProcessEnginePort;
import fico.crm.onboard.domain.CallCustomer;
import fico.crm.shared.constants.ProcessConstant;
import fico.crm.shared.constants.ProcessVariable;
import fico.crm.shared.process.ProcessEngineService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("ProcessEnginePort4LeadOnboard")
@Slf4j
public class ProcessEngineAdapter implements ProcessEnginePort {
    private ProcessEngineService processEngineService;
    public ProcessEngineAdapter (ProcessEngineService processEngineService){
        this.processEngineService = processEngineService;
    }
    @Override
    public String startOnboarding(String leadId) {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("leadId", leadId);
        String processInstanceId = processEngineService.getRuntimeService()
                .startProcessInstanceByKey(ProcessConstant.Process_Key, variables)
                .getProcessInstanceId();
        //log.info(ProcessConstant.Process_Name + " processInstanceId = " + processInstanceId);

        return processInstanceId;
    }


    @Override
    public void callCustomer(CallCustomer input) throws BadExecuteProcessException {
        var taskService = processEngineService.getTaskService();
        var task = queryCurrentTask(input.getProcessInstance(), ProcessConstant.Call_Customer_Task_Key);
        if(task == null)
            throw new BadExecuteProcessException("Task not found");
        String taskId = task.getId();

        taskService.setVariable(taskId, ProcessVariable.IS_ACCEPT_ADVICE, input.isAcceptAdvice());
        taskService.complete(taskId);
    }

    private Task queryCurrentTask(String processInstance, String taskDefinitionKey) {
        return processEngineService.getTaskService()
                .createTaskQuery()
                .processInstanceId(processInstance)
                .taskDefinitionKey(taskDefinitionKey)
                .singleResult();
    }
}
