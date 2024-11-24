package com.blog.entity;

import com.blog.payload.commentdto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class posts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
private Integer id;
    @Column(name= "post_title", length = 100,nullable = false)
private String title;
private String content;
private String image;
private Date date;

@ManyToOne
@JoinColumn(name = "cate_id")
private categories ca;
@ManyToOne
private user u;

@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
   private Set<comment> comm= new HashSet<>();
}
