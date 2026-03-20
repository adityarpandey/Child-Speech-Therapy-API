package com.ARP.example.Child_Speech_Therapy_API.Domain.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionStartResponse {

    private String sessionId;

    private String status;


}
