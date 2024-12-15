package org.example.jparelationi.Service;

import lombok.RequiredArgsConstructor;
import org.example.jparelationi.Api.ApiException;
import org.example.jparelationi.DTO.AddressDTO;
import org.example.jparelationi.DTO.AddressODTO;
import org.example.jparelationi.Model.Address;
import org.example.jparelationi.Model.Teacher;
import org.example.jparelationi.Repository.AddressRepository;
import org.example.jparelationi.Repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final TeacherRepository teacherRepository;

    public void addAddress(AddressDTO addressDTO){
        Teacher teacher=getTeacherById(addressDTO.getTeacher_id());

        Address address=new Address(null,addressDTO.getArea(), addressDTO.getStreet(), addressDTO.getBuildingNumber(), teacher);

        addressRepository.save(address);
    }

    public void updateAddress(AddressDTO addressDTO){
        Address oldAddress=getAddressById(addressDTO.getTeacher_id());

        oldAddress.setArea(addressDTO.getArea());
        oldAddress.setStreet(addressDTO.getStreet());
        oldAddress.setBuildingNumber(addressDTO.getBuildingNumber());

        addressRepository.save(oldAddress);
    }

    public void deleteAddress(Integer id){
        Address address=getAddressById(id);

        addressRepository.delete(address);
    }

    public Address getAddressById(Integer id){
        Address address=addressRepository.findAddressById(id);

        if (address==null)
            throw new ApiException("Address not found");

        return address;
    }

    public Teacher getTeacherById(Integer id){
        Teacher teacher= teacherRepository.findTeacherById(id);

        if (teacher==null)
            throw new ApiException("Teacher not found");

        return teacher;
    }

    public AddressODTO convertAddressToDTO(Address address){
        if (address==null)
            return null;

        return new AddressODTO(address.getArea(),address.getStreet(), address.getBuildingNumber());
    }
}
