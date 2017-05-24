package Expressions;

import java.util.List;

import Statements.AssignementStatement;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

public class ReadVariableExpression extends ValueExpression {

	public ReadVariableExpression(String variableName, SourceLocation sourceLocation){
		setVariableName(variableName);
		setSourceLocation(sourceLocation);
	}
	
	private String variableName;
	private SourceLocation sourceLocation;

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	public double evaluate() throws ModelException{
			Variable toReturn = null;
			if (getProgram() != null){
				List<Variable> globalVariables = getProgram().getVariables();
				for (Variable variable : globalVariables){
					if (variable.getName().equals(this.getVariableName())){
						toReturn = variable;
					}
				}
			}
			if (getFunction() != null){
				List<Variable> localVariables = getFunction().getVariables();
				for(Variable variable : localVariables){
					if (variable.getName().equals(this.getVariableName())){
						toReturn = variable; 
					}
				}
			}
			if(toReturn == null){
				throw new ModelException("The variable is undefined");
			}
			return toReturn.getValue();
						
	}


}
