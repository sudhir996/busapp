package com.busbooking.busapp.repository;

import com.busbooking.busapp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);            //finding the record based on Name

}