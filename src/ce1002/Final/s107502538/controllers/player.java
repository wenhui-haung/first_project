package ce1002.Final.s107502538.controllers;

public class player extends role{
	private int layoutX;
	private int layoutY;
	private int fitX;
	private int fitY;
	public player(int hp, int atk){
		super(hp, atk);
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
}
