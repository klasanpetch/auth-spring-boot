package com.klasanpetch.auth_spring_boot.repositories;

import com.klasanpetch.auth_spring_boot.entites.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);
}
