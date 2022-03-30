package ce1002.Final.s107502538.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;

import ce1002.Final.s107502538.Final;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Boss implements Initializable {
	double G = 9.8;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		maincontroller.level++;
		enemy boss = new enemy(1000 * ((maincontroller.level+2) / 3), 50 * ((maincontroller.level+2) / 3), 400, 391, 238, 186, new ImageView());
		_showblood.setText(maincontroller.newplayer.gethp() + "/" + maincontroller.newplayer.getmaxhp());
		_blood.setWidth(maincontroller.newplayer.gethp() * 100 / maincontroller.newplayer.getmaxhp());
		_showlayer.setText("Level " + ((maincontroller.level+2) / 3));
		_showlevel.setVisible(true);
		_showlayer.setVisible(true);
		_warning1.setVisible(false);
		_warning2.setVisible(false);
		_warning3.setVisible(false);
		_warning4.setVisible(false);
		_warning5.setVisible(false);
		_warning6.setVisible(false);
		_talkingbox.setVisible(false);
		_kotoba.setVisible(false);
		_role.setLayoutX(129);
		_role.setLayoutY(424);
		_choose.setVisible(false);
		_buff1.setVisible(false);
		_buff1.setDisable(true);
		_buff2.setVisible(false);
		_buff2.setDisable(true);
		_buff3.setVisible(false);
		_buff3.setDisable(true);
		KeyFrame frame = new KeyFrame(Duration.millis(1000 / 60), (e) -> {
			if(!showlevel) {
				if(showlevelcounter > 120 && showlevelcounter <= 170) {
					if(showlevelcounter % 5 == 0) {
						_showlevel.setOpacity(_showlevel.getOpacity() - 0.1);
						_showlayer.setOpacity(_showlayer.getOpacity() - 0.1);
					}
				}
				showlevelcounter++;
				if(showlevelcounter == 180) {
					_showlevel.setVisible(false);
					_showlayer.setVisible(false);
					showlevelcounter = 0;
					showlevel = true;
				}
			}
//--------------------------------------------
			ArrayList<ImageView> tRightBullets = new ArrayList<ImageView>(_rightbullets);
			ArrayList<ImageView> tLeftBullets = new ArrayList<ImageView>(_leftbullets);
			for (int i = 0; i < tRightBullets.size(); i++) {
				tRightBullets.get(i).setCache(true);
				tRightBullets.get(i).setLayoutX(tRightBullets.get(i).getLayoutX() + 11);
				tRightBullets.get(i).setVisible(true);
				if (tRightBullets.get(i).getLayoutX() > _background.getWidth() + 30
						|| tRightBullets.get(i).getLayoutX() < -5 || roledamageright) {
					roledamageright = false;
					_rightbullets.remove(tRightBullets.get(i));
					_background.getChildren().remove(tRightBullets.get(i));
				}
			}
			for (int i = 0; i < tLeftBullets.size(); i++) {
				tLeftBullets.get(i).setCache(true);
				tLeftBullets.get(i).setLayoutX(tLeftBullets.get(i).getLayoutX() - 11);
				tLeftBullets.get(i).setVisible(true);
				if (tLeftBullets.get(i).getLayoutX() > _background.getWidth() + 30 || tLeftBullets.get(i)
						.getLayoutX() < -5 || roledamageleft) {
					roledamageleft = false;
					_leftbullets.remove(tLeftBullets.get(i));
					_background.getChildren().remove(tLeftBullets.get(i));
				}
			}
//------------------------------------------------
			if (attackready && CurrentLoctionX < _boss.getLayoutX() + _boss.getFitWidth() / 2) {
				_leftweapon.setCache(true);
				if(weaponcounter == 0) {
					MediaPlayer weaponplayer;
					Media weapon = new Media(getClass().getResource("../bgm/bossweapon.wav").toString());
					weaponplayer = new MediaPlayer(weapon);
					weaponplayer.play();
				}
				if (weaponcounter < 30) {
					_leftweapon.setLayoutX(_leftweapon.getLayoutX() + changingX
							* Math.abs(_boss.getLayoutX() + _boss.getFitWidth() / 2 - CurrentLoctionX) / 35);
					_leftweapon
							.setRotate(_leftweapon.getRotate() - 0.01275 * G * Math.pow(30 - weaponcounter - 1, -0.5));
					_leftweapon
							.setLayoutY(_leftweapon.getLayoutY() - 0.017 * G * Math.pow(30 - weaponcounter - 1, 1.5));
				}
				weaponcounter++;
				if (weaponcounter >= 30) {
					if (weaponcounter == 30) {
						_leftweapon.setRotate(-90);
					}
					if (weaponcounter <= 39) {
						_leftweapon.setRotate(_leftweapon.getRotate() - 9);
					}
					_leftweapon.setLayoutY(_leftweapon.getLayoutY() + 0.017 * G * Math.pow(weaponcounter - 30, 1.5));
				}
				if (_leftweapon.getLayoutY() > 588 || _leftweapon.getLayoutX() < -15
						|| _leftweapon.getLayoutX() > 995) {
					weaponcounter = 0;
					attackready = false;
				}
			}
			if (attackready && CurrentLoctionX > _boss.getLayoutX() + _boss.getFitWidth() / 2) {
				_rightweapon.setCache(true);
				if(weaponcounter == 0) {
					MediaPlayer weaponplayer;
					Media weapon = new Media(getClass().getResource("../bgm/bosssweapon.wav").toString());
					weaponplayer = new MediaPlayer(weapon);
					weaponplayer.play();
				}
				if (weaponcounter < 30) {
					_rightweapon.setLayoutX(_rightweapon.getLayoutX() + changingX
							* Math.abs(_boss.getLayoutX() + _boss.getFitWidth() / 2 - CurrentLoctionX) / 35);
					_rightweapon
							.setRotate(_rightweapon.getRotate() + 0.01275 * G * Math.pow(30 - weaponcounter - 1, -0.5));
					_rightweapon
							.setLayoutY(_rightweapon.getLayoutY() - 0.017 * G * Math.pow(30 - weaponcounter - 1, 1.5));
				}
				weaponcounter++;
				if (weaponcounter >= 30) {
					if (weaponcounter == 30) {
						_rightweapon.setRotate(90);
					}
					if (weaponcounter <= 39) {
						_rightweapon.setRotate(_rightweapon.getRotate() + 9);
					}
					_rightweapon.setLayoutY(_rightweapon.getLayoutY() + 0.017 * G * Math.pow(weaponcounter - 30, 1.5));
				}
				if (_rightweapon.getLayoutY() > 588 || _rightweapon.getLayoutX() < -15
						|| _rightweapon.getLayoutX() > 995) {
					weaponcounter = 0;
					attackready = false;
				}
			}
			if (skillready) {
				if (LeftMissAt == 1) {
					if (Skill1LeftCounter >= 5 && Skill1LeftCounter < 60) {
						_talkingbox.setVisible(true);
						_kotoba.setText("看我的地刺攻擊！！");
						_kotoba.setVisible(true);
					}
					if (Skill1LeftCounter <= 59) {
						if (Skill1LeftCounter % 10 <= 4) {
							_warning2.setVisible(true);
							_warning3.setVisible(true);
						} else {
							_warning2.setVisible(false);
							_warning3.setVisible(false);
						}
					}
					Skill1LeftCounter++;
					if (Skill1LeftCounter == 60) {
						_warning2.setVisible(true);
						_warning3.setVisible(true);
					}
					if (Skill1LeftCounter > 60 && Skill1LeftCounter <= 70) {
						_weapon2.setLayoutY(_weapon2.getLayoutY() - 10);
						_weapon3.setLayoutY(_weapon3.getLayoutY() - 10);
					}
					if (Skill1LeftCounter > 100 && Skill1LeftCounter <= 110) {
						_weapon2.setLayoutY(_weapon2.getLayoutY() + 10);
						_weapon3.setLayoutY(_weapon3.getLayoutY() + 10);
					}
					if (Skill1LeftCounter == 111) {
						_talkingbox.setVisible(false);
						_kotoba.setVisible(false);
						_warning2.setVisible(false);
						_warning3.setVisible(false);
						Skill1LeftCounter = 0;
						skillready = false;
					}
				} else if (LeftMissAt == 2) {
					if (Skill1LeftCounter >= 5 && Skill1LeftCounter < 60) {
						_talkingbox.setVisible(true);
						_kotoba.setText("看我的地刺攻擊！！");
						_kotoba.setVisible(true);
					}
					if (Skill1LeftCounter <= 59) {
						if (Skill1LeftCounter % 10 <= 4) {
							_warning1.setVisible(true);
							_warning3.setVisible(true);
						} else {
							_warning1.setVisible(false);
							_warning3.setVisible(false);
						}
					}
					Skill1LeftCounter++;
					if (Skill1LeftCounter == 60) {
						_warning1.setVisible(true);
						_warning3.setVisible(true);
					}
					if (Skill1LeftCounter > 60 && Skill1LeftCounter <= 70) {
						_weapon1.setLayoutY(_weapon1.getLayoutY() - 10);
						_weapon3.setLayoutY(_weapon3.getLayoutY() - 10);
					}			
					if (Skill1LeftCounter > 100 && Skill1LeftCounter <= 110) {
						_weapon1.setLayoutY(_weapon1.getLayoutY() + 10);
						_weapon3.setLayoutY(_weapon3.getLayoutY() + 10);
					}
					if (Skill1LeftCounter == 111) {
						_talkingbox.setVisible(false);
						_kotoba.setVisible(false);
						_warning1.setVisible(false);
						_warning3.setVisible(false);
						Skill1LeftCounter = 0;
						skillready = false;
					}
				} else if (LeftMissAt == 3) {
					if (Skill1LeftCounter >= 5 && Skill1LeftCounter < 60) {
						_talkingbox.setVisible(true);
						_kotoba.setText("看我的地刺攻擊！！");
						_kotoba.setVisible(true);
					}
					if (Skill1LeftCounter <= 59) {
						if (Skill1LeftCounter % 10 <= 4) {
							_warning2.setVisible(true);
							_warning1.setVisible(true);
						} else {
							_warning2.setVisible(false);
							_warning1.setVisible(false);
						}
					}
					Skill1LeftCounter++;
					if (Skill1LeftCounter == 60) {
						_warning2.setVisible(true);
						_warning1.setVisible(true);
					}
					if (Skill1LeftCounter > 60 && Skill1LeftCounter <= 70) {
						_weapon2.setLayoutY(_weapon2.getLayoutY() - 10);
						_weapon1.setLayoutY(_weapon1.getLayoutY() - 10);
					}
					if (Skill1LeftCounter > 100 && Skill1LeftCounter <= 110) {
						_weapon2.setLayoutY(_weapon2.getLayoutY() + 10);
						_weapon1.setLayoutY(_weapon1.getLayoutY() + 10);
					}
					if (Skill1LeftCounter == 111) {
						_talkingbox.setVisible(false);
						_kotoba.setVisible(false);
						_warning2.setVisible(false);
						_warning1.setVisible(false);
						Skill1LeftCounter = 0;
						skillready = false;
					}
				}
				if (RightMissAt == 1) {
					if (Skill1RightCounter >= 5 && Skill1RightCounter < 60) {
						_talkingbox.setVisible(true);
						_kotoba.setText("看我的地刺攻擊！！");
						_kotoba.setVisible(true);
					}
					if (Skill1RightCounter <= 59) {
						if (Skill1RightCounter % 10 <= 4) {
							_warning5.setVisible(true);
							_warning6.setVisible(true);
						} else {
							_warning5.setVisible(false);
							_warning6.setVisible(false);
						}
					}
					Skill1RightCounter++;
					if (Skill1RightCounter == 60) {
						_warning5.setVisible(true);
						_warning6.setVisible(true);
					}
					if (Skill1RightCounter > 60 && Skill1RightCounter <= 70) {
						_weapon5.setLayoutY(_weapon5.getLayoutY() - 10);
						_weapon6.setLayoutY(_weapon6.getLayoutY() - 10);
					}
					if (Skill1RightCounter > 100 && Skill1RightCounter <= 110) {
						_weapon5.setLayoutY(_weapon5.getLayoutY() + 10);
						_weapon6.setLayoutY(_weapon6.getLayoutY() + 10);
					}
					if (Skill1RightCounter == 111) {
						_talkingbox.setVisible(false);
						_kotoba.setVisible(false);
						_warning5.setVisible(false);
						_warning6.setVisible(false);
						Skill1RightCounter = 0;
						skillready = false;
					}
				} else if (RightMissAt == 2) {
					if (Skill1RightCounter >= 5 && Skill1RightCounter < 60) {
						_talkingbox.setVisible(true);
						_kotoba.setText("看我的地刺攻擊！！");
						_kotoba.setVisible(true);
					}
					if (Skill1RightCounter <= 59) {
						if (Skill1RightCounter % 10 <= 4) {
							_warning4.setVisible(true);
							_warning6.setVisible(true);
						} else {
							_warning4.setVisible(false);
							_warning6.setVisible(false);
						}
					}
					Skill1RightCounter++;
					if (Skill1RightCounter == 60) {
						_warning4.setVisible(true);
						_warning6.setVisible(true);
					}
					if (Skill1RightCounter > 60 && Skill1RightCounter <= 70) {
						_weapon4.setLayoutY(_weapon4.getLayoutY() - 10);
						_weapon6.setLayoutY(_weapon6.getLayoutY() - 10);
					}
					if (Skill1RightCounter > 100 && Skill1RightCounter <= 110) {
						_weapon4.setLayoutY(_weapon4.getLayoutY() + 10);
						_weapon6.setLayoutY(_weapon6.getLayoutY() + 10);
					}
					if (Skill1RightCounter == 111) {
						_talkingbox.setVisible(false);
						_kotoba.setVisible(false);
						_warning4.setVisible(false);
						_warning6.setVisible(false);
						Skill1RightCounter = 0;
						skillready = false;
					}
				} else if (RightMissAt == 3) {
					if (Skill1RightCounter >= 5 && Skill1RightCounter < 60) {
						_talkingbox.setVisible(true);
						_kotoba.setText("看我的地刺攻擊！！");
						_kotoba.setVisible(true);
					}
					if (Skill1RightCounter <= 59) {
						if (Skill1RightCounter % 10 <= 4) {
							_warning5.setVisible(true);
							_warning4.setVisible(true);
						} else {
							_warning5.setVisible(false);
							_warning4.setVisible(false);
						}
					}
					Skill1RightCounter++;
					if (Skill1RightCounter == 60) {
						_warning5.setVisible(true);
						_warning4.setVisible(true);
					}
					if (Skill1RightCounter > 60 && Skill1RightCounter <= 70) {
						_weapon5.setLayoutY(_weapon5.getLayoutY() - 10);
						_weapon4.setLayoutY(_weapon4.getLayoutY() - 10);
					}
					if (Skill1RightCounter > 100 && Skill1RightCounter <= 110) {
						_weapon5.setLayoutY(_weapon5.getLayoutY() + 10);
						_weapon4.setLayoutY(_weapon4.getLayoutY() + 10);
					}
					if (Skill1RightCounter == 111) {
						_talkingbox.setVisible(false);
						_kotoba.setVisible(false);
						_warning5.setVisible(false);
						_warning4.setVisible(false);
						Skill1RightCounter = 0;
						skillready = false;
					}
				}
			}
			if (jumpready) {
				if (BossJumpCounter == 0) {
					_talkingbox.setVisible(true);
					_kotoba.setText("看我的震地衝擊！！");
					_kotoba.setVisible(true);
				}
				BossJumpCounter++;
				if (BossJumpCounter == 60) {
					_talkingbox.setVisible(false);
					_kotoba.setVisible(false);
				}
				if (BossJumpCounter > 60 && BossJumpCounter <= 90) {
					_boss.setLayoutY(_boss.getLayoutY() - 0.033 * G * Math.pow(91 - BossJumpCounter, 1.5));
					_bossbloodback
							.setLayoutY(_bossbloodback.getLayoutY() - 0.033 * G * Math.pow(91 - BossJumpCounter, 1.5));
					_bossblood.setLayoutY(_bossblood.getLayoutY() - 0.033 * G * Math.pow(91 - BossJumpCounter, 1.5));
				}
				if (BossJumpCounter > 150 && BossJumpCounter <= 180) {
					_boss.setLayoutY(_boss.getLayoutY() + 0.033 * G * Math.pow(BossJumpCounter - 150, 1.5));
					_bossbloodback
							.setLayoutY(_bossbloodback.getLayoutY() + 0.033 * G * Math.pow(BossJumpCounter - 150, 1.5));
					_bossblood.setLayoutY(_bossblood.getLayoutY() + 0.033 * G * Math.pow(BossJumpCounter - 150, 1.5));
				}
				if (BossJumpCounter > 180 && BossJumpCounter <= 240) {
					if (BossJumpCounter == 185) {
						MediaPlayer explodeplayer;
						Media explode = new Media(getClass().getResource("../bgm/explode.wav").toString());
						explodeplayer = new MediaPlayer(explode);
						explodeplayer.play();
						if (_role.getLayoutY() == 424) {
							maincontroller.newplayer.sethp(maincontroller.newplayer.gethp() - boss.getatk() * 2);
							_showblood.setText(maincontroller.newplayer.gethp() + "/" + maincontroller.newplayer.getmaxhp());
							_blood.setWidth(maincontroller.newplayer.gethp() * 100 / maincontroller.newplayer.getmaxhp());
						}
					}
					if (BossJumpCounter % 10 <= 4) {
						_background.setLayoutX(_background.getLayoutX() + 5);
						_background.setLayoutY(_background.getLayoutY() + 5);
					}
					if (BossJumpCounter % 10 >= 5) {
						_background.setLayoutX(_background.getLayoutX() - 5);
						_background.setLayoutY(_background.getLayoutY() - 5);
					}
					if(BossJumpCounter == 240) {
						_background.setLayoutX(0);
						_background.setLayoutY(0);
						BossJumpCounter = 0;
						jumpready = false;
					}
				}
			}
//------------------------------------------------------		
			for (int i = 0; i < _rightbullets.size(); i++) {
				if (_rightbullets.get(i).getLayoutX() + _rightbullets.get(i).getFitWidth() > _boss.getLayoutX()
						&& _rightbullets.get(i).getLayoutX() < _boss.getLayoutX() + _boss.getFitWidth()
						&& _rightbullets.get(i).getLayoutY() < _boss.getLayoutY() + _boss.getFitHeight()
						&& _rightbullets.get(i).getLayoutY() + _rightbullets.get(i).getFitHeight() > _boss
								.getLayoutY()) {
					roledamageright = true;
					MediaPlayer roleatkplayer;
					Media roleatk = new Media(getClass().getResource("../bgm/collesion.wav").toString());
					roleatkplayer = new MediaPlayer(roleatk);
					roleatkplayer.play();
				}
			}
			for (int i = 0; i < _leftbullets.size(); i++) {
				if (_leftbullets.get(i).getLayoutX() + _leftbullets.get(i).getFitWidth() > _boss.getLayoutX()
						&& _leftbullets.get(i).getLayoutX() < _boss.getLayoutX() + _boss.getFitWidth()
						&& _leftbullets.get(i).getLayoutY() < _boss.getLayoutY() + _boss.getFitHeight()
						&& _leftbullets.get(i).getLayoutY() + _leftbullets.get(i).getFitHeight() > _boss.getLayoutY()) {
					roledamageleft = true;
					MediaPlayer roleatkplayer;
					Media roleatk = new Media(getClass().getResource("../bgm/collesion.wav").toString());
					roleatkplayer = new MediaPlayer(roleatk);
					roleatkplayer.play();
				}
			}
			if (_rightweapon.getLayoutX() + _rightweapon.getFitWidth() > _role.getLayoutX() + 25
					&& _rightweapon.getLayoutX() < _role.getLayoutX() + _role.getFitWidth()
					&& _rightweapon.getLayoutY() < _role.getLayoutY() + _role.getFitHeight()
					&& _rightweapon.getLayoutY() + _rightweapon.getFitHeight() > _role.getLayoutY()) {
				normaldamage = true;
			}
			if (_leftweapon.getLayoutX() + _leftweapon.getFitWidth() > _role.getLayoutX() + 25
					&& _leftweapon.getLayoutX() < _role.getLayoutX() + _role.getFitWidth()
					&& _leftweapon.getLayoutY() < _role.getLayoutY() + _role.getFitHeight()
					&& _leftweapon.getLayoutY() + _leftweapon.getFitHeight() > _role.getLayoutY()) {
				normaldamage = true;
			}
			if (_weapon1.getLayoutX() + _weapon1.getFitWidth() > _role.getLayoutX() + 25
					&& _weapon1.getLayoutX() < _role.getLayoutX() + _role.getFitWidth()
					&& _weapon1.getLayoutY() < _role.getLayoutY() + _role.getFitHeight()
					&& _weapon1.getLayoutY() + _weapon1.getFitHeight() > _role.getLayoutY()) {
				skilldamage = true;
			}
			if (_weapon2.getLayoutX() + _weapon2.getFitWidth() > _role.getLayoutX() + 25
					&& _weapon2.getLayoutX() < _role.getLayoutX() + _role.getFitWidth()
					&& _weapon2.getLayoutY() < _role.getLayoutY() + _role.getFitHeight()
					&& _weapon2.getLayoutY() + _weapon2.getFitHeight() > _role.getLayoutY()) {
				skilldamage = true;
			}
			if (_weapon3.getLayoutX() + _weapon3.getFitWidth() > _role.getLayoutX() + 25
					&& _weapon3.getLayoutX() < _role.getLayoutX() + _role.getFitWidth()
					&& _weapon3.getLayoutY() < _role.getLayoutY() + _role.getFitHeight()
					&& _weapon3.getLayoutY() + _weapon3.getFitHeight() > _role.getLayoutY()) {
				skilldamage = true;
			}
			if (_weapon4.getLayoutX() + _weapon4.getFitWidth() > _role.getLayoutX() + 25
					&& _weapon4.getLayoutX() < _role.getLayoutX() + _role.getFitWidth()
					&& _weapon4.getLayoutY() < _role.getLayoutY() + _role.getFitHeight()
					&& _weapon4.getLayoutY() + _weapon4.getFitHeight() > _role.getLayoutY()) {
				skilldamage = true;
			}
			if (_weapon5.getLayoutX() + _weapon5.getFitWidth() > _role.getLayoutX() + 25
					&& _weapon5.getLayoutX() < _role.getLayoutX() + _role.getFitWidth()
					&& _weapon5.getLayoutY() < _role.getLayoutY() + _role.getFitHeight()
					&& _weapon5.getLayoutY() + _weapon5.getFitHeight() > _role.getLayoutY()) {
				skilldamage = true;
			}
			if (_weapon6.getLayoutX() + _weapon6.getFitWidth() > _role.getLayoutX() + 25
					&& _weapon6.getLayoutX() < _role.getLayoutX() + _role.getFitWidth()
					&& _weapon6.getLayoutY() < _role.getLayoutY() + _role.getFitHeight()
					&& _weapon6.getLayoutY() + _weapon6.getFitHeight() > _role.getLayoutY()) {
				skilldamage = true;
			}
			if (_boss.getLayoutX() + _boss.getFitWidth() > _role.getLayoutX() + 25
					&& _boss.getLayoutX() < _role.getLayoutX() + _role.getFitWidth()
					&& _boss.getLayoutY() < _role.getLayoutY() + _role.getFitHeight()
					&& _boss.getLayoutY() + _boss.getFitHeight() > _role.getLayoutY()) {
				touchdamage = true;
			}
//------------------------------------------------------
			if (boss.gethp() <= 0) {
				_boss.setLayoutX(-500);
				_boss.setLayoutY(-500);
				_talkingbox.setVisible(false);
				_kotoba.setVisible(false);
				if(BossDeathCounter == 0) {
					_bossblood.setVisible(false);
					_bossbloodback.setVisible(false);
				}
				BossDeathCounter++;
				if(BossDeathCounter <= 30) {
					if(BossDeathCounter % 6 == 0) {
						_boss.setOpacity(_boss.getOpacity() - 0.2);
					}
				}
				if(BossDeathCounter == 30) {
					_background.getChildren().remove(_boss);
					bossdeath = true;
				}
			}
			if (bossdeath) {
				if (!showbuff) {
					_choose.setVisible(true);
					_buff1.setVisible(true);
					_buff1.setDisable(false);
					_buff2.setVisible(true);
					_buff2.setDisable(false);
					_buff3.setVisible(true);
					_buff3.setDisable(false);
					showbuff = true;
					time.stop();
					attack.stop();
					enemyattack.stop();
					damage.stop();
				}
			}
			if (maincontroller.newplayer.gethp() <= 0) {
				if (!lose) {
					time.stop();
					lose = true;
					FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/loseview.fxml"));
					Parent lose = null;
					try {
						lose = loadder.load();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Scene losescene = new Scene(lose);
					Final.mainstage.setScene(losescene);
				}
			}
//------------------------------------------------------
			if (w) {
				if(jumpcounter == 0) {
					MediaPlayer jumpplayer;
					Media jump = new Media(getClass().getResource("../bgm/jump.wav").toString());
					jumpplayer = new MediaPlayer(jump);
					jumpplayer.play();
				}
				if (jumpcounter < 20) {
					_role.setLayoutY(_role.getLayoutY() - 0.033 * G * Math.pow(jumpcountdown - jumpcounter - 1, 1.5));
					_showblood.setLayoutY(_showblood.getLayoutY() - 0.033 * G * Math.pow(jumpcountdown - jumpcounter - 1, 1.5));
					_bloodback.setLayoutY(
							_bloodback.getLayoutY() - 0.033 * G * Math.pow(jumpcountdown - jumpcounter - 1, 1.5));
					_blood.setLayoutY(_blood.getLayoutY() - 0.033 * G * Math.pow(jumpcountdown - jumpcounter - 1, 1.5));
					jumpcounter++;
				}
				if (jumpcounter == 20) {
					_role.setLayoutY(_role.getLayoutY() + 0.033 * G * Math.pow(jumpcounter - jumpcountdown, 1.5));
					_showblood.setLayoutY(_showblood.getLayoutY() + 0.033 * G * Math.pow(jumpcounter - jumpcountdown, 1.5));
					_bloodback.setLayoutY(
							_bloodback.getLayoutY() + 0.033 * G * Math.pow(jumpcounter - jumpcountdown, 1.5));
					_blood.setLayoutY(_blood.getLayoutY() + 0.033 * G * Math.pow(jumpcounter - jumpcountdown, 1.5));
					jumpcountdown--;
				}
				if (jumpcountdown == 0) {
					w = false;
					jumpcounter = 0;
					jumpcountdown = 20;
				}
			}
			if (a && _role.getLayoutX() >= 0) {
				_role.setLayoutX(_role.getLayoutX() - 7);
				_showblood.setLayoutX(_showblood.getLayoutX() - 7);
				_bloodback.setLayoutX(_bloodback.getLayoutX() - 7);
				_blood.setLayoutX(_blood.getLayoutX() - 7);
			}
			if (d && _role.getLayoutX() <= 893) {
				_role.setLayoutX(_role.getLayoutX() + 7);
				_showblood.setLayoutX(_showblood.getLayoutX() + 7);
				_bloodback.setLayoutX(_bloodback.getLayoutX() + 7);
				_blood.setLayoutX(_blood.getLayoutX() + 7);
			}
		});
		time.getKeyFrames().addAll(frame);
		time.setCycleCount(Timeline.INDEFINITE);
		time.play();
		KeyFrame damageframe = new KeyFrame(Duration.millis(1000 / 60), (d) -> {
			if (normaldamage) {
				if (NormalDamageCounter == 0) {
					maincontroller.newplayer.sethp(maincontroller.newplayer.gethp() - boss.getatk());
					_showblood.setText(maincontroller.newplayer.gethp() + "/" + maincontroller.newplayer.getmaxhp());
					_blood.setWidth(maincontroller.newplayer.gethp() * 100 / maincontroller.newplayer.getmaxhp());
				}
				NormalDamageCounter++;
				if (NormalDamageCounter == 60) {
					NormalDamageCounter = 0;
					normaldamage = false;
				}
			}
			if (skilldamage) {
				if (SkillDamageCounter == 0) {
					maincontroller.newplayer.sethp(maincontroller.newplayer.gethp() - boss.getatk());
					_showblood.setText(maincontroller.newplayer.gethp() + "/" + maincontroller.newplayer.getmaxhp());
					_blood.setWidth(maincontroller.newplayer.gethp() * 100 / maincontroller.newplayer.getmaxhp());
				}
				SkillDamageCounter++;
				if (SkillDamageCounter == 60) {
					SkillDamageCounter = 0;
					skilldamage = false;
				}
			}
			if (touchdamage) {
				if (TouchDamageCounter == 0) {
					maincontroller.newplayer.sethp(maincontroller.newplayer.gethp() - boss.getatk());
					_showblood.setText(maincontroller.newplayer.gethp() + "/" + maincontroller.newplayer.getmaxhp());
					_blood.setWidth(maincontroller.newplayer.gethp() * 100 / maincontroller.newplayer.getmaxhp());
				}
				TouchDamageCounter++;
				if (TouchDamageCounter == 60) {
					TouchDamageCounter = 0;
					touchdamage = false;
				}
			}
			if (roledamageright) {
				boss.sethp(boss.gethp() - maincontroller.newplayer.getatk());
				_bossblood.setWidth(boss.gethp() * 200 / 1000 / ((maincontroller.level+2) / 3));
			}
			if (roledamageleft) {
				boss.sethp(boss.gethp() - maincontroller.newplayer.getatk());
				_bossblood.setWidth(boss.gethp() * 200 / 1000 / ((maincontroller.level+2) / 3));
			}
		});
		damage.getKeyFrames().addAll(damageframe);
		damage.setCycleCount(Timeline.INDEFINITE);
		damage.play();
		KeyFrame attackframe = new KeyFrame(Duration.millis(1), (e) -> {
			ImageView newBullet = new ImageView();
			if (atkpermission) {
				if (attackcounter == 0 && face) {
					newBullet.setImage(bulletimage);
					newBullet.setFitWidth(20);
					newBullet.setFitHeight(26);
					newBullet.setLayoutX(_role.getLayoutX() + _role.getFitWidth());
					newBullet.setLayoutY(_role.getLayoutY() + _role.getFitHeight() / 2 - _bullet.getFitHeight() / 2);
					_rightbullets.push(newBullet);
					_background.getChildren().add(newBullet);
				} else if (attackcounter == 0 && !face) {
					newBullet.setImage(bulletimage);
					newBullet.setFitWidth(20);
					newBullet.setFitHeight(26);
					newBullet.setLayoutX(_role.getLayoutX() - _bullet.getFitWidth());
					newBullet.setLayoutY(_role.getLayoutY() + _role.getFitHeight() / 2 - _bullet.getFitHeight() / 2);
					_leftbullets.push(newBullet);
					_background.getChildren().add(newBullet);
				}
				attackcounter++;
				if (attackcounter == 600) {
					attackcounter = 0;
					atkpermission = false;
				}
			}
		});
		attack.getKeyFrames().addAll(attackframe);
		attack.setCycleCount(Timeline.INDEFINITE);
		attack.play();
		KeyFrame enemyattackframe = new KeyFrame(Duration.millis(1), (e) -> {
			if (!bossdeath) {
				if (NormalAttackCounter == 800) {
					if (_boss.getLayoutX() + 12 > _role.getLayoutX() + _role.getFitWidth() / 2) {
						changingX = -1;
					} else {
						changingX = 1;
					}
					CurrentLoctionX = _role.getLayoutX() + _role.getFitWidth() / 2;
					if (CurrentLoctionX < _boss.getLayoutX() + _boss.getFitWidth() / 2) {
						_leftweapon.setLayoutX(_boss.getLayoutX() + 64);
						_leftweapon.setLayoutY(_boss.getLayoutY() + 18);
						_leftweapon.setRotate(-31);
						attackready = true;
					}
					if (CurrentLoctionX > _boss.getLayoutX() + _boss.getFitWidth() / 2) {
						_rightweapon.setLayoutX(_boss.getLayoutX() + 167);
						_rightweapon.setLayoutY(_boss.getLayoutY() + 10);
						_rightweapon.setRotate(44);
						attackready = true;
					}
				}
				NormalAttackCounter++;
				if (!jumpready) {
					if (NormalAttackCounter > 1500 && NormalAttackCounter <= 1750) {
						_boss.setLayoutX(_boss.getLayoutX() - 0.1);
						_bossbloodback.setLayoutX(_bossbloodback.getLayoutX() - 0.1);
						_bossblood.setLayoutX(_bossblood.getLayoutX() - 0.1);
					}
					if (NormalAttackCounter > 1750 && NormalAttackCounter <= 2250) {
						_boss.setLayoutX(_boss.getLayoutX() + 0.1);
						_bossbloodback.setLayoutX(_bossbloodback.getLayoutX() + 0.1);
						_bossblood.setLayoutX(_bossblood.getLayoutX() + 0.1);
					}
					if (NormalAttackCounter > 2250 && NormalAttackCounter <= 2500) {
						_boss.setLayoutX(_boss.getLayoutX() - 0.1);
						_bossbloodback.setLayoutX(_bossbloodback.getLayoutX() - 0.1);
						_bossblood.setLayoutX(_bossblood.getLayoutX() - 0.1);
					}
				}
				if (NormalAttackCounter == 2500) {
					NormalAttackCounter = 0;
				}
			}
//---------------------------------------------------
			if (skillcounter == 5000 || skillcounter == 10000) {
				LeftMissAt = rand.nextInt(3) + 1;
				RightMissAt = rand.nextInt(3) + 1;
				skillready = true;
			}
			skillcounter++;
			if (skillcounter == 15000) {
				jumpready = true;
			}
			if (skillcounter == 19000) {
				skillcounter = 1000;
			}
		});
		enemyattack.getKeyFrames().addAll(enemyattackframe);
		enemyattack.setCycleCount(Timeline.INDEFINITE);
		enemyattack.play();
	}

	@FXML
	private Pane _background;

	@FXML
	private ImageView _role;

	@FXML
	private ImageView _bullet;

	@FXML
	private Label _choose;

	@FXML
	private Button _buff1;

	@FXML
	private Button _buff2;

	@FXML
	private Button _buff3;

	@FXML
	private ImageView _boss;

	@FXML
	private Pane _zone1;

	@FXML
	private ImageView _warning1;

	@FXML
	private ImageView _weapon1;

	@FXML
	private Pane _zone2;

	@FXML
	private ImageView _warning2;

	@FXML
	private ImageView _weapon2;

	@FXML
	private Pane _zone3;

	@FXML
	private ImageView _warning3;

	@FXML
	private ImageView _weapon3;

	@FXML
	private Pane _zone4;

	@FXML
	private ImageView _warning4;

	@FXML
	private ImageView _weapon4;

	@FXML
	private Pane _zone5;

	@FXML
	private ImageView _warning5;

	@FXML
	private ImageView _weapon5;

	@FXML
	private Pane _zone6;

	@FXML
	private ImageView _warning6;

	@FXML
	private ImageView _weapon6;

	@FXML
	private ImageView _talkingbox;

	@FXML
	private Label _kotoba;

	@FXML
	private ImageView _leftweapon;

	@FXML
	private ImageView _rightweapon;

	@FXML
	private Rectangle _bloodback;

	@FXML
	private Rectangle _blood;

	@FXML
	private Rectangle _bossbloodback;

	@FXML
	private Rectangle _bossblood;
	
    @FXML
    private Label _showblood;
    
    @FXML
    private Label _showlevel;

    @FXML
    private Label _showlayer;
    
    @FXML
    private Rectangle _pause;

    @FXML
    private Label _pauseshow;

	boolean w, a, d, atkpermission, face = true, lose = false, showbuff = false, bossdeath = false, attackready = false,
			skillready = false, jumpready = false, normaldamage = false, skilldamage = false, touchdamage = false,
			roledamageright = false, roledamageleft = false, showlevel = false;

	int jumpcounter = 0, jumpcountdown = 20, attackcounter = 0, NormalAttackCounter = 0, weaponcounter = 0,
			skillcounter = 0, Skill1LeftCounter = 0, Skill1RightCounter = 0, changingX, LeftMissAt, RightMissAt,
			BossJumpCounter = 0, NormalDamageCounter = 0, SkillDamageCounter = 0, TouchDamageCounter = 0, BossDeathCounter = 0, showlevelcounter = 0;

	double CurrentLoctionX;

	LinkedList<ImageView> _rightbullets = new LinkedList<ImageView>();
	LinkedList<ImageView> _leftbullets = new LinkedList<ImageView>();

	Random rand = new Random();

	File bulletfile = new File(
			"C:/Users/Vivo Book Pro/Desktop/ce1002.Final.s107502538/src/ce1002/Final/s107502538/image/bullet.png");
	Image bulletimage = new Image(bulletfile.toURI().toString());

	Timeline time = new Timeline();
	Timeline attack = new Timeline();
	Timeline enemyattack = new Timeline();
	Timeline damage = new Timeline();

	EventHandler<KeyEvent> onKeyPressed = (e) -> {
		if (e.getCode() == KeyCode.W) {
			w = true;
		}
		if (e.getCode() == KeyCode.A) {
			a = true;
			face = false;
		}
		if (e.getCode() == KeyCode.D) {
			d = true;
			face = true;
		}
		if (e.getCode() == KeyCode.ESCAPE) {
			// 遊戲選單
		}
	};
	EventHandler<KeyEvent> OnKeyReleased = (f) -> {
		if (f.getCode() == KeyCode.A) {
			a = false;
		}
		if (f.getCode() == KeyCode.D) {
			d = false;
		}
	};
	EventHandler<MouseEvent> OnMouseClicked = (m) -> {
		if (m.getButton() == MouseButton.PRIMARY) {
			atkpermission = true;
		}
	};

	@FXML
	void OnBuff1Pressed(ActionEvent event) throws IOException {
		maincontroller.newplayer.setatk(maincontroller.newplayer.getatk() + 30);
        FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/playing.fxml"));
	    Parent play = loadder.load();
	    Scene playscene = new Scene(play);
	    playcontroller Ctrl = loadder.getController();
	    playscene.setOnKeyPressed(Ctrl.onKeyPressed);
	    playscene.setOnKeyReleased(Ctrl.OnKeyReleased);
	    playscene.setOnMouseClicked(Ctrl.OnMouseClicked);
	    Final.mainstage.setScene(playscene);
	}

	@FXML
	void OnBuff2Pressed(ActionEvent event) throws IOException {
		if (maincontroller.newplayer.getmaxhp() - maincontroller.newplayer.gethp() < 500) {
			maincontroller.newplayer.sethp(maincontroller.newplayer.getmaxhp());
		} else {
			maincontroller.newplayer.sethp(maincontroller.newplayer.gethp() + 500);
		}
        FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/playing.fxml"));
	    Parent play = loadder.load();
	    Scene playscene = new Scene(play);
	    playcontroller Ctrl = loadder.getController();
	    playscene.setOnKeyPressed(Ctrl.onKeyPressed);
	    playscene.setOnKeyReleased(Ctrl.OnKeyReleased);
	    playscene.setOnMouseClicked(Ctrl.OnMouseClicked);
	    Final.mainstage.setScene(playscene);
	}

	@FXML
	void OnBuff3Pressed(ActionEvent event) throws IOException {
		maincontroller.newplayer.setmaxhp(maincontroller.newplayer.getmaxhp() + 500);
        FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/playing.fxml"));
	    Parent play = loadder.load();
	    Scene playscene = new Scene(play);
	    playcontroller Ctrl = loadder.getController();
	    playscene.setOnKeyPressed(Ctrl.onKeyPressed);
	    playscene.setOnKeyReleased(Ctrl.OnKeyReleased);
	    playscene.setOnMouseClicked(Ctrl.OnMouseClicked);
	    Final.mainstage.setScene(playscene);
	}
}
