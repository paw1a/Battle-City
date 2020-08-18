package main;

import states.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {

	public static final int WIDTH = (416 + 150 + 160) * 2;
	public static final int HEIGHT = 416 * 2;

	private Thread thread;
	private boolean running;

	private static BufferedImage image;
	private static Graphics2D g;
	
	private int FPS = 30;
	private int targetTime = 1000 / FPS;
	
	GameStateManager gsm;

	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	private void init() {
		running = true;
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		gsm = new GameStateManager();
	}
	
	
	@Override
	public void run() {

		init();

		long startTime;
		long urdTime;
		long waitTime;

		while(running) {

			startTime = System.nanoTime();
			//System.out.println("Before update "+System.currentTimeMillis());
			update();
			//System.out.println("After update "+System.currentTimeMillis());
			draw();
			//System.out.println("After draw "+System.currentTimeMillis());
			drawToScreen();
			//System.out.println("After draw to screen "+System.currentTimeMillis());

			urdTime = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime - urdTime;
			if(waitTime < 0) waitTime = 0;
			try{
				Thread.sleep(waitTime);
			} catch(Exception e) {}
			//System.out.println("After wait"+System.currentTimeMillis());
		}
	}
	
	private void update() {
		gsm.update();
	}
	
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
	
	private void draw() {
		gsm.draw(g);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e.getKeyCode());
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		gsm.keyReleased(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		gsm.mouseClicked(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		try {
			gsm.mouseEntered(e);
		} catch(Exception g) {}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		gsm.mouseExited(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gsm.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		gsm.mouseReleased(e);
	}
	
	public static Graphics2D getG() {
		return g;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		gsm.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		try {
			gsm.mouseMoved(e);
		} catch (Exception g) {}
	}
}
