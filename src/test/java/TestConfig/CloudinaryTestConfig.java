package TestConfig;

import com.cloudinary.Cloudinary;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class CloudinaryTestConfig {

    @Bean
    public Cloudinary cloudinary() {
        return (mock(Cloudinary.class));
    }
}
