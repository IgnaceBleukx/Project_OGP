package Statements;

import Expressions.Expression;
import asteroids.part3.programs.SourceLocation;

public class PrintStatement extends Statement {

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
}
