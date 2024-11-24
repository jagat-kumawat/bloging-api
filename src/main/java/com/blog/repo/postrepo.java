package com.blog.repo;

import com.blog.entity.categories;
import com.blog.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.entity.posts;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@EnableJpaRepositories
public interface postrepo extends JpaRepository<posts,Integer> {

    List<posts> findByU(user u );
    List<posts> findByCa(categories c);
    List<posts> findByTitleContaining(String title);

}