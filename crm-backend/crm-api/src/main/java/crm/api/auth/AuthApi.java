package crm.api.auth;

import crm.auth.application.port.in.AuthUseCase;
import crm.auth.domain.LoginRequest;
import crm.auth.domain.LoginResponse;
import crm.auth.domain.RegisterRequest;
import crm.auth.domain.RegisterResponse;
import crm.configuration.payload.ResponseHandler;
import crm.configuration.payload.ResponseWrapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping("/auth")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthApi {
    private final AuthUseCase useCase;
    public AuthApi(AuthUseCase useCase){
        this.useCase = useCase;
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseWrapper<LoginResponse>> login
            (@Valid @RequestBody LoginRequest input) {
        return ResponseHandler.success(useCase
                .login(input));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseWrapper<RegisterResponse>> register
            (@Valid @RequestBody RegisterRequest input) throws RoleNotFoundException {
        return ResponseHandler
                .success(useCase.register(input));
    }
}
