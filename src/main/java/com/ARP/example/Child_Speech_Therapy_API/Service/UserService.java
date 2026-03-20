package com.ARP.example.Child_Speech_Therapy_API.Service;

import com.ARP.example.Child_Speech_Therapy_API.Domain.DTO.UserRequestDto;
import com.ARP.example.Child_Speech_Therapy_API.Domain.Entities.User;

import java.util.List;
import java.util.UUID;


public interface UserService {

    User createUser(UserRequestDto userRequestDto);

    User getUser(UUID id);

    User updateInterest(User user , List<String> interest);

    void deleteUserAccount(UUID id );

    User getUserByEmail(String email);

}
