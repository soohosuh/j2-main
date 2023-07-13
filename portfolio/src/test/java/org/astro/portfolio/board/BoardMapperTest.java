package org.astro.portfolio.board;

import java.util.List;

import org.astro.portfolio.dto.BoardDTO;
import org.astro.portfolio.mappers.BoardMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardMapperTest {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    public void getList(){

        List<BoardDTO> boardList = boardMapper.getList();

        log.info(boardList);
        
    }
    
}
