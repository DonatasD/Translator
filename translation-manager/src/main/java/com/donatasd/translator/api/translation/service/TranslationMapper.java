package com.donatasd.translator.api.translation.service;

import com.donatasd.translator.api.translation.entity.Translation;
import com.donatasd.translator.api.translation.dto.TranslationCreateDTO;
import com.donatasd.translator.api.translation.dto.TranslationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Donatas Daubaras
 */
@Mapper(componentModel = "spring")
public interface TranslationMapper {

  @Mapping(target = "id", ignore = true)
  Translation createDTOToEntity(TranslationCreateDTO createDTO);

  TranslationDTO entityToDTO(Translation translation);

}
