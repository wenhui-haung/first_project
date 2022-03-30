package ce1002.Final.s107502538.controllers;

public class role {
	private int _hp;
	private int _maxhp;
	private int _atk;
	role(int hp, int atk){
		_hp = hp;
		_maxhp = hp;
		_atk = atk;
	}
	public void sethp(int hp) {
		_hp = hp;
	}
	public void setmaxhp(int maxhp) {
		_maxhp = maxhp;
	}
	public void setatk(int atk) {
		_atk = atk;
	}
	public int gethp() {
		return _hp;
	}
	public int getmaxhp() {
		return _maxhp;
	}
	public int getatk() {
		return _atk;
	}
}
