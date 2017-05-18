package com.github.hnefatl.interactiondesign.ui;

import javax.swing.JComponent;

public class IDSubFrame extends IDComponent
{
	private IDFrame frame;
	private IDLocationFrame ownFrame;
	
	public IDSubFrame()
	{
		this(null);
	}
	
	public IDSubFrame(IDLocationFrame ownFrame)
	{
		frame = new IDFrame();
		
		this.ownFrame = ownFrame;
	}
	
	public IDFrame get()
	{
		return frame;
	}
	
	public JComponent getRaw()
	{
		return frame.getRaw();
	}
	
	public void repaint(IDLocationFrame frame)
	{
		if (ownFrame != null)
		{
			ownFrame.setParent(frame);
			this.frame.repaint(ownFrame);
		}
		
		else
		{
			this.frame.repaint(frame);
		}
	}
}
