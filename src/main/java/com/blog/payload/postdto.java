package com.blog.payload;

import com.blog.entity.categories;
import com.blog.entity.comment;
import com.blog.entity.user;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class postdto {
    private String id;
    private String title;
    private String content;
    private String image;
    private Date date;
    private categoresdto ca;
    private userdto u;
    private Set<commentdto> comm = new HashSet<>();
}
