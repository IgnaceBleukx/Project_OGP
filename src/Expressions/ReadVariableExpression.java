package Expressions;

public class ReadVariableExpression {

	public ReadVariableExpression(String variableName){
		setVariableName(variableName);
	}
	
	private String variableName;

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
}
