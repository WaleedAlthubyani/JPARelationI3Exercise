package org.example.jparelationi.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jparelationi.Api.ApiResponse;
import org.example.jparelationi.DTO.StudentDTO;
import org.example.jparelationi.Service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/school-management-system/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<StudentDTO>>> getAllStudents(){
        return ResponseEntity.status(200).body(new ApiResponse<>(studentService.getAllStudents()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addStudent(@RequestBody @Valid StudentDTO studentDTO){
        studentService.addStudent(studentDTO);
        return ResponseEntity.status(201).body(new ApiResponse<>("Student added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<String>> updateStudent(@PathVariable Integer id,@RequestBody @Valid StudentDTO studentDTO){
        studentService.updateStudent(id,studentDTO);
        return ResponseEntity.status(200).body(new ApiResponse<>("Student updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteStudent(@PathVariable Integer id){
        studentService.deleteStudent(id);
        return ResponseEntity.status(200).body(new ApiResponse<>("Student deleted successfully"));
    }

    @PutMapping("/register-course/{student-id}/{course-id}")
    public ResponseEntity<ApiResponse<String>> registerCourse(@PathVariable(name = "student-id") Integer studentId,@PathVariable(name = "course-id") Integer courseId){
        studentService.registerCourse(studentId,courseId);
        return ResponseEntity.status(200).body(new ApiResponse<>("Registered course successfully"));
    }

    @PutMapping("/change-major/{id}/{major}")
    public ResponseEntity<ApiResponse<String>> changeMajor(@PathVariable Integer id,@PathVariable String major){
        studentService.changeMajor(id,major);
        return ResponseEntity.status(200).body(new ApiResponse<>("Major changed successfully"));
    }

    @GetMapping("/get-course-students/{id}")
    public ResponseEntity<ApiResponse<List<StudentDTO>>> getStudentsByCourseId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(new ApiResponse<>(studentService.getStudentsByCourseId(id)));
    }
}
