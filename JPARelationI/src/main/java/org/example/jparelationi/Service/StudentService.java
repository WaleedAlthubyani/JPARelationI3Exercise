package org.example.jparelationi.Service;

import lombok.RequiredArgsConstructor;
import org.example.jparelationi.Api.ApiException;
import org.example.jparelationi.DTO.StudentDTO;
import org.example.jparelationi.Model.Course;
import org.example.jparelationi.Model.Student;
import org.example.jparelationi.Repository.CourseRepository;
import org.example.jparelationi.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseService courseService;
    private final CourseRepository courseRepository;

    public List<StudentDTO> getAllStudents(){

        return convertStudentsToDTO(studentRepository.findAll());
    }

    public void addStudent(StudentDTO studentDTO){
        Student student=new Student(null,studentDTO.getName(),studentDTO.getAge(),studentDTO.getMajor(),null);
        studentRepository.save(student);
    }

    public void updateStudent(Integer id,StudentDTO studentDTO){
        Student oldStudent=getStudentById(id);

        oldStudent.setName(studentDTO.getName());
        oldStudent.setAge(studentDTO.getAge());
        oldStudent.setMajor(studentDTO.getMajor());

        studentRepository.save(oldStudent);
    }

    public void deleteStudent(Integer id){
        Student student=getStudentById(id);
        studentRepository.delete(student);
    }

    public Student getStudentById(Integer id){
        Student student=studentRepository.findStudentById(id);

        if (student==null)
            throw new ApiException("Student not found");

        return student;
    }

    public void registerCourse(Integer studentId,Integer courseId){
        Student student=getStudentById(studentId);
        Course course=courseService.getCourseById(courseId);

        student.getCourses().add(course);
        course.getStudents().add(student);

        studentRepository.save(student);
        courseRepository.save(course);
    }

    public void changeMajor(Integer id,String major){
        Student student=getStudentById(id);

        if (student.getMajor().equalsIgnoreCase(major))
            throw new ApiException("You can't change major to itself");

        student.setMajor(major);
        student.setCourses(null);
        studentRepository.save(student);
    }

    public List<StudentDTO> getStudentsByCourseId(Integer id){
        Course course=courseService.getCourseById(id);

        return convertStudentsToDTO(course.getStudents());
    }

    public List<StudentDTO> convertStudentsToDTO(Collection<Student> students){
        List<StudentDTO> studentDTOS=new ArrayList<>();

        for (Student s: students){
            studentDTOS.add(new StudentDTO(s.getName(),s.getAge(),s.getMajor(),courseService.convertCoursesToDTO(s.getCourses())));
        }

        return studentDTOS;
    }




}
