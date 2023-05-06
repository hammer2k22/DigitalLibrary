package ru.hammer2000.springapp.model;

import javax.validation.constraints.*;

public class Book {

    private int id;
    private int personId;


    @Size(min = 2, message = "Название должно быть длиной от 2 символов")
    private String title;

    @Size(min = 5, message = "Фамилия и имя автора должны быть минимум по 2 символа")
    private String author;

    @Max(value = 2023, message = "Год не может быть позже 2023")
    @Min(value = 1900, message = "Год не может быть раньше 1900")
    private int year;

    public Book() {
    }

    public Book(int id, int personId, String title, String author, int year) {
        this.id = id;
        this.personId = personId;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", personId=" + personId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}
