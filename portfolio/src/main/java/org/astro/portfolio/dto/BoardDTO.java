package org.astro.portfolio.dto;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Integer bno;
    private String mod_date;
    private String reg_date;
    private String content;
    private String title;
    private String writer;
    private String moddate;
    
}
