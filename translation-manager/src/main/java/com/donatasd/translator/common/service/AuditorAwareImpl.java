package com.donatasd.translator.common.service;

import com.donatasd.translator.api.user.entity.User;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;

/**
 * @author Donatas Daubaras
 */
public class AuditorAwareImpl implements AuditorAware<User> {

  @Override
  public Optional<User> getCurrentAuditor() {
    return Optional.empty();
  }
}
