package Statements;

import Expressions.Expression;
import Expressions.ValueExpression;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class TurnStatement extends ActionStatement {

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
	
	public void execute() throws ModelException, BreakException{
		if (getFunction() != null){
			throw new ModelException("Turn action in function body");
		}
		if(getAngle() instanceof ValueExpression){
			try {
				passInformation(getAngle());
				if(this.getProgram().getFirstRun()){
					this.getProgram().setTimeNeeded(this.getProgram().getTimeNeeded()+0.2);
				}
				if(!getExecutedState()){
					if(this.getProgram().getTime() >= 0.2){
						this.getProgram().getShip().rotate(((ValueExpression) getAngle()).evaluate());
						this.getProgram().setTime(this.getProgram().getTime()-0.2);
						setExecutedState(true);
					}
				}
				
				
			} catch (ModelException e) {
				throw new ModelException("ModelException in TurnStatement");
			}
		}
		else{
			throw new IllegalArgumentException("The expression does not evaluate to a value");
		}
	}
}
