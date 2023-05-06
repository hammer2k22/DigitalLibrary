package ru.hammer2000.springapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.hammer2000.springapp.model.Book;
import ru.hammer2000.springapp.model.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }


    public Person show(int id) {

        Person person = jdbcTemplate.query("SELECT * FROM person WHERE person_id=?",
                        new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);

//        List<Integer> booksId = jdbcTemplate.query("select book_id from person join book " +
//                        "on person.person_id = book.person_id where person.person_id=?",
//                new Object[]{id}, new BookFromPersonMapper());

        List<Book> books = jdbcTemplate.query("select book_id, book.person_id, title, author, year " +
                        "from person join book on person.person_id = book.person_id where person.person_id = ?",
                new Object[]{id}, new BookMapper());

        if (person != null) {
            person.setBooks(books);
        }
        return person;
    }

    public Optional<Person> show(Person person) {
        return jdbcTemplate.query("SELECT * FROM person WHERE name=? and surname=? and middlename=? " +
                                "and year_of_birth=?",
                        new Object[]{person.getName(), person.getSurname(), person.getMiddlename(),
                                person.getYearOfBirth()},
                        new PersonMapper())
                .stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person (name, surname, middlename, year_of_birth) " +
                        "VALUES(?, ?, ?, ?)", person.getName(), person.getSurname(),
                person.getMiddlename(), person.getYearOfBirth());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE  person SET name=?, surname=?, middlename=?, year_of_birth=? " +
                        "WHERE person_id=? ", person.getName(), person.getSurname(),
                person.getMiddlename(), person.getYearOfBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", id);
    }
}
