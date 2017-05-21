package com.github.hnefatl.interactiondesign.ui;

import java.awt.event.MouseEvent;

public class IDClickEvent extends IDEvent
{
	public static int NO_BUTTON = MouseEvent.NOBUTTON;
	public static int LEFT_BUTTON = MouseEvent.BUTTON1;
	public static int RIGHT_BUTTON = MouseEvent.BUTTON2;
	public static int MIDDLE_BUTTON = MouseEvent.BUTTON3;
	
	private IDPosition position;
	private int button;
	
	public IDClickEvent(IDPosition position)
	{
		this(position, NO_BUTTON);
	}
	
	public IDClickEvent(IDPosition position, int button)
	{
		this.position = position;
		this.button = button;
	}
	
	public IDPosition getPosition()
	{
		return position;
	}
	
	public int getButton()
	{
		return button;
	}
}
