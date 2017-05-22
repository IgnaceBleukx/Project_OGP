package Statements;

import Expressions.Expression;
import Expressions.ValueExpression;
import asteroids.part3.programs.SourceLocation;

public class TurnStatement extends VoidStatement {

	public TurnStatement(Expression angle, SourceLocation sourceLocation){
		setAngle(angle);
		setSourceLocation(sourceLocation);
	}
	
	private Expression angle;
	public Expression getAngle() {
		return angle;
	}
	public void setAngle(Expression angle) {
		this.angle = angle;
	}
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}
	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	private SourceLocation sourceLocation;
	
	public void execute() throws IllegalArgumentException{
		if(getAngle() instanceof ValueExpression){
			this.getFunction().getProgram().getShip().rotate(((ValueExpression) getAngle()).evaluate());
		}
		else{
			throw new IllegalArgumentException("The expression does not evaluate to a value");
		}
	}
}
