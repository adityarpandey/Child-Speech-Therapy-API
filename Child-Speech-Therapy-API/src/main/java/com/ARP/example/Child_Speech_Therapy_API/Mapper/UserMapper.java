package com.ARP.example.Child_Speech_Therapy_API.Mapper;

import com.ARP.example.Child_Speech_Therapy_API.Domain.DTO.UserDto;
import com.ARP.example.Child_Speech_Therapy_API.Domain.Entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" ,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto toDto(User user);

}
