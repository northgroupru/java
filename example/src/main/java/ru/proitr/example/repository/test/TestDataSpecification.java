package ru.proitr.example.repository.test;

import org.springframework.data.jpa.domain.Specification;
import ru.proitr.example.domain.test.TestData;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gainutdinov on 18.09.17.
 */
public class TestDataSpecification implements Specification<TestData>
{
	private Integer valInt;

	private String userId;

	public TestDataSpecification(Integer valInt, String userId)
	{
		this.valInt = valInt;
		this.userId = userId;
	}

	public Integer getValInt()
	{
		return valInt;
	}

	public void setValInt(Integer valInt)
	{
		this.valInt = valInt;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	@Override
	public Predicate toPredicate(Root<TestData> root, CriteriaQuery<?> query, CriteriaBuilder cb)
	{
		List<Predicate> predicates = new ArrayList<>();

		if (userId != null)
		{
			predicates.add(cb.equal(root.get("userId"), userId));
		}

		if (valInt != null)
		{
			predicates.add(cb.lessThan(root.get("valInt"), valInt));
		}

		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}
}
