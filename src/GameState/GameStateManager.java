package GameState;

import Util.Progress;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class GameStateManager {

	private GameState[] gameStates;
	private int currentState;
	private Progress pr;
	
	public static final int NUMGAMESTATES = 5;
	public static final int LEVELSTATE = 0;
	public static final int MENUSTATE = 1;
	public static final int SELECTLEVEL = 2;
	public static final int CAREERSTATE = 3;
	public static final int CONSTRUCTIONSTATE = 4;
	
	
	public GameStateManager() {
		
		gameStates = new GameState[NUMGAMESTATES];
		pr = Progress.getInstance();
		
		currentState = Integer.parseInt(pr.get("startState"));
		loadState(currentState);
	}
	
	private void loadState(int state) {
		if(state == MENUSTATE)
			gameStates[state] = new MenuState(this);
		else if(state == LEVELSTATE)
			gameStates[state] = new LevelState(this);
		else if(state == CAREERSTATE)
			gameStates[state] = new CareerState(this);
		else if(state == CONSTRUCTIONSTATE)
			gameStates[state] = new ConstructionState(this);
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

	public void mouseDragged(MouseEvent e) {
		try {
			gameStates[currentState].mouseDragged(e);
		} catch(Exception g) {}
	}

	public void mouseMoved(MouseEvent e) {
		try {
			gameStates[currentState].mouseMoved(e);
		} catch(Exception g) {}
	}
	
}
