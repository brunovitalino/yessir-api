package lol.bvlabs.yessir.infra.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
	
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*"
                //"http://localhost:4200",
                //"http://www.bvlabs.lol:80, http://54.89.37.115:80",
                //"http://yessir-api-homol-application-lb-1320874028.us-east-1.elb.amazonaws.com:80"
            ).allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
            .allowedHeaders("*")
            //.allowCredentials(true)
            //.allowedOriginPatterns("https://yessir-api-alb-*.us-east-1.elb.amazonaws.com")
            .maxAge(Long.valueOf(3600));
    }

}
