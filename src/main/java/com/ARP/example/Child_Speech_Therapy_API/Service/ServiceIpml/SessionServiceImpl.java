package com.ARP.example.Child_Speech_Therapy_API.Service.ServiceIpml;

import com.ARP.example.Child_Speech_Therapy_API.Domain.DTO.*;

import com.ARP.example.Child_Speech_Therapy_API.Domain.Entities.User;
import com.ARP.example.Child_Speech_Therapy_API.Repository.UserRepository;
import com.ARP.example.Child_Speech_Therapy_API.Service.ElevenLabs;
import com.ARP.example.Child_Speech_Therapy_API.Service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    @Autowired
    private UserRepository userRepository;

    private final ElevenLabs elevenLabs;

    private final RestTemplate restTemplate;

    @Override
    public String createSession(UUID userId) {

        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));

        return UUID.randomUUID().toString();

    }

    @Override
    public SessionRequest createSessionRequest(UUID userId , String sessionId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        return SessionRequest.builder()
                .userId(userId.toString())
                .sessionId(sessionId)
                .age(user.getAge())
                .interests(user.getInterests())
                .build();


    }

    @Override
    public SessionStartResponse startSession(SessionRequest sessionRequest) {

        String url = "http://localhost:8000/agent/session/start";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SessionRequest> entity = new HttpEntity<>(sessionRequest , headers);

        String aiResponse = restTemplate.postForObject(url,entity,String.class);

        return SessionStartResponse.builder()
                .sessionId(sessionRequest.getSessionId())
                .status(aiResponse)
                .build();

    }

    @Override
    public String deleteSession(String sessionId) {

        String url = "http://localhost:8000/agent/session/end";

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("session_id", sessionId);

        HttpEntity<Map<String , String>> entity = new HttpEntity<>(body , headers);

        return restTemplate.postForObject(url , entity , String.class);


    }

    @Override
    public InputStream respondSession(SessionRespondRequest sessionRespondRequest) throws IOException {

        String url = "http://localhost:8000/agent/respond";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SessionRespondRequest> entity = new HttpEntity<>(sessionRespondRequest , headers);

        AgentResponse agentResponse =  restTemplate.postForObject(url , entity , AgentResponse.class);

        String text = agentResponse.getResponse();

        return elevenLabs.generateAudio(text);

    }

}
