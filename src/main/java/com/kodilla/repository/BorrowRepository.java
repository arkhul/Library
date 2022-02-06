package com.kodilla.repository;

import com.kodilla.domain.Borrow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BorrowRepository extends CrudRepository<Borrow, Integer> {

    List<Borrow> findAll();
}
