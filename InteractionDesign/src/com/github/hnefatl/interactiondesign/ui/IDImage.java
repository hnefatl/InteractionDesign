package com.github.hnefatl.interactiondesign.ui;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class IDImage
{
	BufferedImage image;
	IDSize size;
	
	public IDImage(String location)
	{
		this(location, null);
	}
	
	public IDImage(String location, IDSize size)
	{
		try
		{
			image = ImageIO.read(new File(location));
			
			if ((size != null) && ((((int) size.x) != image.getWidth()) || (((int) size.y)!= image.getHeight())))
			{
				AffineTransform at = new AffineTransform();
				at.scale(size.x / ((double) image.getWidth()), size.y / ((double) image.getHeight()));
				
				AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
				
				BufferedImage newImage = new BufferedImage((int) size.x, (int) size.y, BufferedImage.TYPE_INT_ARGB);
				image = ato.filter(image, newImage);
			}
		}
		
		catch (Exception e)
		{
			// TODO: Error modal?
			
			image = null;
		}
	}
	
	public BufferedImage getRaw()
	{
		return image;
	}
}
