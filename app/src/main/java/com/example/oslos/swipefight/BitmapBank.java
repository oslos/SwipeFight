package com.example.oslos.swipefight;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;


public class BitmapBank
{
    Bitmap _main_character;

    public BitmapBank(Resources res) {

        _main_character = BitmapFactory.decodeResource(res, R.drawable.main_character);
    }
    public Bitmap GetMainCharacter()
    {
        return _main_character;
    }


    public static Bitmap GetBitmap(Bitmap source)
    {
        Matrix matrix = new Matrix();

        return Bitmap.createBitmap
                (
                        source,
                        0, 0,
                        source.getWidth(),
                        source.getHeight(),
                        matrix,
                        true
                );
    }
}

