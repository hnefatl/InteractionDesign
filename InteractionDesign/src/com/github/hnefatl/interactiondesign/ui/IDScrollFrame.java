package com.github.hnefatl.interactiondesign.ui;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class IDScrollFrame extends IDComponent
{
	private IDLocationFrame frame;
	private Map<IDComponent, IDLocationFrame> components;
	
	private JLayeredPane pane;
	private JPanel dragPanel;
	
	private Integer nextLayer;
	
	private IDPosition currentPosition;
	
	private double minX;
	private double minY;
	
	private double maxX;
	private double maxY;
	
	private Point cursorPosition;
	
	private IDAction clickAction;
	
	public IDScrollFrame()
	{
		this(null);
	}
	
	public IDScrollFrame(IDLocationFrame frame)
	{
		this.frame = frame;
		clickAction = null;
		
		if (this.frame != null)
		{
			this.frame.setHasOwnGraphicsContext(true);
		}
		
		components = new HashMap<IDComponent, IDLocationFrame>();
		nextLayer = 0;
		
		pane = new JLayeredPane();
		
		pane.setLayout(null);
		pane.setOpaque(false);
		
		dragPanel = new JPanel();
		
		dragPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				cursorPosition = e.getPoint();
			}
			
			@Override
			public void mouseClicked(MouseEvent e)
			{
				Point clickPoint = e.getPoint();
				
				double actualX = clickPoint.x + currentPosition.x;
				double actualY = clickPoint.y + currentPosition.y;
				
				if (clickAction != null)
				{
					clickAction.onAction(new IDClickEvent(new IDPosition(actualX, actualY), e.getButton()));
				}
			}
		});
		
		dragPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e)
			{
				double sensitivity = 3.0;
				
				int dx = e.getX() - cursorPosition.x;
				int dy = e.getY() - cursorPosition.y;
				
				if ((dx == 0) && (dy == 0))
				{
					return;
				}
				
				currentPosition.x -= (dx * sensitivity);
				currentPosition.y -= (dy * sensitivity);
				
				cursorPosition = e.getPoint();
				
				//recalculateCurrentPosition();
				repaint();
			}
		});
		
		dragPanel.setOpaque(false);
		
		pane.add(dragPanel, nextLayer);
		nextLayer++;
		
		if (frame != null)
		{
			dragPanel.setSize(frame.getActualSize().toRaw());
			dragPanel.setLocation(new Point(0, 0));
		}
		
		currentPosition = new IDPosition(0, 0);
		
		minX = 0;
		minY = 0;
		
		maxX = 0;
		maxY = 0;
	}
	
	public IDLocationFrame getFrame()
	{
		return frame;
	}
	
	public void addClickAction(IDAction clickAction)
	{
		this.clickAction = clickAction;
	}
	
	public void addComponent(IDComponent component, IDLocation location)
	{
		if (component.getRaw() == null)
		{
			return;
		}
		
		IDLocationFrame lFrame = new IDLocationFrame(location);
		lFrame.setParent(frame);
		
		components.put(component, lFrame);
		pane.add(component.getRaw(), nextLayer);
		
		nextLayer += 1;
		
		//component.repaint(lFrame);
		
		//recalculateBounds();
		repaint();
	}
	
	public void removeComponent(IDComponent component)
	{
		if (component.getRaw() == null)
		{
			return;
		}
		
		pane.remove(component.getRaw());
		components.remove(component);
		
		recalculateBounds();
		repaint();
	}
	
	public void clear()
	{
		clear(true);
	}
	
	public void clear(boolean setDirty)
	{
		for (IDComponent component : components.keySet())
		{
			pane.remove(component.getRaw());
		}
		
		components.clear();
		
		if (setDirty)
		{
			recalculateBounds();
			repaint();
		}
	}
	
	public IDPosition getPosition()
	{
		return currentPosition;
	}
	
	public void setPosition(IDPosition position)
	{
		currentPosition = position;
		
		repaint();
	}
	
	protected void recalculateBounds()
	{
		Double tMinX = null;
		Double tMinY = null;
		
		Double tMaxX = null;
		Double tMaxY = null;
		
		for (Map.Entry<IDComponent, IDLocationFrame> componentPair : components.entrySet())
		{
			IDLocationFrame lFrame = componentPair.getValue();
			
			IDPosition lOffset = lFrame.getOffset();
			IDSize lSize = lFrame.getSize();
			
			if (tMinX == null)
			{
				// First element
				
				tMinX = lOffset.x;
				tMinY = lOffset.y;
				
				tMaxX = tMinX + lSize.x;
				tMaxY = tMinY + lSize.y;
			}
			
			else
			{
				double tempMinX = lOffset.x;
				double tempMinY = lOffset.y;
				
				double tempMaxX = tempMinX + lSize.x;
				double tempMaxY = tempMinY + lSize.y;
				
				tMinX = (tempMinX < tMinX) ? tempMinX : tMinX;
				tMinY = (tempMinY < tMinY) ? tempMinY : tMinY;
				tMaxX = (tempMaxX > tMaxX) ? tempMaxX : tMaxX;
				tMaxY = (tempMaxY > tMaxY) ? tempMaxY : tMaxY;
			}
		}
		
		minX = (tMinX == null) ? 0 : tMinX;
		minY = (tMinY == null) ? 0 : tMinY;
		
		maxX = (tMaxX == null) ? 0 : tMaxX;
		maxY = (tMaxY == null) ? 0 : tMaxY;
		
		recalculateCurrentPosition();
	}
	
	protected void recalculateCurrentPosition()
	{
		if (frame == null)
		{
			return;
		}
		
		double xSize = frame.getSize().x;
		double ySize = frame.getSize().y;
		
		if (xSize > (maxX - minX))
		{
			currentPosition.x = minX;
		}
		
		else
		{
			if (currentPosition.x < minX)
			{
				currentPosition.x = minX;
			}
			
			else if (currentPosition.x > (maxX - xSize))
			{
				currentPosition.x = (maxX - xSize);
			}
		}
		
		if (ySize > (maxY - minY))
		{
			currentPosition.y = minY;
		}
		
		else
		{
			if (currentPosition.y < minY)
			{
				currentPosition.y = minY;
			}
			
			else if (currentPosition.y > (maxY - ySize))
			{
				currentPosition.y = (maxY - ySize);
			}
		}
	}
	
	public void repaint()
	{
		if (frame == null)
		{
			return; // Can't do much if frame is null as it provides our bounds
		}
		
		// TODO: Implement dirty flag for recalculating
		
		recalculateBounds();
		//recalculateCurrentPosition();
		
		pane.setSize(frame.getActualSize().toRaw());
		pane.setLocation(frame.getActualOffset().toRaw());
		
		double xOff = -currentPosition.x * frame.getScale();
		double yOff = -currentPosition.y * frame.getScale();
		
		for (Map.Entry<IDComponent, IDLocationFrame> componentPair : components.entrySet())
		{
			IDLocationFrame lFrame = componentPair.getValue();
			IDPosition lOffset = lFrame.getOffset();
			
			IDPosition tempOffset = new IDPosition(lOffset.x + xOff, lOffset.y + yOff);
			
			IDLocationFrame tempFrame = new IDLocationFrame(new IDLocation(tempOffset, lFrame.getSize()), lFrame.getScale());
			tempFrame.setParent(frame);
			
			componentPair.getKey().repaint(tempFrame);
		}
	}
	
	public void repaint(IDLocationFrame frame)
	{
		this.frame = frame;
		
		if (this.frame != null)
		{
			this.frame.setHasOwnGraphicsContext(true);
			
			dragPanel.setSize(frame.getActualSize().toRaw());
			dragPanel.setLocation(new Point(0, 0));
		}
		
		repaint();
	}
	
	public JComponent getRaw()
	{
		return pane;
	}
}
