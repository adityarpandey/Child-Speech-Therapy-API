package com.ARP.example.Child_Speech_Therapy_API.Domain.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SessionResponse {

    private String audioStream;

    private String response;

}
