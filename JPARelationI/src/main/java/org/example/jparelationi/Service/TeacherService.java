package org.example.jparelationi.Service;

import lombok.RequiredArgsConstructor;
import org.example.jparelationi.Api.ApiException;
import org.example.jparelationi.Model.Teacher;
import org.example.jparelationi.Repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }

    public void addTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Integer id,Teacher teacher){
        Teacher oldTeacher=getTeacherById(id);

        oldTeacher.setName(teacher.getName());
        oldTeacher.setEmail(teacher.getEmail());
        oldTeacher.setAge(teacher.getAge());
        oldTeacher.setSalary(teacher.getSalary());


        teacherRepository.save(oldTeacher);
    }

    public void deleteTeacher(Integer id){
        Teacher teacher=getTeacherById(id);

        if (teacher.getAddress()!=null)
            teacher.setAddress(null);

        teacherRepository.delete(teacher);
    }

    public Teacher getTeacherById(Integer id){
        Teacher teacher=teacherRepository.findTeacherById(id);

        if (teacher==null)
            throw new ApiException("Teacher not found");

        return teacher;
    }
}
