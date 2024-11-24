package com.blog.entity;

import com.blog.payload.commentdto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class user implements UserDetails {

//    id, email,password,about
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    @Column(name = "user_name",nullable = false, length = 100)

    private String name;

    private String email;
@NotNull
    private String password;

    private  String aboud;
    @OneToMany(mappedBy = "u",cascade = CascadeType.ALL)
    private List<posts> p = new ArrayList<>();

     @OneToMany(mappedBy = "uu",cascade = CascadeType.ALL)
         private Set<comment> cc = new HashSet<>();




     @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
     @JoinTable(name = "user_Role",
             joinColumns =@JoinColumn (name="user",referencedColumnName = "id"),
     inverseJoinColumns = @JoinColumn(name = "role",referencedColumnName = "id")
     )
     private Set<roles> role= new HashSet<>();



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> collect = this.role.stream().map((ro) -> new SimpleGrantedAuthority(ro.getName())).collect(Collectors.toList());
        return collect;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
