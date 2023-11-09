package fico.crm.auth.adapters;

import fico.crm.auth.application.port.out.AuthPersistencePort;
import fico.crm.infrastructure.entities.Role;
import fico.crm.infrastructure.entities.User;
import fico.crm.infrastructure.repositories.RoleRepository;
import fico.crm.infrastructure.repositories.UserRepository;
import fico.crm.shared.enums.ERole;
import jakarta.persistence.EntityExistsException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthPersistenceAdapter implements AuthPersistencePort {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    public AuthPersistenceAdapter(UserRepository userRepository,
                                  RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public User findAuthUser(String username) {
        var optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty())
            throw new BadCredentialsException("wrong username or password");
            //throw new UsernameNotFoundException("User Not Found with username: " + loginRequest.getUsername());

        return optionalUser.get();
    }

    @Override
    public void checkRegisterUser(String username) {
        var optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isPresent())
            throw new EntityExistsException("user: " + username + "is existing!!!");
    }

    @Override
    public Set<Role> findRegisterRoles(Set<ERole> roles) throws RoleNotFoundException {
        Set<Role> output = new HashSet<>();
        for (ERole role : roles) {
            var entityRole = roleRepository.findById(role);
            if(entityRole.isEmpty())
                throw new RoleNotFoundException(role.name() + " is not existing");
            output.add(entityRole.get());
        }

        return output;
    }

    @Override
    public User insertUser(User userEntity) {
        return userRepository.save(userEntity);
    }
}
