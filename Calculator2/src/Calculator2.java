public class Calculator2 {
	public static void main(String[] args){
		System.out.println(Calculation2.solveRPN(Expression.toRPN(Expression.split("(2+2)*3"))));
	}
}
