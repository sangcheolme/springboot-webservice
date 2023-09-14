package com.jojoldu.book.springbootwebservice;

import com.jojoldu.book.springbootwebservice.service.PostsService;
import com.jojoldu.book.springbootwebservice.web.dto.PostsSaveRequestDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;

@RequiredArgsConstructor
public class TestDataInit {

    private final PostsService postsService;

    @Profile("local")
    @PostConstruct
    public void init() {
        PostsSaveRequestDto requestDto1 = new PostsSaveRequestDto("제목1", "내용1", "작가1");
        PostsSaveRequestDto requestDto2 = new PostsSaveRequestDto("제목2", "내용2", "작가2");
        PostsSaveRequestDto requestDto3 = new PostsSaveRequestDto("제목3", "내용3", "작가3");
        postsService.save(requestDto1);
        postsService.save(requestDto2);
        postsService.save(requestDto3);
    }
}
