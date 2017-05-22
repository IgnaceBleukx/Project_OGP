package Expressions;

import asteroids.part3.programs.SourceLocation;

public class ReadParameterExpression extends ValueExpression{

	public ReadParameterExpression(String parameterName, SourceLocation sourceLocation){
		setParameterName(parameterName);
		setSourceLocation(sourceLocation);
	}
	
	private String parameterName;
	private SourceLocation sourceLocation;
	
	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
}
