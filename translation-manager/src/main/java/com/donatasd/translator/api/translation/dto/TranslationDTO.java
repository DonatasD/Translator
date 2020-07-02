package com.donatasd.translator.api.translation.dto;

import java.util.Map;
import lombok.Data;

/**
 * @author Donatas Daubaras
 */
@Data
public class TranslationDTO {

  private String id;

  private String languageId;

  private Map<String, Object> translation;
}
