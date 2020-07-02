package com.donatasd.translator.common.entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

/**
 * @author Donatas Daubaras
 */
@Data
@MappedSuperclass
public class BaseEntity {

  @Id
  private String id;

}
