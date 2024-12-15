package org.example.jparelationi.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jparelationi.Api.ApiResponse;
import org.example.jparelationi.DTO.TeacherDTO;
import org.example.jparelationi.Model.Teacher;
import org.example.jparelationi.Service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/school-management-system/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<TeacherDTO>>> getAllTeachers(){
        return ResponseEntity.status(200).body(new ApiResponse<>(teacherService.getAllTeachers()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addTeacher(@RequestBody @Valid Teacher teacher){
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(201).body(new ApiResponse<>("Teacher added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<String>> updateTeacher(@PathVariable Integer id,@RequestBody @Valid Teacher teacher){
        teacherService.updateTeacher(id,teacher);
        return ResponseEntity.status(200).body(new ApiResponse<>("Teacher updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTeacher(@PathVariable Integer id){
        teacherService.deleteTeacher(id);
        return ResponseEntity.status(200).body(new ApiResponse<>("Teacher deleted successfully"));
    }

    @GetMapping("/get-teacher-by-id/{id}")
    public ResponseEntity<ApiResponse<TeacherDTO>> getTeacherById(@PathVariable Integer id){
        return ResponseEntity.status(201).body(new ApiResponse<>(teacherService.getTeacherById(id)));
    }


}
