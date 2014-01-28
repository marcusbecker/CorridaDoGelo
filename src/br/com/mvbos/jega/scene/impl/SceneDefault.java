package br.com.mvbos.jega.scene.impl;

import java.util.Random;

import android.graphics.Canvas;
import br.com.mvbos.jega.element.ElementModel;
import br.com.mvbos.jega.element.ElementMovableModel;
import br.com.mvbos.jega.element.IButtonElement;
import br.com.mvbos.jega.scene.Click;
import br.com.mvbos.jega.scene.IScene;
import br.com.mvbos.jega.window.GameConfig;
import br.com.mvbos.jega.window.IMemory;
import br.com.mvbos.jega.window.impl.MemoryImpl;

public class SceneDefault implements IScene {

	protected IMemory memo;

	@Override
	public void update() {
		for (int i = 0; i < memo.getElementCount(); i++) {
			memo.getByElement(i).update();
		}

	}

	@Override
	public void changeSceneEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectElement(ElementModel e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusElement(ElementModel e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void releaseElement(ElementModel element, ElementModel anotherElement) {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeWindow() {
		// TODO Auto-generated method stub

	}

	@Override
	public void startScene() {
		memo = new MemoryImpl(30);

		for (int i = 0; i < memo.getCapacity(); i++) {
			int x = new Random().nextInt(GameConfig.getConfig().getWindowWidth());
			int y = new Random().nextInt(GameConfig.getConfig().getWindowHeight());
			ElementModel e = new ElementModel(x, y, 10, 11, "" + i);

			memo.registerElement(e);
		}
	}

	@Override
	public IMemory getElements() {
		return memo;
	}

	@Override
	public void clickElement(int clickCount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clickElement(Click m) {
		clickElement(0);
	}

	@Override
	public void selectElement(ElementModel[] arr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMove(ElementModel e, Click m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void releaseElement(ElementModel element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawElements(Canvas canvas) {
		for (int i = 0; i < memo.getElementCount(); i++) {
			if (memo.getByElement(i).isVisible()) {
				memo.getByElement(i).drawMe(canvas);
			}
		}
	}

	@Override
	public void clickButton(IButtonElement button) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveElement(ElementMovableModel selectedMovableElement) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reflashElementPosition(ElementMovableModel e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sensorChanged(float axisX, float axisY, float axisZ) {
		// TODO Auto-generated method stub

	}

}
