package com.register.InputOutputControlRegister.JwtSecurityLayer.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.register.InputOutputControlRegister.JwtSecurityLayer.Models.Role;
import com.register.InputOutputControlRegister.JwtSecurityLayer.Models.RoleName;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}