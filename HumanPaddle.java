package com.pongGame.pkg;

import java.awt.Color;
import java.awt.Graphics;

public class HumanPaddle implements Paddle{
	// x and y are the start co-ordinates of the paddle
	// yVel is vertical velocity of the paddle
	// upAccel and downAccel will determine the direction of acceleration of paddle
	// player is to identify if it is user or AI. Also, can be used to determine the side of the paddle for human
	double y, yVel;
	boolean upAccel, downAccel;
	int player, x;
	final double GRAVITY = 0.94; // gravity co-efficient
	
	public HumanPaddle(int player) {
		// initializing the variables
		upAccel = false;
		downAccel = false;
		y = 210;
		yVel = 0;
		if(player == 1) { // Determining the side of paddle for human
			x = 20;
		}
		else {
			x = 660;
		}
	}
	@Override
	public void draw(Graphics g) { 
		g.setColor(Color.white);
		g.fillRect(x,(int)y, 20, 80); // drawing the paddle on the applet of 20*80 size
	}

	@Override
	public void move() {
		if(upAccel) {
			//define set of actions when up key is pressed
			yVel -= 2; // move paddle 2 co-ordinates upward
		}
		else if(downAccel) {
			//define set of actions when up key is pressed
			yVel += 2; // move paddle 2 co-ordinates downward
		}
		else if(!upAccel && !downAccel) {
			yVel *= GRAVITY;
		}
		// key press will keep adding the velocity (up or down)
		// hence need to control the movement speed of paddle
		// restricting the velocity of paddle
		if(yVel >=5)
			yVel = 5;
		else if(yVel <= -5)
			yVel = -5;
		
		y += yVel; // resetting the position of y co-ordinate
		// restricting the paddle from moving out of screen
		if(y<0)
			y = 0;
		if(y > 420) // height of screen - height of paddle
			y = 420;
	}
	
	public void setUpAccel(boolean input) { // Mutates for upAccel variable
		upAccel = input;
	}
	
	public void setDownAccel(boolean input) { // Mutates for downAccel variable
		downAccel = input;
	}

	@Override
	public int getY() {
		return (int)y;
	}

}
