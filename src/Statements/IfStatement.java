package Statements;

import Expressions.BooleanExpression;
import Expressions.Expression;
import Expressions.ValueExpression;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;;

public class IfStatement extends ValueStatement{

	public IfStatement(Expression condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation){
		setCondition(condition);
		setIfBody(ifBody);
		setElseBody(elseBody);
		setSourceLocation(sourceLocation);
	}
		
	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public Statement getElseBody() {
		return elseBody;
	}

	public void setElseBody(Statement elseBody) {
		this.elseBody = elseBody;
	}

	public Statement getIfBody() {
		return IfBody;
	}

	public void setIfBody(Statement ifBody) {
		IfBody = ifBody;
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	private Expression condition;
	private Statement IfBody;
	private Statement elseBody;
	private SourceLocation sourceLocation;

	
	@Override
	public double execute() throws ModelException, BreakException{
		if(getCondition() instanceof BooleanExpression){
			passInformation(getCondition());
			if (((BooleanExpression) getCondition()).evaluate()){
				passInformation(getIfBody());
				if (getIfBody() instanceof ValueStatement){
					return ((ValueStatement) getIfBody()).execute();
				}
				else{
					((VoidStatement) getIfBody()).execute();
					return Double.NaN;
				}
			}
			else{
				if (getElseBody() != null){
					if (getElseBody() != null && getElseBody() instanceof ValueStatement){
						passInformation(getElseBody());
						return ((ValueStatement) getElseBody()).execute();
					}
					else{
						passInformation(getElseBody());
						((VoidStatement) getElseBody()).execute();
						return Double.NaN;
					}
				}
				else{
					return Double.NaN;
				}
			}
		}
		else{
			throw new ModelException("The condition does not evaluate to a boolean");
		}
	}
	
	
}
