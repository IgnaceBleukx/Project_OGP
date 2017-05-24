package Expressions;

import java.util.List;

import Statements.BreakException;
import asteroids.part3.programs.SourceLocation;
import asteroids.util.ModelException;

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
	
	@Override
	public  double evaluate() throws ModelException, NumberFormatException, BreakException{
		if (getFunction() != null){
			List<Expression> parameters = getFunction().getParameters();
			try{
				if (parameters.get(Integer.parseInt(getParameterName().substring(1))-1) instanceof ValueExpression){
					passInformation(parameters.get(Integer.parseInt(getParameterName().substring(1))-1));
					return ((ValueExpression) parameters.get(Integer.parseInt(getParameterName().substring(1))-1)).evaluate();
				}
				else{
					throw new ModelException("The parameter given does not evaluate to a value");
				}
			}catch(IndexOutOfBoundsException e){
				throw new ModelException("To little parameters given");
			}
		}
		else{
				throw new ModelException("The parameter is read outside of a function body");
			}
		}
	
}
