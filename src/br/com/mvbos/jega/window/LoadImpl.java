package br.com.mvbos.jega.window;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import br.com.mvbos.jega.element.ElementModel;
import br.com.mvbos.jega.engine.Engine;
import br.com.mvbos.jega.scene.IScene;

public class LoadImpl {

	private int isLoading = -1;
	private int loadElementCount;
	private IScene scene;

	public boolean inLoad() {
		return isLoading != -1;
	}

	public void loadNext(Canvas canvas, Paint paint) {
		Engine.FREEZE = true;

		if (isLoading >= 0) {
			paint.setStyle(Style.FILL);
			paint.setTextSize(16);
			paint.setColor(Color.BLACK);

			if (isLoading == 0) {
				if (Engine.DEBUG_MODE) {
					Engine.log(scene.toString());
					Engine.log("start " + System.currentTimeMillis());
				}

				canvas.drawText("CARREGANDO CENARIO", getWidth(), getHeight(),
						paint);

				/*
				 * GraphicUtil.drawString((Graphics2D) g, GraphicUtil.BIG_FONT,
				 * "Carregando cenario", getWidth() / 3, getHeight() / 2);
				 */

				scene.startScene();
				isLoading++;

			} else if (isLoading == 1) {
				canvas.drawText("CARREGANDO PLANO DE FUNDO", getWidth(),
						getHeight(), paint);

				if (scene.getElements().getBackground() != null) {
					scene.getElements().getBackground().loadElement();
					// setBackGrountElement(scene.getElements().getBackground());
				}

				isLoading++;

			} else if (isLoading == 2
					&& loadElementCount < scene.getElements().getElementCount()) {

				canvas.drawText("CARREGANDO ELEMENTOS", getWidth(),
						getHeight(), paint);

				ElementModel e = scene.getElements().getByElement(
						loadElementCount++);
				e.loadElement();

			} else {
				isLoading = -1;
				Engine.FREEZE = false;
				if (Engine.DEBUG_MODE) {
					Engine.log("end " + System.currentTimeMillis());
				}
			}
		}
	}

	private int getHeight() {
		return GameConfig.getConfig().getWindowHeight() / 2;
	}

	private int getWidth() {
		return 15;
	}

	public void reload() {

	}

	public void reload(IScene scene) {
		isLoading = 0;
		loadElementCount = 0;
		this.scene = scene;
	}

}
