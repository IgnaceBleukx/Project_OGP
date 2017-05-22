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
	public double execute() throws ModelException{
		if(getCondition() instanceof BooleanExpression){
			try{
				if (((BooleanExpression) getCondition()).evaluate()){
					getIfBody().setProgram(getProgram());
					if (getIfBody() instanceof ValueStatement){
						return ((ValueStatement) getIfBody()).execute();
					}
					else{
						((VoidStatement) getIfBody()).execute();
						return (Double) null;
					}
				}
				else{
					getElseBody().setProgram(getProgram());
					if (getElseBody() != null && getElseBody() instanceof ValueStatement){
						return ((ValueStatement) getElseBody()).execute();
					}
					else{
						((VoidStatement) getElseBody()).execute();
						return (Double) null;
					}
				}
			}catch (ModelException e){
				throw new ModelException("ModelException in IfStatement");
			}
		}
		else{
			throw new ModelException("The condition does not evaluate to a boolean");
		}
	}
	
	
}
