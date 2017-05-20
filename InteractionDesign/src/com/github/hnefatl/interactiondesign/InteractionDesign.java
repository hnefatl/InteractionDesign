package com.github.hnefatl.interactiondesign;

import java.util.Locale;
import java.util.ResourceBundle;

public class InteractionDesign
{
	public static void main(String[] args)
	{
		IDLocaleManager.loadLocale(Locale.ENGLISH, ResourceBundle.getBundle("localisation/IDResources", Locale.ENGLISH));
		IDLocaleManager.loadLocale(Locale.FRENCH, ResourceBundle.getBundle("localisation/IDResources", Locale.FRANCE));
		
		new IDApp(Locale.ENGLISH);
	}
}
