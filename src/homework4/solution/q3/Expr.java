package homework4.solution.q3;

/***
 * Abs Func.
 * a composite element which Serves as a constant expression as defined in the example
 * 
 * Rep Inv.
 * none
 * @author Doron
 *
 */
public class Expr extends Statement {

	public Expr() {
	}
	
	@Override
	public String operation(int indentation) {
		return "expression";
	}
}
