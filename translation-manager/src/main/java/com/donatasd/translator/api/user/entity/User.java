package com.donatasd.translator.api.user.entity;

import com.donatasd.translator.api.role.OrganizationRole;
import com.donatasd.translator.common.entity.SoftDeletableEntity;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Donatas Daubaras
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class User extends SoftDeletableEntity {

  private String email;

  @OneToMany(mappedBy = "user")
  private Set<OrganizationRole> organizationRoles;
}
