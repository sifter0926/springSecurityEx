package com.example.securityex01.service;

import com.example.securityex01.domain.Board;
import com.example.securityex01.domain.User;

import java.util.List;

public interface BoardService {
    void insert(Board board, User user);
    public List<Board> list();
    public Board findById(Long num);
    public void update(Board board);
    public void delete(Long num);
}
