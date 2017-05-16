package com.github.hnefatl.interactiondesign.ui;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class IDRectangle extends IDComponent
{
	private JPanel panel;
	
	public IDRectangle(IDColour colour)
	{
		panel = new JPanel();
		
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
