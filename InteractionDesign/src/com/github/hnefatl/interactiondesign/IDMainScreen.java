package com.github.hnefatl.interactiondesign;

import com.github.hnefatl.interactiondesign.ui.IDAction;
import com.github.hnefatl.interactiondesign.ui.IDButton;
import com.github.hnefatl.interactiondesign.ui.IDColour;
import com.github.hnefatl.interactiondesign.ui.IDEvent;
import com.github.hnefatl.interactiondesign.ui.IDFrame;
import com.github.hnefatl.interactiondesign.ui.IDGradientRectangle;
import com.github.hnefatl.interactiondesign.ui.IDLocation;
import com.github.hnefatl.interactiondesign.ui.IDLocationFrame;
import com.github.hnefatl.interactiondesign.ui.IDPosition;
import com.github.hnefatl.interactiondesign.ui.IDRectangle;
import com.github.hnefatl.interactiondesign.ui.IDScrollFrame;
import com.github.hnefatl.interactiondesign.ui.IDSize;
import com.github.hnefatl.interactiondesign.ui.IDString;
import com.github.hnefatl.interactiondesign.ui.IDSubFrame;

@SuppressWarnings("unused")
public class IDMainScreen
{
	private static final IDPosition dfOffset = new IDPosition(0, 20);
	private static final IDSize dfSize = new IDSize(1242, 2148);
	
	private static final IDLocation dfLocation = new IDLocation(dfOffset, dfSize);
	private static final double dfScale = 3.0;
	
	private IDFrame mainFrame;
	
	private IDScrollFrame scrollFrame;
	
	public IDMainScreen()
	{
		mainFrame = createFrame();
	}
	
	private IDFrame createFrame()
	{
		IDFrame rootFrame = new IDFrame(new IDLocationFrame(dfLocation, dfScale));
		
		scrollFrame = constructScrollFrame();
		
		rootFrame.addComponent(constructMenuBar(), new IDLocation(IDPosition.ZERO, new IDSize(1242, 132)));
		rootFrame.addComponent(constructScrollFrame(), new IDLocation(new IDPosition(0, 132), new IDSize(1242, 2016)));
		
		return rootFrame;
	}
	
	private IDSubFrame constructMenuBar()
	{
		IDSubFrame menuBar = new IDSubFrame();
		
		menuBar.get().addComponent(new IDRectangle(new IDColour(0xF0, 0xF0, 0xF0)), new IDLocation(IDPosition.ZERO, new IDSize(1242, 132)));
		
		menuBar.get().addComponent(new IDButton(new IDString("\u2630", IDString.getDefaultFont(20.0)), new IDAction() {
			public void onAction(IDEvent e)
			{
				System.out.println("TODO: Open menu");
			}
		}), new IDLocation(new IDPosition(24, 0), new IDSize(132, 132)));
		
		menuBar.get().addComponent(new IDButton(new IDString("+", IDString.getDefaultFont(20.0)), new IDAction() {
			public void onAction(IDEvent e)
			{
				System.out.println("TODO: Add new");
				
				//mainFrame.setModal(constructDefaultModal());
			}
		}), new IDLocation(new IDPosition(1242 - (24 + 132), 0), new IDSize(132, 132)));
		
		return menuBar;
	}
	
	private IDScrollFrame constructScrollFrame()
	{
		IDScrollFrame sFrame = new IDScrollFrame();
		
		sFrame.addComponent(new IDGradientRectangle(new IDColour(0xFF, 0xFF, 0xFF), new IDColour(0xC8, 0xA2, 0xC8)), new IDLocation(IDPosition.ZERO, new IDSize(1242, 4000)));
		
		return sFrame;
	}
	
	public IDFrame getRaw()
	{
		return mainFrame;
	}
}
