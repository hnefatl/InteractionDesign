package com.github.hnefatl.interactiondesign.ui;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class IDText extends IDComponent
{
	private JLabel label;
	
	public IDText(IDString string)
	{
		this(string, null);
	}
	
	public IDText(IDString string, IDColour colour)
	{
		label = new JLabel(string.getText());
		label.setFont(string.getFont());
		
		if (colour != null)
		{
			label.setForeground(colour.toColor());
		}
		
		label.setOpaque(false);
	}
	
	public JComponent getRaw()
	{
		return label;
	}
	
	public void repaint(IDLocationFrame frame)
	{
		label.setSize(frame.getActualSize().toRaw());
		label.setLocation(frame.getActualOffset().toRaw());
	}
}
