package br.com.mvbos.corridadogelo.el;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import br.com.mvbos.corridadogelo.R;
import br.com.mvbos.jega.element.ElementModel;
import br.com.mvbos.jega.engine.Engine;
import br.com.mvbos.jega.window.GameConfig;

public class IceCubeElement extends ElementModel {

	@Override
	public void loadElement() {
		setImageAndSize(BitmapFactory.decodeResource(GameConfig.getConfig()
				.getResources(), R.drawable.gelo));
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
}
