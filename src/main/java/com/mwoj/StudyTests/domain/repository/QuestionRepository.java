package com.mwoj.StudyTests.domain.repository;

import com.mwoj.StudyTests.model.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question,Long> {

    List<Question> findByName(String name);

    Question deleteById(String name);
}