package Statements;

import java.util.ArrayList;
import java.util.List;

import Expressions.Expression;
import Expressions.ValueExpression;
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
	
	public void execute() throws ModelException, BreakException{
		if (getProgram() == null){
			throw new ModelException("The variable is not part of a program");
		}
		passInformation(getValue());
		if (getFunction() != null){
			for (Variable variable : getFunction().getVariables()){
				if (variable.getName().equals(this.getVariableName())){
					variable.setValue(((ValueExpression) this.getValue()).evaluate());
					return;
				}
			}
			getFunction().addVariable(new Variable(((ValueExpression) getValue()).evaluate(),getVariableName()));
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
					variable.setValue(((ValueExpression) this.getValue()).evaluate());
					return;
				}
			}
			getProgram().addVariable(new Variable(((ValueExpression)this.getValue()).evaluate(),this.getVariableName()));
			return;
			
		}
	}
	
}
