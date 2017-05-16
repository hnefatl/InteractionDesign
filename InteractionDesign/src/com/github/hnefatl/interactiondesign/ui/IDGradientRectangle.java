package com.github.hnefatl.interactiondesign.ui;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class IDGradientRectangle extends IDComponent
{
	private JPanel panel;
	
	private IDColour top;
	private IDColour bottom;
	
	public IDGradientRectangle(IDColour top, IDColour bottom)
	{
		this.top = top;
		this.bottom = bottom;
		
		setupPanel();
	}
	
	public void setupPanel()
	{
		panel = new JPanel() {
			static final long serialVersionUID = 0x12371202;
			
			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				
		        Graphics2D g2d = (Graphics2D) g;
		        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		        int w = getWidth();
		        int h = getHeight();
		        
		        GradientPaint gp = new GradientPaint(0, 0, top.toColor(), 0, h, bottom.toColor());
		        g2d.setPaint(gp);
		        g2d.fillRect(0, 0, w, h);
			}
		};
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
