package com.github.hnefatl.interactiondesign.ui;

import java.awt.Font;

import javax.swing.JButton;

public class IDString
{
	public static int PLAIN = Font.PLAIN;
	public static int BOLD = Font.BOLD;
	public static int ITALIC = Font.ITALIC;
	
	private String text;
	private Font font;
	
	public IDString(String text)
	{
		this(text, null);
	}
	
	public IDString(String text, Font font)
	{
		this.text = text;
		this.font = font;
	}
	
	public String getText()
	{
		return text;
	}
	
	public Font getFont()
	{
		return font;
	}
	
	public static Font getDefaultFont()
	{
		return (new JButton()).getFont();
	}
	
	public static Font getDefaultFont(double size)
	{
		return getDefaultFont().deriveFont((float) size);
	}
	
	public static Font getDefaultFont(int style)
	{
		return getDefaultFont().deriveFont(style);
	}
	
	public static Font getDefaultFont(double size, int style)
	{
		return getDefaultFont().deriveFont(style, (float) size);
	}
}