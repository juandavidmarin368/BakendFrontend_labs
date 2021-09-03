package com.root.Generic.JwtSecurityLayer.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.root.Generic.JwtSecurityLayer.Models.Role;
import com.root.Generic.JwtSecurityLayer.Models.RoleName;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}