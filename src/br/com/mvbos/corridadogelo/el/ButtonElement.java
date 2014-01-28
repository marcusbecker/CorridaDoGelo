package br.com.mvbos.corridadogelo.el;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import br.com.mvbos.jega.element.ElementModel;
import br.com.mvbos.jega.element.IButtonElement;
import br.com.mvbos.jega.engine.Engine;

public class ButtonElement extends ElementModel implements IButtonElement {

	private static final int TEXT_SIZE = 25;
	private String text;

	public ButtonElement(int id, String text) {
		super.setId(id);
		super.setDefaultColor(Color.LTGRAY);
		super.setSize(60, 20);
		this.text = text;
	}

	@Override
	public void update() {
	}

	@Override
	public void drawMe(Canvas canvas) {
		Paint p = Engine.getPaint();
		p.setColor(getDefaultColor());
		p.setStyle(Style.FILL);
		p.setTextSize(TEXT_SIZE);

		canvas.drawRect(getPx(), getPy(), getAllWidth(), getAllHeight(), p);

		canvas.drawText(text, getPx() - TEXT_SIZE, getPy() + TEXT_SIZE, p);
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPress() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRelease() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoseFocus() {
		// TODO Auto-generated method stub

	}
}
