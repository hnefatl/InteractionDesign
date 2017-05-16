package com.github.hnefatl.interactiondesign.data;

public class Coord
{
	private float lat;
	private float lon;
	
	public Coord(float lat, float lon)
	{
		this.lat = lat;
		this.lon = lon;
	}
	
	public float getLat()
	{
		return lat;
	}
	public float getLon()
	{
		return lon;
	}
}
