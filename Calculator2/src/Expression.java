import java.util.ArrayDeque;
import java.util.Deque;

public class Expression {
	public static String[] split(String ex){
		return ex.split("((?<=\\+)|(?=\\+))|((?<=-)|(?=-))|((?<=\\*)|(?=\\*))|((?<=/)|(?=/))");
	}
	
	public static Deque<String> toRPN(String[] ex){
		Deque<String> rpn=new ArrayDeque<String>();
		Deque<String> temp=new ArrayDeque<String>();
		for(int i=0;i<ex.length;i++){
			switch(ex[i]){
				case "*":
					if(temp.isEmpty()){
						temp.add(ex[i]);
					}else if(temp.peekLast().equals("*")||temp.peekLast().equals("/")){
						do{
							rpn.add(temp.removeLast());
							if(temp.isEmpty()) break;
						}while(temp.peekLast().equals("*")||temp.peekLast().equals("/"));
						temp.add(ex[i]);
					}else{
						temp.add(ex[i]);
					}
					break;
				case "/":
					if(temp.isEmpty()){
						temp.add(ex[i]);
					}else if(temp.peekLast().equals("*")||temp.peekLast().equals("/")){
						do{
							rpn.add(temp.removeLast());
							if(temp.isEmpty()) break;
						}while(temp.peekLast().equals("*")||temp.peekLast().equals("/"));
						temp.add(ex[i]);
					}else{
						temp.add(ex[i]);
					}
					break;
				case "+":
					if(temp.isEmpty()){
						temp.add(ex[i]);
					}else if(temp.peekLast().equals("+")||temp.peekLast().equals("-")||temp.peekLast().equals("*")||temp.peekLast().equals("/")){
						do{
							rpn.add(temp.removeLast());
							if(temp.isEmpty()) break;
						}while(temp.peekLast().equals("+")||temp.peekLast().equals("-")||temp.peekLast().equals("*")||temp.peekLast().equals("/"));
						temp.add(ex[i]);
					}else{
						temp.add(ex[i]);
					}
					break;
				case "-":
					if(temp.isEmpty()){
						temp.add(ex[i]);
					}else if(temp.peekLast().equals("+")||temp.peekLast().equals("-")||temp.peekLast().equals("*")||temp.peekLast().equals("/")){
						do{
							rpn.add(temp.removeLast());
							if(temp.isEmpty()) break;
						}while(temp.peekLast().equals("+")||temp.peekLast().equals("-")||temp.peekLast().equals("*")||temp.peekLast().equals("/"));
						temp.add(ex[i]);
					}else{
						temp.add(ex[i]);
					}
					break;
				default:
					rpn.add(ex[i]);
			}
		}
		while(!temp.isEmpty()){
			rpn.add(temp.removeLast());
		}
		return rpn;
	}
}
