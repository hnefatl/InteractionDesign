package com.github.hnefatl.interactiondesign.ui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

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
	
	private JPanel panel;
	
	public IDFrame()
	{
		this(null);
	}
	
	public IDFrame(IDLocationFrame frame)
	{
		this.frame = frame;
		this.frame.setHasOwnGraphicsContext(true);
		
		components = new HashMap<IDComponent, IDLocationFrame>();
		
		panel = new JPanel();
		
		panel.setLayout(null);
		panel.setOpaque(false);
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
		panel.add(component.getRaw());
		
		component.repaint(lFrame);
	}
	
	public void removeComponent(IDComponent component)
	{
		panel.remove(component.getRaw());
		components.remove(component);
	}
	
	public void repaint()
	{
		panel.setSize(frame.getActualSize().toRaw());
		panel.setLocation(frame.getActualOffset().toRaw());
		
		for (Map.Entry<IDComponent, IDLocationFrame> componentPair : components.entrySet())
		{
			componentPair.getKey().repaint(componentPair.getValue());
		}
	}
	
	public void repaint(IDLocationFrame frame)
	{
		this.frame = frame;
		
		repaint();
	}
	
	public JPanel getRaw()
	{
		return panel;
	}
}
