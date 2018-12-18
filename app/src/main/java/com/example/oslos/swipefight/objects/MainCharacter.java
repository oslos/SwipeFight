package com.example.oslos.swipefight.objects;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class MainCharacter
{
	public MainCharacter()
	{
		_width = 50;
		_height = 100;
		_rotation = 0;
	}
	
	static int _width;
	static int _height;
	static float _rotation;
	static int _x, _y;
	static Rect _rect;
	

	public void SetX(int x)
	{
		_x = x;
	}
	
	public void SetY(int y)
	{
		_y = y;
	}
	
	public int GetX()
	{
		return _x;
	}
	
	public int GetY()
	{
		return _y;
	}
	
	public int GetWidth()
	{
		return _width;
	}
	public int GetHeight()
	{
		return _height;
	}

	public Rect GetRect(int screen_width, int screen_height, Bitmap b)
	{
		if(_rect == null)
		{
			int left = screen_width / 2 - (GetWidth() / 2);
			int bottom = screen_height - GetHeight();
			int top = bottom - GetHeight();
			int right = left + GetWidth();
			
			SetX(left);
			SetY(top);
			_rect = new Rect( left , top, right, bottom);  
		}
		
		return _rect;
	}
}
