package com.ARP.example.Child_Speech_Therapy_API.Domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionRespondRequest {
    @JsonProperty("session_id")
    private String sessionId;
    @JsonProperty("text")
    private String childText;

}
