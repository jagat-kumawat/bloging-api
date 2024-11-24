package com.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="categories")
public class categories {
@Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cate_Id;
//@Column(name = "title",length=100,nullable = false)
@Column(name = "titlename",nullable = false, length = 100)
private String title;

private  String description;
@OneToMany(mappedBy = "ca",cascade = CascadeType.ALL)
private List<posts> p = new ArrayList<>();
}
