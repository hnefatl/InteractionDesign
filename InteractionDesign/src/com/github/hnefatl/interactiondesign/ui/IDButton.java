package com.github.hnefatl.interactiondesign.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;

public class IDButton extends IDComponent
{
	private FlatJButton button;
	
	private IDAction pressAction;
	
	private class FlatJButton extends JButton
	{
		static final long serialVersionUID = 0x78128193;
		
		private boolean showPressed = true;
		
		public FlatJButton()
		{
			super();
			
			super.setContentAreaFilled(false);
		}
		
		public FlatJButton(IDString string)
		{
			super(string.getText());
			
			super.setFont(string.getFont());
			super.setContentAreaFilled(false);
		}
		
		public FlatJButton(Icon icon)
		{
			super(icon);
			
			super.setContentAreaFilled(false);
		}
		
		public void setShowPressed(boolean showPressed)
		{
			this.showPressed = showPressed;
		}
		
		@Override
		protected void paintComponent(Graphics g)
		{
			if (!getModel().isPressed() || !showPressed)
			{
				g.setColor(new Color(0, 0, 0, 0));
			}
			
			else
			{
				g.setColor(new Color(0, 0, 0, 0.2f));
			}
			
			g.fillRect(0, 0, getWidth(), getHeight());
			
			super.paintComponent(g);
		}
	}
	
	public IDButton()
	{
		this(true);
	}
	
	public IDButton(boolean showPressed)
	{
		this((IDAction) null, showPressed);
	}
	
	public IDButton(IDAction pressAction)
	{
		this(pressAction, true);
	}
	
	public IDButton(IDAction pressAction, boolean showPressed)
	{
		button = new FlatJButton();
		this.pressAction = pressAction;
		
		setup(showPressed);
	}
	
	public IDButton(IDString string)
	{
		this(string, true);
	}
	
	public IDButton(IDString string, boolean showPressed)
	{
		this(string, null, showPressed);
	}
	
	public IDButton(IDString string, IDAction pressAction)
	{
		this(string, pressAction, true);
	}
	
	public IDButton(IDString string, IDAction pressAction, boolean showPressed)
	{
		button = new FlatJButton(string);
		this.pressAction = pressAction;
		
		setup(showPressed);
	}
	
	public IDButton(Icon icon)
	{
		this(icon, true);
	}
	
	public IDButton(Icon icon, boolean showPressed)
	{
		this(icon, null, showPressed);
	}
	
	public IDButton(Icon icon, IDAction pressAction)
	{
		this(icon, null, true);
	}
	
	public IDButton(Icon icon, IDAction pressAction, boolean showPressed)
	{
		button = new FlatJButton(icon);
		this.pressAction = pressAction;
		
		setup(showPressed);
	}
	
	private void setup(boolean showPressed)
	{
		button.setBorder(null);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				onPress(e);
			}
		});
		
		button.setShowPressed(showPressed);
	}
	
	public JComponent getRaw()
	{
		return button;
	}
	
	public void repaint(IDLocationFrame frame)
	{
		button.setSize(frame.getActualSize().toRaw());
		button.setLocation(frame.getActualOffset().toRaw());
	}
	
	public void setOnPress(IDAction action)
	{
		pressAction = action;
	}
	
	private void onPress(ActionEvent e)
	{
		if (pressAction != null)
		{
			// TODO: Use correct IDEvent
			
			pressAction.onAction(null);
		}
	}
}
