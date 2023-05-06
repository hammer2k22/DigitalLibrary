package ru.hammer2000.springapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.hammer2000.springapp.dao.BookDAO;
import ru.hammer2000.springapp.dao.PersonDAO;
import ru.hammer2000.springapp.model.Book;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("people", personDAO.index());

        if (bookDAO.show(id).getPersonId() != 0) {
            int personId = bookDAO.show(id).getPersonId();
            model.addAttribute("person", personDAO.show(personId));
        }

        return "books/show";
    }

    @GetMapping("/new")
    public String newEmployee(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping()
    public String create(NumberFormatException e, @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDAO.save(book);
        return "redirect:/books";

    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/add/{id}")
    public String addBookToPerson(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        bookDAO.addBookToPerson(id, book.getPersonId());
        return "redirect:/books";
    }

    @PatchMapping("/remove/{id}")
    public String removeBookFromPerson(@PathVariable("id") int id) {
        bookDAO.removeBookFromPerson(id);
        return "redirect:/books";
    }

}
