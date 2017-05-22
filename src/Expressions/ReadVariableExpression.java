package Expressions;

import asteroids.part3.programs.SourceLocation;

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
}
