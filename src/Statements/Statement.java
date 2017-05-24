package Statements;

import Expressions.Expression;
import asteroids.model.Program;
import asteroids.model.programs.Function;
import asteroids.util.ModelException;

public class Statement {

	private Function function;
	private Program program;

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}
	
	private boolean inWhile = false;
	
	public void setWhileState(boolean state){
		this.inWhile = state;
	}
	
	public boolean getWhileState(){
		return this.inWhile;
	}
	
	public void passInformation(Statement statement){
		statement.setFunction(getFunction());
		statement.setProgram(getProgram());
		statement.setWhileState(getWhileState());
	}
	
	public void passInformation(Expression expression){
		expression.setFunction(getFunction());
		expression.setProgram(getProgram());
		expression.setWhileState(getWhileState());
	}
	
	public void passInformation(Function function){
		function.setFunction(getFunction());
		function.setProgram(getProgram());
		function.setWhileState(getWhileState());
	}


}
