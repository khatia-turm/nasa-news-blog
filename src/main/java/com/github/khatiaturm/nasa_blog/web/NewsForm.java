package com.github.khatiaturm.nasa_blog.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class NewsForm {

    @NotBlank(message = "{validation.title.required}")
    @Size(min = 3, max = 120, message = "{validation.title.size}")
    private String title;

    @NotBlank(message = "{validation.content.required}")
    @Size(min = 10, max = 5000, message = "{validation.content.size}")
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
