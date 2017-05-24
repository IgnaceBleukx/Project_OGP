package Statements;

import Expressions.Expression;
import asteroids.model.programs.Function;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class AssignementStatement extends VoidStatement {
	
	public AssignementStatement(String variableName, Expression value, SourceLocation scourcelocation){
		setValue(value);
		setVariableName(variableName);
	}
	
	public Expression getValue() {
		return value;
	}
	public void setValue(Expression value) {
		this.value = value;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	private Expression value;
	private String variableName;
	
	public void execute() throws ModelException{
		if (getProgram() == null){
			throw new ModelException("The variable is not part of a program");
		}
		for (Function function : getProgram().getFunctions()){
			if (function.getFunctionName().equals(this.getVariableName())){
				throw new ModelException("The name is already in use for a function");
			}
		}
		if (getFunction() != null){
			for (Object variable : getFunction().getVariables()){
				if (((AssignementStatement) variable).getVariableName().equals(this.getVariableName())){
					((AssignementStatement) variable).setValue(this.getValue());
					return;
				}
			}
			getFunction().addVariable(this);
		}
		else{
			for (Object variable : getProgram().getVariables()){
				if (((AssignementStatement) variable).getVariableName().equals(this.getVariableName())){
					((AssignementStatement) variable).setValue(this.getValue());
					return;
				}
			}
			getProgram().addVariable(this);
		}
	}
	
}
