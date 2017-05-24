package asteroids.model;

import java.util.ArrayList;
import java.util.List;

import Expressions.ValueVariable;
import Expressions.Variable;
import Statements.Statement;
import Statements.ValueStatement;
import Statements.VoidStatement;
import asteroids.model.programs.Function;
import asteroids.util.ModelException;

public class Program {

	public Program(List<Function> functions, Statement main){
		setFunctions(functions);
		setMain(main);
		getMain().setProgram(this); 
	}
	
	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

	public Statement getMain() {
		return main;
	}

	public void setMain(Statement main) {
		this.main = main;
	}

	private List<Function> functions;
	private Statement main;
	private Ship ship;

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}
	
	private List<Object> printedObjects = new ArrayList<Object>();
	
	public List<Object> getPrintedObjects() {
		return printedObjects;
	}

	public void addPrintedObject(Object object){
		printedObjects.add(object);
	}
	
	public List<Variable> getVariables() {
		return variables;
	}
	public void addVariable(Variable variable) {
		this.variables.add(variable);
	}

	private List<Variable> variables = new ArrayList<Variable>();
	
	public List<Object> execute(double dt) throws ModelException{
		if (getMain() instanceof ValueStatement){
			((ValueStatement) getMain()).execute();
		}
		if (getMain() instanceof VoidStatement){
			((VoidStatement) getMain()).execute();
		}
		return getPrintedObjects();
		
	}

	
}
