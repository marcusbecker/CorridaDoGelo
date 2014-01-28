package br.com.mvbos.jega.element;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import br.com.mvbos.jega.engine.Engine;

/**
 * 
 * @author Marcus Becker
 * 
 */
public class ElementModel {

	public static final String EMPTY = "";
	private int id;
	private float pX;
	private float pY;
	private int width;
	private int height;
	private String name = EMPTY;
	private boolean visible = true;
	private boolean enabled = true;
	private boolean destroyed = false;
	private Bitmap image;
	private String imageURL;
	private int defaultColor = Color.BLUE;

	public void loadElement() {
		// BitmapFactory.decodeResource(getResources(),R.drawable.nave)
		/*
		 * if (imageURL != null && image == null) { image =
		 * ImageUtil.loadImage(imageURL); }
		 */
	}

	public ElementModel() {
	}

	public ElementModel(int width, int height, String name) {
		this.width = width;
		this.height = height;
		this.name = name;
	}

	public ElementModel(float positionX, float positionY, int width,
			int height, String name) {
		this.pX = positionX;
		this.pY = positionY;
		this.width = width;
		this.height = height;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPx() {
		return (int) pX;
	}

	public void setPx(float positionX) {
		this.pX = positionX;
	}

	public void incPx(float positionX) {
		this.pX += positionX;
	}

	public float getPy() {
		return pY;
	}

	public void setPy(float positionY) {
		this.pY = positionY;
	}

	public void incPy(float positionY) {
		this.pY += positionY;
	}

	public void setPxy(float x, float y) {
		this.pX = x;
		this.pY = y;
	}

	public void drawMe(Canvas canvas) {

		if (getImage() != null) {
			canvas.drawBitmap(getImage(), getPx(), getPy(), null);
		} else {
			Paint p = Engine.getPaint();

			if (Engine.isPaused()) {
				p.setColor(Color.RED);
			} else {
				p.setColor(getDefaultColor());
			}

			/*
			 * canvas.drawRect(getWidth(), getHeight(), getPositionX(),
			 * getPositionY(), p);
			 */

			canvas.drawCircle(getPx(), getPy(), getWidth(), p);
		}
	}

	public int getDefaultColor() {
		return defaultColor;
	}

	public void setDefaultColor(int defaultColor) {
		this.defaultColor = defaultColor;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void show(boolean s) {
		this.visible = s;
		this.enabled = s;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public void update() {
	}

	public float getAllWidth() {
		return getPx() + getWidth();
	}

	public float getAllHeight() {
		return getPy() + getHeight();
	}

	public float getAllSize() {
		return getPx() + getWidth() + getPy() + getHeight();
	}

	public float getCenterX() {
		return getPx() + (getWidth() / 2);
	}

	public float getCenterY() {
		return getPy() + (getHeight() / 2);
	}

	@Override
	public String toString() {
		return "ElementModel{" + "positionX=" + pX + ", positionY=" + pY
				+ ", width=" + width + ", height=" + height + ", name=" + name
				+ '}';
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ElementModel other = (ElementModel) obj;
		if (this.id != other.id) {
			return false;
		}
		if ((this.name == null) ? (other.name != null) : !this.name
				.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		return hash;
	}

	/*
	 * public Rectangle getBounds() { return new Rectangle((int) getPositionX(),
	 * (int) getPositionY(), getWidth(), getHeight()); }
	 */

	public void destroy() {
		visible = false;
		enabled = false;
		destroyed = true;
	}

	public void setSize(int w, int h) {
		setWidth(w);
		setHeight(h);
	}

	/**
	 * <p>
	 * Used to notification that this Element can be replaced.
	 * </p>
	 * <p>
	 * Usado para dizer que este Element pode ser substituido
	 * </p>
	 * 
	 * @return
	 */
	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public void setAll(int x, int y, int w, int h) {
		setSize(w, h);
		setPxy(x, y);
	}

	/**
	 * Os metodos hitX, hitY, hitWidth, hitHeight sao usado no detector de
	 * colisao para detectar colisao nos elementos independente do tamanho e
	 * posicao origianl
	 * 
	 * @return
	 */
	public float getHitX() {
		return getPx();
	}

	public float getHitY() {
		return getPy();
	}

	public int getHitWidth() {
		return getWidth();
	}

	public int getHitHeight() {
		return getHeight();
	}

	/**
	 * Define a largura e altura do elemento igual a largura e altura da imagem
	 */
	public void copyImgeSize() {
		if (getImage() != null) {
			setSize(getImage().getWidth(), getImage().getHeight());
		}
	}

	/**
	 * Define a imagem e seta os valores de largura e altura com base nas
	 * dimensoes da imagem
	 */
	public void setImageAndSize(Bitmap bitmap) {
		setImage(bitmap);
		copyImgeSize();
	}

}
