package com.donatasd.translator.common.entity;

import com.donatasd.translator.api.user.entity.User;
import java.time.LocalDate;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Donatas Daubaras
 */
@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public class SoftDeletableEntity extends Audible {

  public Boolean deleted = false;

  public LocalDate deletedAt;

  @ManyToOne
  public User deletedBy;
}
