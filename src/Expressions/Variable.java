package Expressions;

public class Variable {

	public Variable(double value, String name){
		this.setValue(value);
		this.setName(name);
	}
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private double value;
	private String name;

	
}
