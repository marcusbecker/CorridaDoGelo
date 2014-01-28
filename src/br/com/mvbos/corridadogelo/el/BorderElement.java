package br.com.mvbos.corridadogelo.el;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import br.com.mvbos.corridadogelo.R;
import br.com.mvbos.corridadogelo.RaceScene;
import br.com.mvbos.jega.element.ElementModel;
import br.com.mvbos.jega.engine.Engine;
import br.com.mvbos.jega.window.GameConfig;

public class BorderElement extends ElementModel {

	private boolean right;
	private RaceScene scene;

	public BorderElement(RaceScene scene) {
		this(scene, false);
	}

	public BorderElement(RaceScene scene, boolean borderRight) {
		setDefaultColor(Color.BLACK);
		setWidth(3);

		this.right = borderRight;
		this.scene = scene;
	}

	@Override
	public void loadElement() {
		setImageAndSize(BitmapFactory.decodeResource(GameConfig.getConfig()
				.getResources(), right ? R.drawable.borda_dir
				: R.drawable.borda_esq));

	}

	@Override
	public void update() {

		if (right && getPx() == 0) {
			setPx(GameConfig.getConfig().getWindowWidth() - getWidth());
		}

		if (getImage() == null) {
			if (getPy() - 5 < GameConfig.getConfig().getWindowHeight()) {
				// incPy(scene.getVel() <= maxVel ? scene.getVel() : maxVel);
				incPy(scene.getVel());
			} else {
				setPy(-20);
			}
		} else {
			if (getPy() > GameConfig.getConfig().getWindowHeight()) {
				setPy(0);
			} else {
				incPy(scene.getVel());
			}
		}

	}

	@Override
	public void drawMe(Canvas canvas) {

		if (getImage() != null) {
			/*
			 * int px = 0; if (right) { px =
			 * GameConfig.getConfig().getWindowWidth() - getWidth(); }
			 */

			if (getPy() > 0)
				canvas.drawBitmap(getImage(), getPx(), -getHeight() + getPy(),
						null);

			canvas.drawBitmap(getImage(), getPx(), getPy(), null);

		} else {
			Paint p = Engine.getPaint();
			p.setColor(getDefaultColor());

			if (!right) {
				canvas.drawCircle(getPx() + 5, getPy() + 10, getWidth(), p);
				canvas.drawCircle(getPx() + 10, getPy() + 25, getWidth(), p);

			} else {
				canvas.drawCircle(GameConfig.getConfig().getWindowWidth() - 10,
						getPy() + 15, getWidth(), p);
				canvas.drawCircle(GameConfig.getConfig().getWindowWidth() - 5,
						getPy() + 30, getWidth(), p);
			}

		}
	}
}
