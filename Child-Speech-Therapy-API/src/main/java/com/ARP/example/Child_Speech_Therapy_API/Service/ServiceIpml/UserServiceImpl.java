package com.ARP.example.Child_Speech_Therapy_API.Service.ServiceIpml;

import com.ARP.example.Child_Speech_Therapy_API.Domain.DTO.UserRequestDto;
import com.ARP.example.Child_Speech_Therapy_API.Domain.Entities.User;
import com.ARP.example.Child_Speech_Therapy_API.Repository.UserRepository;
import com.ARP.example.Child_Speech_Therapy_API.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserRequestDto userRequestDto) {

        User createdUser = User.builder()
                .userName(userRequestDto.getUserName())
                .email(userRequestDto.getEmail())
                .age(userRequestDto.getAge())
                .interests(userRequestDto.getInterests())
                .build();

        return userRepository.save(createdUser);


    }

    @Override
    public User getUser(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateInterest(User user , List<String> interest){

        user.setInterests(interest);
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);

    }
    @Transactional
    @Override
    public void deleteUserAccount(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByEmail(String email) {
       return userRepository.findByEmail(email);
    }

}
