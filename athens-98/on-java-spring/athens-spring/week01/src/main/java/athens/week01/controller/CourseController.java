package athens.week01.controller;

import athens.week01.Course;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    @GetMapping("/courses")
    public Course getCourses() {
        Course course = new Course();
        course.setTitle("웹 개발");
        course.setDays(35);
        course.setTutor("J Song");
        return course;
    }
}
