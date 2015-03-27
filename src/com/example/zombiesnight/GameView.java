package com.example.zombiesnight;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends SurfaceView {
	private Bitmap bmp;
	private SurfaceHolder holder;
	private GameLoopThread gameLoopThread;
	private List <Sprite> sprite = new ArrayList<Sprite>();
	private long lastTouch;
	private List<Temp> temps= new ArrayList<Temp>();
	private Bitmap bmpLapida;
	private Paint pincel = new Paint();
	
	public GameView(Context context) {
		super(context);
		//pincel.setColor(Color.WHITE);
		pincel.setTextSize(20);
		gameLoopThread = new GameLoopThread(this);
		holder = getHolder();
		holder.addCallback(new Callback(){

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				//metodo que indica cuando es creado el escenario
				createSpritesMalos();//llama al metodo crear sprites
				createSpritesBuenos();
				gameLoopThread.setRunning(true);
				gameLoopThread.start();
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				
			}
		});
		bmpLapida = BitmapFactory.decodeResource(getResources(), R.drawable.lapida);
	}
	void createSpritesMalos(){
		sprite.add(createSprite(R.drawable.zombie2));
		sprite.add(createSprite(R.drawable.zombie3));
		sprite.add(createSprite(R.drawable.zombie4));
		sprite.add(createSprite(R.drawable.zombie5));
		sprite.add(createSprite(R.drawable.zombie6));
		sprite.add(createSprite(R.drawable.zombie7));
	}
	private void createSpritesBuenos(){
		sprite.add(createSprite(R.drawable.good1));
		sprite.add(createSprite(R.drawable.good2));
		sprite.add(createSprite(R.drawable.good3));
	}
	private Sprite createSprite(int resource){
		 bmp = BitmapFactory.decodeResource(getResources(), resource);
		 return new Sprite(this,bmp);
	}
	public boolean onTouchEvent(MotionEvent event){
		if(System.currentTimeMillis() - lastTouch > 300){
			lastTouch = System.currentTimeMillis();
			synchronized (getHolder()){
				float x = event.getX();
				float y = event.getY();
				//evita que choquen los hilos de dibujado con el de la modificacion
				for(int i =sprite.size()-1;i >= 0; i--){
					Sprite sprites = sprite.get(i);
					if(sprites.isCollision(x,y)){
						sprite.remove(sprites);
						temps.add(new Temp(temps,this,x,y,bmpLapida));
						break;
					}
				}
			}
		}
		return true;
	}
	@Override
    protected void onDraw(Canvas lienzo) {
        lienzo.drawColor(Color.BLACK);
        for(int i = temps.size()-1; i >= 0; i--){
			temps.get(i).onDraw(lienzo);
		}
        for(Sprite sprites:sprite){
        	sprites.onDraw(lienzo);
        }
        lienzo.drawText("Score",0,0,pincel);
    }
}
