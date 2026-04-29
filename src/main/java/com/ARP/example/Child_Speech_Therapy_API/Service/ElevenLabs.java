package com.ARP.example.Child_Speech_Therapy_API.Service;

import java.io.IOException;
import java.io.InputStream;

public interface ElevenLabs {

    InputStream generateAudio(String text) throws IOException;

}
