package com.recruit.assignment.domain.board.dto;

import com.recruit.assignment.domain.board.BoardComment;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BoardDetailResponseDto implements Serializable {

    private static final long serialVersionUID = -1424317357078505726L;

    private long id;

    private String title;

    private String contents;

    private List<BoardComment> comments;

}
