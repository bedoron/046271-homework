package homework4.solution.q2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.Timer;

/***
 * This class is the color generator which is responsible to generate random colors for all defines panels
 * every predetermined time an even of random color picking will start which will then, in turn trigger 
 * the appropriate event in the observing objects (in our case, the panels).
 * The class will order the update events by several different strategies that are of ColorGeneratorStrategy type
 * and this will also serve as a context for them.
 * 
 * Rep Inv.
 * This shall be a singleton, strategy may be null.
 * 
 * Abs. Function.
 * Generate events for random colors choosing for different panels which can observe this.
 *
 */
public class ColorGenerator extends Observable implements ColorGeneratorContext {
	private static ColorGenerator instance = new ColorGenerator(); // Eager initialization because we need this ready for the gui thread
	private static int DELAY_TO_EVENT = 2000; // 2 seconds
	private static int DELAY_BETWEEN_UPDATES = 40;
	private ColorGeneratorStrategy strategy = null;
	
	private ColorGenerator() { } // Disallow the creation of this

	/***
	 * Singleton entry point
	 * @return this
	 */
	public static ColorGenerator getInstance() {
		return instance;
	}

	/***
	 * Startup function... create a billboard and starts execute strategy timer.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Code generator app main");
		
		@SuppressWarnings("unused")
		Billboard bb = new Billboard();
		
		new Timer(DELAY_TO_EVENT, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ColorGenerator.getInstance().executeStrategy();
			}
		}).start();
		
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***
	 * Abs Function.
	 * Internal class which is being passed as an event object when a specific panel is being updated.
	 * a column and a row are enough to fully identify a specific panel in the billboard. 
	 * it will allow to send the new color and identify the panel which the event was targeted for.
	 * 
	 * Rep Inv.
	 * row & col need to be within range of 0 and available panels which are defined by Billboard.java
	 * color can't be null and should represent a valid color
	 *
	 */
	public class UpdatePanel {
		private int row = -1;
		private int col = -1;
		private Color color = Color.WHITE; // Default color

		/**
		 * @return the row
		 */
		public int getRow() {
			return row;
		}

		/**
		 * @return the col
		 */
		public int getCol() {
			return col;
		}

		/**
		 * @return the color
		 */
		public Color getColor() {
			return color;
		}

		/***
		 * Creates a new this
		 * @param row target panel in row
		 * @param col target panel in col
		 * @param color
		 */
		public UpdatePanel(int row, int col, Color color) {
			this.row = row;
			this.col = col;
			this.color = color;
			checkRep();
		}
		
		private void checkRep() {
			assert((row>0)&&(row<Billboard.PANEL_ROWS)): "Invalid row update";
			assert((col>0)&&(col<Billboard.PANEL_COLS)): "Invalid col update";
			assert(color != null): "Color can't be null";
		}
	}

	/***
	 * Sets the strategy required by Strategy context
	 */
	@Override
	public void setStrategy(ColorGeneratorStrategy strategy) {
		this.strategy = strategy;
		checkRep();
	}

	/***
	 * Execute strategy algorithm outcome - this will call all the observers which
	 * registered to receive events from this
	 * @effects this
	 * @effects observing objects
	 */
	@Override
	public void executeStrategy() {
		if(strategy != null) {
			ArrayList<UpdatePanel> order = strategy.execute(this);
			Timer[] timers = new Timer[order.size()];
			
			for(int i=0; i < order.size(); ++i) {
				int currentDelay = i * DELAY_BETWEEN_UPDATES;
				final UpdatePanel up = order.get(i);
				timers[i] = new Timer(currentDelay, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						setChanged();
						notifyObservers(up);
					}
				});
				timers[i].setRepeats(false);
				timers[i].start();
			}
			
		} else  {
			System.out.println("Strategy wasn't defined, doing nothing...");
		}
	}
	
	private void checkRep() {
		assert(strategy != null): "Strategy can't be null";
	}
}
