package com.ARP.example.Child_Speech_Therapy_API.Controller;

import com.ARP.example.Child_Speech_Therapy_API.Domain.DTO.SessionRequest;

import com.ARP.example.Child_Speech_Therapy_API.Domain.DTO.SessionRespondRequest;
import com.ARP.example.Child_Speech_Therapy_API.Domain.DTO.SessionResponse;
import com.ARP.example.Child_Speech_Therapy_API.Domain.DTO.SessionStartResponse;
import com.ARP.example.Child_Speech_Therapy_API.Service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(path = "/api/v1/session")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @PostMapping(path = "/{id}")
    public ResponseEntity<SessionStartResponse> startSession(@PathVariable UUID id){

        String sessionID = sessionService.createSession(id);

        SessionRequest sessionRequest = sessionService.createSessionRequest(id , sessionID);

        SessionStartResponse response = sessionService.startSession(sessionRequest);

        return ResponseEntity.ok(response);

    }
    @PostMapping(path = "/delete/{sessionId}")
    public ResponseEntity<String> endSession(@PathVariable String sessionId){

        String response = sessionService.deleteSession(sessionId);

        return ResponseEntity.ok(response);

    }

    @PostMapping(path = "/respond/{sessionId}")
    public ResponseEntity<StreamingResponseBody> respondSession(
            @PathVariable String sessionId,
            @RequestBody SessionRespondRequest sessionRespondRequest
    ) throws IOException {

        sessionRespondRequest.setSessionId(sessionId);

        InputStream inputStream = sessionService.respondSession(sessionRespondRequest);

        StreamingResponseBody stream = outputStream -> {
            byte[] buffer = new byte[4096]; // 🔥 important
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        };

        return ResponseEntity.ok()
                .header("Content-Type", "audio/mpeg")
                .body(stream);
    }




}
