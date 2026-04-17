package com.github.khatiaturm.nasa_blog.controller;

import java.io.IOException;
import java.util.NoSuchElementException;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.khatiaturm.nasa_blog.model.NewsPost;
import com.github.khatiaturm.nasa_blog.service.NewsService;
import com.github.khatiaturm.nasa_blog.web.NewsForm;

@Controller
public class NewsController {

    private static final Logger log = LoggerFactory.getLogger(NewsController.class);

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/")
    public String showHome(Model model) {
        model.addAttribute("newsList", newsService.findAll());
        return "home";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        if (!model.containsAttribute("newsForm")) {
            model.addAttribute("newsForm", new NewsForm());
        }
        return "create";
    }

    @PostMapping("/create")
    public String createNews(@Valid @ModelAttribute("newsForm") NewsForm newsForm,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "create";
        }

        try {
            NewsPost createdPost = newsService.create(newsForm);
            log.info("Created news post with id={}", createdPost.getId());
            redirectAttributes.addFlashAttribute("successMessage", "News post created successfully.");
            return "redirect:/news/" + createdPost.getId();
        } catch (IOException exception) {
            log.error("Failed to store uploaded file", exception);
            bindingResult.rejectValue("file", "upload.failed", "Image upload failed. Please try again.");
            return "create";
        }
    }

    @GetMapping("/news/{id}")
    public String showNews(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("news", newsService.findById(id));
            return "news";
        } catch (NoSuchElementException exception) {
            redirectAttributes.addFlashAttribute("errorMessage", "Requested news post was not found.");
            return "redirect:/";
        }
    }
}
