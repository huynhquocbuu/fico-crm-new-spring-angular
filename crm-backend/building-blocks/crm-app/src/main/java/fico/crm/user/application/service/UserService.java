package fico.crm.user.application.service;

import fico.crm.shared.dtos.RoleDto;
import fico.crm.user.domain.UserDto;
import fico.crm.user.application.port.in.UserUseCase;
import fico.crm.user.application.port.out.PersistencePort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserUseCase {

    private PersistencePort persistencePort;
    public UserService(@Qualifier("PersistencePort4UserDomain") PersistencePort persistencePort){
        this.persistencePort = persistencePort;
    }
    @Override
    public List<RoleDto> getAllRoles(){
        return persistencePort.getAllRoles();
    }

    @Override
    public List<UserDto> getAllUsers() {
        return persistencePort.getAllUsers();
    }
}
