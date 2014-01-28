package br.com.mvbos.corridadogelo.el;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import br.com.mvbos.corridadogelo.R;
import br.com.mvbos.corridadogelo.RaceScene;
import br.com.mvbos.jega.element.ElementModel;
import br.com.mvbos.jega.engine.Engine;
import br.com.mvbos.jega.engine.GraphicTool;
import br.com.mvbos.jega.window.GameConfig;

public class RampElement extends ElementModel {

	private final int RAMP_DELAY = -300;
	private RaceScene scene;

	public RampElement(RaceScene scene) {
		this(scene, false);
	}

	public RampElement(RaceScene scene, boolean borderRight) {
		// #3399CC
		setDefaultColor(Color.rgb(51, 153, 204));
		setSize(15, 25);
		GraphicTool.g().centerWindow(this);
		setPy(RAMP_DELAY);
		this.scene = scene;
	}

	@Override
	public void loadElement() {
		setImageAndSize(BitmapFactory.decodeResource(GameConfig.getConfig()
				.getResources(), R.drawable.rampa));
	}

	@Override
	public void update() {
		if (getPy() - 5 < GameConfig.getConfig().getWindowHeight()) {
			incPy(scene.getVel());
		} else {
			setEnabled(false);
		}

	}

	@Override
	public void drawMe(Canvas canvas) {

		if (!isEnabled()) {
			return;
		}

		Paint p = Engine.getPaint();
		p.setColor(getDefaultColor());

		if (getAllHeight() >= 0f) {
			if (getImage() != null) {
				canvas.drawBitmap(getImage(), getPx(), getPy(), null);

			} else {
				canvas.drawRect(getPx(), getPy(), getAllWidth(),
						getAllHeight(), p);
			}
		} else {
			// indicacao da onde a rampa vai aparecer
			canvas.drawCircle(getPx(), 1, 5, p);
		}
	}

	public void reset(int i) {
		// setPxy(30, -10);//
		setPxy(i * 30, RAMP_DELAY);
		setEnabled(true);
	}
}
