package com.example.zombiesnight;
import java.util.Random;

import android.R.color;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
public class Sprite {
	private static final int BMP_COLUMNS =3;
	private static final int BMP_ROWS =4;
	
	private int x = 0;
	private int y=0;
	private int xSpeed;
	private int ySpeed;
	private GameView gameView;
	private Bitmap bmp;
	private int width;
	private int height;
	private int currentFrame;
	//direction = 0 up, 1 left, 2 down, 3 right 
	//animation = 3 back, 1 left, 0 front, 2 right
	private int[] _D_Anim_Map={3,1,0,2};

	
	public Sprite(GameView gameView, Bitmap bmp){
		this.gameView = gameView;
		this.bmp = bmp;
		this.width=bmp.getWidth()/BMP_COLUMNS;
		this.height=bmp.getHeight()/BMP_ROWS;
		Random rnd = new Random();
		//sorteo de pociciones de arranque de los sprites
		x = rnd.nextInt(gameView.getWidth() - width);
		do{
			y = rnd.nextInt(gameView.getHeight()- (height-30));
		}while(y<100 || y > gameView.getHeight()-400);
		
		//sorteo de velocidad de aranque de los sprites
		xSpeed = rnd.nextInt(10)-5;
		ySpeed = rnd.nextInt(10)-5;
	}
	
	public void update(){
		if(x > gameView.getWidth() - width - xSpeed || x + xSpeed<0){
			xSpeed = -xSpeed;
		}
		x += xSpeed;
		if(y > gameView.getHeight() - height - ySpeed || y + ySpeed<100){
			ySpeed = -ySpeed;
		}
		y += ySpeed;
		currentFrame = ++currentFrame % BMP_COLUMNS;
	}
	
	public void onDraw(Canvas lienzo){
		
		update();
		int srcX = currentFrame * width;
		int srcY = getAnimationRow() * height;
		Rect src = new Rect (srcX,srcY,srcX + width,srcY + height);
		Rect dst = new Rect (x,y,x + width,y + height);
		if(!(y == gameView.getHeight()- height)){
			lienzo.drawBitmap(bmp,src,dst,null);
		}
	}
	private int getAnimationRow(){
		double dirD=(Math.atan2(xSpeed,ySpeed)/(Math.PI/2)+2);
		int direction = (int)Math.round(dirD)%BMP_ROWS;
		return _D_Anim_Map[direction];
				
	}
	public boolean isCollision(float x_c, float y_c){
		return x_c > x && x_c < x + width && y_c > y && y_c < y + height;
	}

}
