package br.com.mvbos.jega.engine;

import android.os.AsyncTask;
import br.com.mvbos.jega.window.IWindowGame;

public class GameEngineModel {

	private IWindowGame wg;

	private AsyncTask<IWindowGame, Void, Void> gameThread;

	private final int id;

	private int fps;

	public GameEngineModel(int engineId) {
		this.id = engineId;
	}

	public void fill(IWindowGame wg, int framePerSeconds, int updatePeerSeconds) {
		this.wg = wg;
		this.fps = framePerSeconds;

		gameThread = new AsyncTask<IWindowGame, Void, Void>() {

			long nextUpdate = 0;

			@Override
			protected Void doInBackground(IWindowGame... params) {
				IWindowGame wg = params[0];
				Engine.log("enginit: " + Engine.gameLoop);

				try {
					long maxTime = 0;
					int skipDraw = 0;
					while (Engine.gameLoop) {

						if (nextUpdate <= getMillis()) {
							wg.engineNotification(id);

							long beforeTime = getMillis();

							if (skipDraw >= fps) {
								wg.updateGame();

								Engine.log("Draw skiped: " + skipDraw);
								skipDraw -= fps;

							} else {
								wg.updateGame();

								// Engine.log("Time to update: " + (getMillis()
								// - beforeTime));

								// long temp = getMillis();
								wg.drawGame();
								onProgressUpdate();

								// Engine.log("Time to draw: " + (getMillis() -
								// temp));
							}

							long res = getMillis() - beforeTime;

							if (res <= fps) {
								nextUpdate = getMillis() + (fps - res);
							} else {
								skipDraw += res - fps;
								maxTime = res;
								Engine.log("Time to process: " + maxTime);

								nextUpdate = getMillis() + fps;
							}

							/*
							 * Engine.log("FPS " + (lastUpdate - System
							 * .currentTimeMillis()));
							 */

							// Engine.log("Time process: " + maxTime);
						}

					}

					Engine.log("Max time process: " + maxTime);

				} catch (Exception e) {
					e.printStackTrace();
					Engine.log("Game loop abort " + Engine.gameLoop);
				}
				return null;
			}

			private long getMillis() {
				return System.currentTimeMillis();
			}

		};
	}

	public boolean start() {
		Engine.gameLoop = true;
		gameThread.execute(wg);
		return true;
	}

	public boolean pause() {
		Engine.pauseGame(true);
		return true;
	}

	public boolean resume() {
		Engine.pauseGame(false);
		return true;
	}

	public boolean stop() {
		gameThread.cancel(true);
		Engine.gameLoop = false;
		return true;
	}
}
