package com.pongGame.pkg;

import java.awt.Color;
import java.awt.Graphics;

public class AIPaddle implements Paddle{
	// x and y are the start co-ordinates of the paddle
	// yVel is vertical velocity of the paddle
	// upAccel and downAccel will determine the direction of acceleration of paddle
	// player is to identify if it is user or AI. Also, can be used to determine the side of the paddle for human
	double y, yVel;
	boolean upAccel, downAccel;
	int player, x;
	final double GRAVITY = 0.94; // gravity co-efficient
	Ball b1; // to track the movement of the ball
	
	public AIPaddle(int player, Ball b) {
		// initializing the variables
		b1 = b;
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
		y = b1.getY() - 40; // resetting the position of y co-ordinate to center of the ball
		// restricting the paddle from moving out of screen
		if(y<0)
			y = 0;
		if(y > 420) // height of screen - height of paddle
			y = 420;
	}
	
	@Override
	public int getY() {
		return (int)y;
	}

}
