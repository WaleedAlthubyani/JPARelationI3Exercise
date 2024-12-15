package org.example.jparelationi.Service;

import lombok.RequiredArgsConstructor;
import org.example.jparelationi.Api.ApiException;
import org.example.jparelationi.DTO.TeacherDTO;
import org.example.jparelationi.Model.Teacher;
import org.example.jparelationi.Repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final AddressService addressService;
    private final CourseService courseService;

    public List<TeacherDTO> getAllTeachers(){

        return convertTeachersToDTO(teacherRepository.findAll());
    }

    public void addTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Integer id,Teacher teacher){
        Teacher oldTeacher=teacherRepository.findTeacherById(id);

        if (teacher==null)
            throw new ApiException("teacher not found");

        oldTeacher.setName(teacher.getName());
        oldTeacher.setEmail(teacher.getEmail());
        oldTeacher.setAge(teacher.getAge());
        oldTeacher.setSalary(teacher.getSalary());


        teacherRepository.save(oldTeacher);
    }

    public void deleteTeacher(Integer id){
        Teacher teacher=teacherRepository.findTeacherById(id);

        if (teacher==null)
            throw new ApiException("Teacher not found");

        if (teacher.getAddress()!=null)
            teacher.setAddress(null);

        teacherRepository.delete(teacher);
    }

    public TeacherDTO getTeacherById(Integer id){
        Teacher teacher=teacherRepository.findTeacherById(id);

        if (teacher==null)
            throw new ApiException("Teacher not found");

        return new TeacherDTO(teacher.getName(),teacher.getAge(),teacher.getEmail(),teacher.getSalary(),addressService.convertAddressToDTO(teacher.getAddress()),courseService.convertCoursesToDTO(teacher.getCourses()));
    }

    public List<TeacherDTO> convertTeachersToDTO(Collection<Teacher> teachers){
        List<TeacherDTO> teacherDTOS=new ArrayList<>();

        for (Teacher t:teachers){
            teacherDTOS.add(new TeacherDTO(t.getName(),t.getAge(),t.getEmail(),t.getSalary(),addressService.convertAddressToDTO(t.getAddress()),courseService.convertCoursesToDTO(t.getCourses())));
        }

        return teacherDTOS;
    }
}
