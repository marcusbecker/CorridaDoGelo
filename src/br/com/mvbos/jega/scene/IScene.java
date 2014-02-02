package br.com.mvbos.jega.scene;

import android.graphics.Canvas;
import br.com.mvbos.jega.element.ElementModel;
import br.com.mvbos.jega.element.ElementMovableModel;
import br.com.mvbos.jega.element.IButtonElement;
import br.com.mvbos.jega.window.IMemory;

/**
 * 
 * @author Marcus Becker
 */
public interface IScene {

	public void update();

	public void changeSceneEvent();

	public void selectElement(ElementModel e);

	public void focusElement(ElementModel e);

	public void releaseElement(ElementModel element, ElementModel anotherElement);

	public void closeWindow();

	/**
	 * Chamado ao iniciar load
	 */
	public void startScene();

	public IMemory getElements();

	public void clickElement(int clickCount);

	public void clickElement(Click m);

	public void selectElement(ElementModel[] arr);

	public void mouseMove(ElementModel e, Click m);

	public void setTitle(String title);

	public String getTitle();

	public void releaseElement(ElementModel element);

	public void drawElements(Canvas canvas);

	public void clickButton(IButtonElement button);

	@Deprecated
	public void moveElement(ElementMovableModel selectedMovableElement);

	@Deprecated
	public void reflashElementPosition(ElementMovableModel e);

	public void sensorChanged(float axisX, float axisY, float axisZ);

	/**
	 * Chamado ao finalizar load
	 */
	public void startGame();

}
