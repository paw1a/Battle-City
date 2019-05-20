package GameState;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class GameStateManager {

	private GameState[] gameStates;
	private int currentState;
	
	public static final int NUMGAMESTATES = 33;
	public static final int LEVEL1STATE = 0;
	public static final int LEVEL2STATE = 1;
	public static final int LEVEL3STATE = 2;
	public static final int LEVEL4STATE = 3;
	public static final int LEVEL5STATE = 4;
	public static final int LEVEL6STATE = 5;
	public static final int LEVEL7STATE = 6;
	public static final int LEVEL8STATE = 7;
	public static final int LEVEL9STATE = 8;
	public static final int LEVEL10STATE = 9;
	public static final int MENUSTATE = 30;
	public static final int SELECTLEVEL = 31;
	public static final int CAREERSTATE = 32;
	
	
	public GameStateManager() {
		
		gameStates = new GameState[NUMGAMESTATES];
		
		currentState = MENUSTATE;
		loadState(currentState);
		
	}
	
	private void loadState(int state) {
		if(state == MENUSTATE)
			gameStates[state] = new MenuState(this);
		if(state == LEVEL1STATE)
			gameStates[state] = new LevelState(this);
		if(state == CAREERSTATE)
			gameStates[state] = new CareerState(this);
	}
	
	private void unloadState(int state) {
		gameStates[state] = null;
	}
	
	public void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
	}
	
	public void update() {
		try {
			gameStates[currentState].update();
		} catch(Exception e) {}
	}
	
	public void draw(Graphics2D g) {
		try {
			gameStates[currentState].draw(g);
		} catch(Exception e) {}
	}
	
	public void keyPressed(int k) {
		try{
			gameStates[currentState].keyPressed(k);
		} catch(Exception e) {}
	}
	
	public void keyReleased(int k) {
		try {	
			gameStates[currentState].keyReleased(k);
		} catch(Exception e) {}
	}
	
	public void mouseClicked(MouseEvent e) {
		try {	
			gameStates[currentState].mouseClicked(e);
		} catch(Exception g) {}
	}

	public void mouseEntered(MouseEvent e) {
		try {
			gameStates[currentState].mouseEntered(e);
		} catch(Exception g) {}
	}

	public void mouseExited(MouseEvent e) {
		try {
			gameStates[currentState].mouseExited(e); 
		} catch(Exception h) {}
	}

	public void mousePressed(MouseEvent e) {
		try {
			gameStates[currentState].mousePressed(e);
		} catch(Exception h) {}
	}

	public void mouseReleased(MouseEvent e) {
		try {
			gameStates[currentState].mouseReleased(e); 
		} catch(Exception g) {} 
	}
	
}
