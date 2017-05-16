package com.github.hnefatl.interactiondesign.ui;

import javax.swing.JComponent;

public abstract class IDComponent
{
	public IDComponent()
	{
	}
	
	public abstract JComponent getRaw();
	public abstract void repaint(IDLocationFrame frame);
}
