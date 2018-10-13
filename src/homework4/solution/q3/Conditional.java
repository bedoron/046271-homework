package homework4.solution.q3;

/***
 * Rep Abs.
 * Composite element which represents a conditional statement. a conditional
 * statement should have an expression to evaluate and may have a true & false
 * statments which will be executed/printed accordingly
 * 
 * Rep Inv.
 * Expression may not be null
 * any of the true/false statements can be null, they will show empty blocks
 *
 */
public class Conditional extends Statement {
	Expr exp = null;
	Statement stmtTrue = null;
	Statement stmtFalse = null;
	
	public Conditional(Expr exp, Statement stmtTrue, Statement stmtFalse) {
		this.exp = exp;
		this.stmtTrue = stmtTrue;
		this.stmtFalse = stmtFalse;
		checkRep();
	}
	
	private void checkRep() {
		assert(exp!=null): "Expression can't be null";
	}

	/* (non-Javadoc)
	 * @see q3.hw4.oopCourse.Statement#operation(int)
	 */
	@Override
	public String operation(int indentation) {
		String tabs = tabs(indentation);
		String output = tabs + "if " + exp.operation(indentation) + "then\n";
		if(null != stmtTrue) {
			output += stmtTrue.operation(indentation + 1);
		}
		if(null != stmtFalse) {
			output += tabs + "else\n";
			output += stmtFalse.operation(indentation + 1);
		}
		return output;
	}
}
