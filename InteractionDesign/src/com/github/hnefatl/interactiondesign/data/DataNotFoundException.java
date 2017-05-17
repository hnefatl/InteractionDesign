package com.github.hnefatl.interactiondesign.data;

public class DataNotFoundException extends Exception
{
	private static final long serialVersionUID = 4274066968987924714L;

	public DataNotFoundException()
	{
	}
	public DataNotFoundException(Exception inner)
	{
		super(inner);
	}
}