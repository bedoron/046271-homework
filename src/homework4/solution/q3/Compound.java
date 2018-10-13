package homework4.solution.q3;

/***
 * Rep Abs.
 * a composite element which represents a statement block which is surrounded by a begin-end
 * 
 * Rep Inv.
 * Block of statements, the block is surrounded by a begin-end
 * null statements will create an empty block.
 * Statement is an ellipsis (can hold any amount of statements)
 *
 */
public class Compound extends Statement {

	public Compound(Statement... statements ) {
		if(statements != null) {
			for(Statement statement : statements) {
				add(statement);
			}
		}
	}

	@Override
	public String operation(int indentation) {
		String tabs = tabs(indentation);
		String out = tabs + "begin\n";
		out += super.operation(indentation+1);
		out += tabs + "end\n";
		return out;
	}
	
}
