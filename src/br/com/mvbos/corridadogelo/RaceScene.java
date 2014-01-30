package br.com.mvbos.corridadogelo;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import br.com.mvbos.corridadogelo.el.BorderElement;
import br.com.mvbos.corridadogelo.el.ButtonElement;
import br.com.mvbos.corridadogelo.el.EndLineElement;
import br.com.mvbos.corridadogelo.el.IceCubeElement;
import br.com.mvbos.corridadogelo.el.RampElement;
import br.com.mvbos.corridadogelo.el.ScoreElement;
import br.com.mvbos.corridadogelo.el.TextElement;
import br.com.mvbos.jega.engine.GraphicTool;
import br.com.mvbos.jega.scene.Click;
import br.com.mvbos.jega.scene.impl.SceneDefault;
import br.com.mvbos.jega.window.GameConfig;
import br.com.mvbos.jega.window.impl.MemoryImpl;

public class RaceScene extends SceneDefault {

	public static final int VEL_PERCEPTION = 3;
	private final boolean ENABLE_CLICK = false;
	private final int RAMP_TO_END = 29;
	//
	private final float RAMP_JUMP = 1.5f;
	//
	private final float VEL_INC = 0.1f;
	private final float VEL_STOP = 0.05f;
	private final float MAX_VEL = 10f;
	private final float MIN_VEL = 0.2f;

	private float vel;
	private float initPy;
	private IceCubeElement ice;
	private RampElement ramp;
	private ButtonElement button;
	private boolean endRace = false;

	// float score;

	private Random random = new Random();
	private int rampCount;
	private int playerRamps;
	private float playerMaxVel;
	private float playerScore;

	@Override
	public void startScene() {
		memo = new MemoryImpl(30);
		ice = new IceCubeElement();
		ramp = new RampElement(this);

		ice.setSize(20, 20);
		ice.setDefaultColor(Color.BLUE);

		GraphicTool.g().centerWindow(ice);
		initPy = GameConfig.getConfig().getWindowHeight() - ice.getHeight()
				- 10;

		ice.setPy(initPy);

		// System.out.println("*ice py " + ice.getPy());

		memo.registerElement(ice);// 0
		memo.registerElement(ramp);// 1

		memo.registerElement(new BorderElement(this));// 2
		memo.registerElement(new BorderElement(this, true));// 3
		memo.registerElement(new TextElement(this));// 4
		memo.registerElement(new EndLineElement(this));// 5
		memo.registerElement(new ScoreElement());// 6

		// buttons
		// memo.registerElement(new ButtonElement(1, "PAUSA"));
		button = new ButtonElement(2, "REINICIAR");
		button.setVisible(false);
		GraphicTool.g().centerWindow(button);
		memo.registerElement(button);
		// memo.registerElement(new ButtonElement(3, "SAIR"));

		background = BitmapFactory.decodeResource(GameConfig.getConfig()
				.getResources(), R.drawable.fundo);

		System.out.println("*************** TELA *************");
		System.out.print(GameConfig.getConfig().getWindowWidth() + "x");
		System.out.println(GameConfig.getConfig().getWindowHeight());

		System.out.println("*** BACKGROUND ***");
		System.out.print(background.getWidth() + "x");
		System.out.println(background.getHeight());

	}

	boolean newRamp = true;
	private Bitmap background;

	@Override
	public void update() {

		playerLogic();

		// valida velocidade
		if (vel < 0f) {
			vel = 0f;

		} else if (vel > playerMaxVel) {
			playerMaxVel = vel;
		}

		for (int i = 0; i < memo.getElementCount(); i++) {
			memo.getByElement(i).update();
		}

		sceneLogic();
	}

	@Override
	public void drawElements(Canvas canvas) {
		canvas.drawBitmap(background, 0, 0, null);

		for (int i = memo.getElementCount() - 1; i >= 0; i--) {
			if (memo.getByElement(i).isVisible()) {
				memo.getByElement(i).drawMe(canvas);
			}
		}
	}

	private void playerLogic() {
		if (!endRace) {
			// TODO verificar tratamento no sensorChanged
			if (ice.getPy() >= initPy) {
				// jogador em posicao de desaceleracao
				if (vel > MIN_VEL) {
					vel -= VEL_INC;
				} else {
					vel = MIN_VEL;
				}

			} else if (vel > (MAX_VEL + 0.1f)) {
				// jogador acima da velocidade normal
				// vel -= VEL_INC;//vel -= 0.05f;
				vel -= 0.01f;

			} else if (vel < MAX_VEL) {
				// jogador moveu-se para cima, ganhando aceleracao
				vel += VEL_INC;
			}

			if (GraphicTool.g().collide(ice, ramp) != null) {
				vel += RAMP_JUMP;

				if (newRamp) {
					playerRamps++;
					newRamp = false;
				}

			} else if (GraphicTool.g().collide(ice, memo.getByElement(2)) != null
					|| GraphicTool.g().collide(ice, memo.getByElement(3)) != null) {

				vel -= RAMP_JUMP;
			}

			playerScore += vel;

		} else if (vel > 0f) {
			// corrida terminada, jogador para gradualmente
			vel -= VEL_STOP;
		}
	}

	private void sceneLogic() {
		if (rampCount == RAMP_TO_END) {
			if (!ramp.isEnabled()) {
				EndLineElement end = (EndLineElement) memo.getByElement(5);
				end.setSize(memo.getElementList()[2].getWidth() + 5, 10);
				end.show();

				if (end.getPy() > ice.getPy()) {
					ScoreElement scoreEl = (ScoreElement) memo.getByElement(6);
					scoreEl.configure((int) playerScore * (rampCount + 1),
							(int) playerMaxVel * VEL_PERCEPTION, playerRamps);

					endRace = true;
					button.setVisible(true);
				}

			}

		} else {
			if (!ramp.isEnabled()) {
				// rampas dinamicas
				int temp = random.nextInt(6) + 1;
				
				// mantem rampa longe da borda
				temp = temp * memo.getByElement(2).getWidth();
				ramp.reset(temp);

				rampCount++;
				newRamp = true;
			}
		}
	}

	@Override
	public void clickElement(Click m) {
		if (button.isVisible() && GraphicTool.g().intersec(m, button)) {
			GameConfig.getConfig().getWindowGame().changeScene(new RaceScene());
			return;
		}

		if (ENABLE_CLICK) {
			if (m.getPy() < ice.getPy()) {
				ice.setPy(initPy - 20);
			} else {
				ice.setPy(initPy);
			}

			ice.setPx(m.getPx());
		}
	}

	@Override
	public void sensorChanged(float axisX, float axisY, float axisZ) {
		if (endRace) {
			return;
		}

		int y = (int) axisY;

		// TODO verificar tratamento no playerLogic
		if (y < 0) {
			// Inclinacao para aceleracao
			if (ice.getPy() > getForwardLimit()) {
				ice.incPy(axisY);
			}
		} else if (y > 0) {
			if (ice.getAllHeight() < getBackwardLimit()) {
				ice.incPy(axisY);
			}
		}

		int x = (int) axisX * -2;

		if (x < 0 && (ice.getPx() + x) > 0) {
			ice.incPx(x);

		} else if (x > 0
				&& (ice.getAllWidth() < GameConfig.getConfig().getWindowWidth())) {
			ice.incPx(x);
		}
	}

	private int getForwardLimit() {
		return GameConfig.getConfig().getWindowHeight() - (ice.getHeight() * 2);
	}

	private int getBackwardLimit() {
		return GameConfig.getConfig().getWindowHeight();
	}

	public float getVel() {
		return vel;
	}

}
