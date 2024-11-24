package com.blog.payload;

import com.blog.entity.roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class userdto {

    private  int id;
    @NotBlank
    @Size(min = 3,max = 20,message = "please enter valid user name")
    private String name;
    @Email
    private String email;
    @NotBlank
    private String password;
@NotBlank
    private  String aboud;
    private Set<rolesDto> role= new HashSet<>();
}

