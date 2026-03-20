package com.ARP.example.Child_Speech_Therapy_API.Service;

import com.ARP.example.Child_Speech_Therapy_API.Domain.DTO.SessionRequest;
import com.ARP.example.Child_Speech_Therapy_API.Domain.DTO.SessionRespondRequest;
import com.ARP.example.Child_Speech_Therapy_API.Domain.DTO.SessionResponse;
import com.ARP.example.Child_Speech_Therapy_API.Domain.DTO.SessionStartResponse;

import java.util.UUID;

public interface SessionService {

     String createSession(UUID userId);

     SessionRequest createSessionRequest(UUID userId , String sessionId);

     SessionStartResponse startSession(SessionRequest sessionRequest);

     String deleteSession(String sessionId);

     SessionResponse respondSession(SessionRespondRequest sessionRespondRequest);

}
