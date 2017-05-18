package com.github.hnefatl.interactiondesign;

import java.util.Locale;
import java.util.ResourceBundle;

public class InteractionDesign
{
	public static void main(String[] args)
	{
		IDLocaleManager.loadLocale(Locale.ENGLISH, ResourceBundle.getBundle("IDResources", Locale.ENGLISH));
		
		new IDApp();
	}
}
