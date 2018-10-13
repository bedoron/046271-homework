package homework4.solution.q2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Supply colors for panels in a random order
 *
 */
public class RandomStrategy implements ColorGeneratorStrategy {

	public RandomStrategy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<ColorGenerator.UpdatePanel> execute(ColorGenerator cg) {
		ArrayList<ColorGenerator.UpdatePanel> order = new ArrayList<ColorGenerator.UpdatePanel>();
		Random rn = new Random();

		// Shuffle
		int[] indices = new int[Billboard.PANEL_COLS*Billboard.PANEL_ROWS];
		for(int i=0; i < indices.length; ++i) {
			indices[i] = i;
		}
		
		for(int i=(indices.length-1); i>0; i--) {
			int messUp = rn.nextInt(i+1);
			int oldVal = indices[messUp]; 
			indices[messUp] = indices[i];
			indices[i] = oldVal;
		}
		
		// Set
		for(int i=0; i < indices.length; ++i) {
			Color color = Color.getHSBColor(rn.nextFloat(), rn.nextFloat(), rn.nextFloat());
			int row = indices[i]/Billboard.PANEL_COLS;
			int col = indices[i]%Billboard.PANEL_COLS;
			ColorGenerator.UpdatePanel update = cg.new UpdatePanel(row, col, color);
			order.add(update);			
		}
		
		return order;
	}

}
