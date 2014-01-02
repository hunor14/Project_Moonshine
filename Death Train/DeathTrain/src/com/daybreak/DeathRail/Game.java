package com.daybreak.DeathRail;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;














import sun.font.TextLabel;
import sun.java2d.pipe.TextRenderer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;

public class Game implements ApplicationListener, InputProcessor {

	SpriteBatch batch;
	Texture player;
	int playerWidth;
	int playerHeight;
	int LRButtonX=260;	//# X & Y positions at which 
	int LRButtonY=170; 	//# the Left & Right Buttons should be drawn
	
	Vector2 playerPosition;
	ShapeRenderer drawShape;
	int touchX=0,touchY=0;
	BitmapFont font;
	
	float theBigScale=1;
	
	@Override
	public void create() {		
		Texture.setEnforcePotImages(false);  //TEMPORARY - stops enforcing that images' height and width must be a power of 2.
		
		//Gdx.graphics.setDisplayMode(
	
		font = new BitmapFont(); //font for rendering touch coordinates
		font.setColor(Color.MAGENTA);
		font.setScale(3);
		
		batch = new SpriteBatch();
		
		
		player = new Texture(Gdx.files.internal("soldier_idle_0.gif"));
		playerWidth = player.getWidth()*3;
		playerHeight = player.getHeight()*3;
		
		playerPosition = new Vector2((Gdx.graphics.getWidth()/2)-(playerWidth/2),(Gdx.graphics.getHeight()/2)-(playerHeight/2));
		
		drawShape = new ShapeRenderer();


	}

	@Override
	public void dispose() {

	}

	
	@Override
	public void render() {
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		

		

		
		
		//random black circle
		drawShape.setColor(Color.BLACK);
		drawShape.begin(ShapeType.FilledCircle);
		drawShape.filledCircle(100, 100, 50);
		drawShape.end();

		
		//zqsd key detection (temp)
		if(keyDown(54)){ //Z
			playerPosition.y+=3;
		}
		if(keyDown(47)){ //S
			playerPosition.y-=3;
		}
		if(keyDown(32)){ //D
			playerPosition.x+=3;
		}
		if(keyDown(45)){ //Q
			playerPosition.x-=3;
		}
		 

		//draw player
		batch.begin();
		batch.draw(player,playerPosition.x,playerPosition.y,playerWidth,playerHeight);
		batch.end();
		
		
		//calculating new coordinates, if any
		if(Gdx.input.isTouched()){
			touchX = Gdx.input.getX();
			touchY = Gdx.graphics.getHeight()-Gdx.input.getY();
		}
		//draw a shape at coordinates
		drawShape.begin(ShapeType.Line);
		drawShape.setColor(Color.RED);
		drawShape.line(touchX,0,touchX,Gdx.graphics.getHeight()); 		//x-line
		drawShape.line(0,touchY,Gdx.graphics.getWidth(),touchY);		//y-line
		drawShape.end();
		

		//Control UI
		//Draw aim control
		Gdx.gl.glEnable(GL10.GL_BLEND);											//# Enable
		Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);		//# Transparency/Opacity

		drawShape.begin(ShapeType.FilledCircle);
		drawShape.setColor(0.5f,0.5f,0.5f,0.5f);
		drawShape.filledCircle(1500,200,150);
		drawShape.end();
		
		//Draw right & left pads
		drawShape.begin(ShapeType.FilledTriangle);
		drawShape.setColor(0.8f,0.1f,0.1f,0.5f);
		drawShape.filledTriangle(LRButtonX+40, LRButtonY+100, LRButtonX+170, LRButtonY, LRButtonX+40, LRButtonY-100);
		drawShape.filledTriangle(LRButtonX-40, LRButtonY+100, LRButtonX-170, LRButtonY, LRButtonX-40, LRButtonY-100);
		drawShape.end();
		
		Gdx.gl.glDisable(GL10.GL_BLEND);										//# Disable Transparency/Opacity
		//End control UI
		
		
		//drawing coordinates
		batch.begin();
		font.draw(batch, "x: " + touchX, 10, Gdx.graphics.getHeight()-10);
		font.draw(batch, "y: " + touchY, 10, Gdx.graphics.getHeight()-50);
		batch.end();
			
	}

	
	public void resize(int width, int height) {
	}

	
	public void pause() {
	}

	
	public void resume() {
	}

	public boolean keyDown(int keycode) {
		if(Gdx.input.isKeyPressed(keycode)){
			return true;
		}else{
			return false;
		}
	}

	
	public boolean keyUp(int keycode) {
		if(!Gdx.input.isKeyPressed(keycode)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		//if(Gdx.input.isTouched()){
		//	return true;
		//}else{
			return false;
		//}
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
