package com.github.hnefatl.interactiondesign.ui;

import java.awt.Color;

public class IDColour
{
	double r;
	double g;
	double b;
	double a;
	
	public IDColour(int r, int g, int b)
	{
		this(r, g, b, 0xFF);
	}
	
	public IDColour(int r, int g, int b, int a)
	{
		this(((double) r) / 255.0, ((double) g) / 255.0, ((double) b) / 255.0, ((double) a) / 255.0);
	}
	
	public IDColour(double r, double g, double b)
	{
		this(r, g, b, 1.0);
	}
	
	public IDColour(double r, double g, double b, double a)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public Color toColor()
	{
		return new Color((float) r, (float) g, (float) b, (float) a);
	}
}
