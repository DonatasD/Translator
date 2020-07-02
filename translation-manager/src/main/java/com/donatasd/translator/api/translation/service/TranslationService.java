package com.donatasd.translator.api.translation.service;

import com.donatasd.translator.api.translation.entity.Translation;
import com.donatasd.translator.api.translation.repository.TranslationRepository;
import org.springframework.stereotype.Service;

/**
 * @author Donatas Daubaras
 */
@Service
public class TranslationService {

  private final TranslationRepository translationRepository;

  public TranslationService(TranslationRepository translationRepository) {
    this.translationRepository = translationRepository;
  }

  public Translation create(Translation translation) {
    return translationRepository.save(translation);
  }

}
