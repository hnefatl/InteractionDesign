package com.github.hnefatl.interactiondesign;

import com.github.hnefatl.interactiondesign.ui.IDColour;
import com.github.hnefatl.interactiondesign.ui.IDFrame;
import com.github.hnefatl.interactiondesign.ui.IDLocation;
import com.github.hnefatl.interactiondesign.ui.IDLocationFrame;
import com.github.hnefatl.interactiondesign.ui.IDMainFrame;
import com.github.hnefatl.interactiondesign.ui.IDPosition;
import com.github.hnefatl.interactiondesign.ui.IDRectangle;
import com.github.hnefatl.interactiondesign.ui.IDSize;

public class IDApp
{
	private IDMainFrame mainFrame;
	
	private static final IDColour bgColour = new IDColour(1.0, 1.0, 1.0);
	private static final IDColour sbColour = new IDColour(0.2, 0.2, 0.2);
	private static final IDColour mbColour = new IDColour(0.0, 0.0, 0.0, 0.5);
	
	private static final IDLocation bfLocation = new IDLocation(new IDPosition(0, 0), new IDSize(1242, 2208));
	private static final double bfScale = 3.0;
	
	private static final IDPosition dfOffset = new IDPosition(0, 20);
	private static final IDSize dfSize = new IDSize(1242, 2148);
	
	private static final IDPosition noOffset = new IDPosition(0, 0);
	
	private static final IDLocation bgLocation = new IDLocation(new IDPosition(0, 60), dfSize);
	private static final IDLocation sbLocation = new IDLocation(noOffset, new IDSize(1242, 60));
	
	private static final IDLocation dfLocation = new IDLocation(dfOffset, dfSize);
	private static final double dfScale = 3.0;
	
	public IDApp()
	{
		mainFrame = new IDMainFrame(constructBackgroundFrame(), constructModalBackgroundFrame());
		
		mainFrame.setFrame(constructRootFrame());
	}
	
	private IDFrame constructBackgroundFrame()
	{
		IDFrame backgroundFrame = new IDFrame(new IDLocationFrame(bfLocation, bfScale));
		
		backgroundFrame.addComponent(new IDRectangle(sbColour), sbLocation);
		backgroundFrame.addComponent(new IDRectangle(bgColour), bgLocation);
		
		return backgroundFrame;
	}
	
	private IDFrame constructRootFrame()
	{
		IDFrame rootFrame = new IDFrame(new IDLocationFrame(dfLocation, dfScale));
		
		rootFrame.addComponent(new IDRectangle(new IDColour(0xC8, 0xA2, 0xC8)), new IDLocation(noOffset, new IDSize(1242, 600)));
		
		return rootFrame;
	}
	
	private IDFrame constructModalBackgroundFrame()
	{
		IDFrame modalBackgroundFrame = new IDFrame(new IDLocationFrame(dfLocation, dfScale));
		
		modalBackgroundFrame.addComponent(new IDRectangle(mbColour), new IDLocation(noOffset, dfSize));
		
		return modalBackgroundFrame;
	}
	
	private IDFrame constructDefaultModal()
	{
		IDFrame modalFrame = new IDFrame(new IDLocationFrame(dfLocation, dfScale));
		
		modalFrame.addComponent(new IDRectangle(new IDColour(1.0, 1.0, 1.0)), new IDLocation(new IDPosition(0, 574), new IDSize(1242, 1000)));
		
		return modalFrame;
	}
}
