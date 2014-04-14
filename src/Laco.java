/* Classe para lacos */

import java.util.ArrayList;
import java.util.Scanner;

class Laco extends Bloco {

	public void executar() {

		Expressao exp = new Expressao();

		float retorno = exp.identifica(this.condicao); //identifica se o if tem retorno verdadeiro ou falso
		
		while (exp.identifica(this.condicao) == 1) {	
		
			ArrayList<String> t = new ArrayList<String>();
			super.executar(t);	
		}
		
	}
	
}
