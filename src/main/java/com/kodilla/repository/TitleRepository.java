package com.kodilla.repository;

import com.kodilla.domain.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TitleRepository extends CrudRepository<Title, Integer> {

    List<Title> findAll();

    Title findByTitle(String title);
}
