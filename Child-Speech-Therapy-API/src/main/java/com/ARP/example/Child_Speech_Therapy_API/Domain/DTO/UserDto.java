package com.ARP.example.Child_Speech_Therapy_API.Domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
public class UserDto {

    private UUID id;
    private String userName;
    private String email;
    private int age;
    private List<String> interests;
    private LocalDateTime createdAt;

}
