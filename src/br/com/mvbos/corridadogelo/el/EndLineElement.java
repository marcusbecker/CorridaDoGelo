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

public class EndLineElement extends ElementModel {

	private RaceScene scene;

	public EndLineElement(RaceScene scene) {
		setDefaultColor(Color.YELLOW);
		setPxy(0, -30);
		this.scene = scene;
		setEnabled(false);
		setVisible(false);
	}

	@Override
	public void loadElement() {
		setImage(BitmapFactory.decodeResource(GameConfig.getConfig()
				.getResources(), R.drawable.linha_chegada));
	}

	@Override
	public void update() {
		if (isEnabled()) {
			if (getPy() > GameConfig.getConfig().getWindowHeight()) {
				setEnabled(false);
			} else {
				incPy(scene.getVel());
			}
		}
	}

	@Override
	public void drawMe(Canvas canvas) {

		if (getImage() != null) {
			canvas.drawBitmap(getImage(), getPx(), getPy(), null);

		} else {
			Paint p = Engine.getPaint();
			p.setColor(getDefaultColor());

			canvas.drawRect(getPx(), getPy(), getAllWidth(), getAllHeight(), p);

		}
	}

	public void show() {
		setSize(GameConfig.getConfig().getWindowWidth(), 10);
		setEnabled(true);
		setVisible(true); 
	}
}
