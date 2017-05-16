package com.github.hnefatl.interactiondesign.ui;

import java.awt.Point;

public class IDPosition
{
	public static final IDPosition ZERO = new IDPosition(0, 0);
	
	public double x;
	public double y;
	
	public IDPosition(int x, int y)
	{
		this((double) x, (double) y);
	}
	
	public IDPosition(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Point toRaw()
	{
		return new Point((int) x, (int) y);
	}
}
