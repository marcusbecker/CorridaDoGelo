package br.com.mvbos.corridadogelo.el;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import br.com.mvbos.corridadogelo.R;
import br.com.mvbos.jega.element.ElementModel;
import br.com.mvbos.jega.engine.Engine;
import br.com.mvbos.jega.window.GameConfig;

public class ScoreElement extends ElementModel {

	private int score;
	private int velMax;
	private int rampCount;
	private int incc = 45;

	public ScoreElement() {
		setDefaultColor(Color.BLACK);
		setEnabled(false);
		setVisible(false);
		setPxy(25, 75);
	}

	@Override
	public void loadElement() {
		setImage(BitmapFactory.decodeResource(GameConfig.getConfig()
				.getResources(), R.drawable.end_race));
	}

	@Override
	public void update() {
	}

	@Override
	public void drawMe(Canvas canvas) {
		Paint p = Engine.getPaint();
		p.setColor(getDefaultColor());
		p.setStyle(Style.FILL);
		p.setTextSize(15);

		if (getImage() != null) {
			canvas.drawBitmap(getImage(), 0, getPy(), null);
		} else {
			canvas.drawText("FINISH!", getPx(), getPy(), p);
		}

		canvas.drawText("VEL. MAXIMA " + velMax, getPx(), getPy() + incc * 3, p);
		canvas.drawText("RAMPAS " + rampCount, getPx(), getPy() + incc * 4, p);
		canvas.drawText("TOTAL DE PONTOS " + score, getPx(), getPy() + incc * 2, p);
	}

	public void configure(int score, int velMax, int rampCount) {
		this.score = score;
		this.velMax = velMax;
		this.rampCount = rampCount;

		setVisible(true);
	}
}
