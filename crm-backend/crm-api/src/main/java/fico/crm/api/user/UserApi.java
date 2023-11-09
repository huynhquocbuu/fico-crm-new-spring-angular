package fico.crm.api.user;

import fico.crm.configuration.payload.ResponseHandler;
import fico.crm.configuration.payload.ResponseWrapper;
import fico.crm.shared.dtos.RoleDto;
import fico.crm.user.application.port.in.UserUseCase;
import fico.crm.user.domain.UserDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'ADMIN')")
public class UserApi {
    private UserUseCase useCase;
    public UserApi (UserUseCase useCase){
        this.useCase = useCase;
    }
    @GetMapping("/get-all")
    public ResponseEntity<ResponseWrapper<List<UserDto>>> getAll() {
        return ResponseHandler.success(useCase.getAllUsers());
    }
}
