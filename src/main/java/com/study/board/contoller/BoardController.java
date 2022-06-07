package com.study.board.contoller;

import com.study.board.Service.BoardService;
import com.study.board.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("board/write") //localhost:8080/board/write
    public String boardWriteForm() {

        return "boardWrite";
    }

    @PostMapping("/board/writePro")
    public String boardWritePro(Board board, Model model, MultipartFile file) throws Exception{

        boardService.write(board, file);

        model.addAttribute("message", "글 작성완료");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model) {

        model.addAttribute("list", boardService.boardList());

        return "boardList";
    }

    @GetMapping("/board/view")
    public String boardView(Model model, Integer id) {

        model.addAttribute("board", boardService.boardView(id));

        return "boardView";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id) {
        boardService.boardDelete(id);

        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("board", boardService.boardView(id));

        return "boardModify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, Model model, MultipartFile file) throws Exception {

        //기존 내용가져오기
        Board boardTemp = boardService.boardView(id);

        //새로운 데이터 덮어씌우기
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());
        boardService.write(boardTemp, file);

        model.addAttribute("message", "글 수정완료");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

}
