package ru.proitr.example.configuration.jpa.eclipselink;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.internal.databaseaccess.Accessor;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.sequencing.Sequence;
import org.eclipse.persistence.sessions.Session;

import java.util.UUID;
import java.util.Vector;

/*
 * Интеллектуальная собственность ООО "БТФ-консалтинг". 2014.
 * Использование ограничено прилагаемой лицензией.
 */

public class UuidSequence extends Sequence implements SessionCustomizer
{
	private static final long serialVersionUID = 4447578321058246146L;

	public UuidSequence()
	{
		super();

		shouldAlwaysOverrideExistingValue = false;
	}

	public UuidSequence(String name)
	{
		super(name);
	}

	@Override
	public Object getGeneratedValue(Accessor accessor, AbstractSession writeSession, String seqName)
	{
		return UUID.randomUUID();
	}

	@Override
	public Vector getGeneratedVector(Accessor accessor, AbstractSession writeSession, String seqName, int size)
	{
		return null;
	}

	@Override
	public void onConnect()
	{
	}

	@Override
	public void onDisconnect()
	{
	}

	@Override
	public boolean shouldAcquireValueAfterInsert()
	{
		return false;
	}

	@Override
	public boolean shouldUseTransaction()
	{
		return false;
	}

	@Override
	public boolean shouldUsePreallocation()
	{
		return false;
	}

	public void customize(Session session) throws Exception
	{
		UuidSequence sequence = new UuidSequence("system-uuid");

		session.getLogin().addSequence(sequence);
	}
}