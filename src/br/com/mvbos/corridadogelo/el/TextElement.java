package br.com.mvbos.corridadogelo.el;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import br.com.mvbos.corridadogelo.RaceScene;
import br.com.mvbos.jega.element.ElementModel;
import br.com.mvbos.jega.engine.Engine;
import br.com.mvbos.jega.window.GameConfig;

public class TextElement extends ElementModel {

	private RaceScene scene;

	public TextElement(RaceScene scene) {
		// #3399CC
		setDefaultColor(Color.rgb(51, 153, 204));
		this.scene = scene;
	}

	@Override
	public void update() {
	}

	@Override
	public void drawMe(Canvas canvas) {
		Paint p = Engine.getPaint();
		p.setColor(getDefaultColor());
		p.setStyle(Style.FILL);
		p.setTextSize(25);

		int km = (int) (scene.getVel()) * RaceScene.VEL_PERCEPTION;
		canvas.drawText(km + "Km",
				GameConfig.getConfig().getWindowWidth() - 80, GameConfig
						.getConfig().getWindowHeight() - 5, p);
	}
}
