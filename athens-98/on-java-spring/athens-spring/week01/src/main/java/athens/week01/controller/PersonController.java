package athens.week01.controller;

import athens.week02.domain.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @GetMapping("/myinfo")
    public Person getPerson() {
        Person person = new Person();
        person.setName("alpha");
        person.setAge(30);
        person.setAddress("San Jose");
        return person;
    }
}
