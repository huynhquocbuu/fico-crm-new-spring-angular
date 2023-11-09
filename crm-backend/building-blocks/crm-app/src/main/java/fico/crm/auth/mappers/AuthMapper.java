package fico.crm.auth.mappers;

import fico.crm.auth.domain.RegisterRequest;
import fico.crm.auth.domain.RegisterResponse;
import fico.crm.shared.dtos.RoleDto;
import fico.crm.infrastructure.entities.Role;
import fico.crm.infrastructure.entities.User;
import fico.crm.shared.enums.EAuthType;
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
