package com.donatasd.translator.api.language.service;

import com.donatasd.translator.api.language.entity.Language;
import com.donatasd.translator.api.language.repository.LanguageRepository;
import org.springframework.stereotype.Service;

/**
 * @author Donatas Daubaras
 */
@Service
public class LanguageService {

  private final LanguageRepository languageRepository;

  public LanguageService(LanguageRepository languageRepository) {
    this.languageRepository = languageRepository;
  }

  public Iterable<Language> findAll() {
    return languageRepository.findAll();
  }

  public void deleteById(String id) {
    languageRepository.softDelete(id);
  }

  public Language create(Language language) {
    return languageRepository.save(language);
  }

  public Language findById(String id) {
    // TODO exception
    return languageRepository.findById(id).orElseThrow(RuntimeException::new);
  }
}
