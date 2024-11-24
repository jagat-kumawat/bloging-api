package com.blog.repo;

import com.blog.entity.categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoresrepo extends JpaRepository<categories,Integer> {



}
