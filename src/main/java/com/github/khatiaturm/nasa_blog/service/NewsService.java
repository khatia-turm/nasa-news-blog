package com.github.khatiaturm.nasa_blog.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.github.khatiaturm.nasa_blog.model.NewsPost;
import com.github.khatiaturm.nasa_blog.repository.NewsPostRepository;
import com.github.khatiaturm.nasa_blog.web.NewsForm;

@Service
public class NewsService {

    private final NewsPostRepository newsPostRepository;
    private final Path uploadDirectory = Paths.get("uploads");

    public NewsService(NewsPostRepository newsPostRepository) {
        this.newsPostRepository = newsPostRepository;
    }

    public List<NewsPost> findAll() {
        return newsPostRepository.findAllByOrderByIdDesc();
    }

    public NewsPost findById(long id) {
        return newsPostRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("News post not found: " + id));
    }

    public NewsPost create(NewsForm form) throws IOException {
        String imagePath = storeFile(form.getFile());
        NewsPost post = new NewsPost(form.getTitle().trim(), form.getContent().trim(), imagePath);
        return newsPostRepository.save(post);
    }

    private String storeFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        Files.createDirectories(uploadDirectory);

        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String storedFilename = UUID.randomUUID() + "-" + originalFilename;
        Path targetPath = uploadDirectory.resolve(storedFilename);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
        }

        return "/uploads/" + storedFilename;
    }
}
