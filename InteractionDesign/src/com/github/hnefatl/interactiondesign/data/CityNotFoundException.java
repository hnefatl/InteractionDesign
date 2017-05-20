package com.github.hnefatl.interactiondesign.data;

public class CityNotFoundException extends Exception
{
	private static final long serialVersionUID = 1076026144882578959L;

	public CityNotFoundException()
	{
		
	}
	public CityNotFoundException(Exception inner)
	{
		super(inner);
	}
}
