package com.donatasd.translator.api.language;

import com.donatasd.translator.api.language.dto.LanguageCreateDTO;
import com.donatasd.translator.api.language.dto.LanguageDTO;
import com.donatasd.translator.api.language.service.LanguageMapper;
import com.donatasd.translator.api.language.service.LanguageService;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Donatas Daubaras
 */
@RestController
@RequestMapping("/api/languages")
public class LanguageRestController {

  private final LanguageService languageService;

  private final LanguageMapper languageMapper;

  public LanguageRestController(LanguageService languageService,
      LanguageMapper languageMapper) {
    this.languageService = languageService;
    this.languageMapper = languageMapper;
  }

  @GetMapping
  public Iterable<LanguageDTO> findAll() {
    return StreamSupport
        .stream(languageService.findAll().spliterator(), false)
        .map(languageMapper::entityToDto)
        .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public LanguageDTO findById(@PathVariable String id) {
    return languageMapper.entityToDto(languageService.findById(id));
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable String id) {
    languageService.deleteById(id);
  }

  @PostMapping
  public LanguageDTO create(@RequestBody LanguageCreateDTO createDTO) {
    var result = languageService.create(languageMapper.createDtoToEntity(createDTO));
    return languageMapper.entityToDto(result);
  }

}
