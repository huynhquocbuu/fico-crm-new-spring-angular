package crm.api.auth;

import crm.auth.application.port.in.LoginUseCase;
import crm.auth.domain.LoginRequest;
import crm.auth.domain.LoginResponse;
import crm.configuration.payload.ResponseWrapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthApi {
    private final LoginUseCase loginUseCase;
    public AuthApi(LoginUseCase loginUseCase){
        this.loginUseCase = loginUseCase;
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseWrapper<LoginResponse>> login
            (@Valid @RequestBody LoginRequest input) {
        return null;
//        return loginUseCase
//                .login(input)
//                .map(ResponseHandler::responseSuccess)
//                .orElseGet(() -> ResponseHandler
//                        .responseErr(
//                                null,
//                                EErrorCode.Invalid_UserName_Password.name(),
//                                EErrorCode.Invalid_UserName_Password.name()));
    }
}
