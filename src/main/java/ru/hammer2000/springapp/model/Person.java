package ru.hammer2000.springapp.model;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.List;

public class Person {

    private int id;

    @Size(min = 2, max = 30, message = "Имя должно быть длиной от 2 до 30 символов")
    private String name;

    @Size(min = 2, max = 30, message = "Фамилия должна быть длиной от 2 до 30 символов")
    private String surname;

    @Size(max = 30, message = "Отчество должно быть длиной до 30 символов")
    private String middlename;

    @Max(value = 2016, message = "Год рождения не может быть позже 2016")
    @Min(value = 1900, message = "Год рождения не может быть раньше 1900")
    private int yearOfBirth;

    private List<Book> books;

    public Person() {
    }

    public Person(int id, String name, String surname, String middlename, int yearOfBirth, List<Book> books) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
        this.yearOfBirth = yearOfBirth;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getFullName() {
        return surname + " " + name + " " + middlename;
    }

}
