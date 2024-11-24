package com.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class comment {
 @Id
 @GeneratedValue(strategy =  GenerationType.AUTO)
    private int id;
 private String coment;
 @ManyToOne
 private posts post;
@ManyToOne
    private user uu;


}
