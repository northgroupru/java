package ru.proitr.example.repository.test;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.proitr.example.domain.test.TestData;

/**
 * Created by Gainutdinov on 13.09.17.
 */
@Repository
public interface TestDataRepository extends JpaRepository<TestData, Long>
{
	@Query("select p from TestData p where p.valInt < 30 order by p.valString")
	Page<TestData> findTestDataValInt30(Pageable pageable);
}
