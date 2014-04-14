
import java.util.Stack;
import java.util.ArrayList;

class Expressao {


	public String exp;

	public int precedencia(String operador) {
	
		if(operador.equals(")")) {
	
			return 8;
		}
		else if(operador.equals("(")) {
			return 7;
		}
		else if(operador.equals("*") || operador.equals("/") || operador.equals("%")) {

			return 6;
		}
		else if(operador.equals("-") || operador.equals("+")  ) {
			return 5;
		}
		else if(operador.equals("<") || operador.equals(">") || operador.equals("@") || operador.equals("$")) {
			return 3;
		}
		else if(operador.equals(".") || operador.equals("#")  ) {

			return 2;
		}
		else if(operador.equals("&")) {
			return 1;
		}
		else if(operador.equals("|") ) {
			return 0;
		}
		
		return -1;

	}	
	public float calcula(Stack<String> pilha) {

		float resultado = 0.0f;

		Float var1 = null, var2 = null;
		String op = null;
		
		ArrayList<String> fila = new ArrayList<String>();

		while (!pilha.empty()) {
			fila.add(pilha.pop());
		}

		for(int u = 0; u < fila.size(); u++) {
			pilha.push(fila.get(u));
		}


		while (!pilha.empty()) {
			
			String temp = pilha.pop();

			if (temp.equals("(") || temp.equals(")") || temp.equals("") || temp.equals(" ")) {

				continue;

			}
			else if (this.ehOperador(temp)) {
				
				op = temp;

			 } else {

				if (var1 == null) {

					var1 = Float.parseFloat(temp);

					if(!pilha.empty() && pilha.peek().equals(")")) 

						pilha.push(Float.toString(var1 * 1.0f));

					temp = "";

					if (!pilha.empty())
						temp = pilha.pop();

					if (this.ehOperador(temp)) {

						op = temp;
					}
		
				} else {

					var2 = Float.parseFloat(temp);
				}
			}

			if (var1 != null && var2 != null && op != null) {

				if (op.equals("+")) {

					pilha.push(Float.toString(var1 + var2));

				} else if (op.equals("-")) {

					pilha.push(Float.toString(var1 - var2));

				} else if (op.equals("*")) {

					pilha.push(Float.toString(var1 * var2));

				} else if (op.equals("/")) {

					pilha.push(Float.toString(var1 / var2));
				} else if (op.equals("&")) {
					float r = 0.0f;
					if (Float.compare(var1, 0) != 0 && Float.compare(var2, 0) != 0)
						r = 1.0f;
					pilha.push(Float.toString(r));
				} else if (op.equals("|")) {
					float r = 0.0f;
					if (Float.compare(var1, 0) != 0 || Float.compare(var2, 0) != 0)
						r = 1.0f;
					pilha.push(Float.toString(r));
				} else if (op.equals(">")) {
					float r = 0.0f;
					if (Float.compare(var1, var2) > 0)
						r = 1.0f;
					pilha.push(Float.toString(r));
				} else if (op.equals("<")) {
					float r = 0.0f;
					if (Float.compare(var1, var2) < 0)
						r = 1.0f;
					pilha.push(Float.toString(r));
				} else if (op.equals(".")) {
					float r = 0.0f;
					if(Float.compare(var1,var2) == 0)
						r = 1.0f; 
					pilha.push(Float.toString(r));
				} else if (op.equals("#")) {
					float r = 0.0f;
					if (Float.compare(var1, var2) != 0)
						r = 1.0f;
					pilha.push(Float.toString(r));
				} else if (op.equals("@")) {
					float r = 0.0f;
					if (Float.compare(var1, var2) >= 0)
						r = 1.0f;
					pilha.push(Float.toString(r));
				} else if (op.equals("$")) {
					float r = 0.0f;
					if (Float.compare(var1, var2) <= 0)
						r = 1.0f;
					pilha.push(Float.toString(r));
				}
				
				var1 = null;
				var2 = null;
				op = null;
			}
		}

		if (var1 != null) {
			//System.out.println("Return: "+var1);
			return var1;
		}
		//System.out.println("Return: "+resultado);		
		return resultado;
	}

	public boolean ehOperador(String s)
	{

		boolean teste = false;
		
		String operadores[] = {"=", "(", ")", "!", "#", ".", ">", "<", "-", "+","/", "%", "*", "&", "|", "@", "$"};

		for(int i = 0; i < operadores.length; i++) {

			if( (operadores[i].equals(s))) {

				return true;
			}

		}
		return teste; 
	}
	public float identifica(String s)
	{

		String operador = new String();
		Stack<String> pilha = new Stack<String>();
		int cont =  0;
		String temp = "";

		for(int i = 0; i < s.length(); i++) {

			if (s.charAt(i) == ' ')
				continue;
			
			if(this.ehOperador(Character.toString(s.charAt(i)))) {

				//System.out.println("Aqui: "+Character.toString(s.charAt(i)));
				//System.out.println("temp: " + temp);
				if (!temp.equals("") && this.ehOperador(temp)) {
					//System.out.println("chega aqui?");
					if (Character.toString(s.charAt(i)).equals(")")) {

						
						pilha.push(Character.toString(s.charAt(i)));//empilha operador
						float valor = calcula(pilha);
						pilha.push(Float.toString(valor));//empilha operador
						continue;

					} else if(Character.toString(s.charAt(i)).equals("(")) {
						
						pilha.push(temp.trim());
						pilha.push(Character.toString(s.charAt(i)));
						temp = "";

					} else {

						//System.out.println("operador a espera"+s.charAt(i));
						
						if (!temp.equals("") && !this.ehOperador(temp))						
							pilha.push(temp.trim()); // empilha operando

						if(!operador.equals("") && !operador.equals(")")) { // verifica se não tem nenhum operador na espera 

							//System.out.println("operador a espera"+s.charAt(i));
							operador += Character.toString(s.charAt(i)); 
							pilha.push(operador);//empilha operador
							

						} else {
							operador = Character.toString(s.charAt(i));//empilha operador
						}
						if(precedencia(temp) < precedencia(operador) ) {
							//System.out.println("será");
							float valor = calcula(pilha);
							pilha.push(Float.toString(valor));//empilha operador
							
						}
						
						temp = "";
						operador = "";
					}
					
				} else if(!temp.equals("")) {

					Variavel v = new Variavel();

					if (v.buscaVariavel(temp) == -1) 

						pilha.push(temp.trim()); // empilha operando
					else
						pilha.push(Float.toString(Interpretador.variaveis.get(v.buscaVariavel(temp)).valor));
				}
				
				temp = "" + Character.toString(s.charAt(i));
				operador = Character.toString(s.charAt(i));
				
			} else {
			
				if (!temp.equals("") && this.ehOperador(temp)) {
					pilha.push(temp);
					temp = "";
				}
				temp = temp + (Character.toString(s.charAt(i)));
			}
			
		}

		if (temp != "") {
			Variavel v = new Variavel();
			if(v.buscaVariavel(temp) > -1)
				pilha.push(Float.toString(Interpretador.variaveis.get(v.buscaVariavel(temp)).valor));
			else
				pilha.push(temp);		
		}
		return calcula(pilha);
	}

}
