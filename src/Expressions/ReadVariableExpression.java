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
	
	@Override
	public double evaluate() throws ModelException{
			List<Object> programVariables = getProgram().getVariables();
			Expression toReturn = null;
			for (Object globalVariable : programVariables){
				if (((AssignementStatement) globalVariable).getVariableName().equals(getVariableName())){
					toReturn = ((AssignementStatement) globalVariable).getValue();
				}
			}
			if(getFunction() != null){
				List<Object> functionVariables = getFunction().getVariables();
				for (Object localVariable : functionVariables){
					if (((AssignementStatement) localVariable).getVariableName().equals(getVariableName())){
						toReturn = ((AssignementStatement) localVariable).getValue();
					}
				}
			}
			
			if(toReturn != null){
				passInformation(toReturn);
				return ((ValueExpression) toReturn).evaluate();
			}
			else{
				throw new ModelException("The variable does not occur in the current functionbody or program");
			}
	}


}
