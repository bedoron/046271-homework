package homework4.solution.q3;

/***
 * Rep. Abs.
 * Composite element which represents an assignment.
 * 
 * Rep. Inv.
 * left value can't be null.
 * if exp is null then null string is printed as an assignment.
 *
 */
public class Assignment extends Statement {
	String lval = null;
	Expr exp = null;
	
	public Assignment(String lval, Expr exp) {
		this.lval = lval;
		this.exp = exp;
		checkRep();
	}
	
	private void checkRep() {
		assert(lval!=null): "Left value of assignment can't be null";
	}

	@Override
	public String operation(int indentation) {
		String tabs = tabs(indentation);
		String out = tabs + lval + " := ";
		if(exp==null) {
			out += "null";
		} else {
			out += exp.operation(indentation);
		}
		out += "\n";
		return out;
	}
	
	

}
