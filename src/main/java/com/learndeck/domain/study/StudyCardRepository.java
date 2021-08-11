package com.learndeck.domain.study;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyCardRepository extends JpaRepository<StudyCard, Long>, StudyCardRepositoryCustom {

}
