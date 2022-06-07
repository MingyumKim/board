package com.study.board.Service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;


    //글 작성
    public void write(Board board, MultipartFile file) throws Exception {

        //저장할 경로
        String projectPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\files";

        //고유번호
        UUID uuid = UUID.randomUUID();

        //고유번호_파일명
        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, "name");

        file.transferTo(saveFile);

        boardRepository.save(board);
    }

    //리스트
    public List<Board> boardList() {
        return boardRepository.findAll();
    }

    //상세페이지
    public Board boardView(Integer id) {
        return boardRepository.findById(id).get();
    }

    //글 삭제
    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
    }

    //글 수정

}
