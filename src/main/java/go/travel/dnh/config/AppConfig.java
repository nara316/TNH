package go.travel.dnh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    //여기서 BcryptPasswordEncoder를 등록하지 않고 PasswordEncoder를 등록한 이유는
    // 디폴트 PasswordEncoder가 Bcrypt + salt를 사용하기 때문.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
