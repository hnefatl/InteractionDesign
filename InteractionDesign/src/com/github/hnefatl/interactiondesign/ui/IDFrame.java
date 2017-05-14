package com.github.hnefatl.interactiondesign.ui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;

/**
 * 
 * @author Piers Cole
 *
 * @Description A general container for components
 *
 */

public class IDFrame
{
	private IDLocationFrame frame;
	private Map<IDComponent, IDLocationFrame> components;
	
	private JLayeredPane pane;
	
	private Integer nextLayer;
	
	public IDFrame()
	{
		this(null);
	}
	
	public IDFrame(IDLocationFrame frame)
	{
		this.frame = frame;
		
		if (this.frame != null)
		{
			this.frame.setHasOwnGraphicsContext(true);
		}
		
		components = new HashMap<IDComponent, IDLocationFrame>();
		nextLayer = 0;
		
		pane = new JLayeredPane();
		
		pane.setLayout(null);
		pane.setOpaque(false);
	}
	
	public IDLocationFrame getFrame()
	{
		return frame;
	}
	
	public void addComponent(IDComponent component, IDLocation location)
	{
		IDLocationFrame lFrame = new IDLocationFrame(location);
		lFrame.setParent(frame);
		
		components.put(component, lFrame);
		pane.add(component.getRaw(), nextLayer);
		
		nextLayer += 1;
		
		component.repaint(lFrame);
	}
	
	public void removeComponent(IDComponent component)
	{
		pane.remove(component.getRaw());
		components.remove(component);
	}
	
	public void repaint()
	{
		if (frame != null)
		{
			pane.setSize(frame.getActualSize().toRaw());
			pane.setLocation(frame.getActualOffset().toRaw());
		}
		
		for (Map.Entry<IDComponent, IDLocationFrame> componentPair : components.entrySet())
		{
			componentPair.getValue().setParent(frame);
			componentPair.getKey().repaint(componentPair.getValue());
		}
	}
	
	public void repaint(IDLocationFrame frame)
	{
		this.frame = frame;
		
		if (this.frame != null)
		{
			this.frame.setHasOwnGraphicsContext(true);
		}
		
		repaint();
	}
	
	public JComponent getRaw()
	{
		return pane;
	}
}
