package Expressions;

public class GetDirectionExpression extends ValueExpression {

	public GetDirectionExpression(){
	}
	
	@Override
	public double evaluate(){
		return this.getProgram().getShip().getOrientation();
	}
}
