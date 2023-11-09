package fico.crm.user.mappers;

import fico.crm.infrastructure.entities.Role;
import fico.crm.infrastructure.entities.User;
import fico.crm.shared.dtos.RoleDto;
import fico.crm.user.domain.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Mapping(target = "roles", expression = "java(mapToRoleDto(source.getRoles()))")
    UserDto toUserDto(User source);

    default Set<RoleDto> mapToRoleDto(Set<Role> roles){
        return roles.stream()
                .map(r -> new RoleDto(r.getId(), r.getRoleName()))
                .collect(Collectors.toSet());
    }
}