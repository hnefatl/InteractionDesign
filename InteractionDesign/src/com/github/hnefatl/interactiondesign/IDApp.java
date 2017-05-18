package com.github.hnefatl.interactiondesign;

import java.util.Locale;

import com.github.hnefatl.interactiondesign.ui.IDMainFrame;

public class IDApp
{
	private Locale defaultLocale;
	
	private IDMainFrame mainFrame;
	
	private IDBackground background;
	private IDModalBackground modalBackground;
	
	private IDMainScreen mainScreen;
	
	public IDApp()
	{
		this(null);
	}
	
	public IDApp(Locale defaultLocale)
	{
		this.defaultLocale = defaultLocale;
		
		background = new IDBackground();
		modalBackground = new IDModalBackground(e -> {
			mainFrame.clearModal();
		});
		
		mainFrame = new IDMainFrame(background.getRaw(), modalBackground.getRaw());
		
		mainScreen = new IDMainScreen(this.defaultLocale);
		
		mainFrame.setFrame(mainScreen.getRaw());
	}
	
	/*
	//@SuppressWarnings("unused")
	private IDFrame constructDefaultModal()
	{
		IDFrame modalFrame = new IDFrame(new IDLocationFrame(dfLocation, dfScale));
		
		modalFrame.addComponent(new IDRectangle(new IDColour(1.0, 1.0, 1.0)), new IDLocation(new IDPosition(0, 574), new IDSize(1242, 1000)));
		modalFrame.addComponent(new IDButton(false), new IDLocation(new IDPosition(0, 574), new IDSize(1242, 1000)));
		
		return modalFrame;
	}
	*/
}
