package com.donatasd.translator.config;

import com.donatasd.translator.api.user.entity.User;
import com.donatasd.translator.common.repository.SoftDeleteRepositoryImpl;
import com.donatasd.translator.common.service.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Donatas Daubaras
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.donatasd.translator",
    repositoryBaseClass = SoftDeleteRepositoryImpl.class
)
@EnableJpaAuditing
public class JPAConfig {

  @Bean
  public AuditorAware<User> auditorAware() {
    return new AuditorAwareImpl();
  }
}
