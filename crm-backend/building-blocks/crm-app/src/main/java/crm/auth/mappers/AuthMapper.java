package crm.auth.mappers;

import crm.auth.domain.RegisterRequest;
import crm.auth.domain.RegisterResponse;
import crm.auth.domain.RoleDto;
import crm.infrastructure.entities.Role;
import crm.infrastructure.entities.User;
import crm.shared.enums.EAuthType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

//@Mapper
@Mapper(componentModel = "spring")
public interface AuthMapper {
    AuthMapper INSTANCE = Mappers.getMapper( AuthMapper.class );

    @Mapping(target = "username", source = "registerRequest.username")
    @Mapping(target = "authType", source = "authType")
    @Mapping(target = "fullName", source = "registerRequest.fullName")
    @Mapping(target = "email", source = "registerRequest.email")
    @Mapping(target = "password", source = "passwordHash")
    @Mapping(target = "roles", source = "userRoles")
    User toUserEntity(RegisterRequest registerRequest, Set<Role> userRoles, EAuthType authType, String passwordHash);

    default RegisterResponse toRegisterResponse(User user){
        return RegisterResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .roles(user.getRoles()
                        .stream()
                        .map(r -> new RoleDto(r.getId(), r.getRoleName()))
                        .collect(Collectors.toSet()))
                .build();
    }
}
