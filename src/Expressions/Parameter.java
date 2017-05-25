package Expressions;



public class Parameter {

	public Parameter (double value){
		this.setValue(value);
	}
		
	public void setValue(double value) {	
		this.value = value;
	}

	public double getValue(){
		return this.value;
	}
	
	private double value;
}
