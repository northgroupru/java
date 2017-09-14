package ru.proitr.example.domain.test;

import ru.proitr.example.domain.MainConstants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.function.Consumer;

/**
 * Created by Gainutdinov on 13.09.17.
 */
@Entity
@Table(name = "t_data", schema = MainConstants.AUTH_SCHEMA_NAME)
public class TestData implements Serializable
{
	private static final long serialVersionUID = -2699654687615886406L;

	@Id
	@SequenceGenerator(schema = MainConstants.AUTH_SCHEMA_NAME, name = "seqGenerator", sequenceName = "s_t_data", allocationSize = 1)
	@GeneratedValue(generator = "seqGenerator", strategy = GenerationType.AUTO)
	@NotNull
	@Column(updatable = false)
	private Long id;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "val_int")
	private Integer valInt;

	@Column(name = "val_string", length = 60)
	private String valString;

	public TestData()
	{

	}

	public static TestData TestData(Consumer<TestData> consumer)
	{
		TestData testData = new TestData();
		consumer.accept(testData);

		return testData;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
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

	public String getValString()
	{
		return valString;
	}

	public void setValString(String valString)
	{
		this.valString = valString;
	}
}
