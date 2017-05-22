package Statements;

import asteroids.model.programs.Function;

public class ValueStatement extends Statement {

	public double execute(){
		throw new IllegalStateException("This method should be overrided by all subclasses");
	}
	
	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	private Function function;
	
}
