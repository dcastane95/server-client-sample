package model;

public class Operation {
	private Integer firstNumber;
	private Integer secondNumber;
	private String operator;
	
	public Operation(Integer firstNumber, Integer secondNumber, String operator) {
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
		this.operator = operator;
	}
	
	public Operation() {
	}
	
	public Integer getFirstNumber() {
		return firstNumber;
	}
	public void setFirstNumber(Integer firstNumber) {
		this.firstNumber = firstNumber;
	}
	public Integer getSecondNumber() {
		return secondNumber;
	}
	public void setSecondNumber(Integer secondNumber) {
		this.secondNumber = secondNumber;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		if(operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/"))
			this.operator = operator;
		else
			this.operator = null;
	}
	
	public Double getResult() {
		Double result = null;
		if(isComplete()) {
			switch(operator) {
				case "+":
					result = new Double(getFirstNumber() + getSecondNumber());
					break;
				case "-":
					result = new Double(getFirstNumber() - getSecondNumber());
					break;
				case "*":
					result = new Double( getFirstNumber() * getSecondNumber());
					break;
				case "/":
					if (getSecondNumber() != 0)
						result = new Double(getFirstNumber()) / new Double(getSecondNumber());
					break;
				default:
					result = null;
					break;
			}
		}
		return result;
	}
	private boolean isComplete() {
		return (getFirstNumber() != null && getSecondNumber() != null && getOperator() != null);
	}
	
	public String getError() {
		String error = null;
		
		if(!isComplete()) {
			error = "Missing value(s).There should be a firstNumber, a secondNumber and a valid operator (+,-,*,/)";
		}else if (getResult() == null) {
			error = "Trying to divide by 0!"; //result can only be null if is trying to divide by 0 if is complete
		}
		
		return error;
	}
	
	public String toString() {
		return String.format("%d %s %d = %.2f", 
				getFirstNumber(),getOperator(), getSecondNumber(), getResult());
	}
	
}
