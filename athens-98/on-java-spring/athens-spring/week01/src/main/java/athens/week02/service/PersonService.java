package athens.week02.service;

import athens.week02.domain.Person;
import athens.week02.dto.PersonRequestDto;
import athens.week02.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Transactional
    public Long update(Long id, PersonRequestDto requestDto) {
        Person person1 = personRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("no id!")
        );
        person1.update(requestDto);
        return person1.getId();
    }

}
