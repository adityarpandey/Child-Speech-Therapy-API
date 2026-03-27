package com.ARP.example.Child_Speech_Therapy_API.Controller;


import com.ARP.example.Child_Speech_Therapy_API.Domain.DTO.LoginRequest;
import com.ARP.example.Child_Speech_Therapy_API.Domain.DTO.UserDto;
import com.ARP.example.Child_Speech_Therapy_API.Domain.DTO.UserRequestDto;
import com.ARP.example.Child_Speech_Therapy_API.Domain.Entities.User;
import com.ARP.example.Child_Speech_Therapy_API.Mapper.UserMapper;
import com.ARP.example.Child_Speech_Therapy_API.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/user")
public class UserController {


    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserDto> userRegistration(@RequestBody UserRequestDto userRequestDto){

            User createUser = userService.createUser(userRequestDto);

        UserDto userDto = userMapper.toDto(createUser);

            return new ResponseEntity<>(userDto , HttpStatus.CREATED);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDto> updateInterest(@PathVariable UUID id
            , @Valid @RequestBody List<String> interestToUpdate ){

        User user = userService.getUser(id);

        User updatedUser = userService.updateInterest(user , interestToUpdate);

        UserDto userDto = userMapper.toDto(updatedUser);

        return ResponseEntity.ok(userDto);



    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id){

            userService.deleteUserAccount(id);

            return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody LoginRequest request){





        User user = userService.getUserByEmail(request.getEmail());



        UserDto userDto = userMapper.toDto(user);

        return ResponseEntity.ok(userDto);

    }


}
