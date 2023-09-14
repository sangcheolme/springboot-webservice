package com.jojoldu.book.springbootwebservice.config;

import com.jojoldu.book.springbootwebservice.TestDataInit;
import com.jojoldu.book.springbootwebservice.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@RequiredArgsConstructor
@Configuration
public class InitConfig {

    private final PostsService postsService;

    @Bean
    @Profile("local")
    public TestDataInit testDataInit() {
        return new TestDataInit(postsService);
    }
}
