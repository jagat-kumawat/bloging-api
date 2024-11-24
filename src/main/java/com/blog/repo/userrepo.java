package com.blog.repo;

import com.blog.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userrepo extends JpaRepository<user,Integer> {
    Optional<user> findByEmail(String email);



}
