import java.util.Queue;
import java.util.Stack;

public class Calculation2 {
	public static BigDecimalFraction solveRPN(Queue<String> rpn){
		Stack<BigDecimalFraction> result=new Stack<BigDecimalFraction>();
		BigDecimalFraction xs, ys=null;
		while(!rpn.isEmpty()){
			switch(rpn.peek()){
				case "+":
					ys=result.pop();
					xs=result.pop();
					result.push(xs.add(ys));
					rpn.remove();
					break;
				case "-":
					ys=result.pop();
					xs=result.pop();
					result.push(xs.subtract(ys));
					rpn.remove();
					break;
				case "*":
					ys=result.pop();
					xs=result.pop();
					result.push(xs.multiply(ys));
					rpn.remove();
					break;
				case "/":
					ys=result.pop();
					xs=result.pop();
					result.push(xs.divide(ys));
					rpn.remove();
					break;
				default:
					result.push(new BigDecimalFraction(rpn.remove()));
			}
		}
		return result.peek();
	}
}
