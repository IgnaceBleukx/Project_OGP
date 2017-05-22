package Statements;

import asteroids.model.programs.Function;

public class VoidStatement extends Statement {

	public void execute() {
		throw new IllegalStateException("This method should be overwritten by all subclasses of 'Statement'");
	}
	
	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	private Function function;
	
}
