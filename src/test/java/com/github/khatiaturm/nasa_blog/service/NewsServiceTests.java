package com.github.khatiaturm.nasa_blog.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.khatiaturm.nasa_blog.model.NewsPost;
import com.github.khatiaturm.nasa_blog.repository.NewsPostRepository;
import com.github.khatiaturm.nasa_blog.web.NewsForm;
//enables Mockito for JUnit 5
@ExtendWith(MockitoExtension.class)
class NewsServiceTests {

    @Mock
    private NewsPostRepository newsPostRepository;

    @InjectMocks
    private NewsService newsService;

    @Test
    void findAllReturnsPostsFromRepository() {
        List<NewsPost> posts = List.of(
                new NewsPost("Moon mission", "Details about the Moon mission.", null),
                new NewsPost("Mars update", "Details about the Mars rover.", null));
        when(newsPostRepository.findAllByOrderByIdDesc()).thenReturn(posts);

        List<NewsPost> result = newsService.findAll();

        assertThat(result).isEqualTo(posts);
        verify(newsPostRepository).findAllByOrderByIdDesc();
    }

    @Test
    void findByIdThrowsExceptionWhenPostDoesNotExist() {
        when(newsPostRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> newsService.findById(99L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("News post not found: 99");

        verify(newsPostRepository).findById(99L);
    }


}
