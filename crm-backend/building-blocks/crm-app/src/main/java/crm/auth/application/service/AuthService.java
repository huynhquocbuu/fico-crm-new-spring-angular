package crm.auth.application.service;

import crm.auth.application.port.in.AuthUseCase;
import crm.auth.application.port.out.AuthPersistencePort;
import crm.auth.domain.*;
import crm.auth.mappers.AuthMapper;
import crm.configuration.security.JwtUtil;
import crm.infrastructure.entities.Role;
import crm.infrastructure.entities.User;
import crm.shared.enums.EAuthType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService implements AuthUseCase {
    @Value("${app.user.default.password}")
    private String defaultPassword;
    private AuthPersistencePort persistencePort;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    public AuthService(AuthPersistencePort persistencePort,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil){
        this.persistencePort = persistencePort;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    @Override
    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        User user = persistencePort.findAuthUser(loginRequest.getUsername());
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
            throw new BadCredentialsException("wrong username or password");
        var accessToken = jwtUtil.generateJwtToken(user, user.getRoles());

        return LoginResponse.builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .roles(user.getRoles()
                        .stream()
                        .map(r -> new RoleDto(r.getId(), r.getRoleName()))
                        .collect(Collectors.toSet()))
                .accessToken(accessToken)
                .authType("jwt Bearer")
                .build();
    }

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest registerRequest) throws RoleNotFoundException {
        persistencePort.checkRegisterUser(registerRequest.getUsername());
        Set<Role> userRoles = persistencePort.findRegisterRoles(registerRequest.getRoles());
        User userEntity = AuthMapper.INSTANCE
                .toUserEntity(
                    registerRequest,
                    userRoles,
                EAuthType.LOCAL,
                passwordEncoder.encode(defaultPassword));
        userEntity =  persistencePort.insertUser(userEntity);
        return AuthMapper.INSTANCE
                .toRegisterResponse(userEntity);
    }


}
