package com.shin.ricu;

import com.shin.ricu.dto.CommentDTO;
import com.shin.ricu.service.BoardService;
import com.shin.ricu.service.CommentService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class CommentTests {

    @Autowired
    CommentService commentService;
    @Autowired
    BoardService boardService;

    @BeforeEach
    public void deleteAll()
    {

    }

    @Test
    public void replyDTOTest()
    {

    }
}
