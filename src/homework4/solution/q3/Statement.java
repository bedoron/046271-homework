package homework4.solution.q3;

import java.util.ArrayList;

/**
 * Rep. Abs.
 * Statement class for the Composite design pattern. all elements in this
 * programming language will be derived from this.
 * Any leaf would be printed out by the format that was supplied by hw. question.
 * 
 * Rep. Inv.
 * Can't hold null leaves. a leaf should be of type statement.
 */
public abstract class Statement {
	ArrayList<Statement> leaves = new ArrayList<>();
	
	/**
	 * Adds an element to the composite
	 * @param composite the statement to add
	 * @effects this
	 */
	public void add(Statement composite) {
		leaves.add(composite);
		checkRep();
	}
	
	/**
	 * Removes an element from the composite
	 * @param composite
	 * @return true if removed, otherwise false
	 * @effects this
	 */
	public boolean remove(Statement composite) {
		boolean retVal =leaves.remove(composite);
		checkRep();
		return retVal;
	}
	
	/**
	 * Checks for null leaves
	 */
	private void checkRep() {
		for(Statement leaf : leaves) {
			assert(leaf!=null): "Can't have null elements";
		}
	}
	
	/***
	 * Static function which generates spaces according to indentation
	 * if indentation is non positive no indentation would be created
	 * @param indentation how many double spaces to add
	 * @return a double space times indentation string
	 */
	public static String tabs(int indentation) {
		String tabs = "";
		for(int i = 0; i < indentation; ++i) {
			tabs += "  ";
		}
		return tabs;
	}
	
	/***
	 * Calls the operation of all composites with the specified indentation.
	 * I've added the indentation because coupling it out from the composite
	 * would make my life harder.
	 * @param indentation indentation level of current block
	 * @return string representation of this block
	 */
	public String operation(int indentation) {		
		String output = "";
		for(Statement statement : leaves) {
			output += statement.operation(indentation);
		}
		return output;
	}
	
	public String toString() {
		return operation(0);
	}
	
	public static void main(String[] args) {
		Statement factorial = new Compound(
				new Assignment("fact", new Expr()),
				new While(new Expr(), new Compound(
						new Assignment("fact", new Expr()),
						new Assignment("n", new Expr())
						))
				);
		
		System.out.println(factorial);
	}
}
