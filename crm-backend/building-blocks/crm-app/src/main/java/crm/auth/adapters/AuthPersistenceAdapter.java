package crm.auth.adapters;

import crm.auth.application.port.out.AuthPersistencePort;
import crm.auth.mappers.AuthMapper;
import crm.infrastructure.entities.Role;
import crm.infrastructure.entities.User;
import crm.infrastructure.repositories.RoleRepository;
import crm.infrastructure.repositories.UserRepository;
import crm.shared.enums.ERole;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.management.relation.RoleNotFoundException;
import java.util.HashSet;
import java.util.Optional;
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
                throw new RoleNotFoundException(role.name());
            output.add(entityRole.get());
        }


        return output;
    }

    @Override
    public User insertUser(User userEntity) {
        return userRepository.save(userEntity);
    }
}
