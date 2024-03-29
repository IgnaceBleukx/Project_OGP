package asteroids.model;

import java.util.ArrayList;
import java.util.List;

import Expressions.ValueVariable;
import Expressions.Variable;
import Statements.ActionStatement;
import Statements.BreakException;
import Statements.SequenceStatement;
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
	
	public boolean getFirstRun(){
		return this.firstRun;
	}
	
	public boolean firstRun = true;
	
	public void setFirstRunFalse(){
		this.firstRun = false;
	}
	
	public double getTimeNeeded() {
		return timeNeeded;
	}

	public void setTimeNeeded(double timeNeeded) {
		this.timeNeeded = timeNeeded;
	}	
	
	private double timeNeeded = 0;
	
	
	
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
	
	public void clearPrintedObject(){
		printedObjects.clear();
	}
	
	public List<Variable> getVariables() {
		return variables;
	}
	public void addVariable(Variable variable) {
		this.variables.add(variable);
	}

	private double time;
	
	public double getTime(){
		return this.time;
	}
	public void setTime(double d){
		this.time = d;
	}
	
	
	
	private boolean insterrupted = false;
	
	public boolean isInsterrupted() {
		return insterrupted;
	}

	public void setInsterrupted(boolean insterrupted) {
		this.insterrupted = insterrupted;
	}
	
	private List<Variable> variables = new ArrayList<Variable>();
	
	public List<Object> execute(double dt) throws ModelException{
		System.out.println("gettime= " +getTime());
			try{
				setTime(getTime()+dt);
				
				if (getMain() instanceof ValueStatement){
					((ValueStatement) getMain()).execute();
				}
				if (getMain() instanceof VoidStatement){
					((VoidStatement) getMain()).execute();
				}
				if(getFirstRun()){
					setTimeOneLap(getTimeNeeded());
					setFirstRunFalse();
				}
				setTimeOneLap(getTimeOneLap()-dt);
				if (getTimeOneLap() > 0){
					clearPrintedObject();
					return null;	
				}
				else{
					return getPrintedObjects();
				}
			}catch(BreakException e){
				throw new IllegalStateException("The breakException should have been catched by the while-statement");
			}
	}

	
	public double getTimeOneLap() {
		return timeOneLap;
	}

	public void setTimeOneLap(double timeOneLap) {
		this.timeOneLap = timeOneLap;
	}

	private double timeOneLap = 0;
}
