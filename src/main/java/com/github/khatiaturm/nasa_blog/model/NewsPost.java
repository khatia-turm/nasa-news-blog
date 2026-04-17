package com.github.khatiaturm.nasa_blog.model;

public class NewsPost {

    private final long id;
    private final String title;
    private final String content;
    private final String imagePath;

    public NewsPost(long id, String title, String content, String imagePath) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImagePath() {
        return imagePath;
    }
}
