package ru.hammer2000.springapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.hammer2000.springapp.dao.PersonDAO;
import ru.hammer2000.springapp.model.Person;
@Component
public class PersonValidator implements Validator {

    private  final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if(personDAO.show(person).isPresent() && personDAO.show(person).get().getId()!= person.getId()){
            errors.rejectValue("yearOfBirth","", "Этот читатель уже существует");
        }
    }
}
