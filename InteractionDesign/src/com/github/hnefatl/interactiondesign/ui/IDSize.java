package com.github.hnefatl.interactiondesign.ui;

import java.awt.Dimension;

public class IDSize
{
	public double x;
	public double y;
	
	public IDSize(int x, int y)
	{
		this((double) x, (double) y);
	}
	
	public IDSize(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Dimension toRaw()
	{
		return new Dimension((int) x, (int) y);
	}
}
