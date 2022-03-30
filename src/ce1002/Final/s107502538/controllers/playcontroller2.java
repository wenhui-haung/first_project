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

public class playcontroller2 implements Initializable {
	double G = 9.8;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		maincontroller.level++;
		enemy peashooter = new enemy(100 * ((maincontroller.level+2) / 3), 30 * ((maincontroller.level+2) / 3), 941, 269, 64, 81, new ImageView());
		enemy spider1 = new enemy(100 * ((maincontroller.level+2) / 3), 30 * ((maincontroller.level+2) / 3), 68, 514, 79, 49, new ImageView());
		enemy spider2 = new enemy(100 * ((maincontroller.level+2) / 3), 30 * ((maincontroller.level+2) / 3), 848, 514, 79, 49, new ImageView());
		_showblood.setText(maincontroller.newplayer.gethp() + "/" + maincontroller.newplayer.getmaxhp());
		_blood.setWidth(maincontroller.newplayer.gethp() * 100 / maincontroller.newplayer.getmaxhp());
		_showlayer.setText("Level " + ((maincontroller.level+2) / 3));
		_showlevel.setVisible(true);
		_showlayer.setVisible(true);
		_role.setLayoutX(440);
		_role.setLayoutY(424);
		_pea.setLayoutX(1110);
		_pea.setLayoutY(0);
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
//--------------------------------------------------
			ArrayList<ImageView> tRightBullets = new ArrayList<ImageView>(_rightbullets);
			ArrayList<ImageView> tLeftBullets = new ArrayList<ImageView>(_leftbullets);
			for (int i = 0; i < tRightBullets.size(); i++) {
				tRightBullets.get(i).setCache(true);
				tRightBullets.get(i).setLayoutX(tRightBullets.get(i).getLayoutX() + 11);
				tRightBullets.get(i).setVisible(true);
				if (tRightBullets.get(i).getLayoutX() > _background.getWidth() + 30
						|| tRightBullets.get(i).getLayoutX() < -5 || RightDamageOnPeashooter || RightDamageOnSpider1
						|| RightDamageOnSpider2) {
					RightDamageOnPeashooter = false;
					RightDamageOnSpider1 = false;
					RightDamageOnSpider2 = false;
					_rightbullets.remove(tRightBullets.get(i));
					_background.getChildren().remove(tRightBullets.get(i));
				}
			}
			for (int i = 0; i < tLeftBullets.size(); i++) {
				tLeftBullets.get(i).setCache(true);
				tLeftBullets.get(i).setLayoutX(tLeftBullets.get(i).getLayoutX() - 11);
				tLeftBullets.get(i).setVisible(true);
				if (tLeftBullets.get(i).getLayoutX() > _background.getWidth() + 30
						|| tLeftBullets.get(i).getLayoutX() < -5 || LeftDamageOnPeashooter || LeftDamageOnSpider1
						|| LeftDamageOnSpider2) {
					LeftDamageOnPeashooter = false;
					LeftDamageOnSpider1 = false;
					LeftDamageOnSpider2 = false;
					_leftbullets.remove(tLeftBullets.get(i));
					_background.getChildren().remove(tLeftBullets.get(i));
				}
			}
			if (peaready) {
				_pea.setCache(true);
				_pea.setLayoutX(_pea.getLayoutX()
						+ _changingpea * Math.abs(peashooter.getx() + peashooter.getfx() / 2 - CurrentLoctionX) / 65);
				if (peacounter < 30) {
					_pea.setLayoutY(_pea.getLayoutY() - 0.017 * G * Math.pow(30 - peacounter - 1, 1.5));
				}
				peacounter++;
				if (peacounter >= 30) {
					_pea.setLayoutY(_pea.getLayoutY() + 0.017 * G * Math.pow(peacounter - 30, 1.5));
				}
				if (_pea.getLayoutY() > 588 || _pea.getLayoutX() < 0 || _pea.getLayoutX() > 995 || peadamage) {
					peacounter = 0;
					_pea.setLayoutX(1110);
					_pea.setLayoutY(0);
					peaready = false;
					peadamage = false;
				}
			}
			if (_spider1.getLayoutX() > 0 && _spider1.getLayoutX() < 906) {
				if (Math.abs(_spider1.getLayoutX() + _spider1.getFitWidth() / 2 - _role.getLayoutX()
						- _role.getFitWidth() / 2) < 300) {
					jumpready1 = true;
				} else if (!jumpready1) {
					if (((_role.getLayoutX() + _role.getFitWidth() / 2 - _spider1.getLayoutX()
							- _spider1.getFitWidth() / 2)
							/ Math.abs(_spider1.getLayoutX() + _spider1.getFitWidth() / 2 - _role.getLayoutX()
									- _role.getFitWidth() / 2)) == 1) {
						_spider1.setRotate(0);
					}
					if (((_role.getLayoutX() + _role.getFitWidth() / 2 - _spider1.getLayoutX()
							- _spider1.getFitWidth() / 2)
							/ Math.abs(_spider1.getLayoutX() + _spider1.getFitWidth() / 2 - _role.getLayoutX()
									- _role.getFitWidth() / 2)) == -1) {
						_spider1.setRotate(180);
					}
					_spider1.setLayoutX(_spider1.getLayoutX() + ((_role.getLayoutX() + _role.getFitWidth() / 2
							- _spider1.getLayoutX() - _spider1.getFitWidth() / 2)
							/ Math.abs(_spider1.getLayoutX() + _spider1.getFitWidth() / 2 - _role.getLayoutX()
									- _role.getFitWidth() / 2))
							* 4);
					_spiderblood1.setLayoutX(_spiderblood1.getLayoutX() + ((_role.getLayoutX() + _role.getFitWidth() / 2
							- _spider1.getLayoutX() - _spider1.getFitWidth() / 2)
							/ Math.abs(_spider1.getLayoutX() + _spider1.getFitWidth() / 2 - _role.getLayoutX()
									- _role.getFitWidth() / 2))
							* 4);
					_spiderbloodback1.setLayoutX(_spiderbloodback1.getLayoutX() + ((_role.getLayoutX()
							+ _role.getFitWidth() / 2 - _spider1.getLayoutX() - _spider1.getFitWidth() / 2)
							/ Math.abs(_spider1.getLayoutX() + _spider1.getFitWidth() / 2 - _role.getLayoutX()
									- _role.getFitWidth() / 2))
							* 4);
				}
				if (jumpready1) {
					if (spiderpausecounter1 == 0) {
						jumpCurrentLoctionX1 = _role.getLayoutX() + _role.getFitWidth() / 2;
					}
					spiderpausecounter1++;
					if (spiderpausecounter1 >= 45 && spiderpausecounter1 <= 50) {
						_spider1.setLayoutX(_spider1.getLayoutX()
								+ (jumpCurrentLoctionX1 - _spider1.getLayoutX() - _spider1.getFitWidth() / 2) / 4);
						_spiderblood1.setLayoutX(_spiderblood1.getLayoutX()
								+ (jumpCurrentLoctionX1 - _spiderblood1.getLayoutX() - _spider1.getFitWidth() / 2) / 4);
						_spiderbloodback1.setLayoutX(_spiderbloodback1.getLayoutX()
								+ (jumpCurrentLoctionX1 - _spiderbloodback1.getLayoutX() - _spider1.getFitWidth() / 2)
										/ 4);
						if (spiderpausecounter1 <= 47) {
							_spider1.setLayoutY(_spider1.getLayoutY() - 10);
							_spiderblood1.setLayoutY(_spiderblood1.getLayoutY() - 10);
							_spiderbloodback1.setLayoutY(_spiderbloodback1.getLayoutY() - 10);
						}
						if (spiderpausecounter1 >= 48) {
							_spider1.setLayoutY(_spider1.getLayoutY() + 10);
							_spiderblood1.setLayoutY(_spiderblood1.getLayoutY() + 10);
							_spiderbloodback1.setLayoutY(_spiderbloodback1.getLayoutY() + 10);
						}
						if (_spider1.getLayoutX() + _spider1.getFitWidth() > _role.getLayoutX() + 25
								&& _spider1.getLayoutX() + 10 < _role.getLayoutX() + _role.getFitWidth()
								&& _spider1.getLayoutY() < _role.getLayoutY() + _role.getFitHeight()
								&& _spider1.getLayoutY() + _spider1.getFitHeight() > _role.getLayoutY()) {
							bitedamage1 = true;
						}
					}
					if (spiderpausecounter1 > 110) {
						if (((_role.getLayoutX() + _role.getFitWidth() / 2 - _spider1.getLayoutX()
								- _spider1.getFitWidth() / 2)
								/ Math.abs(_spider1.getLayoutX() + _spider1.getFitWidth() / 2 - _role.getLayoutX()
										- _role.getFitWidth() / 2)) == 1) {
							_spider1.setRotate(0);
						}
						if (((_role.getLayoutX() + _role.getFitWidth() / 2 - _spider1.getLayoutX()
								- _spider1.getFitWidth() / 2)
								/ Math.abs(_spider1.getLayoutX() + _spider1.getFitWidth() / 2 - _role.getLayoutX()
										- _role.getFitWidth() / 2)) == -1) {
							_spider1.setRotate(180);
						}
						spiderpausecounter1 = 0;
						jumpready1 = false;
					}
				}
			}
			if (_spider2.getLayoutX() > 0 && _spider2.getLayoutX() < 906) {
				if (Math.abs(_spider2.getLayoutX() + _spider2.getFitWidth() / 2 - _role.getLayoutX()
						- _role.getFitWidth() / 2) < 300) {
					jumpready2 = true;
				} else if (!jumpready2) {
					if (((_role.getLayoutX() + _role.getFitWidth() / 2 - _spider2.getLayoutX()
							- _spider2.getFitWidth() / 2)
							/ Math.abs(_spider2.getLayoutX() + _spider2.getFitWidth() / 2 - _role.getLayoutX()
									- _role.getFitWidth() / 2)) == 1) {
						_spider2.setRotate(0);
					}
					if (((_role.getLayoutX() + _role.getFitWidth() / 2 - _spider2.getLayoutX()
							- _spider2.getFitWidth() / 2)
							/ Math.abs(_spider2.getLayoutX() + _spider2.getFitWidth() / 2 - _role.getLayoutX()
									- _role.getFitWidth() / 2)) == -1) {
						_spider2.setRotate(180);
					}
					_spider2.setLayoutX(_spider2.getLayoutX() + ((_role.getLayoutX() + _role.getFitWidth() / 2
							- _spider2.getLayoutX() - _spider2.getFitWidth() / 2)
							/ Math.abs(_spider2.getLayoutX() + _spider2.getFitWidth() / 2 - _role.getLayoutX()
									- _role.getFitWidth() / 2))
							* 4);
					_spiderblood2.setLayoutX(_spiderblood2.getLayoutX() + ((_role.getLayoutX() + _role.getFitWidth() / 2
							- _spider2.getLayoutX() - _spider2.getFitWidth() / 2)
							/ Math.abs(_spider2.getLayoutX() + _spider2.getFitWidth() / 2 - _role.getLayoutX()
									- _role.getFitWidth() / 2))
							* 4);
					_spiderbloodback2.setLayoutX(_spiderbloodback2.getLayoutX() + ((_role.getLayoutX()
							+ _role.getFitWidth() / 2 - _spider2.getLayoutX() - _spider2.getFitWidth() / 2)
							/ Math.abs(_spider2.getLayoutX() + _spider2.getFitWidth() / 2 - _role.getLayoutX()
									- _role.getFitWidth() / 2))
							* 4);
				}
				if (jumpready2) {
					if (spiderpausecounter2 == 0) {
						jumpCurrentLoctionX2 = _role.getLayoutX() + _role.getFitWidth() / 2;
					}
					spiderpausecounter2++;
					if (spiderpausecounter2 >= 45 && spiderpausecounter2 <= 50) {
						_spider2.setLayoutX(_spider2.getLayoutX()
								+ (jumpCurrentLoctionX2 - _spider2.getLayoutX() - _spider2.getFitWidth() / 2) / 4);
						_spiderblood2.setLayoutX(_spiderblood2.getLayoutX()
								+ (jumpCurrentLoctionX2 - _spiderblood2.getLayoutX() - _spider2.getFitWidth() / 2) / 4);
						_spiderbloodback2.setLayoutX(_spiderbloodback2.getLayoutX()
								+ (jumpCurrentLoctionX2 - _spiderbloodback2.getLayoutX() - _spider2.getFitWidth() / 2)
										/ 4);
						if (spiderpausecounter2 <= 47) {
							_spider2.setLayoutY(_spider2.getLayoutY() - 10);
							_spiderblood2.setLayoutY(_spiderblood2.getLayoutY() - 10);
							_spiderbloodback2.setLayoutY(_spiderbloodback2.getLayoutY() - 10);
						}
						if (spiderpausecounter2 >= 48) {
							_spider2.setLayoutY(_spider2.getLayoutY() + 10);
							_spiderblood2.setLayoutY(_spiderblood2.getLayoutY() + 10);
							_spiderbloodback2.setLayoutY(_spiderbloodback2.getLayoutY() + 10);
						}
						if (_spider1.getLayoutX() + _spider1.getFitWidth() > _role.getLayoutX() + 25
								&& _spider1.getLayoutX() + 10 < _role.getLayoutX() + _role.getFitWidth()
								&& _spider1.getLayoutY() < _role.getLayoutY() + _role.getFitHeight()
								&& _spider1.getLayoutY() + _spider1.getFitHeight() > _role.getLayoutY()) {
							bitedamage2 = true;
						}
					}
					if (spiderpausecounter2 > 110) {
						if (((_role.getLayoutX() + _role.getFitWidth() / 2 - _spider2.getLayoutX()
								- _spider2.getFitWidth() / 2)
								/ Math.abs(_spider2.getLayoutX() + _spider2.getFitWidth() / 2 - _role.getLayoutX()
										- _role.getFitWidth() / 2)) == 1) {
							_spider2.setRotate(0);
						}
						if (((_role.getLayoutX() + _role.getFitWidth() / 2 - _spider2.getLayoutX()
								- _spider2.getFitWidth() / 2)
								/ Math.abs(_spider2.getLayoutX() + _spider2.getFitWidth() / 2 - _role.getLayoutX()
										- _role.getFitWidth() / 2)) == -1) {
							_spider2.setRotate(180);
						}
						spiderpausecounter2 = 0;
						jumpready2 = false;
					}
				}
			}
//------------------------------------------------------		
			for (int i = 0; i < _rightbullets.size(); i++) {
				if (_rightbullets.get(i).getLayoutX() + _rightbullets.get(i).getFitWidth() > peashooter.getx()
						&& _rightbullets.get(i).getLayoutX() < peashooter.getx() + peashooter.getfx()
						&& _rightbullets.get(i).getLayoutY() < peashooter.gety() + peashooter.getfy()
						&& _rightbullets.get(i).getLayoutY() + _rightbullets.get(i).getFitHeight() > peashooter
								.gety()) {
					RightDamageOnPeashooter = true;
					MediaPlayer roleatkplayer;
					Media roleatk = new Media(getClass().getResource("../bgm/collesion.wav").toString());
					roleatkplayer = new MediaPlayer(roleatk);
					roleatkplayer.play();
				}
			}
			for (int i = 0; i < _rightbullets.size(); i++) {
				if (_rightbullets.get(i).getLayoutX() + _rightbullets.get(i).getFitWidth() > _spider1.getLayoutX()
						&& _rightbullets.get(i).getLayoutX() < _spider1.getLayoutX() + _spider1.getFitWidth()
						&& _rightbullets.get(i).getLayoutY() < _spider1.getLayoutY() + _spider1.getFitHeight()
						&& _rightbullets.get(i).getLayoutY()
								+ _rightbullets.get(i).getFitHeight() > _spider1.getLayoutY() - 15) {
					RightDamageOnSpider1 = true;
					MediaPlayer roleatkplayer;
					Media roleatk = new Media(getClass().getResource("../bgm/collesion.wav").toString());
					roleatkplayer = new MediaPlayer(roleatk);
					roleatkplayer.play();
				}
			}
			for (int i = 0; i < _rightbullets.size(); i++) {
				if (_rightbullets.get(i).getLayoutX() + _rightbullets.get(i).getFitWidth() > _spider2.getLayoutX()
						&& _rightbullets.get(i).getLayoutX() < _spider2.getLayoutX() + _spider2.getFitWidth()
						&& _rightbullets.get(i).getLayoutY() < _spider2.getLayoutY() + _spider2.getFitHeight()
						&& _rightbullets.get(i).getLayoutY()
								+ _rightbullets.get(i).getFitHeight() > _spider2.getLayoutY() - 15) {
					RightDamageOnSpider2 = true;
					MediaPlayer roleatkplayer;
					Media roleatk = new Media(getClass().getResource("../bgm/collesion.wav").toString());
					roleatkplayer = new MediaPlayer(roleatk);
					roleatkplayer.play();
				}
			}
			for (int i = 0; i < _leftbullets.size(); i++) {
				if (_leftbullets.get(i).getLayoutX() + _leftbullets.get(i).getFitWidth() > peashooter.getx()
						&& _leftbullets.get(i).getLayoutX() < peashooter.getx() + peashooter.getfx()
						&& _leftbullets.get(i).getLayoutY() < peashooter.gety() + peashooter.getfy()
						&& _leftbullets.get(i).getLayoutY() + _leftbullets.get(i).getFitHeight() > peashooter.gety()) {
					LeftDamageOnPeashooter = true;
					MediaPlayer roleatkplayer;
					Media roleatk = new Media(getClass().getResource("../bgm/collesion.wav").toString());
					roleatkplayer = new MediaPlayer(roleatk);
					roleatkplayer.play();
				}
			}
			for (int i = 0; i < _leftbullets.size(); i++) {
				if (_leftbullets.get(i).getLayoutX() + _leftbullets.get(i).getFitWidth() > _spider1.getLayoutX()
						&& _leftbullets.get(i).getLayoutX() < _spider1.getLayoutX() + _spider1.getFitWidth()
						&& _leftbullets.get(i).getLayoutY() < _spider1.getLayoutY() + _spider1.getFitHeight()
						&& _leftbullets.get(i).getLayoutY() + _leftbullets.get(i).getFitHeight() > _spider1.getLayoutY()
								- 15) {
					LeftDamageOnSpider1 = true;
					MediaPlayer roleatkplayer;
					Media roleatk = new Media(getClass().getResource("../bgm/collesion.wav").toString());
					roleatkplayer = new MediaPlayer(roleatk);
					roleatkplayer.play();
				}
			}
			for (int i = 0; i < _leftbullets.size(); i++) {
				if (_leftbullets.get(i).getLayoutX() + _leftbullets.get(i).getFitWidth() > _spider2.getLayoutX()
						&& _leftbullets.get(i).getLayoutX() < _spider2.getLayoutX() + _spider2.getFitWidth()
						&& _leftbullets.get(i).getLayoutY() < _spider2.getLayoutY() + _spider2.getFitHeight()
						&& _leftbullets.get(i).getLayoutY() + _leftbullets.get(i).getFitHeight() > _spider2.getLayoutY()
								- 15) {
					LeftDamageOnSpider2 = true;
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
				peadamage = true;
			}
			if (_role.getLayoutX() + _role.getFitWidth() > _peashooter.getLayoutX()
					&& _role.getLayoutX() + 25 < _peashooter.getLayoutX() + _peashooter.getFitWidth()
					&& _role.getLayoutY() < _peashooter.getLayoutY() + _peashooter.getFitHeight()
					&& _role.getLayoutY() + _role.getFitHeight() > _peashooter.getLayoutY()) {
				peashootertouch = true;
			}
			if (_spider1.getLayoutX() + _spider1.getFitWidth() > _role.getLayoutX() + 25
					&& _spider1.getLayoutX() + 10 < _role.getLayoutX() + _role.getFitWidth()
					&& _spider1.getLayoutY() < _role.getLayoutY() + _role.getFitHeight()
					&& _spider1.getLayoutY() + _spider1.getFitHeight() > _role.getLayoutY()) {
				spidertouch1 = true;
			}
			if (_spider2.getLayoutX() + _spider2.getFitWidth() > _role.getLayoutX() + 25
					&& _spider2.getLayoutX() + 10 < _role.getLayoutX() + _role.getFitWidth()
					&& _spider2.getLayoutY() < _role.getLayoutY() + _role.getFitHeight()
					&& _spider2.getLayoutY() + _spider2.getFitHeight() > _role.getLayoutY()) {
				spidertouch2 = true;
			}
//------------------------------------------------------
			if (peashooter.gethp() <= 0) {
				if(PeashooterDeathCounter == 0) {
					_peashooterblood.setVisible(false);
					_peashooterbloodback.setVisible(false);
				}
				PeashooterDeathCounter++;
				if(PeashooterDeathCounter <= 30) {
					if(PeashooterDeathCounter % 6 == 0) {
						_peashooter.setOpacity(_peashooter.getOpacity() - 0.2);
					}
				}
				if(PeashooterDeathCounter == 30) {
					_background.getChildren().remove(_peashooter);
					peashooterdeath = true;
				}
			}
			if (spider1.gethp() <= 0) {
				_spider1.setLayoutX(-100);
				_spider1.setLayoutY(-100);
				if(Spider1DeathCounter == 0) {
					_spiderblood1.setVisible(false);
					_spiderbloodback1.setVisible(false);
				}
				Spider1DeathCounter++;
				if(Spider1DeathCounter <= 30) {
					if(Spider1DeathCounter % 6 == 0) {
						_spider1.setOpacity(_spider1.getOpacity() - 0.2);
					}
				}
				if(Spider1DeathCounter == 30) {
					_background.getChildren().remove(_spider1);
					spider1death = true;
				}
			}
			if (spider2.gethp() <= 0) {
				_spider2.setLayoutX(-100);
				_spider2.setLayoutY(-100);
				if(Spider2DeathCounter == 0) {
					_spiderblood2.setVisible(false);
					_spiderbloodback2.setVisible(false);
				}
				Spider2DeathCounter++;
				if(Spider2DeathCounter <= 30) {
					if(Spider2DeathCounter % 6 == 0) {
						_spider2.setOpacity(_spider2.getOpacity() - 0.2);
					}
				}
				if(Spider2DeathCounter == 30) {
					_background.getChildren().remove(_spider2);
					spider2death = true;
				}
			}
			if (peashooterdeath && spider1death && spider2death) {
				nextconter++;
				if (!showbuff && nextconter == 60) {
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
		time.getKeyFrames().addAll(frame);
		time.setCycleCount(Timeline.INDEFINITE);
		time.play();
		KeyFrame damageframe = new KeyFrame(Duration.millis(1000 / 60), (d) -> {
			if (bitedamage1) {
				if (BiteDamageCounter1 == 0) {
					maincontroller.newplayer.sethp(maincontroller.newplayer.gethp() - spider1.getatk());
					_showblood.setText(maincontroller.newplayer.gethp() + "/" + maincontroller.newplayer.getmaxhp());
					_blood.setWidth(maincontroller.newplayer.gethp() * 100 / maincontroller.newplayer.getmaxhp());
				}
				BiteDamageCounter1++;
				if (BiteDamageCounter1 == 60) {
					BiteDamageCounter1 = 0;
					bitedamage1 = false;
				}
			}
			if (spidertouch1) {
				if (SpiderTouchCounter1 == 0) {
					maincontroller.newplayer.sethp(maincontroller.newplayer.gethp() - spider1.getatk());
					_showblood.setText(maincontroller.newplayer.gethp() + "/" + maincontroller.newplayer.getmaxhp());
					_blood.setWidth(maincontroller.newplayer.gethp() * 100 / maincontroller.newplayer.getmaxhp());
				}
				SpiderTouchCounter1++;
				if (SpiderTouchCounter1 == 60) {
					SpiderTouchCounter1 = 0;
					spidertouch1 = false;
				}
			}
			if (bitedamage2) {
				if (BiteDamageCounter2 == 0) {
					maincontroller.newplayer.sethp(maincontroller.newplayer.gethp() - spider2.getatk());
					_showblood.setText(maincontroller.newplayer.gethp() + "/" + maincontroller.newplayer.getmaxhp());
					_blood.setWidth(maincontroller.newplayer.gethp() * 100 / maincontroller.newplayer.getmaxhp());
				}
				BiteDamageCounter2++;
				if (BiteDamageCounter2 == 60) {
					BiteDamageCounter2 = 0;
					bitedamage2 = false;
				}
			}
			if (spidertouch2) {
				if (SpiderTouchCounter2 == 0) {
					maincontroller.newplayer.sethp(maincontroller.newplayer.gethp() - spider2.getatk());
					_showblood.setText(maincontroller.newplayer.gethp() + "/" + maincontroller.newplayer.getmaxhp());
					_blood.setWidth(maincontroller.newplayer.gethp() * 100 / maincontroller.newplayer.getmaxhp());
				}
				SpiderTouchCounter2++;
				if (SpiderTouchCounter2 == 60) {
					SpiderTouchCounter2 = 0;
					spidertouch2 = false;
				}
			}
			if (peadamage) {
				maincontroller.newplayer.sethp(maincontroller.newplayer.gethp() - peashooter.getatk());
				_showblood.setText(maincontroller.newplayer.gethp() + "/" + maincontroller.newplayer.getmaxhp());
				_blood.setWidth(maincontroller.newplayer.gethp() * 100 / maincontroller.newplayer.getmaxhp());
			}
			if (peashootertouch) {
				if (PeashooterTouchCounter == 0) {
					maincontroller.newplayer.sethp(maincontroller.newplayer.gethp() - peashooter.getatk());
					_showblood.setText(maincontroller.newplayer.gethp() + "/" + maincontroller.newplayer.getmaxhp());
					_blood.setWidth(maincontroller.newplayer.gethp() * 100 / maincontroller.newplayer.getmaxhp());
				}
				PeashooterTouchCounter++;
				if (PeashooterTouchCounter == 60) {
					PeashooterTouchCounter = 0;
					peashootertouch = false;
				}
			}
			if (RightDamageOnPeashooter) {
				peashooter.sethp(peashooter.gethp() - maincontroller.newplayer.getatk());
				_peashooterblood.setWidth(peashooter.gethp() * 50 / 100 / ((maincontroller.level+2) / 3));
			}
			if (LeftDamageOnPeashooter) {
				peashooter.sethp(peashooter.gethp() - maincontroller.newplayer.getatk());
				_peashooterblood.setWidth(peashooter.gethp() * 50 / 100 / ((maincontroller.level+2) / 3));
			}
			if (RightDamageOnSpider1) {
				spider1.sethp(spider1.gethp() - maincontroller.newplayer.getatk());
				_spiderblood1.setWidth(spider1.gethp() * 50 / 100 / ((maincontroller.level+2) / 3));
			}
			if (LeftDamageOnSpider1) {
				spider1.sethp(spider1.gethp() - maincontroller.newplayer.getatk());
				_spiderblood1.setWidth(spider1.gethp() * 50 / 100 / ((maincontroller.level+2) / 3));
			}
			if (RightDamageOnSpider2) {
				spider2.sethp(spider2.gethp() - maincontroller.newplayer.getatk());
				_spiderblood2.setWidth(spider2.gethp() * 50 / 100 / ((maincontroller.level+2) / 3));
			}
			if (LeftDamageOnSpider2) {
				spider2.sethp(spider2.gethp() - maincontroller.newplayer.getatk());
				_spiderblood2.setWidth(spider2.gethp() * 50 / 100 / ((maincontroller.level+2) / 3));
			}
		});
		damage.getKeyFrames().addAll(damageframe);
		damage.setCycleCount(Timeline.INDEFINITE);
		damage.play();
		KeyFrame atkframe = new KeyFrame(Duration.millis(1), (e) -> {
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
		attack.getKeyFrames().addAll(atkframe);
		attack.setCycleCount(Timeline.INDEFINITE);
		attack.play();
		KeyFrame enemyatkframe = new KeyFrame(Duration.millis(1), (e) -> {
			if (!peashooterdeath) {
				if (PeaAttackCounter == 1800) {
					if (_peashooter.getLayoutX() + 12 > _role.getLayoutX() + _role.getFitWidth() / 2) {
						_changingpea = -1;
					} else {
						_changingpea = 1;
					}
					CurrentLoctionX = _role.getLayoutX() + _role.getFitWidth() / 2;
					_pea.setImage(peaimage);
					_pea.setFitWidth(20);
					_pea.setFitHeight(16);
					_pea.setVisible(true);
					_pea.setLayoutX(_peashooter.getLayoutX() + 12);
					_pea.setLayoutY(_peashooter.getLayoutY());
					peaready = true;
				}
				PeaAttackCounter++;
				if (PeaAttackCounter == 2000) {
					PeaAttackCounter = 0;
				}
			}
			if (showbuff) {
				enemyattack.stop();
			}
		});
		enemyattack.getKeyFrames().addAll(enemyatkframe);
		enemyattack.setCycleCount(Timeline.INDEFINITE);
		enemyattack.play();
	}

	@FXML
	private Pane _background;

	@FXML
	private ImageView _role;

	@FXML
	private ImageView _peashooter;

	@FXML
	private ImageView _spider1;

	@FXML
	private ImageView _spider2;

	@FXML
	private ImageView _bullet;

	@FXML
	private ImageView _pea;

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
	private Rectangle _peashooterblood;

	@FXML
	private Rectangle _peashooterbloodback;

	@FXML
	private Rectangle _spiderbloodback2;

	@FXML
	private Rectangle _spiderblood2;

	@FXML
	private Rectangle _spiderbloodback1;

	@FXML
	private Rectangle _spiderblood1;
	
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

	boolean w, a, d, atkpermission, face = true, lose = false, showbuff = false, peaready, peashooterdeath = false,
			spider1death = false, jumpready1 = false, spider2death = false, jumpready2 = false, bitedamage1 = false,
			peadamage = false, spidertouch1 = false, peashootertouch = false, bitedamage2 = false, spidertouch2 = false,
			RightDamageOnPeashooter = false, RightDamageOnSpider1 = false, RightDamageOnSpider2 = false,
			LeftDamageOnPeashooter = false, LeftDamageOnSpider1 = false, LeftDamageOnSpider2 = false, showlevel = false;

	int jumpcounter = 0, jumpcountdown = 20, attackcounter = 0, PeaAttackCounter = 0, peacounter = 0, _changingpea,
			spiderpausecounter1 = 0, spiderpausecounter2 = 0, BiteDamageCounter1 = 0, SpiderTouchCounter1 = 0,
			PeashooterTouchCounter = 0, BiteDamageCounter2 = 0, SpiderTouchCounter2 = 0, nextconter = 0,
			PeashooterDeathCounter = 0, Spider1DeathCounter = 0, Spider2DeathCounter = 0 , showlevelcounter = 0;

	double CurrentLoctionX, jumpCurrentLoctionX1, jumpCurrentLoctionX2;

	LinkedList<ImageView> _rightbullets = new LinkedList<ImageView>();
	LinkedList<ImageView> _leftbullets = new LinkedList<ImageView>();

	File bulletfile = new File(
			"C:/Users/Vivo Book Pro/Desktop/ce1002.Final.s107502538/src/ce1002/Final/s107502538/image/bullet.png");
	Image bulletimage = new Image(bulletfile.toURI().toString());
	File peafile = new File(
			"C:/Users/Vivo Book Pro/Desktop/ce1002.Final.s107502538/src/ce1002/Final/s107502538/image/pea.png");
	Image peaimage = new Image(peafile.toURI().toString());
	File spiderfile = new File(
			"C:/Users/Vivo Book Pro/Desktop/ce1002.Final.s107502538/src/ce1002/Final/s107502538/image/spider.png");
	Image spiderimage = new Image(spiderfile.toURI().toString());

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
			// ¹CÀ¸¿ï³æ
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
		maincontroller.newplayer.setatk(maincontroller.newplayer.getatk() + 15);
		FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/boss.fxml"));
		Parent boss = loadder.load();
		Scene bossscene = new Scene(boss);
		Boss Ctrl = loadder.getController();
		bossscene.setOnKeyPressed(Ctrl.onKeyPressed);
		bossscene.setOnKeyReleased(Ctrl.OnKeyReleased);
		bossscene.setOnMouseClicked(Ctrl.OnMouseClicked);
		Final.mainstage.setScene(bossscene);
	}

	@FXML
	void OnBuff2Pressed(ActionEvent event) throws IOException {
		if (maincontroller.newplayer.getmaxhp() - maincontroller.newplayer.gethp() < 250) {
			maincontroller.newplayer.sethp(maincontroller.newplayer.getmaxhp());
		} else {
			maincontroller.newplayer.sethp(maincontroller.newplayer.gethp() + 250);
		}
		FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/boss.fxml"));
		Parent boss = loadder.load();
		Scene bossscene = new Scene(boss);
		Boss Ctrl = loadder.getController();
		bossscene.setOnKeyPressed(Ctrl.onKeyPressed);
		bossscene.setOnKeyReleased(Ctrl.OnKeyReleased);
		bossscene.setOnMouseClicked(Ctrl.OnMouseClicked);
		Final.mainstage.setScene(bossscene);
	}

	@FXML
	void OnBuff3Pressed(ActionEvent event) throws IOException {
		maincontroller.newplayer.setmaxhp(maincontroller.newplayer.getmaxhp() + 500);
		FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/boss.fxml"));
		Parent boss = loadder.load();
		Scene bossscene = new Scene(boss);
		Boss Ctrl = loadder.getController();
		bossscene.setOnKeyPressed(Ctrl.onKeyPressed);
		bossscene.setOnKeyReleased(Ctrl.OnKeyReleased);
		bossscene.setOnMouseClicked(Ctrl.OnMouseClicked);
		Final.mainstage.setScene(bossscene);
	}
}
