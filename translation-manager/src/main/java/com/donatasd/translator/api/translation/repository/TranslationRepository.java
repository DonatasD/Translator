package com.donatasd.translator.api.translation.repository;

import com.donatasd.translator.api.translation.entity.Translation;
import com.donatasd.translator.common.repository.SoftDeleteRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Donatas Daubaras
 */
@Repository
public interface TranslationRepository extends SoftDeleteRepository<Translation, String> {

}
