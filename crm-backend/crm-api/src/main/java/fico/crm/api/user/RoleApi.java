package fico.crm.api.user;

import fico.crm.configuration.payload.ResponseHandler;
import fico.crm.configuration.payload.ResponseWrapper;
import fico.crm.shared.dtos.RoleDto;
import fico.crm.user.application.port.in.UserUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/role")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'ADMIN')")
public class RoleApi {
    private UserUseCase useCase;
    public RoleApi(UserUseCase useCase){
        this.useCase = useCase;
    }
    @GetMapping("/get-all")
    public ResponseEntity<ResponseWrapper<List<RoleDto>>> getAll() {
        return ResponseHandler.success(useCase.getAllRoles());
    }
}
