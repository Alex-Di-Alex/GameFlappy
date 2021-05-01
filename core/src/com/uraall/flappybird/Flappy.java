package com.uraall.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Flappy extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	//Texture bird_wings_up;
	Texture[] bird;
	int birdStateFlag = 0;
	float flyHeight;
	float fallSpeed = 0;
	int gameStateFlag = 0;
	Texture topTube;
	Texture bottomTube;
	int spaceBetweenTune = 500;





	@Override
	public void create() {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		background = new Texture("background.png");
		bird = new Texture[2];
		bird[0] = new Texture("bird_wings_up.png");
		bird[1] = new Texture("bird_wings_down.png");
		flyHeight = Gdx.graphics.getHeight() / 2 - bird[0].getHeight() / 2;
		topTube = new Texture("top_tube.png");
		bottomTube = new Texture("bottom_tube.png");

	}

	@Override
	public void render() {

		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		if (Gdx.input.justTouched()) {
			Gdx.app.log("Tap", "OOOps");
			gameStateFlag = 1;

		}
		if (gameStateFlag == 1) {

			batch.draw(topTube, Gdx.graphics.getWidth() / 2 - topTube.getWidth() / 2, Gdx.graphics.getHeight() / 2 + spaceBetweenTune / 2);
			batch.draw(bottomTube, Gdx.graphics.getWidth() / 2 - bottomTube.getWidth() / 2, Gdx.graphics.getHeight() / 2 - spaceBetweenTune / 2 - bottomTube.getHeight());

			if (Gdx.input.justTouched()) {

fallSpeed = -30;
			}
if (flyHeight > 0 || fallSpeed < 0){
			fallSpeed++;
			flyHeight -= fallSpeed;}
		} else {

			if (Gdx.input.justTouched()) {
				Gdx.app.log("Tap", "OOOps");
				gameStateFlag = 1;
			}

		}

		if (birdStateFlag == 0) {
			birdStateFlag = 1;
		} else {

			birdStateFlag = 0;
		}


		batch.draw(bird[birdStateFlag], Gdx.graphics.getWidth() / 2 - bird[birdStateFlag].getWidth() / 2, flyHeight);
		batch.end();

	}


	public void dispose() {

	}

}