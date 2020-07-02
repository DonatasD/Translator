package com.donatasd.translator.api.language.service;

import com.donatasd.translator.api.language.entity.Language;
import com.donatasd.translator.api.language.dto.LanguageCreateDTO;
import com.donatasd.translator.api.language.dto.LanguageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Donatas Daubaras
 */
@Mapper(componentModel = "spring")
public interface LanguageMapper {

  LanguageDTO entityToDto(Language language);

  @Mapping(target = "lastModifiedDate", ignore = true)
  @Mapping(target = "lastModifiedBy", ignore = true)
  @Mapping(target = "deletedBy", ignore = true)
  @Mapping(target = "deletedAt", ignore = true)
  @Mapping(target = "deleted", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "id", ignore = true)
  Language createDtoToEntity(LanguageCreateDTO createDTO);
}
