package Expressions;

import asteroids.part3.programs.SourceLocation;

public class DoubleLiteralExpression extends ValueExpression {

	public DoubleLiteralExpression(double value, SourceLocation sourceLocation){
		setValue(value);
		setSourcelocation(sourceLocation);
		
	}
	
	double value;
	private SourceLocation sourcelocation;
	
	public void setValue(double value){
		this.value = value;
	}
	
	public double getValue(){
		return this.value;
	}
	
	public SourceLocation getSourcelocation() {
		return sourcelocation;
	}

	public void setSourcelocation(SourceLocation sourcelocation) {
		this.sourcelocation = sourcelocation;
	}

	@Override
	public double evaluate(){
		return getValue();
	}
}
