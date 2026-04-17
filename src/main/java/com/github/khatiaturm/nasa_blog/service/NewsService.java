package com.github.khatiaturm.nasa_blog.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.github.khatiaturm.nasa_blog.model.NewsPost;
import com.github.khatiaturm.nasa_blog.web.NewsForm;

@Service
public class NewsService {

    private final AtomicLong nextId = new AtomicLong(1);
    private final List<NewsPost> posts = new ArrayList<>();
    private final Path uploadDirectory = Paths.get("uploads");

    public synchronized List<NewsPost> findAll() {
        return posts.stream()
                .sorted(Comparator.comparingLong(NewsPost::getId).reversed())
                .toList();
    }

    public synchronized NewsPost findById(long id) {
        return posts.stream()
                .filter(post -> post.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("News post not found: " + id));
    }

    public synchronized NewsPost create(NewsForm form) throws IOException {
        long id = nextId.getAndIncrement();
        String imagePath = storeFile(form.getFile());
        NewsPost post = new NewsPost(id, form.getTitle().trim(), form.getContent().trim(), imagePath);
        posts.add(post);
        return post;
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
