package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao{

    private final JdbcTemplate jdbcTemplate;
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        return 0;
    }

    @Override
    public List<Person> getAllPerson() {

        String sql = "SELECT id, name FROM person";
        List<Person> list = jdbcTemplate.query(sql, (requestedParam, i) -> {
            UUID id = UUID.fromString(requestedParam.getString("id"));
            String name = requestedParam.getString("name");
            return new Person(id, name);
        });
        return list;
    }

    @Override
    public int deletePersonById(UUID id) {
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return 0;
    }

    @Override
    public Optional<Person> getPersonById(UUID id) {
        String sql  = "select id, name from person where id = ?";

        Person person = jdbcTemplate.queryForObject(sql, new Object[]{id}, (requestedParam, i) -> {
            UUID personId = UUID.fromString(requestedParam.getString("id"));
            String name = requestedParam.getString("name");
            return new Person(personId, name);
        });
        return Optional.ofNullable(person);
    }
}
