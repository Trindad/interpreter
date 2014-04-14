
/* Classe para condições*/

import java.util.ArrayList;
import java.util.Scanner;

class Condicional extends Bloco {

	public ArrayList<String> linhaselse;

	public Condicional() {
	
		super();
		linhaselse = new ArrayList<String>();
	}

	public void executar() {

		//System.out.println("size: " + linhaselse.size());
		Expressao exp = new Expressao();

		float retorno = exp.identifica(this.condicao); //identifica se o if tem retorno verdadeiro ou falso
		//System.out.println("ret: " + retorno);
		if (retorno == 0) {
			
			if (linhaselse.size() > 0) {
				super.executar(linhaselse);
				return;
			}
			return;
		}

		ArrayList<String> t = new ArrayList<String>();
		super.executar(t);
		
	}

}
