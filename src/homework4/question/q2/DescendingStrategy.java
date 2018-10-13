package homework4.question.q2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Supply colors for panels in a descending order
 *
 */
public class DescendingStrategy implements ColorGeneratorStrategy {

	public DescendingStrategy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<ColorGenerator.UpdatePanel> execute(ColorGenerator cg) {
		ArrayList<ColorGenerator.UpdatePanel> updateOrder = new ArrayList<>();
		Random rn = new Random();
		
		for(int row = Billboard.PANEL_ROWS-1; row >= 0; --row) {
			for(int col = Billboard.PANEL_COLS-1; col >= 0; --col) {
				Color color = Color.getHSBColor(rn.nextFloat(), rn.nextFloat(), rn.nextFloat());
				ColorGenerator.UpdatePanel update = cg.new UpdatePanel(row, col, color);
				updateOrder.add(update);				
			}
		}
		return updateOrder;
	}

}
