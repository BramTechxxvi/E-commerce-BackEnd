package org.bram.TestConfig;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfiguration
@Profile("test")
public class CloudinaryTestConfig {

    @Bean
    public Cloudinary cloudinary() throws IOException {
        Cloudinary cloudinary = mock(Cloudinary.class);
        Uploader uploader = mock(Uploader.class);
        when(cloudinary.uploader()).thenReturn(uploader);

        Map<String, Object> uploadResult = new HashMap<>();
        uploadResult.put("secure_url", "https://fake-imageUrl.com");
        when(uploader.upload(any(byte[].class), anyMap())).thenReturn(uploadResult);

        return cloudinary;
    }
}
