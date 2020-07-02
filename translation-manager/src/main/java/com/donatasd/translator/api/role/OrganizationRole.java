package com.donatasd.translator.api.role;

import com.donatasd.translator.api.company.entity.Organization;
import com.donatasd.translator.api.user.entity.User;
import com.donatasd.translator.common.entity.SoftDeletableEntity;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Donatas Daubaras
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class OrganizationRole extends SoftDeletableEntity {

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "organization_id")
  private Organization organization;

  @Enumerated(EnumType.STRING)
  private Role role;
}
