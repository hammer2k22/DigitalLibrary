package ru.hammer2000.springapp.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.hammer2000.springapp.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookFromPersonMapper implements RowMapper<Integer> {
    @Override
    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
        Integer bookId = resultSet.getInt("book_id");
        return bookId;
    }
}
