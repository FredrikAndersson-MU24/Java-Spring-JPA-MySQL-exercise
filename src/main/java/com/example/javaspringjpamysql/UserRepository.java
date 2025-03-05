package com.example.javaspringjpamysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByIdAndNameAndEmail(long id, String name, String email);

    List<User> findByNameContainingIgnoreCase(String name);

}
