package com.donatasd.translator.api.company.entity;

import com.donatasd.translator.api.role.OrganizationRole;
import com.donatasd.translator.api.translation.entity.Translation;
import com.donatasd.translator.common.entity.SoftDeletableEntity;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Donatas Daubaras
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Organization extends SoftDeletableEntity {

  private String name;

  @OneToMany
  private Set<Translation> translations;

  @OneToMany(mappedBy = "organization")
  private Set<OrganizationRole> organizationRoles;

}
