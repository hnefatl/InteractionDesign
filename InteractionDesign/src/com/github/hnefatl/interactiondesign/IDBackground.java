package com.github.hnefatl.interactiondesign;

import com.github.hnefatl.interactiondesign.ui.IDColour;
import com.github.hnefatl.interactiondesign.ui.IDFrame;
import com.github.hnefatl.interactiondesign.ui.IDLocation;
import com.github.hnefatl.interactiondesign.ui.IDLocationFrame;
import com.github.hnefatl.interactiondesign.ui.IDPosition;
import com.github.hnefatl.interactiondesign.ui.IDRectangle;
import com.github.hnefatl.interactiondesign.ui.IDSize;

public class IDBackground
{
	private IDFrame backgroundFrame;
	
	private static final IDColour bgColour = new IDColour(1.0, 1.0, 1.0);
	private static final IDColour sbColour = new IDColour(0.2, 0.2, 0.2);
	
	private static final IDLocation bgLocation = new IDLocation(new IDPosition(0, 0), new IDSize(1242, 2208));
	private static final double bgScale = 3.0;
	
	private static final IDLocation sbLocation = new IDLocation(IDPosition.ZERO, new IDSize(1242, 60));
	
	public IDBackground()
	{
		backgroundFrame = constructBackgroundFrame();
	}
	
	private IDFrame constructBackgroundFrame()
	{
		IDFrame backgroundFrame = new IDFrame(new IDLocationFrame(bgLocation, bgScale));
		
		backgroundFrame.addComponent(new IDRectangle(bgColour), bgLocation);
		backgroundFrame.addComponent(new IDRectangle(sbColour), sbLocation);
		
		return backgroundFrame;
	}
	
	public IDFrame getRaw()
	{
		return backgroundFrame;
	}
}
