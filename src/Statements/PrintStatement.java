package Statements;

import Expressions.EntityExpression;
import Expressions.Expression;
import Expressions.ValueExpression;
import asteroids.part3.programs.SourceLocation;

public class PrintStatement extends VoidStatement {

	public PrintStatement(Expression value, SourceLocation sourceLocation){
		setValue(value);
	}
	
	private Expression value;
	private SourceLocation sourceLocation;
	
	public Expression getValue() {
		return value;
	}

	public void setValue(Expression value) {
		this.value = value;
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	public void execute(){
		if (getValue() instanceof ValueExpression){
			System.out.println(((ValueExpression) getValue()).evaluate());
			this.getFunction().getProgram().addPrintedObject(((ValueExpression) getValue()).evaluate());
		}
		else if (getValue() instanceof EntityExpression){
			System.out.println(((EntityExpression) getValue()).evaluate().toString());
			this.getFunction().getProgram().addPrintedObject(((EntityExpression) getValue()).evaluate());
		}
		else{
			throw new IllegalArgumentException("The expression does not evaluate to a value");
		}
		
	}
}
