package com.donatasd.translator.api.language.entity;

import com.donatasd.translator.common.entity.SoftDeletableEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Donatas Daubaras
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class Language extends SoftDeletableEntity {

  @Column(nullable = false)
  private String code;
}
