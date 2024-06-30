package org.example;

import org.example.ApplicationConfiguration;
import org.example.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfiguration.class })
public class BookTests {

    @Autowired
    private ApplicationContext context;

    @Test
    @DisplayName("Check that the book title was changed")
    public void checkBookTitleChanged() {
        Book book = context.getBean("book", Book.class);

        assertEquals("Changed title", book.getTitle());
    }
}
