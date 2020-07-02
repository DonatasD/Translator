package com.donatasd.translator.common.repository;

import com.donatasd.translator.common.entity.SoftDeletableEntity;
import java.io.Serializable;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Donatas Daubaras
 */
@NoRepositoryBean
public interface SoftDeleteRepository<T extends SoftDeletableEntity, ID extends Serializable> extends
    PagingAndSortingRepository<T, ID>{

  Iterable<T> findAllActive();

  Iterable<T> findAllActive(Sort sort);

  Page<T> findAllActive(Pageable pageable);

  Optional<T> findOneActive(ID id);

  @Modifying
  void softDelete(ID id);

  @Modifying
  void softDelete(T entity);

  @Modifying
  void softDelete(Iterable<? extends T> entities);

  @Modifying
  void softDeleteAll();

  long countActive();

  boolean existsActive(ID id);
}
