package homework4.solution.q2;



/**
 * Part of the Strategy design pattern. context for the algorithms which the ColorGenerator uses in order
 * to select colors for the panels
 */
public interface ColorGeneratorContext {
	void setStrategy(ColorGeneratorStrategy strategy);
	void executeStrategy();
}
