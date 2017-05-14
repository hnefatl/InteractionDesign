package com.github.hnefatl.interactiondesign.ui;

import javax.swing.JComponent;

public class IDSubFrame extends IDComponent
{
	IDFrame frame;
	
	public IDSubFrame()
	{
		frame = new IDFrame();
	}
	
	public JComponent getRaw()
	{
		return frame.getRaw();
	}
	
	public void repaint(IDLocationFrame frame)
	{
		this.frame.repaint(frame);
	}
}
