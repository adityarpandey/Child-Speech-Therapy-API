package com.ARP.example.Child_Speech_Therapy_API.Controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/test")
public class TestController {
    private static final String API_KEY = "sk_8649404ca09bcd690de3d0701b65516245825cf39be27d82";
    private static final String VOICE_ID = "JBFqnCBsd6RMkjVDRZzb";

    @GetMapping("/tts")
    public ResponseEntity<String> testTTS() {

        String url = "https://api.elevenlabs.io/v1/text-to-speech/" + VOICE_ID;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("xi-api-key", API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_OCTET_STREAM));

        Map<String, Object> body = new HashMap<>();
        body.put("text", "Hello, this is a test from Spring Boot");
        body.put("model_id", "eleven_turbo_v2");

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<byte[]> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                byte[].class
        );

        byte[] audioBytes = response.getBody();

        // return base64 so browser can play it
        String base64Audio = Base64.getEncoder().encodeToString(audioBytes);

        return ResponseEntity.ok(base64Audio);
    }
}
