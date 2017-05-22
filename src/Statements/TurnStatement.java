package Statements;

import Expressions.Expression;
import Expressions.ValueExpression;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

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
	
	public void execute() throws IllegalArgumentException, ModelException{
		if(getAngle() instanceof ValueExpression){
			try {
				this.getProgram().getShip().rotate(((ValueExpression) getAngle()).evaluate());
			} catch (ModelException e) {
				throw new ModelException("ModelException in TurnStatement");
			}
		}
		else{
			throw new IllegalArgumentException("The expression does not evaluate to a value");
		}
	}
}
