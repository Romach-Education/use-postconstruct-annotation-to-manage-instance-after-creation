package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfiguration.class })
public class BookTests {

    @Autowired
    private Book book;

    @Test
    @DisplayName("Check that the book title was changed")
    public void checkBookTitleChanged() {
        String actualTitle = book.getTitle();

        String expectedTitle = "Changed title";

        assertEquals(expectedTitle, actualTitle);
    }
}
