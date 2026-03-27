package com.ARP.example.Child_Speech_Therapy_API.Mapper;

import com.ARP.example.Child_Speech_Therapy_API.Domain.DTO.UserDto;
import com.ARP.example.Child_Speech_Therapy_API.Domain.Entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-10T23:45:27+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.userName( user.getUserName() );
        userDto.email( user.getEmail() );
        userDto.age( user.getAge() );
        List<String> list = user.getInterests();
        if ( list != null ) {
            userDto.interests( new ArrayList<String>( list ) );
        }
        userDto.createdAt( user.getCreatedAt() );

        return userDto.build();
    }
}
