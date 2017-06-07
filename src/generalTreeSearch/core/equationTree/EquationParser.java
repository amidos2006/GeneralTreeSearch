package generalTreeSearch.core.equationTree;

import java.util.Random;

import generalTreeSearch.core.equationTree.operators.binary.*;
import generalTreeSearch.core.equationTree.operators.unary.*;
import generalTreeSearch.core.equationTree.terminals.*;
import generalTreeSearch.core.equationTree.terminals.array.*;

public class EquationParser {
    
    public static EquationParser getInstance(Random random){
	if(EquationParser.parser == null){
	    EquationParser.parser = new EquationParser(random);
	}
	return EquationParser.parser;
    }
    
    private static EquationParser parser=null;
    private Random rnd;
    
    private EquationParser(Random random){
	this.rnd = random;
    }
    
    private EquationNode getTerminal(String name){
	if(name.matches("^[\\-]?[0-9]+[\\.]?[0-9]*")){
	    return new ConstantNode(Double.parseDouble(name));
	}
	else{
	    return new VariableNode(name);
	}
    }
    
    private EquationNode getArrayOperator(String name, String varName){
	switch(name.trim()){
	case "max":
	    return new MaxArrayOperator(varName);
	case "min":
	    return new MinArrayOperator(varName);
	case "mean":
	    return new MeanArrayOperator(varName);
	case "median":
	    return new MedianArrayOperator(varName);
	case "mode":
	    return new ModeArrayOperator(varName);
	case "range":
	    return new RangeArrayOperator(varName);
	case "sum":
	    return new SumArrayOperator(varName);
	}
	return null;
    }
    
    private EquationNode getUnaryOperator(String name, EquationNode child){
	switch(name.trim()){
	case "abs":
	    return new AbsoluteOperator(child);
	case "floor":
	    return new FloorOperator(child);
	case "log":
	case "ln":
	    return new LogOperator(child);
	case "sigmoid":
	    return new SigmoidOperator(child);
	case "tanh":
	    return new TanhOperator(child);
	case "expanded":
	    return new ExpandedOperator(child);
	}
	return null;
    }
    
    private EquationNode getBinaryOperator(String name, EquationNode left, EquationNode right){
	switch(name.trim()){
	case "add":
	case "sum":
	    return new AddOperator(left, right);
	case "divide":
	    return new DivideOperator(left, right);
	case "div":
	    return new DivOperator(left, right);
	case "eq":
	    return new EqualOperator(left, right);
	case "lg":
	    return new LargerOperator(left, right);
	case "ls":
	    return new LessOperator(left, right);
	case "max":
	    return new MaxOperator(left, right);
	case "min":
	    return new MinOperator(left, right);
	case "mod":
	    return new ModOperator(left, right);
	case "mul":
	    return new MultiplyOperator(left, right);
	case "pow":
	    return new PowerOperator(left, right);
	case "sub":
	    return new SubtractOperator(left, right);
	case "randInt":
	    return new RandomOperator(this.rnd, RandomOperator.INTEGER, left, right);
	case "randFloat":
	    return new RandomOperator(this.rnd, RandomOperator.FLOAT, left, right);
	}
	return null;
    }
    
    private int getCommaIndex(String line){
	int index=-1;
	int brackets=0;
	for(int i=0; i<line.length(); i++){
	    if(line.charAt(i) == ',' && brackets==0){
		return i;
	    }
	    if(line.charAt(i)=='('){
		brackets += 1;
	    }
	    if(line.charAt(i) == ')'){
		brackets -= 1;
	    }
	}
	
	return index;
    }
    
    private EquationNode parseContent(String newChild){
	newChild = newChild.trim();
	int firstIndex=-1;
	int lastIndex=-1;
	do{
	    if(firstIndex != -1){
		newChild=newChild.substring(firstIndex+1,lastIndex);
	    }
	    firstIndex=newChild.indexOf("(");
	    lastIndex=newChild.lastIndexOf(")");
	}while(firstIndex==0);
	if(firstIndex == -1){
	    if(newChild.startsWith("c_") || newChild.startsWith("s_")){
		return null;
	    }
	    return this.getTerminal(newChild);
	}
	else{
	    String name = newChild.substring(0, firstIndex);
	    newChild = newChild.substring(firstIndex + 1, lastIndex);
	    int commaIndex = this.getCommaIndex(newChild);
	    if(commaIndex == -1){
		EquationNode child = parseContent(newChild);
		if(child != null){
		    return this.getUnaryOperator(name, child);
		}
		else{
		    return this.getArrayOperator(name, newChild);
		}
	    }
	    else{
		return this.getBinaryOperator(name, parseContent(newChild.substring(0, commaIndex)), 
			parseContent(newChild.substring(commaIndex + 1, newChild.length())));
	    }
	}
    }
    
    private int countSubstring(String input, String substring){
	int lastIndex = 0;
	int count = 0;

	while(lastIndex != -1){
	    lastIndex = input.indexOf(substring,lastIndex);
	    if(lastIndex != -1){
	        count ++;
	        lastIndex += substring.length();
	    }
	}
	return count;
    }
    
    public EquationNode parse(String line) throws Exception{
	line=line.trim();
	
	int openBrackets = countSubstring(line, "(");
	int closeBrackets = countSubstring(line, ")");
	if(openBrackets != closeBrackets){
	    throw new Exception("Number of opened brackets not equal to closed ones.");
	}
	return parseContent(line);
    }
}
