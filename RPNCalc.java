package rpn;

import java.util.Scanner;
import java.util.Stack;

public class RPNCalc {

	public static void main(String[] args) {
		Stack<Double> stack = new Stack<>();
		Scanner scan = new Scanner(System.in);
		double res=0;
		String var;
		System.out.println("Digite sua string de operação no console em formato RPN e aperte duas vezes enter");
		while(scan.hasNextLine()){
			var= scan.nextLine();
			if (var.equals(""))
		        break;
			
			String linha = var;
			Token token= new Token();
			int tam=0;
			while (tam<linha.length()) {
				char c=linha.charAt(tam);
				if (isDigit(c)) {
					if((linha.length()-1)==tam) {
						token.setType("NUM");
						token.setLexeme(linha);
						break;
					}else {
						c=linha.charAt(tam+1);
						if (isDigit(c)) {
							tam++;
							continue;
						}else {
							throw new RuntimeException("UNEXPECTED TOKEN");
							
						}
					}
				}else {
					if(linha.length()>1){
						throw new RuntimeException("UNEXPECTED TOKEN");//error
					}else {
						switch (c) {
						
						case '+':
							token.setType("PLUS");
							token.setLexeme(linha);
							tam++;
							break;
						case '-':
							token.setType("MINUS");
							token.setLexeme(linha);
							tam++;
							break;
						case '*':
							token.setType("TIMES");
							token.setLexeme(linha);
							tam++;
							break;
						case '/':
							token.setType("DIVIDED BY");
							token.setLexeme(linha);
							tam++;
							break;
						case '^':
							token.setType("EXPONENTIATION");
							token.setLexeme(linha);
							tam++;
							break;
						default:
							throw new RuntimeException("UNEXPECTED TOKEN");
							
					}
					}
					
				}
			}
			if (token !=null) {
				System.out.println("Token [Type="+token.getType()+", Lexeme="+token.getLexeme()+"]");
			}
			
			char op= var.charAt(0);
			double a,b=0;
			switch(op){
				case '+':
					a= stack.lastElement();
					stack.pop();
					b= stack.lastElement();
					stack.pop();
					stack.push(b+a);
					break;
				case '-':
					a= stack.lastElement();
					stack.pop();
					b= stack.lastElement();
					stack.pop();
					stack.push(b-a);
					break;
				case '/':
					a= stack.lastElement();
					stack.pop();
					b= stack.lastElement();
					stack.pop();
					if(a==0) {
						System.out.println("Um número não pode ser dividido por 0!");
					}else {
						stack.push(b/a);
					}
					break;
				case '*':
					a= stack.lastElement();
					stack.pop();
					b= stack.lastElement();
					stack.pop();
					stack.push(b*a);
					break;
				case '^':
					a= stack.lastElement();
					stack.pop();
					b= stack.lastElement();
					stack.pop();
					stack.push(Math.pow(b, a));
					break;
			    default:
			    	double num= Double.parseDouble(var);
			    	stack.push(num);
			    	break;
			    	
			}
			
		}
		System.out.println("Resultado:");
		res= stack.firstElement();
		System.out.println(res);

		
	}
	
	
	private static boolean isDigit(char c) {
		return (c>= '0' && c <= '9');
	}
	
	
	
	

}

class Token{
	public static final int TK_NUM=0;
	public static final int TK_OP=1;
	
	private String type;
	private String lexeme;
	
	
	public String getLexeme() {
		return lexeme;
	}

	public void setLexeme(String lexeme) {
		this.lexeme = lexeme;
	}



	public Token(String type, String lexeme) {
		super ();
		this.type= type;
		this.lexeme=lexeme;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Token() {
		super ();
		
	}
	
	
	
}
