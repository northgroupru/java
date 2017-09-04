package ru.proitr.example.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.proitr.example.domain.Test1;

import java.util.List;

@Repository
public interface Test1Repository extends CustomJpaRepository<Test1, String>
{
    @Query("select c from Test1 c where c.shortName = :shortName ")
    List<Test1> findTest1(@Param("shortName") String shortName);
}
