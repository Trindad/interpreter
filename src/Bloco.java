/* Classe para o bloco das condições dos lacos */

import java.util.ArrayList;
import java.util.Scanner;

class Bloco {

	public String condicao;
	public int identificador;
	public ArrayList<String> linhas;

	public Bloco() {

		condicao = new String();
		linhas = new ArrayList<String>();
	}
	public  void executar(ArrayList<String> codigo) {

		ArrayList<String> linhas = this.linhas;

		if (codigo.size() > 0) {

			linhas = codigo;
		}
		for (int j = 0; j < linhas.size(); j++) {

			String k = linhas.get(j);
			k = k.trim();

			if (k.contains(">=")) {
				k = k.replace(">=", "@");
			}

			if (k.contains("<=")) {
				k = k.replace("<=", "$");
			}

			if (k.contains("||")) {
				k = k.replace("||", "|");
			}

			if (k.contains("&&")) {
				k = k.replace("&&", "&");
			}

			if (k.contains("==")) {
				k = k.replace("==", ".");
			}

			if (k.contains("!=")) {
				k = k.replace("!=", "#");
			}

			if (k.contains("++")) {
				String nome_da_variavel = k.substring(0, k.indexOf("++"));
				k = k.replace("++", "= " + nome_da_variavel + " + 1");
			}

			if (k.contains("--")) {
				String nome_da_variavel = k.substring(0, k.indexOf("--"));
				k = k.replace("--", "= " + nome_da_variavel + " - 1");
			}
			
			if(k.startsWith("imprime")) { //imprime e quebra no espaço

				String partes[] = k.split("imprime");

				String expressao = partes[1].trim();

				if(expressao.startsWith("\"")) {

					expressao = expressao.trim().substring(1, expressao.length() - 1).trim();
					while(expressao.indexOf("{") != -1) { //Leitura de variavel para mais de uma variavel

						Variavel v = new Variavel();
						String var = expressao.substring(expressao.indexOf("{") + 1, expressao.indexOf("}"));

						if(v.buscaVariavel(var) != -1) { // busca variavel

							expressao = expressao.replace(expressao.substring(expressao.indexOf("{"), expressao.indexOf("}") + 1),Float.toString(Interpretador.variaveis.get(v.buscaVariavel(var)).valor));
							
						}
					}
					System.out.println(expressao);
					continue;
			
				}
			}
			else if(k.startsWith("se")) {

				Condicional se = new Condicional();
				
				String partes[] = k.split("se");

				se.condicao = partes[1].trim().substring(0,partes[1].length()-2).trim();
				int ses = 1;
				int fins = 0;
				j++;
				if (linhas.size() > j) {
					
					k = linhas.get(j).trim();
					if (k.trim().startsWith("fim"))
						fins++;
					if (k.trim().startsWith("se")  || k.trim().startsWith("enquanto") || k.trim().startsWith("senao"))
						ses++;

					while(ses != fins) {

						se.linhas.add(k);
						j++;

						if (j >= linhas.size())
							break;
						
						k = linhas.get(j).trim();
						if (k.trim().startsWith("fim"))
							fins++;
						if (k.trim().startsWith("se") || k.trim().startsWith("enquanto") || k.trim().startsWith("senao"))
							ses++;
					}
					if(j + 1 < linhas.size()) {

						String teste = linhas.get(j + 1);
						if (teste.trim().equals("senao")) {
							ses++;
							j+=2;
							k = linhas.get(j).trim();
							if (k.trim().startsWith("fim"))
								fins++;
							if (k.trim().startsWith("senao") || k.trim().startsWith("enquanto") || k.trim().startsWith("se"))
								ses++;
							while(ses != fins) { //vefica final da condição

								se.linhaselse.add(k);
								j++;
								if (j >= linhas.size())
									break;
					
								k = linhas.get(j).trim();
								if (k.trim().startsWith("fim"))
									fins++;
								if (k.trim().startsWith("senao") || k.trim().startsWith("enquanto") || k.trim().startsWith("se") )
									ses++;
							}
						}
					
					}
					
				}

				se.executar();

			}
			else if(k.startsWith("enquanto")) { // laco de repetição

				Laco laco = new Laco();
				
				String partes[] = k.split("enquanto");

				laco.condicao = partes[1].trim().substring(0,partes[1].length()-2).trim();
				int ses = 1;
				int fins = 0;
				j++;
				if (linhas.size() > j) {
					
					k = linhas.get(j).trim();
					if (k.trim().startsWith("fim"))
						fins++;
					if (k.trim().startsWith("enquanto") || k.trim().startsWith("se") || k.trim().startsWith("senao"))
						ses++;

					while(ses != fins) { // verifica fim de laco

						laco.linhas.add(k);
						j++;

						if (j >= linhas.size())
							break;
						
						k = linhas.get(j).trim();
						if (k.trim().startsWith("fim"))
							fins++;
						if (k.trim().startsWith("enquanto") || k.trim().startsWith("se") || k.trim().startsWith("senao"))
							ses++;
					}
					
				}

				laco.executar();
			}
			else if(k.startsWith("função")) { // função

				Funcao func = new Funcao();
				
				String partes[] = k.split("função");

				func.nome = partes[1].trim().substring(0,partes[1].length()-2).trim();
				for (int y = 0; y < Interpretador.funcoes.size(); y++) {

					if (Interpretador.funcoes.get(y).nome.equals(func.nome)) { /*verifica se a função já existe */

						System.out.println("ERRO AO CRIAR FUNÇÃO \""+func.nome+"\".FUNÇÃO EXISTENTE");
						System.exit(0);
					}
				}
				int ses = 1;
				int fins = 0;
				j++;
				if (linhas.size() > j) {
					
					k = linhas.get(j).trim();
					if (k.trim().startsWith("fim"))
						fins++;
					if (k.trim().startsWith("enquanto") || k.trim().startsWith("se") || k.trim().startsWith("senao"))
						ses++;

					while(ses != fins) {

						func.linhas.add(k);
						j++;

						if (j >= linhas.size())
							break;
						
						k = linhas.get(j).trim();
						if (k.trim().startsWith("fim"))
							fins++;
						if (k.trim().startsWith("enquanto") || k.trim().startsWith("se") || k.trim().startsWith("senao"))
							ses++;
					}
					
				}

				Interpretador.funcoes.add(func);
			}
			else if(k.startsWith("//")) { // verifica comentarios
				continue;
			}
			if (k.contains("=")) { // declaração de variaveis

				String partes[] = k.split("=");

				float valor = 0.0f;

				if(partes[1].contains("entrada")) { // comandos de entrada
				
					Scanner f = new Scanner(System.in);		
					valor = f.nextFloat();	

				}
				else {
					Expressao exp = new Expressao();
					valor = exp.identifica(partes[1]);
				}

				Variavel v = new Variavel();
				int indice = v.buscaVariavel(partes[0].trim());

				if (indice == -1)
					Interpretador.variaveis.add(new Variavel(partes[0].trim(), valor)); 
				else
					Interpretador.variaveis.get(indice).valor = valor;
			} else if (k.contains(new String("[]"))) { // verifica função
				
				String nome_func = k.substring(0,k.indexOf("[")).trim();
			
				for (int y = 0; y < Interpretador.funcoes.size(); y++) {
					if (Interpretador.funcoes.get(y).nome.equals(nome_func)) {
						Interpretador.funcoes.get(y).executar();
						return;
					}
				}

			}
		}
	}
}
