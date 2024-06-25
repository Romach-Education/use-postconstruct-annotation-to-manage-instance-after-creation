package org.example;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class Book {
    private String title;

    public Book() {
        this.title = "One Hundred Years of Solitude";
    }

    @PostConstruct
    public void init() {
        this.title = "Changed title";
    }

    public String getTitle() {
        return title;
    }
}
