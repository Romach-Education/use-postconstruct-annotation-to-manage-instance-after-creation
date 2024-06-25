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
