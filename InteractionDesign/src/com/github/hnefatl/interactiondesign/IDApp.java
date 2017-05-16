package com.github.hnefatl.interactiondesign;

import com.github.hnefatl.interactiondesign.ui.IDAction;
import com.github.hnefatl.interactiondesign.ui.IDButton;
import com.github.hnefatl.interactiondesign.ui.IDColour;
import com.github.hnefatl.interactiondesign.ui.IDEvent;
import com.github.hnefatl.interactiondesign.ui.IDFrame;
import com.github.hnefatl.interactiondesign.ui.IDGradientRectangle;
import com.github.hnefatl.interactiondesign.ui.IDLocation;
import com.github.hnefatl.interactiondesign.ui.IDLocationFrame;
import com.github.hnefatl.interactiondesign.ui.IDMainFrame;
import com.github.hnefatl.interactiondesign.ui.IDPosition;
import com.github.hnefatl.interactiondesign.ui.IDRectangle;
import com.github.hnefatl.interactiondesign.ui.IDScrollFrame;
import com.github.hnefatl.interactiondesign.ui.IDSize;
import com.github.hnefatl.interactiondesign.ui.IDString;
import com.github.hnefatl.interactiondesign.ui.IDSubFrame;

public class IDApp
{
	private IDMainFrame mainFrame;
	
	private IDScrollFrame scrollFrame;
	
	private static final IDColour bgColour = new IDColour(1.0, 1.0, 1.0);
	private static final IDColour sbColour = new IDColour(0.2, 0.2, 0.2);
	private static final IDColour mbColour = new IDColour(0.0, 0.0, 0.0, 0.5);
	
	private static final IDLocation bgLocation = new IDLocation(new IDPosition(0, 0), new IDSize(1242, 2208));
	private static final double bgScale = 3.0;
	
	private static final IDPosition dfOffset = new IDPosition(0, 20);
	private static final IDSize dfSize = new IDSize(1242, 2148);
	
	private static final IDPosition noOffset = new IDPosition(0, 0);
	
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
		IDFrame backgroundFrame = new IDFrame(new IDLocationFrame(bgLocation, bgScale));
		
		backgroundFrame.addComponent(new IDRectangle(bgColour), bgLocation);
		backgroundFrame.addComponent(new IDRectangle(sbColour), sbLocation);
		
		return backgroundFrame;
	}
	
	private IDFrame constructRootFrame()
	{
		IDFrame rootFrame = new IDFrame(new IDLocationFrame(dfLocation, dfScale));
		
		// Create menu bar
		
		rootFrame.addComponent(constructMenuBar(), new IDLocation(noOffset, new IDSize(1242, 132)));
		rootFrame.addComponent(constructScrollFrame(), new IDLocation(new IDPosition(0, 132), new IDSize(1242, 2016)));
		
		return rootFrame;
	}
	
	private IDSubFrame constructMenuBar()
	{
		IDSubFrame menuBar = new IDSubFrame();
		
		menuBar.get().addComponent(new IDRectangle(new IDColour(0xF0, 0xF0, 0xF0)), new IDLocation(noOffset, new IDSize(1242, 132)));
		
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
				
				mainFrame.setModal(constructDefaultModal());
			}
		}), new IDLocation(new IDPosition(1242 - (24 + 132), 0), new IDSize(132, 132)));
		
		return menuBar;
	}
	
	private IDScrollFrame constructScrollFrame()
	{
		scrollFrame = new IDScrollFrame();
		
		scrollFrame.addComponent(new IDGradientRectangle(new IDColour(0xFF, 0xFF, 0xFF), new IDColour(0xC8, 0xA2, 0xC8)), new IDLocation(noOffset, new IDSize(1242, 4000)));
		
		return scrollFrame;
	}
	
	private IDFrame constructModalBackgroundFrame()
	{
		IDFrame modalBackgroundFrame = new IDFrame(new IDLocationFrame(dfLocation, dfScale));
		
		modalBackgroundFrame.addComponent(new IDRectangle(mbColour), new IDLocation(noOffset, dfSize));
		modalBackgroundFrame.addComponent(new IDButton(new IDAction() {
			public void onAction(IDEvent e)
			{
				mainFrame.clearModal();
			}
		}, false), new IDLocation(noOffset, dfSize));
		
		return modalBackgroundFrame;
	}
	
	//@SuppressWarnings("unused")
	private IDFrame constructDefaultModal()
	{
		IDFrame modalFrame = new IDFrame(new IDLocationFrame(dfLocation, dfScale));
		
		modalFrame.addComponent(new IDRectangle(new IDColour(1.0, 1.0, 1.0)), new IDLocation(new IDPosition(0, 574), new IDSize(1242, 1000)));
		modalFrame.addComponent(new IDButton(false), new IDLocation(new IDPosition(0, 574), new IDSize(1242, 1000)));
		
		return modalFrame;
	}
}
