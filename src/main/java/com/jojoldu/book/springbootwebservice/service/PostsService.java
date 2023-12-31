package com.jojoldu.book.springbootwebservice.service;

import com.jojoldu.book.springbootwebservice.domain.posts.Posts;
import com.jojoldu.book.springbootwebservice.domain.posts.PostsRepository;
import com.jojoldu.book.springbootwebservice.web.dto.PostsListResponseDto;
import com.jojoldu.book.springbootwebservice.web.dto.PostsResponseDto;
import com.jojoldu.book.springbootwebservice.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springbootwebservice.web.dto.PostsUpdateRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional
    public List<PostsListResponseDto> findAll() {
        List<PostsListResponseDto> list = new ArrayList<>();

        List<Posts> posts = postsRepository.findAll();
        for (Posts post : posts) {
            list.add(new PostsListResponseDto(post));
        }

        return list;
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id " + id));
        postsRepository.delete(posts);
    }

}
