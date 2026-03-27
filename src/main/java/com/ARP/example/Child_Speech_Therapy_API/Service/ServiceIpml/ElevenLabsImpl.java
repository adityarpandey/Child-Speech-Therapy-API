package com.ARP.example.Child_Speech_Therapy_API.Service.ServiceIpml;

import com.ARP.example.Child_Speech_Therapy_API.Service.ElevenLabs;

import lombok.RequiredArgsConstructor;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Service
public class ElevenLabsImpl implements ElevenLabs {

    private  final RestTemplate restTemplate;
    private final String API_KEY = "sk_8649404ca09bcd690de3d0701b65516245825cf39be27d82";
    private final String VOICE_ID = "JBFqnCBsd6RMkjVDRZzb";

    @Override
    public String generateAudio(String text) {

        String url = "https://api.elevenlabs.io/v1/text-to-speech/" + VOICE_ID;

        HttpHeaders headers = new HttpHeaders();

        headers.add("xi-api-key",API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_OCTET_STREAM));

        Map<String, Object> body = new HashMap<>();
        body.put("text", text);





        System.out.println("**********" + body);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<byte[]> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                byte[].class
        );

        byte[] audioStream = response.getBody();

        return Base64.getEncoder().encodeToString(audioStream);

    }
}
