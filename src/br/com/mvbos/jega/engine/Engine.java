package br.com.mvbos.jega.engine;

import android.graphics.Paint;
import br.com.mvbos.jega.window.IWindowGame;

public class Engine {

	private static Paint paint;

	private static Engine engine;

	public static Engine getEngine() {
		if (engine == null) {
			engine = new Engine();
		}

		return engine;
	}

	public static Paint getPaint() {
		return paint;
	}

	public static void setPaint(Paint paint) {
		Engine.paint = paint;
	}

	public static boolean drawGrid = Boolean.FALSE;
	public static boolean endGame = Boolean.TRUE;
	private static boolean pausedGame = Boolean.FALSE;
	public static boolean DEBUG_MODE = Boolean.FALSE;
	public static boolean FREEZE = Boolean.TRUE;
	public static final int fps = 40;
	public static IWindowGame window;
	public static ClassLoader cldr;
	public static boolean gameLoop = Boolean.TRUE;
	public static boolean multhread = false;

	public static void log(String s) {
		System.out.println(s);
	}

	public static void log(String s, Object o) {
		System.out.print(s);
		System.out.println(o);
	}

	static public void logGlobalValues() {
		log("drawGrid " + drawGrid);// Boolean.FALSE;
		log("endGame " + endGame);// Boolean.TRUE;
		log("pausedGame " + pausedGame);// Boolean.FALSE;
		log("fps " + fps);// 20;
		log("DEBUG_MODE " + DEBUG_MODE);// Boolean.TRUE;
		log("gameLoop " + gameLoop);// Boolean.TRUE;
		log("multhread " + multhread);// false;
	}

	/*
	 * public static WindowGameIm createWindowGame() { window = new
	 * WindowGameImpl(); window.setVisible(true);
	 * 
	 * return window; }
	 */

	public static IWindowGame getIWindowGame() {
		return window;
	}

	public static void setIWindowGame(IWindowGame w) {
		window = w;
	}

	public static void pauseGame(boolean b) {
		pausedGame = b;
		log("Pause game " + b);
	}

	public static boolean isPaused() {
		return pausedGame;
	}

	public long timePlay() {
		// TODO Auto-generated method stub
		return 0;
	}

}
