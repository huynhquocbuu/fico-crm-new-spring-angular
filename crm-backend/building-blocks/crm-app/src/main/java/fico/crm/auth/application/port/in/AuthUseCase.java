package fico.crm.auth.application.port.in;

import fico.crm.auth.domain.LoginRequest;
import fico.crm.auth.domain.LoginResponse;
import fico.crm.auth.domain.RegisterRequest;
import fico.crm.auth.domain.RegisterResponse;

import javax.management.relation.RoleNotFoundException;

public interface AuthUseCase {
    LoginResponse login(LoginRequest loginRequest);

    RegisterResponse register(RegisterRequest registerRequest) throws RoleNotFoundException;
}
