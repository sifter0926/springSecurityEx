package com.example.securityex01.controller;

import com.example.securityex01.config.auth.PrincipalDetails;
import com.example.securityex01.domain.Board;
import com.example.securityex01.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("insert")
    public String insert() {
        return "/board/register";
    }
    @PostMapping("/insert")
    public String insert(Board board,
                         @AuthenticationPrincipal PrincipalDetails principal) {
        boardService.insert(board,principal.getUser() );
        return "redirect:/board/list";
    }

    @GetMapping("/view")
    public String view(@RequestParam Long num, Model model) {
        model.addAttribute("board", boardService.findById(num));
        return "/board/view";
    }
    //수정폼
    @GetMapping("/modify")
    public String update(@RequestParam Long num, Model model) {
        model.addAttribute("board", boardService.findById(num));
        return "/board/modify";
    }

	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("lists", boardService.list());
		return "/board/list";
	}
    @PutMapping("/update")
    public String update(Board board) {
        boardService.update(board);
        return "redirect:/board/view?num="+board.getNum();
    }
    //삭제
    @GetMapping("/delete")
    public String delete(@RequestParam Long num) {
        boardService.delete(num);
        return "redirect:/board/list";
    }

}
