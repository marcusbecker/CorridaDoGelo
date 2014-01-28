package br.com.mvbos.jega.window;

import br.com.mvbos.jega.element.ElementMovableModel;
import br.com.mvbos.jega.scene.IScene;

public interface IWindowGame {

	public void changeScene(IScene scene);

	public void freeze(boolean b, int option);

	public void startGame();

	public void resumeGame();

	public void updateGame();

	public void drawGame();
	
	public void engineNotification(int id);

	@Deprecated
	public int getWindowWidth();

	@Deprecated
	public int getWindowHeight();

	@Deprecated
	public void selectMovableElement(ElementMovableModel e);

	@Deprecated
	public ElementMovableModel getMovableElement();

}
