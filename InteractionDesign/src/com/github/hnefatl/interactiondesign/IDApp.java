package com.github.hnefatl.interactiondesign;

import java.util.Locale;

import com.github.hnefatl.interactiondesign.ui.IDFrame;
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
		
		mainScreen = new IDMainScreen(this, this.defaultLocale);
		
		mainFrame.setFrame(mainScreen.getRaw());
	}
	
	public void showModal(IDFrame modalFrame)
	{
		mainFrame.setModal(modalFrame);
	}
}
