package com.ARP.example.Child_Speech_Therapy_API.Domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Builder
@Data
public class SessionRequest {

    @JsonProperty("session_id")
    private String sessionId;

    @JsonProperty("user_id")
    private String userId;

    private List<String> interests;

    private int age;

}
