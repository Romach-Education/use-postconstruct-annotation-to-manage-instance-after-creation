# Use @PostConstruct annotation to manage instance after creation

> This project is based on chapter **2.2.2. Using stereotype annotations to add beans to the Spring context** from book **Spring Starts here (2021)** by Laurentiu Spilca.

## Create Maven project with Intellij Idea

File > New project > Java

## Add Spring Context dependency

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>6.1.10</version>
</dependency>
```

## Create entity

```java
@Component
public class Book {
    private String title;

    public Book() {
        this.title = "One Hundred Years of Solitude";
    }

    public String getTitle() {
        return title;
    }
}
```

## Add Jakarta Annotations API dependency

```xml
<dependency>
    <groupId>jakarta.annotation</groupId>
    <artifactId>jakarta.annotation-api</artifactId>
    <version>3.0.0</version>
</dependency>
```

## Add post-construct method to entity

```diff
@Component
public class Book {
    private String title;

    public Book() {
        this.title = "One Hundred Years of Solitude";
    }

+   @PostConstruct
+   public void init() {
+       this.title = "Changed title";
+   }

    public String getTitle() {
        return title;
    }
}
```

## Create configuration class

```java
@Configuration
@ComponentScan(basePackages = "org.example")
public class ApplicationConfiguration {
}
```

## Create Spring context

```java
ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
```

## Get bean from Spring context

```java
Book book = context.getBean(Book.class);
System.out.println("The book's title is " + book.getTitle());
```

## Add tests

### Add dependency for Spring TestContext Framework

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>6.1.10</version>
    <scope>test</scope>
</dependency>
```

### Add dependency for JUnit

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.11.0-M2</version>
    <scope>test</scope>
</dependency>
```

### Create test to check that application context is created

```java
public class ApplicationTests {

    @Test
    @DisplayName("Checks that Application Context is created")
    public void checkApplicationContextCreated() {
        ApplicationContext context = new AnnotationConfigApplicationContext();

        assertNotNull(context);
    }
}
```

### Create test to check that book title was changed after bean creation

- use `@ExtendWith(SpringExtension.class)` to integrate Spring TestContext Framework to the test
- use `@ContextConfiguration` to configure Spring context in Spring integration tests

```java
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
```
