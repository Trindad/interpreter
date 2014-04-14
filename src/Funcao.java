/* Classe para funções para a linguagem ms */

import java.util.ArrayList;
import java.util.Scanner;

class Funcao extends Bloco {

	String nome;
	
	public Funcao(){

		super();
		nome = "";
		
	}
	public void executar() {

		ArrayList<String> vazio = new ArrayList<String>();

		super.executar(vazio);
		
	}
}
