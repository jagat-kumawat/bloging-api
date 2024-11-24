package com.blog;

import com.blog.entity.roles;
import com.blog.payload.content;
import com.blog.repo.rolerepo;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Employees API", version = "2.0", description = "Employees Information"))
//@SecurityScheme(name = "javainuseapi", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class BlogingApiApplication implements CommandLineRunner {
@Autowired
private PasswordEncoder passwordEncoder;
@Autowired
private rolerepo repo;
    public static void main(String[] args) {
        SpringApplication.run(BlogingApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(passwordEncoder.encode("jagat12345"));
        try {
            roles ro = new roles();
            ro.setId(content.ADMIN_USER);
            ro.setName("ROLE_ADMIN");

            roles ro2 = new roles();
            ro2.setId(content.NORMAL_USER);
            ro2.setName("ROLE_NORMAL");
            List<roles> ro1 = List.of(ro, ro2);
            List<roles> roles = this.repo.saveAll(ro1);



        }catch(Exception e){
            e.printStackTrace();


        }
    }
}
