package br.com.mvbos.jega.os;

import android.content.Context;
import android.os.Vibrator;

public class GameShockWave {

	private final Context context;
	private Vibrator v;

	public GameShockWave(Context context) {
		this.context = context;
		v = (Vibrator) this.context.getSystemService(Context.VIBRATOR_SERVICE);
	}

	public boolean isValid() {
		if (v == null) {
			return false;
		}

		try {
			v.vibrate(1);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * Chama o metodo vibrate da classe android.os.Vibrator
	 * 
	 * @param ms
	 */
	public void vibrate(int ms) {
		v.vibrate(ms);
	}

	/**
	 * Chama o metodo vibrate da classe android.os.Vibrator
	 * 
	 * @param ms
	 */
	public void vibrate(long[] patter, int repeat) {
		v.vibrate(patter, repeat);
	}

}
