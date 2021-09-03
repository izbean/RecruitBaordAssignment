package com.recruit.assignment.domain.board;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
@NoArgsConstructor
public class BoardContents {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "boardContents")
    private Board board;

    private String contents;

}
