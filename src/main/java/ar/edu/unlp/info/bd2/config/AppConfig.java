package ar.edu.unlp.info.bd2.config;

import ar.edu.unlp.info.bd2.repository.DeliveryRepository;
import ar.edu.unlp.info.bd2.services.*;
import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public DeliveryService createService() {
        DeliveryRepository repository = this.createRepository();
        return new DeliveryServiceImpl(repository);
    }

    @Bean
    public DeliveryRepository createRepository() {
        return new DeliveryRepository();
    }

}
