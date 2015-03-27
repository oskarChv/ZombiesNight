package com.example.zombiesnight;

import android.graphics.Canvas;

public class GameLoopThread extends Thread{
	private final byte _FPS=10;
	private GameView view;
	private boolean running = false;
	
	public GameLoopThread(GameView view){
		this.view=view;
	}
	
	public void setRunning(boolean run){
		running=run;
	}
	
	public void run(){
		long _ticksPS = 1000/_FPS;
		long _startTime;
		long _sleepTime;
		
		while(running){
			Canvas lienzo = null;
			_startTime = System.currentTimeMillis();
			try{
				lienzo=view.getHolder().lockCanvas();
				synchronized (view.getHolder()){
					view.onDraw(lienzo);
				}
				
			}finally{
				if(lienzo != null){
					view.getHolder().unlockCanvasAndPost(lienzo);
				}
			}
			_sleepTime = _ticksPS-(System.currentTimeMillis()-_startTime);//evitar atrasos y abances 
			try{
				if(_sleepTime>0)
						sleep(_sleepTime);
				else
					sleep(10);
			}catch(Exception e){}
		}
	}

}
