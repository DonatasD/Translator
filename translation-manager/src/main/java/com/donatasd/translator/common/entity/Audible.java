package com.donatasd.translator.common.entity;

import com.donatasd.translator.api.user.entity.User;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Donatas Daubaras
 */
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@Data
@MappedSuperclass
public class Audible extends BaseEntity {

  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdDate;

  @LastModifiedDate
  private LocalDateTime lastModifiedDate;

  @OneToOne
  @CreatedBy
  private User createdBy;

  @OneToOne
  @LastModifiedBy
  private User lastModifiedBy;

}
