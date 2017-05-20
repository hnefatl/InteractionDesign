package com.github.hnefatl.interactiondesign.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class IDIcon extends IDComponent
{
	private JComponent iconComponent = null;
	private BufferedImage rawImage = null;
	
	public IDIcon(IDImage image)
	{
		rawImage = image.getRaw();
		
		if (rawImage != null)
		{
			
			setupComponent();
		}
	}
	
	private void setupComponent()
	{
		iconComponent = new JComponent() {
			static final long serialVersionUID = 0x01882934;
			
			@Override
			public void paintComponent(Graphics g)
			{
				Graphics2D g2 = (Graphics2D)g;
				
		        int newW = this.getWidth();
		        int newH = this.getHeight();
		        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		        g2.drawImage(rawImage, 0, 0, newW, newH, null);
			}
		};
	}
	
	public JComponent getRaw()
	{
		return iconComponent;
	}
	
	public void repaint(IDLocationFrame frame)
	{
		if (iconComponent != null)
		{
			iconComponent.setSize(frame.getActualSize().toRaw());
			iconComponent.setLocation(frame.getActualOffset().toRaw());
		}
	}
}
