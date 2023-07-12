package org.zerock.j2.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductDTO {
    
    private Long pno;
    private String pname;
    private String pdesc;
    private int price;

    //데이터베이스 처리용도로 images있다
    private List<String> images;

    //등록/수정 업로드된 파일 데이터를 수집하는 용도
    private List<MultipartFile> files;
}
