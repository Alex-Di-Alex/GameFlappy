package com.uraall.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class Flappy extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture[] bird;
	int birdStateFlag = 0;
	float flyHeight;
	float fallSpeed = 0;
	int gameStateFlag = 0;
	Texture topTube;
	Texture bottomTube;
	int spaceBetweenTune = 500;
	Random random;
	int tubeSpeed = 5;
	int tubesNumber = 5;
	float tubeX[] = new float[tubesNumber];
	float tubeShift[] = new float[tubesNumber];
	float distanceBetweenTubes;
	Circle birdCircle;
	ShapeRenderer shapeRenderer;




	@Override
	public void create() {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		background = new Texture("background.png");
		birdCircle = new Circle();
		shapeRenderer = new ShapeRenderer();
		bird = new Texture[2];
		bird[0] = new Texture("bird_wings_up.png");
		bird[1] = new Texture("bird_wings_down.png");
		flyHeight = Gdx.graphics.getHeight() / 2 - bird[0].getHeight() / 2;
		topTube = new Texture("top_tube.png");
		bottomTube = new Texture("bottom_tube.png");
		random = new Random();

		distanceBetweenTubes = Gdx.graphics.getWidth() / 2;
		for (int i = 0; i < tubesNumber; i++){

			tubeX[i] = Gdx.graphics.getWidth() / 2 - topTube.getWidth() / 2 + i * distanceBetweenTubes;
			tubeShift[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - spaceBetweenTune - 200);

		}


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
		for (int i = 0; i < tubesNumber; i++){
			if (tubeX[i] < -topTube.getWidth()){

				tubeX[i] = tubesNumber * distanceBetweenTubes;

			}else {

				tubeX[i] -= tubeSpeed;

			}



		batch.draw(topTube, tubeX[i], Gdx.graphics.getHeight() / 2 + spaceBetweenTune / 2 + tubeShift[i]);
		batch.draw(bottomTube, tubeX[i], Gdx.graphics.getHeight() / 2 - spaceBetweenTune / 2 - bottomTube.getHeight() + tubeShift[i]);

		}

		if (birdStateFlag == 0) {
			birdStateFlag = 1;
		} else {

			birdStateFlag = 0;
		}


		batch.draw(bird[birdStateFlag], Gdx.graphics.getWidth() / 2 - bird[birdStateFlag].getWidth() / 2, flyHeight);
		batch.end();
		birdCircle.set(Gdx.graphics.getWidth() / 2, flyHeight + bird[birdStateFlag].getHeight() / 2, bird[birdStateFlag].getWidth() / 2);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.CYAN);
		shapeRenderer.circle(birdCircle.x, birdCircle.y, birdCircle.radius);
		shapeRenderer.end();

	}


	public void dispose() {

	}

}