package org.example.jparelationi.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jparelationi.Api.ApiResponse;
import org.example.jparelationi.DTO.AddressDTO;
import org.example.jparelationi.Service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/school-management-system/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addAddress(@RequestBody @Valid AddressDTO addressDTO){
        addressService.addAddress(addressDTO);
        return ResponseEntity.status(201).body(new ApiResponse<>("Address added successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<String>> updateAddress(@RequestBody @Valid AddressDTO addressDTO){
        addressService.updateAddress(addressDTO);
        return ResponseEntity.status(200).body(new ApiResponse<>("Address updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteAddress(@PathVariable Integer id){
        addressService.deleteAddress(id);
        return ResponseEntity.status(200).body(new ApiResponse<>("Address deleted successfully"));
    }


}
