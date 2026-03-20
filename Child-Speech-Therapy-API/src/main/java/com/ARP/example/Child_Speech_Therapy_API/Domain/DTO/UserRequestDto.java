package com.ARP.example.Child_Speech_Therapy_API.Domain.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    @NotBlank(message = "name can not be null , please enter your name ")
    private String userName;

    @NotBlank(message = "email can not be null , please enter your email in valid formate")
    @Email
    private String email;

    @NotBlank(message = "age can not be null , please enter your age")
    private int age;

    @NotBlank(message = "please enter you interest for best experience")
    private List<String> interests;

}
