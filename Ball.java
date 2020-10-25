package com.pongGame.pkg;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	// variables for determining the speed and position of the ball
	double xVel, yVel, x, y;
	
	public Ball() {
		x = 350;
		y = 250;
		xVel = getRandomVelocity() * getRandomDirection();
		yVel = getRandomVelocity() * getRandomDirection();
		
	}
	
	public double getRandomVelocity() {
		return Math.random()*3 + 2;
	}
	
	public int getRandomDirection() {
		int random = (int)(Math.random()*2);
		if(random == 1)
			return 1;
		else
			return -1;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval((int)x-10, (int)y-10, 20, 20); // drawing the ball on the applet in center of size 20*20
	}
	
	public void checkPaddleCollision(Paddle p1, Paddle p2) {
		if(x <= 50) { // check if ball hits the human paddle surface
			if(y >= p1.getY() && y <= p1.getY()+80) // check if collision is within the height of the human paddle
				xVel = -xVel;
		}
		else if(x >= 650) { // check if ball hits the AI paddle surface
			if(y >= p2.getY() && y <= p2.getY()+80) // check if collision is within the height of the AI paddle
				xVel = -xVel;
			
		}
	}
	
	public void move() {
		x += xVel;
		y += yVel;
		// restricting the ball from going off screen (change direction of the ball)
		if(y < 10)
			yVel = -yVel;
		if(y > 490)
			yVel = -yVel;
	}
	
	public int getX() {
		return (int)x;
	}
	
	public int getY() {
		return (int)y;
	}
}
