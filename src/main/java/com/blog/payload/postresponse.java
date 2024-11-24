package com.blog.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class postresponse {
    private List<postdto> content;
    private int pagenumber;
    private  int pagesize;
    private long totalement;
    private  int totalpage;
    private boolean lastpage;
}
