package homework4.solution.q2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;


/**
 * Supply colors for panels in an ascending order
 *
 */
public class AscendingStrategy implements ColorGeneratorStrategy {

	public AscendingStrategy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<ColorGenerator.UpdatePanel> execute(ColorGenerator cg) {
		ArrayList<ColorGenerator.UpdatePanel> updateOrder = new ArrayList<>();
		Random rn = new Random();
		
		for(int row = 0; row < Billboard.PANEL_ROWS; ++row) {
			for(int col = 0; col < Billboard.PANEL_COLS; ++col) {
				Color color = Color.getHSBColor(rn.nextFloat(), rn.nextFloat(), rn.nextFloat());
				ColorGenerator.UpdatePanel update = cg.new UpdatePanel(row, col, color);
				updateOrder.add(update);				
			}
		}
		return updateOrder;
	}

}
