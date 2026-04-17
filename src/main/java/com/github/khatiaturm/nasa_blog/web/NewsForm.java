package com.github.khatiaturm.nasa_blog.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class NewsForm {

    @NotBlank(message = "Title is required.")
    @Size(min = 3, max = 120, message = "Title must be between 3 and 120 characters.")
    private String title;

    @NotBlank(message = "Content is required.")
    @Size(min = 10, max = 5000, message = "Content must be between 10 and 5000 characters.")
    private String content;

    private MultipartFile file;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
