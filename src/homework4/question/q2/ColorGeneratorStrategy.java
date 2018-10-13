package homework4.question.q2;
import java.util.ArrayList;

/***
 * Abs. Func.
 * Part of the Strategy design pattern. allows the ColorGenerator to switch between coloring algorithms
 * 
 * Rep Inv.
 * Supply a list of new colors by a specific order
 *
 */
public interface ColorGeneratorStrategy {
	ArrayList<ColorGenerator.UpdatePanel> execute(ColorGenerator cg);
}
