package Expressions;

import asteroids.model.Program;
import asteroids.model.programs.Function;

public class Expression {

	private Function function;

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}
	
	public void setProgram(Program program){
		this.program = program;
	}
	public Program getProgram(){
		return this.program;
	}

	private Program program;
	

}
