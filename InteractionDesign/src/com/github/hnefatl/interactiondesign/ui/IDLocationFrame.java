package com.github.hnefatl.interactiondesign.ui;

public class IDLocationFrame
{
	private IDLocation frameLocation;
	private double scale;
	
	private IDLocationFrame parentFrame;
	
	private boolean hasOwnGraphicsContext; // True if this IDLocationFrame belongs to an IDFrame
	
	public IDLocationFrame(IDLocation frameLocation)
	{
		this(frameLocation, 1.0);
	}
	
	public IDLocationFrame(IDLocation frameLocation, double scale)
	{
		this.frameLocation = frameLocation;
		this.scale = scale;
		
		parentFrame = null;
		hasOwnGraphicsContext = false;
	}
	
	public void setParent(IDLocationFrame parentFrame)
	{
		this.parentFrame = parentFrame;
	}
	
	public void setHasOwnGraphicsContext(boolean hasOwnGraphicsContext)
	{
		this.hasOwnGraphicsContext = hasOwnGraphicsContext;
	}
	
	private double getWidth()
	{
		return frameLocation.getSize().x;
	}
	
	private double getHeight()
	{
		return frameLocation.getSize().y;
	}
	
	public IDPosition getOffset()
	{
		return frameLocation.getOffset();
	}
	
	public IDSize getSize()
	{
		return frameLocation.getSize();
	}
	
	public double getScale()
	{
		return scale;
	}
	
	public IDPosition getActualOffset()
	{
		return (parentFrame != null) ? parentFrame.resolveAbsoluteOffset(frameLocation.getOffset()) : frameLocation.getOffset();
	}
	
	public IDSize getActualSize()
	{
		IDSize frameSize = frameLocation.getSize();
		IDSize scaledSize = new IDSize(frameSize.x / scale, frameSize.y / scale);
		
		return (parentFrame != null) ? parentFrame.resolveAbsoluteSize(scaledSize) : scaledSize;
	}
	
	public IDLocation resolveAbsoluteLocation(IDLocation location)
	{
		return new IDLocation(resolveAbsoluteOffset(location.getOffset()), resolveAbsoluteSize(location.getSize()));
	}
	
	public IDLocation resolveRelativeLocation(IDLocation location)
	{
		return new IDLocation(resolveRelativeOffset(location.getOffset()), resolveRelativeSize(location.getSize()));
	}
	
	private IDPosition resolveScaledOffset(IDPosition position)
	{
		double localX = (position.x >= 0) ? position.x : (getWidth() + position.x);
		double localY = (position.y >= 0) ? position.y : (getHeight() + position.y);
		
		IDPosition scaledPosition = new IDPosition(localX / scale, localY / scale);
		
		return (parentFrame != null) ? parentFrame.resolveScaledOffset(scaledPosition) : scaledPosition;
	}
	
	public IDPosition resolveAbsoluteOffset(IDPosition position)
	{
		if (hasOwnGraphicsContext)
		{
			return resolveScaledOffset(position);
		}
		
		double localX = (position.x >= 0) ? position.x : (getWidth() + position.x);
		double localY = (position.y >= 0) ? position.y : (getHeight() + position.y);
		
		double scaledX = frameLocation.getOffset().x + (localX / scale);
		double scaledY = frameLocation.getOffset().y + (localY / scale);
		
		IDPosition scaledPosition = new IDPosition(scaledX, scaledY);
		
		return (parentFrame != null) ? parentFrame.resolveAbsoluteOffset(scaledPosition) : scaledPosition;
	}
	
	public IDPosition resolveRelativeOffset(IDPosition position)
	{
		return resolveAbsoluteOffset(new IDPosition(position.x * (getWidth() - 1), position.y * (getHeight() - 1)));
	}
	
	public IDSize resolveAbsoluteSize(IDSize size)
	{
		double localX = (size.x >= 0) ? size.x : (frameLocation.getSize().x + size.x);
		double localY = (size.y >= 0) ? size.y : (frameLocation.getSize().y + size.y);
		
		IDSize scaledSize = new IDSize(localX / scale, localY / scale);
		
		return (parentFrame != null) ? parentFrame.resolveAbsoluteSize(scaledSize) : scaledSize;
	}
	
	public IDSize resolveRelativeSize(IDSize size)
	{
		return resolveAbsoluteSize(new IDSize(size.x * getWidth(), size.y * getHeight()));
	}
}
