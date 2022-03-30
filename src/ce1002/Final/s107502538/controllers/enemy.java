package ce1002.Final.s107502538.controllers;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class enemy extends role{
	private int layoutX;
	private int layoutY;
	private int fitX;
	private int fitY;
	private ImageView imageview;
	public enemy(int hp, int atk, int x, int y, int fx, int fy, ImageView _enemy){
		super(hp, atk);
		layoutX = x;
		layoutY = y;
		fitX = fx;
		fitY = fy;
		imageview = _enemy;
		File file = new File("C:/Users/Vivo Book Pro/Desktop/ce1002.Final.s107502538/src/ce1002/Final/s107502538/image/enemy1.png");
		Image image = new Image(file.toURI().toString());
		_enemy.setImage(image);
		_enemy.setFitWidth(fx);
		_enemy.setFitHeight(fy);
		_enemy.setLayoutY(x);
		_enemy.setLayoutX(y);
	}
	public int getx() {
		return layoutX;
	}
	public int gety() {
		return layoutY;
	}
	public int getfx() {
		return fitX;
	}
	public int getfy() {
		return fitY;
	}
	public ImageView getimage() {
		return imageview;
	}
}
