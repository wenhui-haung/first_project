package ce1002.Final.s107502538.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
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

public class playcontroller implements Initializable {
	double G = 9.8;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		maincontroller.level++;
		enemy peashooter1 = new enemy(100 * ((maincontroller.level+2) / 3), 30 * ((maincontroller.level+2) / 3), 900, 483, 64, 81, new ImageView());
		enemy peashooter2 = new enemy(100 * ((maincontroller.level+2) / 3), 30 * ((maincontroller.level+2) / 3), 14, 483, 64, 81, new ImageView());
		enemy peashooter3 = new enemy(100 * ((maincontroller.level+2) / 3), 30 * ((maincontroller.level+2) / 3), 931, 259, 64, 81, new ImageView());
		_showblood.setText(maincontroller.newplayer.gethp() + "/" + maincontroller.newplayer.getmaxhp());
		_blood.setWidth(maincontroller.newplayer.gethp() * 100 / maincontroller.newplayer.getmaxhp());
		_showlayer.setText("Level " + ((maincontroller.level+2) / 3));
		_showlevel.setVisible(true);
		_showlayer.setVisible(true);
		_role.setLayoutX(440);
		_role.setLayoutY(424);
		_pea.setLayoutX(1110);
		_pea.setLayoutY(0);
		_pea2.setLayoutX(1110);
		_pea2.setLayoutY(0);
		_pea3.setLayoutX(1110);
		_pea3.setLayoutY(0);
		_choose.setVisible(false);
		_buff1.setVisible(false);
		_buff1.setDisable(true);
		_buff2.setVisible(false);
		_buff2.setDisable(true);
		_buff3.setVisible(false);
		_buff3.setDisable(true);
		KeyFrame key = new KeyFrame(Duration.millis(1000 / 60), (e) -> {
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
//-----------------------------------------
			ArrayList<ImageView> tRightBullets = new ArrayList<ImageView>(_rightbullets);
			ArrayList<ImageView> tLeftBullets = new ArrayList<ImageView>(_leftbullets);
			for (int i = 0; i < tRightBullets.size(); i++) {
				tRightBullets.get(i).setCache(true);
				tRightBullets.get(i).setLayoutX(tRightBullets.get(i).getLayoutX() + 11);
				tRightBullets.get(i).setVisible(true);
				if (tRightBullets.get(i).getLayoutX() > _background.getWidth() + 30
						|| tRightBullets.get(i).getLayoutX() < -5 || RightDamageOnEnemy1 || RightDamageOnEnemy2
						|| RightDamageOnEnemy3) {
					RightDamageOnEnemy1 = false;
					RightDamageOnEnemy2 = false;
					RightDamageOnEnemy3 = false;
					_rightbullets.remove(tRightBullets.get(i));
					_background.getChildren().remove(tRightBullets.get(i));
				}
			}
			for (int i = 0; i < tLeftBullets.size(); i++) {
				tLeftBullets.get(i).setCache(true);
				tLeftBullets.get(i).setLayoutX(tLeftBullets.get(i).getLayoutX() - 11);
				tLeftBullets.get(i).setVisible(true);
				if (tLeftBullets.get(i).getLayoutX() > _background.getWidth() + 30
						|| tLeftBullets.get(i).getLayoutX() < -5 || LeftDamageOnEnemy1 || LeftDamageOnEnemy2
						|| LeftDamageOnEnemy3) {
					LeftDamageOnEnemy1 = false;
					LeftDamageOnEnemy2 = false;
					LeftDamageOnEnemy3 = false;
					_leftbullets.remove(tLeftBullets.get(i));
					_background.getChildren().remove(tLeftBullets.get(i));
				}
			}
//------------------------------------------------------		
			if (pea1ready) {
				_pea.setCache(true);
				_pea.setLayoutX(_pea.getLayoutX() + _changingpea1s
						* Math.abs(peashooter1.getx() + peashooter1.getfx() / 2 - PeaCurrentLoctionX) / 60);
				if (pea1counter < 30) {
					_pea.setLayoutY(_pea.getLayoutY() - 0.017 * G * Math.pow(30 - pea1counter - 1, 1.5));
				}
				pea1counter++;
				if (pea1counter >= 30) {
					_pea.setLayoutY(_pea.getLayoutY() + 0.017 * G * Math.pow(pea1counter - 30, 1.5));
				}
				if (_pea.getLayoutY() > 588 || _pea.getLayoutX() < 0 || _pea.getLayoutX() > 995 || peadamage1) {
					pea1counter = 0;
					_pea.setLayoutX(1110);
					_pea.setLayoutY(0);
					peadamage1 = false;
					pea1ready = false;
				}
			}
			if (pea2ready) {
				_pea2.setCache(true);
				_pea2.setLayoutX(_pea2.getLayoutX() + _changingpea2s
						* Math.abs(PeaCurrentLoctionX - peashooter2.getx() + peashooter2.getfx() / 2) / 60);
				if (pea2counter < 30) {
					_pea2.setLayoutY(_pea2.getLayoutY() - 0.017 * G * Math.pow(30 - pea2counter - 1, 1.5));
				}
				pea2counter++;
				if (pea2counter >= 30) {
					_pea2.setLayoutY(_pea2.getLayoutY() + 0.017 * G * Math.pow(pea2counter - 30, 1.5));
				}
				if (_pea2.getLayoutY() > 588 || _pea2.getLayoutX() < 0 || _pea2.getLayoutX() > 995 || peadamage2) {
					pea2counter = 0;
					_pea2.setLayoutX(1110);
					_pea2.setLayoutY(0);
					peadamage2 = false;
					pea2ready = false;
				}
			}
			if (pea3ready) {
				_pea3.setCache(true);
				_pea3.setLayoutX(_pea3.getLayoutX() + _changingpea3s
						* Math.abs(peashooter3.getx() + peashooter3.getfx() / 2 - PeaCurrentLoctionX) / 65);
				if (pea3counter < 30) {
					_pea3.setLayoutY(_pea3.getLayoutY() - 0.017 * G * Math.pow(30 - pea3counter - 1, 1.5));
				}
				pea3counter++;
				if (pea3counter >= 30) {
					_pea3.setLayoutY(_pea3.getLayoutY() + 0.017 * G * Math.pow(pea3counter - 30, 1.5));
				}
				if (_pea3.getLayoutY() > 588 || _pea3.getLayoutX() < 0 || _pea3.getLayoutX() > 995 || peadamage3) {
					pea3counter = 0;
					_pea3.setLayoutX(1110);
					_pea3.setLayoutY(0);
					peadamage3 = false;
					pea3ready = false;
				}
			}
//------------------------------------------------------
			for (int i = 0; i < _rightbullets.size(); i++) {
				if (_rightbullets.get(i).getLayoutX() + _rightbullets.get(i).getFitWidth() > peashooter1.getx()
						&& _rightbullets.get(i).getLayoutX() < peashooter1.getx() + peashooter1.getfx()
						&& _rightbullets.get(i).getLayoutY() < peashooter1.gety() + peashooter1.getfy()
						&& _rightbullets.get(i).getLayoutY() + _rightbullets.get(i).getFitHeight() > peashooter1
								.gety()) {
					RightDamageOnEnemy1 = true;
					MediaPlayer roleatkplayer;
					Media roleatk = new Media(getClass().getResource("../bgm/collesion.wav").toString());
					roleatkplayer = new MediaPlayer(roleatk);
					roleatkplayer.play();
				}
				if (_rightbullets.get(i).getLayoutX() + _rightbullets.get(i).getFitWidth() > peashooter2.getx()
						&& _rightbullets.get(i).getLayoutX() < peashooter2.getx() + peashooter2.getfx()
						&& _rightbullets.get(i).getLayoutY() < peashooter2.gety() + peashooter2.getfy()
						&& _rightbullets.get(i).getLayoutY() + _rightbullets.get(i).getFitHeight() > peashooter2
								.gety()) {
					RightDamageOnEnemy2 = true;
					MediaPlayer roleatkplayer;
					Media roleatk = new Media(getClass().getResource("../bgm/collesion.wav").toString());
					roleatkplayer = new MediaPlayer(roleatk);
					roleatkplayer.play();
				}
				if (_rightbullets.get(i).getLayoutX() + _rightbullets.get(i).getFitWidth() > peashooter3.getx()
						&& _rightbullets.get(i).getLayoutX() < peashooter3.getx() + peashooter3.getfx()
						&& _rightbullets.get(i).getLayoutY() < peashooter3.gety() + peashooter3.getfy()
						&& _rightbullets.get(i).getLayoutY() + _rightbullets.get(i).getFitHeight() > peashooter3
								.gety()) {
					RightDamageOnEnemy3 = true;
					MediaPlayer roleatkplayer;
					Media roleatk = new Media(getClass().getResource("../bgm/collesion.wav").toString());
					roleatkplayer = new MediaPlayer(roleatk);
					roleatkplayer.play();
				}
			}
			for (int i = 0; i < _leftbullets.size(); i++) {
				if (_leftbullets.get(i).getLayoutX() + _leftbullets.get(i).getFitWidth() > peashooter1.getx()
						&& _leftbullets.get(i).getLayoutX() < peashooter1.getx() + peashooter1.getfx()
						&& _leftbullets.get(i).getLayoutY() < peashooter1.gety() + peashooter1.getfy()
						&& _leftbullets.get(i).getLayoutY() + _leftbullets.get(i).getFitHeight() > peashooter1.gety()) {
					LeftDamageOnEnemy1 = true;
					MediaPlayer roleatkplayer;
					Media roleatk = new Media(getClass().getResource("../bgm/collesion.wav").toString());
					roleatkplayer = new MediaPlayer(roleatk);
					roleatkplayer.play();
				}
				if (_leftbullets.get(i).getLayoutX() + _leftbullets.get(i).getFitWidth() > peashooter2.getx()
						&& _leftbullets.get(i).getLayoutX() < peashooter2.getx() + peashooter2.getfx()
						&& _leftbullets.get(i).getLayoutY() < peashooter2.gety() + peashooter2.getfy()
						&& _leftbullets.get(i).getLayoutY() + _leftbullets.get(i).getFitHeight() > peashooter2.gety()) {
					LeftDamageOnEnemy2 = true;
					MediaPlayer roleatkplayer;
					Media roleatk = new Media(getClass().getResource("../bgm/collesion.wav").toString());
					roleatkplayer = new MediaPlayer(roleatk);
					roleatkplayer.play();
				}
				if (_leftbullets.get(i).getLayoutX() + _leftbullets.get(i).getFitWidth() > peashooter3.getx()
						&& _leftbullets.get(i).getLayoutX() < peashooter3.getx() + peashooter3.getfx()
						&& _leftbullets.get(i).getLayoutY() < peashooter3.gety() + peashooter3.getfy()
						&& _leftbullets.get(i).getLayoutY() + _leftbullets.get(i).getFitHeight() > peashooter3.gety()) {
					LeftDamageOnEnemy3 = true;
					MediaPlayer roleatkplayer;
					Media roleatk = new Media(getClass().getResource("../bgm/collesion.wav").toString());
					roleatkplayer = new MediaPlayer(roleatk);
					roleatkplayer.play();
				}
			}
//------------------------------------------------------
			if (_pea.getLayoutX() + _pea.getFitWidth() > _role.getLayoutX() + 25
					&& _pea.getLayoutX() < _role.getLayoutX() + _role.getFitWidth()
					&& _pea.getLayoutY() < _role.getLayoutY() + _role.getFitHeight()
					&& _pea.getLayoutY() + _pea.getFitHeight() > _role.getLayoutY()) {
				peadamage1 = true;
			}
			if (_role.getLayoutX() + _role.getFitWidth() > _enemy1.getLayoutX()
					&& _role.getLayoutX() + 25 < _enemy1.getLayoutX() + _enemy1.getFitWidth()
					&& _role.getLayoutY() < _enemy1.getLayoutY() + _enemy1.getFitHeight()
					&& _role.getLayoutY() + _role.getFitHeight() > _enemy1.getLayoutY()) {
				peashootertouch1 = true;
			}
			if (_pea2.getLayoutX() + _pea2.getFitWidth() > _role.getLayoutX() + 25
					&& _pea2.getLayoutX() < _role.getLayoutX() + _role.getFitWidth()
					&& _pea2.getLayoutY() < _role.getLayoutY() + _role.getFitHeight()
					&& _pea2.getLayoutY() + _pea2.getFitHeight() > _role.getLayoutY()) {
				peadamage2 = true;
			}
			if (_role.getLayoutX() + _role.getFitWidth() > _enemy2.getLayoutX()
					&& _role.getLayoutX() + 25 < _enemy2.getLayoutX() + _enemy2.getFitWidth()
					&& _role.getLayoutY() < _enemy2.getLayoutY() + _enemy2.getFitHeight()
					&& _role.getLayoutY() + _role.getFitHeight() > _enemy2.getLayoutY()) {
				peashootertouch2 = true;
			}
			if (_pea3.getLayoutX() + _pea3.getFitWidth() > _role.getLayoutX() + 25
					&& _pea3.getLayoutX() < _role.getLayoutX() + _role.getFitWidth()
					&& _pea3.getLayoutY() < _role.getLayoutY() + _role.getFitHeight()
					&& _pea3.getLayoutY() + _pea3.getFitHeight() > _role.getLayoutY()) {
				peadamage3 = true;
			}
			if (_role.getLayoutX() + _role.getFitWidth() > _enemy3.getLayoutX()
					&& _role.getLayoutX() + 25 < _enemy3.getLayoutX() + _enemy3.getFitWidth()
					&& _role.getLayoutY() < _enemy3.getLayoutY() + _enemy3.getFitHeight()
					&& _role.getLayoutY() + _role.getFitHeight() > _enemy3.getLayoutY()) {
				peashootertouch3 = true;
			}
//------------------------------------------------------
			if (peashooter1.gethp() <= 0) {
				if(Peashooter1DeathCounter == 0) {
					_enemy1blood.setVisible(false);
					_enemy1bloodback.setVisible(false);
				}
				Peashooter1DeathCounter++;
				if(Peashooter1DeathCounter <= 30) {
					if(Peashooter1DeathCounter % 6 == 0) {
						_enemy1.setOpacity(_enemy1.getOpacity() - 0.2);
					}
				}
				if(Peashooter1DeathCounter == 30) {
					_background.getChildren().remove(_enemy1);
					peashooter1death = true;
				}
			}
			if (peashooter2.gethp() <= 0) {
				if(Peashooter2DeathCounter == 0) {
					_enemy2blood.setVisible(false);
					_enemy2bloodback.setVisible(false);
				}
				Peashooter2DeathCounter++;
				if(Peashooter2DeathCounter <= 30) {
					if(Peashooter2DeathCounter % 6 == 0) {
						_enemy2.setOpacity(_enemy2.getOpacity() - 0.2);
					}
				}
				if(Peashooter2DeathCounter == 30) {
					_background.getChildren().remove(_enemy2);
					peashooter2death = true;
				}
			}
			if (peashooter3.gethp() <= 0) {
				if(Peashooter3DeathCounter == 0) {
					_enemy3blood.setVisible(false);
					_enemy3bloodback.setVisible(false);
				}
				Peashooter3DeathCounter++;
				if(Peashooter3DeathCounter <= 30) {
					if(Peashooter3DeathCounter % 6 == 0) {
						_enemy3.setOpacity(_enemy3.getOpacity() - 0.2);
					}
				}
				if(Peashooter3DeathCounter == 30) {
					_background.getChildren().remove(_enemy3);
					peashooter3death = true;
				}
			}
			if (peashooter1death && peashooter2death && peashooter3death) {
				nextcounter++;
				if (!showbuff && nextcounter == 60) {
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
		time.getKeyFrames().addAll(key);
		time.setCycleCount(Timeline.INDEFINITE);
		time.play();
		KeyFrame damageframe = new KeyFrame(Duration.millis(1000 / 60), (d) -> {
			if (peadamage1) {
				maincontroller.newplayer.sethp(maincontroller.newplayer.gethp() - peashooter1.getatk());
				_showblood.setText(maincontroller.newplayer.gethp() + "/" + maincontroller.newplayer.getmaxhp());
				_blood.setWidth(maincontroller.newplayer.gethp() * 100 / maincontroller.newplayer.getmaxhp());
			}
			if (peadamage2) {
				maincontroller.newplayer.sethp(maincontroller.newplayer.gethp() - peashooter2.getatk());
				_showblood.setText(maincontroller.newplayer.gethp() + "/" + maincontroller.newplayer.getmaxhp());
				_blood.setWidth(maincontroller.newplayer.gethp() * 100 / maincontroller.newplayer.getmaxhp());
			}
			if (peadamage3) {
				maincontroller.newplayer.sethp(maincontroller.newplayer.gethp() - peashooter3.getatk());
				_showblood.setText(maincontroller.newplayer.gethp() + "/" + maincontroller.newplayer.getmaxhp());
				_blood.setWidth(maincontroller.newplayer.gethp() * 100 / maincontroller.newplayer.getmaxhp());
			}
			if (peashootertouch1) {
				if (PeashooterTouchCounter1 == 0) {
					maincontroller.newplayer.sethp(maincontroller.newplayer.gethp() - peashooter1.getatk());
					_showblood.setText(maincontroller.newplayer.gethp() + "/" + maincontroller.newplayer.getmaxhp());
					_blood.setWidth(maincontroller.newplayer.gethp() * 100 / maincontroller.newplayer.getmaxhp());
				}
				PeashooterTouchCounter1++;
				if (PeashooterTouchCounter1 == 60) {
					PeashooterTouchCounter1 = 0;
					peashootertouch1 = false;
				}
			}
			if (peashootertouch2) {
				if (PeashooterTouchCounter2 == 0) {
					maincontroller.newplayer.sethp(maincontroller.newplayer.gethp() - peashooter2.getatk());
					_showblood.setText(maincontroller.newplayer.gethp() + "/" + maincontroller.newplayer.getmaxhp());
					_blood.setWidth(maincontroller.newplayer.gethp() * 100 / maincontroller.newplayer.getmaxhp());
				}
				PeashooterTouchCounter2++;
				if (PeashooterTouchCounter2 == 60) {
					PeashooterTouchCounter2 = 0;
					peashootertouch2 = false;
				}
			}
			if (peashootertouch3) {
				if (PeashooterTouchCounter3 == 0) {
					maincontroller.newplayer.sethp(maincontroller.newplayer.gethp() - peashooter3.getatk());
					_showblood.setText(maincontroller.newplayer.gethp() + "/" + maincontroller.newplayer.getmaxhp());
					_blood.setWidth(maincontroller.newplayer.gethp() * 100 / maincontroller.newplayer.getmaxhp());
				}
				PeashooterTouchCounter3++;
				if (PeashooterTouchCounter3 == 60) {
					PeashooterTouchCounter3 = 0;
					peashootertouch3 = false;
				}
			}
			if (RightDamageOnEnemy1) {
				peashooter1.sethp(peashooter1.gethp() - maincontroller.newplayer.getatk());
				_enemy1blood.setWidth(peashooter1.gethp() * 50 / 100 / ((maincontroller.level+2) / 3));
			}
			if (RightDamageOnEnemy2) {
				peashooter2.sethp(peashooter2.gethp() - maincontroller.newplayer.getatk());
				_enemy2blood.setWidth(peashooter2.gethp() * 50 / 100 / ((maincontroller.level+2) / 3));
			}
			if (RightDamageOnEnemy3) {
				peashooter3.sethp(peashooter3.gethp() - maincontroller.newplayer.getatk());
				_enemy3blood.setWidth(peashooter3.gethp() * 50 / 100 / ((maincontroller.level+2) / 3));
			}
			if (LeftDamageOnEnemy1) {
				peashooter1.sethp(peashooter1.gethp() - maincontroller.newplayer.getatk());
				_enemy1blood.setWidth(peashooter1.gethp() * 50 / 100 / ((maincontroller.level+2) / 3));
			}
			if (LeftDamageOnEnemy2) {
				peashooter2.sethp(peashooter2.gethp() - maincontroller.newplayer.getatk());
				_enemy2blood.setWidth(peashooter2.gethp() * 50 / 100 / ((maincontroller.level+2) / 3));
			}
			if (LeftDamageOnEnemy3) {
				peashooter3.sethp(peashooter3.gethp() - maincontroller.newplayer.getatk());
				_enemy3blood.setWidth(peashooter3.gethp() * 50 / 100 / ((maincontroller.level+2) / 3));
			}
		});
		damage.getKeyFrames().addAll(damageframe);
		damage.setCycleCount(Timeline.INDEFINITE);
		damage.play();
		KeyFrame atkkey = new KeyFrame(Duration.millis(1), (e) -> {
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
			if (showbuff) {
				attack.stop();
			}
		});
		attack.getKeyFrames().addAll(atkkey);
		attack.setCycleCount(Timeline.INDEFINITE);
		attack.play();
		KeyFrame enemyatkkey = new KeyFrame(Duration.millis(1), (e) -> {
			if (!peashooter1death) {
				if (Pea1AttackCounter == 500) {
					if (_enemy1.getLayoutX() + 12 > _role.getLayoutX() + _role.getFitWidth() / 2) {
						_changingpea1s = -1;
					} else {
						_changingpea1s = 1;
					}
					PeaCurrentLoctionX = _role.getLayoutX() + _role.getFitWidth() / 2;
					_pea.setImage(peaimage);
					_pea.setFitWidth(20);
					_pea.setFitHeight(16);
					_pea.setVisible(true);
					_pea.setLayoutX(_enemy1.getLayoutX() + 12);
					_pea.setLayoutY(_enemy1.getLayoutY());
					pea1ready = true;
				}
				Pea1AttackCounter++;
				if (Pea1AttackCounter == 2000) {
					Pea1AttackCounter = 0;
				}
			}
			if (!peashooter2death) {
				if (Pea2AttackCounter == 1000) {
					if (_enemy2.getLayoutX() + 12 > _role.getLayoutX() + _role.getFitWidth() / 2) {
						_changingpea2s = -1;
					} else {
						_changingpea2s = 1;
					}
					PeaCurrentLoctionX = _role.getLayoutX() + _role.getFitWidth() / 2;
					_pea2.setImage(peaimage);
					_pea2.setFitWidth(20);
					_pea2.setFitHeight(16);
					_pea2.setVisible(true);
					_pea2.setLayoutX(_enemy2.getLayoutX() + 12);
					_pea2.setLayoutY(_enemy2.getLayoutY());
					pea2ready = true;
				}
				Pea2AttackCounter++;
				if (Pea2AttackCounter == 2000) {
					Pea2AttackCounter = 0;
				}
			}
			if (!peashooter3death) {
				if (Pea3AttackCounter == 1800) {
					if (_enemy3.getLayoutX() + 12 > _role.getLayoutX() + _role.getFitWidth() / 2) {
						_changingpea3s = -1;
					} else {
						_changingpea3s = 1;
					}
					PeaCurrentLoctionX = _role.getLayoutX() + _role.getFitWidth() / 2;
					_pea3.setImage(peaimage);
					_pea3.setFitWidth(20);
					_pea3.setFitHeight(16);
					_pea3.setVisible(true);
					_pea3.setLayoutX(_enemy3.getLayoutX() + 12);
					_pea3.setLayoutY(_enemy3.getLayoutY());
					pea3ready = true;
				}
				Pea3AttackCounter++;
				if (Pea3AttackCounter == 2000) {
					Pea3AttackCounter = 0;
				}
			}
			if (showbuff) {
				enemyattack.stop();
			}
		});
		enemyattack.getKeyFrames().addAll(enemyatkkey);
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
	private ImageView _enemy1;

	@FXML
	private ImageView _enemy2;

	@FXML
	private ImageView _enemy3;

	@FXML
	private ImageView _pea;

	@FXML
	private ImageView _pea2;

	@FXML
	private ImageView _pea3;

	@FXML
	private Label _choose;

	@FXML
	private Button _buff1;

	@FXML
	private Button _buff2;

	@FXML
	private Button _buff3;

	@FXML
	private Rectangle _bloodback;

	@FXML
	private Rectangle _blood;

	@FXML
	private Rectangle _enemy3bloodback;

	@FXML
	private Rectangle _enemy3blood;

	@FXML
	private Rectangle _enemy1bloodback;

	@FXML
	private Rectangle _enemy1blood;

	@FXML
	private Rectangle _enemy2bloodback;

	@FXML
	private Rectangle _enemy2blood;
	
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
    
	boolean w, a, d, atkpermission, face = true, pea1ready, peashooter1death = false, pea2ready,
			peashooter2death = false, pea3ready, peashooter3death = false, lose = false, showbuff = false,
			peadamage1 = false, peadamage2 = false, peadamage3 = false, peashootertouch1 = false,
			peashootertouch2 = false, peashootertouch3 = false, RightDamageOnEnemy1 = false, LeftDamageOnEnemy1 = false,
			RightDamageOnEnemy2 = false, LeftDamageOnEnemy2 = false, RightDamageOnEnemy3 = false,
			LeftDamageOnEnemy3 = false, showlevel = false, pause = false;

	int jumpcounter = 0, jumpcountdown = 20, attackcounter = 0, Pea1AttackCounter = 0, pea1counter = 0,
			Pea2AttackCounter = 0, pea2counter = 0, Pea3AttackCounter = 0, pea3counter = 0, _changingpea1s,
			_changingpea2s, _changingpea3s, falldowncounter = 0, PeashooterTouchCounter1 = 0,
			PeashooterTouchCounter2 = 0, PeashooterTouchCounter3 = 0, nextcounter = 0, Peashooter1DeathCounter = 0,
			Peashooter2DeathCounter = 0, Peashooter3DeathCounter = 0, showlevelcounter = 0;

	double PeaCurrentLoctionX;

	LinkedList<ImageView> _rightbullets = new LinkedList<ImageView>();
	LinkedList<ImageView> _leftbullets = new LinkedList<ImageView>();

	File bulletfile = new File(
			"C:/Users/Vivo Book Pro/Desktop/ce1002.Final.s107502538/src/ce1002/Final/s107502538/image/bullet.png");
	Image bulletimage = new Image(bulletfile.toURI().toString());
	File peafile = new File(
			"C:/Users/Vivo Book Pro/Desktop/ce1002.Final.s107502538/src/ce1002/Final/s107502538/image/pea.png");
	Image peaimage = new Image(peafile.toURI().toString());

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
//		if (e.getCode() == KeyCode.ESCAPE) {
////			if(!pause) {
////				time.pause();
////				attack.pause();
////				enemyattack.pause();
////				damage.pause();
////				pause = true;
////			}else {
////				time.play(); aa
////				attack.play();
////				enemyattack.play();
////				damage.play();
////				pause = false;
//			}
//		}
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
		maincontroller.newplayer.setatk(maincontroller.newplayer.getatk() + 15);
		FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/playing2.fxml"));
		Parent play2 = loadder.load();
		Scene playscene2 = new Scene(play2);
		playcontroller2 Ctrl = loadder.getController();
		playscene2.setOnKeyPressed(Ctrl.onKeyPressed);
		playscene2.setOnKeyReleased(Ctrl.OnKeyReleased);
		playscene2.setOnMouseClicked(Ctrl.OnMouseClicked);
		Final.mainstage.setScene(playscene2);
	}

	@FXML
	void OnBuff2Pressed(ActionEvent event) throws IOException {
		if (maincontroller.newplayer.getmaxhp() - maincontroller.newplayer.gethp() < 250) {
			maincontroller.newplayer.sethp(maincontroller.newplayer.getmaxhp());
		} else {
			maincontroller.newplayer.sethp(maincontroller.newplayer.gethp() + 250);
		}
		FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/playing2.fxml"));
		Parent play2 = loadder.load();
		Scene playscene2 = new Scene(play2);
		playcontroller2 Ctrl = loadder.getController();
		playscene2.setOnKeyPressed(Ctrl.onKeyPressed);
		playscene2.setOnKeyReleased(Ctrl.OnKeyReleased);
		playscene2.setOnMouseClicked(Ctrl.OnMouseClicked);
		Final.mainstage.setScene(playscene2);
	}

	@FXML
	void OnBuff3Pressed(ActionEvent event) throws IOException {
		maincontroller.newplayer.setmaxhp(maincontroller.newplayer.getmaxhp() + 500);
		FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/playing2.fxml"));
		Parent play2 = loadder.load();
		Scene playscene2 = new Scene(play2);
		playcontroller2 Ctrl = loadder.getController();
		playscene2.setOnKeyPressed(Ctrl.onKeyPressed);
		playscene2.setOnKeyReleased(Ctrl.OnKeyReleased);
		playscene2.setOnMouseClicked(Ctrl.OnMouseClicked);
		Final.mainstage.setScene(playscene2);
	}
}
