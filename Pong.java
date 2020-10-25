package com.pongGame.pkg;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pong extends Applet implements Runnable, KeyListener{ // KeyListener interface for receiving keyboard events
	private static final long serialVersionUID = -5762027132428989654L;
	final int WIDTH = 700, HEIGHT = 500; // declaring the size of the applet
	Thread thread; // creating global variable of Thread type
	HumanPaddle p1; // creating global variable of HumanPaddle type
	AIPaddle p2; // creating global variable of AIPaddle type
	Ball b1; // creating global variable of Ball type
	boolean gameStart; // check if game has started or not
	Graphics gfx;
	Image img;
	
	public void init() {
		this.resize(WIDTH,HEIGHT); // to resize the size of applet
		gameStart = false;
		this.addKeyListener(this); // ??
		p1 = new HumanPaddle(1); // creating instance of HumanPaddle. 1 is to have human paddle on left
		b1 = new Ball(); // creating instance of HumanPaddle.
		p2 = new AIPaddle(2,b1); // creating instance of AIPaddle. b1 to to track movement of ball
		img = createImage(WIDTH,HEIGHT);
		gfx = img.getGraphics();
		thread = new Thread(this); // creating instance of Thread
		thread.start(); // starting the new thread 
	}
	
	public void paint(Graphics g) {
		gfx.setColor(Color.black); // setting background color to black
		gfx.fillRect(0,0,WIDTH,HEIGHT); // filling applet with the defined color
		// if ball goes out of screen from left or right screen, end the game
		if(b1.getX() < -10 || b1.getX() > 710) {
			gfx.setColor(Color.red);
			gfx.drawString("Game Over!!!", WIDTH/2, HEIGHT/2);
		}
		else {
			p1.draw(gfx); // drawing the human paddle
			b1.draw(gfx); // drawing the ball
			p2.draw(gfx); // drawing the AI paddle
		}
		if(!gameStart) {
			gfx.setColor(Color.white);
			gfx.drawString("PONG!!!",340,100);
			gfx.drawString("Press ENTER key to begin...", 300, 130);
		}
		g.drawImage(img,0,0,this);
	}
	
	public void update(Graphics g) {
		paint(g); //??
	}

	@Override
	public void run() {
		for(;;) { // created infinite loop
			if(gameStart) {
				p1.move(); // each time it iterates, paddle should be moved
				p2.move(); // move the AI paddle in each iteration
				b1.move(); // move the ball in each iteration
				b1.checkPaddleCollision(p1, p2);	
			}
			repaint(); //??
			// sleep for 10ms
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) { // VK_UP - Virtual Keyboard UP key
			p1.setUpAccel(true); // to move the Human Paddle up
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) { // VK_DOWN - Virtual Keyboard DOWN key
			p1.setDownAccel(true); // to move the Human Paddle down
		}
		else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			gameStart = true; // start the game when enter is pressed
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) { // VK_UP - Virtual Keyboard UP key
			p1.setUpAccel(false); // to stop the Human Paddle from moving down
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) { // VK_DOWN - Virtual Keyboard DOWN key
			p1.setDownAccel(false); // to stop the Human Paddle from moving down
		}
	}
}
