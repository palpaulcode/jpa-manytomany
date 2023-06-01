package dev.paul.jpamanytomany.controller;

import dev.paul.jpamanytomany.entity.Course;
import dev.paul.jpamanytomany.entity.Student;
import dev.paul.jpamanytomany.repository.CourseRepository;
import dev.paul.jpamanytomany.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students/course")
public class StudentCourseController {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentCourseController(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping(consumes = "application/json;charset=UTF-8")
    public Student SaveStudentWithCourse(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{studentId}")
    public Student findStudent(@PathVariable Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    @GetMapping("/find/{name}")
    public List<Student> findStudentsContainingByName(@PathVariable String name) {
        return studentRepository.findByNameContaining(name);
    }

    @GetMapping("/search/{price}")
    public List<Course> findCourseLessThanPrice(@PathVariable double price) {
        return courseRepository.findByFeeLessThan(price);
    }
}
