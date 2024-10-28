package com.example.securityex01.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name="tbl_board3")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;
    private String title;
    private String writer;
    private String content;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="regdate")
    private Date regdate;
    private Long hitcount;
    private Long replycnt;
    @OneToMany(mappedBy = "board",
            fetch =FetchType.LAZY, cascade = CascadeType.ALL )
    @JsonIgnoreProperties("board")
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user; //유저정보

    @PrePersist
    public void prePersist() {
        this.hitcount= this.hitcount==null? 0 : this.hitcount;
        this.replycnt= this.replycnt==null? 0 : this.replycnt;
    }
}
