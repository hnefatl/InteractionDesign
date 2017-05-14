package com.github.hnefatl.interactiondesign.ui;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class IDRectangle extends IDComponent
{
	JPanel panel;
	IDColour colour;
	
	public IDRectangle(IDColour colour)
	{
		panel = new JPanel();
		this.colour = colour;
		
		panel.setBackground(colour.toColor());
	}
	
	public JComponent getRaw()
	{
		return panel;
	}
	
	public void repaint(IDLocationFrame frame)
	{
		panel.setSize(frame.getActualSize().toRaw());
		panel.setLocation(frame.getActualOffset().toRaw());
	}
}
