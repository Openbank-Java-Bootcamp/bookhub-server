package com.ironhack.bookhubserver.Service.Implementation;

import com.ironhack.bookhubserver.Model.Role;
import com.ironhack.bookhubserver.Model.User;
import com.ironhack.bookhubserver.Repositories.RoleRepository;
import com.ironhack.bookhubserver.Repositories.UserRepository;
import com.ironhack.bookhubserver.Service.Interface.RoleServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleService implements RoleServiceInterface {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;


    public Role saveRole(Role role) {
        log.info("Saving a new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    public void addRoleToUser(String email, String roleName) {
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);
    }
}
