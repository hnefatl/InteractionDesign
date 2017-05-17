package com.github.hnefatl.interactiondesign.data;

public class InvalidFormatException extends Exception
{
	private static final long serialVersionUID = -4516624509112048812L;
	
	public InvalidFormatException()
	{
		
	}
	public InvalidFormatException(Exception inner)
	{
		super(inner);
	}
}
