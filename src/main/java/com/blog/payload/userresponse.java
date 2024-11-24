package com.blog.payload;

import jakarta.validation.constraints.DecimalMax;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class userresponse {
    private List<userdto> content;
    private int pagenumber;
    private int pagesize;
    private long totleelement;
    private int totlepage;
    private boolean lastpage;
}
