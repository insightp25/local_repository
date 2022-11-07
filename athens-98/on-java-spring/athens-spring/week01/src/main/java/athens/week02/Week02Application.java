package athens.week02;

import athens.week02.domain.Course;
import athens.week02.domain.Person;
import athens.week02.dto.CourseRequestDto;
import athens.week02.dto.PersonRequestDto;
import athens.week02.repository.CourseRepository;
import athens.week02.repository.PersonRepository;
import athens.week02.service.CourseService;
import athens.week02.service.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class Week02Application {

	public static void main(String[] args) {

		SpringApplication.run(Week02Application.class, args);

	}

	@Bean
	public CommandLineRunner demo(CourseRepository courseRepository, CourseService courseService, PersonRepository personRepository, PersonService personService) {
		return (args) -> {
			courseRepository.save(new Course("spring data jpa", "j. song"));
			courseRepository.save(new Course("what up yo?", "writer K"));
			personRepository.save(new Person("wowwow", 55));
			personRepository.save(new Person("meowmeow", 25));

			System.out.println("print data");
			List<Course> courseList = courseRepository.findAll();
			for (int i = 0; i < courseList.size(); i++) {
				Course course = courseList.get(i);
				System.out.println(course.getId());
				System.out.println(course.getTitle());
				System.out.println(course.getTutor());
			}

//			PersonRequestDto personRequestDto = new PersonRequestDto("Yiyi", 16);
//			personService.update(1L, personRequestDto);

			CourseRequestDto requestDto = new CourseRequestDto("fun react", "M. Lim");
			courseService.update(1L, requestDto);

			courseList = courseRepository.findAll();
			for (int i = 0; i < courseList.size(); i++) {
				Course course = courseList.get(i);
				System.out.println(course.getId());
				System.out.println(course.getTitle());
				System.out.println(course.getTutor());
			}

//			repository.save(new Course("spring data jpa", "j"));
//			repository.save(new Course("spring core", "j"));
//
//			List<Course> courseList = repository.findAll();
//			for (int i = 0; i < courseList.size(); i++) {
//				Course course = courseList.get(i);
//				System.out.println(course.getId());
//				System.out.println(course.getTitle());
//				System.out.println(course.getTutor());
//			}
//
//			Course course = repository.findById(1L).orElseThrow(
//					() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
//			);
//			System.out.println(course.getTutor());
		};
	}

}
