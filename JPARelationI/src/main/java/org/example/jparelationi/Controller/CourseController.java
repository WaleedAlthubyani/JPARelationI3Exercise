package org.example.jparelationi.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jparelationi.Api.ApiResponse;
import org.example.jparelationi.Model.Course;
import org.example.jparelationi.Service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/school-management-system/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses(){
        return ResponseEntity.status(200).body(new ApiResponse<>(courseService.getAllCourses()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addCourse(@RequestBody @Valid Course course){
        courseService.addCourse(course);
        return ResponseEntity.status(201).body(new ApiResponse<>("Course added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<String>> updateCourse(@PathVariable Integer id,@RequestBody @Valid Course course){
        courseService.updateCourse(id,course);
        return ResponseEntity.status(200).body(new ApiResponse<>("Course updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCourse(@PathVariable Integer id){
        courseService.deleteCourse(id);
        return ResponseEntity.status(200).body(new ApiResponse<>("Course deleted successfully"));
    }

    @PutMapping("/assign-course-to-teacher/course-id/{course-id}/teacher-id/{teacher-id}")
    public ResponseEntity<ApiResponse<String>> assignCourseToTeacher(@PathVariable(name = "course-id") Integer courseId,@PathVariable(name = "teacher-id") Integer teacherId){
        courseService.assignCourseToTeacher(courseId,teacherId);
        return ResponseEntity.status(200).body(new ApiResponse<>("Course assigned successfully"));
    }

    @GetMapping("/get-course-teacher-name/course-id/{id}")
    public ResponseEntity<ApiResponse<String>> getCourseTeacherName(@PathVariable Integer id){
        return ResponseEntity.status(200).body(new ApiResponse<>(courseService.getCourseTeacherName(id)));
    }
}
