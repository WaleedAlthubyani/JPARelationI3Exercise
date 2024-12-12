package org.example.jparelationi.Service;

import lombok.RequiredArgsConstructor;
import org.example.jparelationi.Api.ApiException;
import org.example.jparelationi.Model.Course;
import org.example.jparelationi.Model.Teacher;
import org.example.jparelationi.Repository.CourseRepository;
import org.example.jparelationi.Repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public void addCourse(Course course){
        courseRepository.save(course);
    }

    public void updateCourse(Integer id, Course course){
        Course oldCourse=getCourseById(id);

        oldCourse.setName(course.getName());
        courseRepository.save(oldCourse);
    }

    public void deleteCourse(Integer id){
        Course course=getCourseById(id);

        courseRepository.delete(course);
    }

    public void assignCourseToTeacher(Integer courseId,Integer teacherId){
        Teacher teacher=getTeacherById(teacherId);
        Course course=getCourseById(courseId);

        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    public String getCourseTeacherName(Integer id){
        Course course=getCourseById(id);

        if (course.getTeacher()==null)
            throw new ApiException("Course doesn't have a teacher");

        return course.getTeacher().getName();
    }

    public Course getCourseById(Integer id){
        Course course=courseRepository.findCourseById(id);

        if (course==null)
            throw new ApiException("Course not found");

        return course;
    }

    public Teacher getTeacherById(Integer id){
        Teacher teacher=teacherRepository.findTeacherById(id);

        if (teacher==null)
            throw new ApiException("Teacher not found");

        return teacher;
    }
}
