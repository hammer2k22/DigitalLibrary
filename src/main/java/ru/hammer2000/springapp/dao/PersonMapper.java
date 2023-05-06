package ru.hammer2000.springapp.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.hammer2000.springapp.model.Book;
import ru.hammer2000.springapp.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();

        person.setId(resultSet.getInt("person_id"));
        person.setName(resultSet.getString("name"));
        person.setSurname(resultSet.getString("surname"));
        person.setMiddlename(resultSet.getString("middlename"));
        person.setYearOfBirth(resultSet.getInt("year_of_birth"));

        return person;
    }
}
