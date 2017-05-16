package com.github.hnefatl.interactiondesign.ui;

public class IDLocation
{
	private IDPosition offset;
	private IDSize size;
	
	public IDLocation(IDSize size)
	{
		this(new IDPosition(0.0, 0.0), size);
	}
	
	public IDLocation(IDPosition offset, IDSize size)
	{
		this.offset = offset;
		this.size = size;
	}
	
	public IDPosition getOffset()
	{
		return offset;
	}
	
	public IDSize getSize()
	{
		return size;
	}
}
