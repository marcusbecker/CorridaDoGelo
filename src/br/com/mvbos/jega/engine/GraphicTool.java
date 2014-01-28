package br.com.mvbos.jega.engine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import br.com.mvbos.jega.element.ElementModel;
import br.com.mvbos.jega.scene.Click;
import br.com.mvbos.jega.window.GameConfig;

public class GraphicTool {

	private static GraphicTool g;

	public static GraphicTool g() {
		if (g == null) {
			g = new GraphicTool();
		}

		return g;
	}


	public void centerWindow(ElementModel el) {
		el.setPx(GameConfig.getConfig().getWindowWidth() / 2 - el.getWidth() / 2);
		el.setPy(GameConfig.getConfig().getWindowHeight() / 2 - el.getHeight() / 2);
	}

	public void drawGrid(Canvas canvas, int w, int h) {
		Paint p = Engine.getPaint();

		p.setColor(Color.GRAY);
		for (int i = 10; i < h; i += 25) {
			canvas.drawLine(0, i, w, i, p);
		}
		for (int i = 10; i < w; i += 25) {
			canvas.drawLine(i, 0, i, h, p);
		}

		p.setColor(Color.BLACK);
		final int mw = Math.round(((float) w) / 2);
		final int my = Math.round(((float) h) / 2);
		canvas.drawLine(0, my, w, my, p);
		canvas.drawLine(mw, 0, mw, h, p);

	}

	/**
	 * Verifica se dois elementos com enabled = true colidem
	 * 
	 * @param e
	 * @param em
	 * @return
	 */
	public ElementModel collide(ElementModel e, ElementModel em) {
		if (e == null || em == null || e == em) {
			return e;
		}
		if (!e.isEnabled() || !em.isEnabled()) {
			return null;
		}
		if (intersecElement(e, em)) {
			return em;
		}

		return null;
	}

	//TODO efetuar testes
	public boolean intersecElement(float ax, float ay, float bx, float by,
			int aw, int ah, int bw, int bh) {
		if (ax + aw >= bx && ax <= bx + bw && ay + ah >= by && ay <= by + bh) {
			return true;
		}

		return false;
	}

	/**
	 * Verifica se dois elementos colidem
	 * 
	 * @param eA
	 * @param eB
	 * @return
	 */

	public boolean intersecElement(ElementModel eA, ElementModel eB) {
		final int pwA = (int) eA.getHitX() + eA.getHitWidth();
		final int pwB = (int) eB.getHitX() + eB.getHitWidth();
		final int phA = (int) eA.getHitY() + eA.getHitHeight();
		final int phB = (int) eB.getHitY() + eB.getHitHeight();

		if (pwA >= eB.getHitX() && eA.getHitX() <= pwB && phA >= eB.getHitY()
				&& eA.getHitY() <= phB) {
			return true;
		}

		return false;
	}

	/**
	 * Verifica se o elemento localiza-se nos pontos passados
	 * 
	 * @param elementAX
	 * @param elementAY
	 * @param e
	 * @return
	 */

	public boolean intersec(int x, int y, ElementModel e) {
		return intersecElement(x, y, e.getHitX(), e.getHitY(), 1, 1, e.getHitWidth(),
				e.getHitHeight());
		/*
		 * if ((elementAX >= e.getHitX() && elementAX <= (e.getHitX() +
		 * e.getHitWidth())) && (elementAY >= e.getHitY() && elementAY <= (e.getHitY()
		 * + e .getHitHeight()))) { return true; }
		 * 
		 * return false;
		 */
	}

	/**
	 * Verifica se o elemento localiza-se nos pontos do click
	 * 
	 * @param elementAX
	 * @param elementAY
	 * @param e
	 * @return
	 */

	public boolean intersec(Click c, ElementModel e) {
		return intersecElement(c.getPx(), c.getPy(), e.getHitX(), e.getHitY(), 1,
				1, e.getHitWidth(), e.getHitHeight());

	}

}
