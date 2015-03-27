package com.example.zombiesnight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Portada extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_portada);
		Button inicio = (Button) findViewById(R.id.Iniciar);
		inicio.setOnClickListener(new OnClickListener() {
	         public void onClick(View v) {
	            Intent intent = new Intent(Portada.this, Juego.class);
	            startActivity(intent);
	         }
	      });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.portada, menu);
		return true;
	}

}
