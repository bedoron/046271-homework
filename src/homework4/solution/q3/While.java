package homework4.solution.q3;

/***
 * Rep Abs.
 * Composite element which represents a while statement in the language. a while statement should have an 
 * expression to evaluate and may have a statment to execute. if no statement found an empty block will be printed 
 * Rep Inv.
 * Expression may not be null
 * @author Doron
 *
 */
public class While extends Statement {
	Expr expr = null;
	Statement stmt = null;
	
	public While(Expr expr, Statement stmt) {
		this.expr = expr;
		this.stmt = stmt;
		checkRep();
	}

	private void checkRep() {
		assert(expr != null): "While Expression can't be null";
	}
	
	public String operation(int indentation) {
		String tabs = tabs(indentation);
		String output = tabs + "while " + expr.operation(indentation) + " do\n";
		if(null != stmt) {
			output += stmt.operation(indentation + 1);
		}
		return output;
	}
}
