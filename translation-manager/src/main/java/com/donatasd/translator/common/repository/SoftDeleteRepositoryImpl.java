package com.donatasd.translator.common.repository;

import com.donatasd.translator.common.entity.SoftDeletableEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Donatas Daubaras
 */
@NoRepositoryBean
public class SoftDeleteRepositoryImpl<T extends SoftDeletableEntity, ID extends Serializable>
    extends SimpleJpaRepository<T, ID>
    implements SoftDeleteRepository<T, ID> {

  private final static String DELETED_FIELD_NAME = "deleted";
  private final static String DELETED_AT_FIELD_NAME = "deletedAt";

  private final JpaEntityInformation<T, ?> entityInformation;
  private final EntityManager em;

  public SoftDeleteRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager em) {
    super(entityInformation, em);
    this.entityInformation = entityInformation;
    this.em = em;
  }

  @Override
  public Iterable<T> findAllActive() {
    return super.findAll(isNotDeleted());
  }

  @Override
  public Iterable<T> findAllActive(Sort sort) {
    return super.findAll(isNotDeleted(), sort);
  }

  @Override
  public Page<T> findAllActive(Pageable pageable) {
    return super.findAll(isNotDeleted(), pageable);
  }

  @Override
  public Optional<T> findOneActive(ID id) {
    return super.findOne(Specification
        .where(new ByIdSpecification<T, ID>(entityInformation, id).and(isNotDeleted())));
  }

  @Override
  public void softDelete(ID id) {
    var entity = findOneActive(id);
    entity.ifPresentOrElse(
        this::softDelete,
        () -> {
          throw new EmptyResultDataAccessException(
              String.format("No %s entity with id %s exists!", entityInformation.getJavaType(), id),
              1
          );
        }
    );
  }

  @Override
  public void softDelete(T entity) {
    var cb = em.getCriteriaBuilder();
    var update = cb.createCriteriaUpdate(this.getDomainClass());
    var root = update.from(this.getDomainClass());
    update.set(DELETED_FIELD_NAME, true);
    update.set(DELETED_AT_FIELD_NAME, LocalDateTime.now());
    if (entityInformation.hasCompositeId()) {
      update.where(
          cb.and(
              StreamSupport.stream(entityInformation.getIdAttributeNames().spliterator(), false)
                  .map(s -> cb.equal(
                      root.get(s),
                      entityInformation.getCompositeIdAttributeValue(
                          Objects.requireNonNull(entityInformation.getId(entity)),
                          s
                      ))
                  ).toArray(Predicate[]::new)
          )
      );
    } else {
      update.where(
          cb.equal(
              root.<ID>get(Objects.requireNonNull(entityInformation.getIdAttribute()).getName()),
              entityInformation.getId(entity)
          )
      );
    }

    em.createQuery(update).executeUpdate();
  }

  @Override
  public void softDelete(Iterable<? extends T> entities) {
    entities.forEach(this::softDelete);
  }

  @Override
  public void softDeleteAll() {
    findAllActive().forEach(this::softDelete);
  }

  @Override
  public long countActive() {
    return super.count(isNotDeleted());
  }

  @Override
  public boolean existsActive(ID id) {
    return findOneActive(id).isPresent();
  }

  private static final class DeletedSpecification<T extends SoftDeletableEntity>
      implements Specification<T> {

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery,
        CriteriaBuilder criteriaBuilder) {
      return criteriaBuilder.isFalse(root.get(DELETED_FIELD_NAME));
    }
  }

  private static <T extends SoftDeletableEntity> Specification<T> isNotDeleted() {
    return Specification.where(new DeletedSpecification<>());
  }

  private static final class ByIdSpecification<T extends SoftDeletableEntity, ID extends Serializable>
      implements Specification<T> {

    private final JpaEntityInformation<T, ?> entityInformation;
    private final ID id;

    public ByIdSpecification(JpaEntityInformation<T, ?> entityInformation, ID id) {
      this.entityInformation = entityInformation;
      this.id = id;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
        CriteriaBuilder criteriaBuilder) {

      if (entityInformation.hasCompositeId()) {
        return criteriaBuilder.and(
            StreamSupport.stream(entityInformation.getIdAttributeNames().spliterator(), false)
                .map(s -> criteriaBuilder.equal(
                    root.<ID>get(s), entityInformation.getCompositeIdAttributeValue(id, s)
                )).toArray(Predicate[]::new)
        );
      }

      return criteriaBuilder.equal(
          root.<ID>get(Objects.requireNonNull(entityInformation.getIdAttribute()).getName()),
          id
      );
    }


  }
}
