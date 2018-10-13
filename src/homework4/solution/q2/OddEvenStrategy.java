package homework4.solution.q2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Supply colors for panels in an odd/even order
 *
 */
public class OddEvenStrategy implements ColorGeneratorStrategy {

	public OddEvenStrategy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<ColorGenerator.UpdatePanel> execute(ColorGenerator cg) {
		ArrayList<ColorGenerator.UpdatePanel> order = new ArrayList<ColorGenerator.UpdatePanel>();
		Random rn = new Random();
		
		ArrayList<ColorGenerator.UpdatePanel> even = new ArrayList<ColorGenerator.UpdatePanel>();
		ArrayList<ColorGenerator.UpdatePanel> odd = new ArrayList<ColorGenerator.UpdatePanel>();
		
		for(int i=0; i < (Billboard.PANEL_COLS*Billboard.PANEL_ROWS); ++i) {
			Color color = Color.getHSBColor(rn.nextFloat(), rn.nextFloat(), rn.nextFloat());
			int row = i/Billboard.PANEL_COLS;
			int col = i%Billboard.PANEL_COLS;
			ColorGenerator.UpdatePanel update = cg.new UpdatePanel(row, col, color);
			
			if(i%2==0) {
				even.add(update);
			} else {
				odd.add(update);
			}
		}

		order.addAll(odd);
		order.addAll(even);
		return order;
	}

}
