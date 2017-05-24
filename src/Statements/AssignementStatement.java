package Statements;

import java.util.ArrayList;
import java.util.List;

import Expressions.Expression;
import Expressions.ValueVariable;
import Expressions.Variable;
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
		passInformation(getValue());
		if (getFunction() != null){
			for (Variable variable : getFunction().getVariables()){
				if (variable.getName().equals(this.getVariableName())){
					variable.setExpression(this.getValue());
					return;
				}
			}
			getFunction().addVariable(new Variable(getValue(),getVariableName()));
			return;
		}
		else{
			List<String> functionNames = new ArrayList<String>();
			for (Function function : getProgram().getFunctions()){
				functionNames.add(function.getFunctionName());
			}
			if (functionNames.contains(getVariableName())){
				throw new ModelException("The variableName is the same as a functioName");
			}
			for (Variable variable : getProgram().getVariables()){
				if (variable.getName().equals(this.getVariableName())){
					variable.setExpression(this.getValue());
					return;
				}
			}
			getProgram().addVariable(new Variable(this.getValue(),this.getVariableName()));
			return;
			
		}
	}
	
}
