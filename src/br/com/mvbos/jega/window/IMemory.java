/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mvbos.jega.window;

import br.com.mvbos.jega.element.ElementModel;

/**
 * 
 * @author MBecker
 */
public interface IMemory {

	public ElementModel findElementById(int id);

	public ElementModel findElementByName(String name);

	public void registerElement(ElementModel e);

	public void unRegisterElement(ElementModel e);

	public void setBackGrountElement(ElementModel e);

	public ElementModel getBackground();

	public ElementModel[] getElementList();

	public ElementModel getByElement(int i);

	public int getElementCount();

	public void clear();
	
	public int getCapacity();
}
