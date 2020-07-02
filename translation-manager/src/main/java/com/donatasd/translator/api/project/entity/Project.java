package com.donatasd.translator.api.project.entity;

import com.donatasd.translator.common.entity.SoftDeletableEntity;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Donatas Daubaras
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Project extends SoftDeletableEntity {

  private String name;

}
