package com.github.khatiaturm.nasa_blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.khatiaturm.nasa_blog.model.NewsPost;

public interface NewsPostRepository extends JpaRepository<NewsPost, Long> {

    List<NewsPost> findAllByOrderByIdDesc();
}
