package com.donatasd.translator.common.entity;

import com.donatasd.translator.api.user.entity.User;
import java.time.LocalDateTime;
import javax.persistence.Column;
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

  @Column(nullable = false, columnDefinition = "boolean default false")
  public Boolean deleted = Boolean.FALSE;

  public LocalDateTime deletedAt;

  @ManyToOne
  public User deletedBy;
}
