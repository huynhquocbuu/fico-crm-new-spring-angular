package fico.crm.user.adapters;

import fico.crm.infrastructure.entities.User;
import fico.crm.infrastructure.repositories.RoleRepository;
import fico.crm.infrastructure.repositories.UserRepository;
import fico.crm.shared.dtos.RoleDto;
import fico.crm.user.domain.UserDto;
import fico.crm.user.application.port.out.PersistencePort;
import fico.crm.user.mappers.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PersistencePort4UserDomain")
public class PersistenceAdapter implements PersistencePort {
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    public PersistenceAdapter(
            RoleRepository roleRepository,
            UserRepository userRepository){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(r -> new RoleDto(r.getId(), r.getRoleName()))
                .toList();
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(u -> UserMapper.INSTANCE.toUserDto(u))
                .toList();
    }
}
