package com.recruit.assignment.domain.board;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class BoardContents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "board_id")
    private Board board;

    private String title;

    private String contents;

    @OneToMany(mappedBy = "boardContents")
    private List<BoardComment> comments;

}
