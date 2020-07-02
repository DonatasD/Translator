package com.donatasd.translator.api.translation;

import com.donatasd.translator.api.translation.dto.TranslationCreateDTO;
import com.donatasd.translator.api.translation.dto.TranslationDTO;
import com.donatasd.translator.api.translation.service.TranslationMapper;
import com.donatasd.translator.api.translation.service.TranslationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Donatas Daubaras
 */
@RestController
@RequestMapping("/api/translations")
public class TranslationRestController {

  private final TranslationService translationService;

  private final TranslationMapper translationMapper;


  public TranslationRestController(
      TranslationService translationService,
      TranslationMapper translationMapper
  ) {
    this.translationService = translationService;
    this.translationMapper = translationMapper;
  }

  @PostMapping
  private TranslationDTO create(@RequestBody TranslationCreateDTO createDTO) {
    var result = translationService.create(translationMapper.createDTOToEntity(createDTO));
    return translationMapper.entityToDTO(result);
  }

}
