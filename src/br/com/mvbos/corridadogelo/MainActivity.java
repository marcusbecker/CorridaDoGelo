package br.com.mvbos.corridadogelo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import br.com.mvbos.jega.engine.Engine;

public class MainActivity extends Activity {

	private GameWindow gameWindow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);

		// landscape
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		gameWindow = new GameWindow(this);
		setContentView(gameWindow);
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		// gameWindow.changeScene(new SceneDefault());
		gameWindow.changeScene(new RaceScene());
		gameWindow.startGame();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Engine.log("onPause");
		gameWindow.pauseGame();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Engine.log("onResume");
		gameWindow.resumeGame();
	}

	@Override
	protected void onStop() {
		super.onStop();
		Engine.log("onStop");
		gameWindow.stop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Engine.log("onDestroy");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
