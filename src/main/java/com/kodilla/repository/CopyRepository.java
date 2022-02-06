package com.kodilla.repository;

import com.kodilla.domain.Copy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CopyRepository extends CrudRepository<Copy, Integer> {

    List<Copy> findAll();

    @Query(nativeQuery = true)
    List<Copy> getQuantityOfCopiesTo_Borrow(@Param("ID") int id);

}
