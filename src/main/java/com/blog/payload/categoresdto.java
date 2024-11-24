package com.blog.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class categoresdto {

    private int  Id;
@NotBlank

    private String title;
@NotBlank
    private  String description;

}
