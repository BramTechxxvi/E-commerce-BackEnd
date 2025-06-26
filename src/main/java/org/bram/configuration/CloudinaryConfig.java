package org.bram.configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dld9a4wcn",
                "api_key", "419937922268742",
                "api_secret", "3q8AbRNjsPXg--BN7vpngpnrypI"
        ));
    }
}
