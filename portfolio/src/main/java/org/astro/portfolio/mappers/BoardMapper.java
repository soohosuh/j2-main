package org.astro.portfolio.mappers;

import java.util.List;

import org.astro.portfolio.dto.BoardDTO;

public interface BoardMapper {

    List<BoardDTO> getList();

    BoardDTO getOne(Integer bno);
    
}
