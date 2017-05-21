package Expressions;

public class ReadParameterExpression {

	public ReadParameterExpression(String parameterName){
		setParameterName(parameterName);
	}
	
	private String parameterName;
	
	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
}
