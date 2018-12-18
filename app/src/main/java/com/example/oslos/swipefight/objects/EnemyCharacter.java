package com.example.oslos.swipefight.objects;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class EnemyCharacter
{
    public EnemyCharacter(int location)
    {
        _width = 100;
        _height = 200;
        _location = location;
        alive = false;
        _alive_since = 0;
    }


    int _location;
    int _width;
    int _height;
    int _x, _y;
    Rect _rect;
    Boolean alive;
    long _alive_since;

    public void Kill()
    {
        alive = false;
    }

    public void SetToSpawn()
    {
        alive = true;
        this._alive_since = System.currentTimeMillis();
    }

    public Boolean IsAlive(){return alive;}

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
        int left = 0;// = screen_width / 2 - (GetWidth() / 2);
        int bottom = 0;// = screen_height / 2 - (GetHeight() / 2);
        int top = 0;// = bottom - GetHeight();
        int right = 0;// = left + GetWidth();

        switch (_location)
        {
            case 1:
                left = screen_width / 2 - (GetWidth() / 2);
                bottom = GetHeight();
                top = bottom - GetHeight();
                right = left + GetWidth();
                break;
            case 2:
                left = GetWidth();
                bottom = screen_height / 2 - (GetHeight() / 2);
                top = bottom - GetHeight();
                right = left + GetWidth();
                break;
            case 3:
                left = screen_width / 2 - (GetWidth() / 2);
                bottom = screen_height - GetHeight();
                top = bottom - GetHeight();
                right = left + GetWidth();
                break;
            case 4:
                left = screen_width - GetWidth();
                bottom = screen_height / 2 - (GetHeight() / 2);
                top = bottom - GetHeight();
                right = left + GetWidth();
                break;
        }


/*
            left = screen_width / 2 - (GetWidth() / 2);
            bottom = screen_height / 2 - (GetHeight() / 2);
            top = bottom - GetHeight();
            //int bottom = screen_height - GetHeight();
            //int top = screen_height / 2 - (GetHeight() / 2);
            right = left + GetWidth();*/

            SetX(left);
            SetY(top);
            _rect = new Rect( left , top, right, bottom);
        }

        return _rect;
    }
}
