package Expressions;

import asteroids.part3.programs.SourceLocation;

public class GetDirectionExpression extends ValueExpression {

	public GetDirectionExpression(SourceLocation location){
		setSourceLocation(location);
	}
	
	private SourceLocation sourceLocation;
	
	@Override
	public double evaluate(){
		return this.getFunction().getProgram().getShip().getOrientation();
	}

	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
}
