package com.donatasd.translator.api.translation.entity;

import com.donatasd.translator.api.language.entity.Language;
import com.donatasd.translator.common.entity.SoftDeletableEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import lombok.Data;

/**
 * @author Donatas Daubaras
 */
@Entity
@Data
public class Translation extends SoftDeletableEntity {

  @OneToOne
  private Language language;

  @Column(columnDefinition = "json")
  private String value;

}
