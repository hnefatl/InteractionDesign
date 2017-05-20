package com.github.hnefatl.interactiondesign;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class IDLocaleManager
{
	// TODO: Make not-global
	
	private static Map<Locale, ResourceBundle> localeMap = new HashMap<Locale, ResourceBundle>();
	
	public static void loadLocale(Locale locale, ResourceBundle bundle)
	{
		localeMap.put(locale, bundle);
	}
	
	public static String get(String string)
	{
		return get(string, null);
	}
	
	public static String get(String string, Locale locale)
	{
		ResourceBundle bundle = localeMap.get(locale);
		
		if (bundle == null)
		{
			bundle = localeMap.get(Locale.ENGLISH);
			
			System.out.println("Locale missing!");
		}
		
		if (bundle == null)
		{
			return new String(string);
		}
		
		return (bundle.containsKey(string) ? bundle.getString(string) : new String(string));
	}
}
