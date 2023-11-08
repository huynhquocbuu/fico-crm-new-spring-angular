package crm.auth.application.port.in;

import crm.auth.domain.LoginRequest;
import crm.auth.domain.LoginResponse;
import crm.auth.domain.RegisterRequest;
import crm.auth.domain.RegisterResponse;

import javax.management.relation.RoleNotFoundException;

public interface AuthUseCase {
    LoginResponse login(LoginRequest loginRequest);

    RegisterResponse register(RegisterRequest registerRequest) throws RoleNotFoundException;
}
