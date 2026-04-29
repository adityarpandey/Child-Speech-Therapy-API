package com.ARP.example.Child_Speech_Therapy_API.Domain.DTO;

import lombok.Builder;
import lombok.Data;

import java.io.InputStream;

@Builder
@Data
public class SessionResponse {

    private InputStream audioStream;

    private String response;

}
