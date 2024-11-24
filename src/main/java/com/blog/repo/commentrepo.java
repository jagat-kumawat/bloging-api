package com.blog.repo;

import com.blog.entity.comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  commentrepo extends JpaRepository <comment,Integer>{
}
