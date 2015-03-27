package com.example.zombiesnight;
import java.util.List;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Temp {
	private float x;
    private float y;
    private Bitmap bmp;
    private List<Temp> temps;
    public Temp(List<Temp> temps, GameView gameView, float x, float y, Bitmap bmp) {
          this.x = Math.min(Math.max(x - bmp.getWidth() / 2, 0),
                        gameView.getWidth() - bmp.getWidth());
          this.y = Math.min(Math.max(y - bmp.getHeight() / 2, 0),
                        gameView.getHeight() - bmp.getHeight());
          this.bmp = bmp;
          this.temps = temps;

    }

    public void onDraw(Canvas canvas) {
          canvas.drawBitmap(bmp, x, y, null);
    }
}
