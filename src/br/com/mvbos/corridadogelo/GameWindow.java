package br.com.mvbos.corridadogelo;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import br.com.mvbos.jega.element.ElementMovableModel;
import br.com.mvbos.jega.engine.Engine;
import br.com.mvbos.jega.engine.GameEngineModel;
import br.com.mvbos.jega.os.OSTool;
import br.com.mvbos.jega.scene.Click;
import br.com.mvbos.jega.scene.IScene;
import br.com.mvbos.jega.window.GameConfig;
import br.com.mvbos.jega.window.IWindowGame;
import br.com.mvbos.jega.window.LoadImpl;

public class GameWindow extends SurfaceView implements IWindowGame,
		OnTouchListener, SensorEventListener {

	private static final boolean drawWallPaper = false;

	private IScene scene;
	public boolean drawMovabelOrderDesc = true;

	private SurfaceHolder holder;
	private Paint paint;

	private SensorManager sm;
	private final Context context;

	private final GameEngineModel e;
	private final Click click = new Click();
	private final LoadImpl loadDefault;
	
	public static GameSound gs;

	public GameWindow(Context context) {
		super(context);
		this.context = context;
		
		//TODO testes
		gs = new GameSound(context);
		gs.load();
		
		paint = new Paint();
		holder = getHolder();
		loadDefault = new LoadImpl();

		Engine.setPaint(paint);
		setKeepScreenOn(true);

		Engine.DEBUG_MODE = true;

		e = new GameEngineModel(0);
		e.fill(this, Engine.fps, Engine.fps);
	}

	public void pauseGame() {
		e.pause();
		sm.unregisterListener(this);
		gs.stopAll();
	}

	public void resumeGame() {
		Sensor defaultSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		boolean accelSupported = sm.registerListener(this, defaultSensor,
				SensorManager.SENSOR_DELAY_GAME);// SensorManager.SENSOR_DELAY_NORMAL

		if (!accelSupported) {
			sm.unregisterListener(this, defaultSensor);
			Engine.log("accel not supported");
		}

		setOnTouchListener(this);

		e.resume();

	}

	@Override
	public void startGame() {
		sm = (SensorManager) getContext().getSystemService(
				Context.SENSOR_SERVICE);

		e.start();

	}

	@Override
	public void drawGame() {

		if (scene == null) {
			return;
		}
		if (!holder.getSurface().isValid()) {
			return;
		}

		GameConfig.getConfig().setWindowWidth(getWidth());
		GameConfig.getConfig().setWindowHeight(getHeight());

		Canvas canvas = holder.lockCanvas();
		paint.setColor(Color.WHITE);
		canvas.drawRect(0, 0, GameConfig.getConfig().getWindowWidth(),
				GameConfig.getConfig().getWindowHeight(), paint);

		if (drawWallPaper) {
			Drawable drawable = WallpaperManager.getInstance(context)
					.getDrawable();
			canvas.drawBitmap(OSTool.drawableToBitmap(drawable), 0, 0, paint);
		}

		if (loadDefault.inLoad()) {
			loadDefault.loadNext(canvas, paint);

		} else {
			if (Engine.drawGrid) {
				// GraphicUtil.drawGrid(g2d, this.getWidth(), this.getHeight());
			}

			scene.drawElements(canvas);
		}

		holder.unlockCanvasAndPost(canvas);
	}

	@Override
	public void updateGame() {

		if (scene == null) {
			return;
		}

		if (!holder.getSurface().isValid()) {
			return;
		}

		if (GameConfig.getConfig() == null) {
			GameConfig.init(this, getWidth(), getHeight());
			GameConfig.getConfig().setResources(getResources());
		}

		if (!loadDefault.inLoad() && !Engine.FREEZE) {
			scene.update();
		}
	}

	@Override
	public void changeScene(IScene scene) {
		if (this.scene != null) {
			this.scene.changeSceneEvent();
		}

		this.scene = scene;
		loadDefault.reload(scene);
	}

	@Override
	public void freeze(boolean b, int option) {
		// freeze = b;
	}

	@Override
	public int getWindowWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWindowHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void selectMovableElement(ElementMovableModel e) {
		// TODO Auto-generated method stub
	}

	@Override
	public ElementMovableModel getMovableElement() {
		// TODO Auto-generated method stub
		return null;
	}

	public void stop() {
		e.stop();

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (loadDefault.inLoad()) {
			return false;
		}

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// click.setEvent(event);
			click.setClick(event.getX(), event.getY(), 0, 0);
			scene.clickElement(click);
		}

		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (loadDefault.inLoad()) {
			return;
		}

		float axisX = event.values[0];
		float axisY = event.values[1];
		float axisZ = event.values[2];

		scene.sensorChanged(axisX, axisY, axisZ);

		// System.out.println("axisX " + axisX);
		// System.out.println("axisY " + axisY);

	}

	@Override
	public void engineNotification(int id) {
		// TODO Auto-generated method stub

	}

}
