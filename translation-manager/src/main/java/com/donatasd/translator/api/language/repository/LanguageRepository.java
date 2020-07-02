package com.donatasd.translator.api.language.repository;

import com.donatasd.translator.api.language.entity.Language;
import com.donatasd.translator.common.repository.SoftDeleteRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

/**
 * @author Donatas Daubaras
 */
@Repository
@Transactional
public interface LanguageRepository extends SoftDeleteRepository<Language, String> {

}
