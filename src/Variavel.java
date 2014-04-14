/*Classe para variavel */

class Variavel 
{

	// Tornar privadas e implementar setters e getters

	public String nome;
	public float valor;

	public Variavel(String s, float v)
	{
		this.nome = s;
		this.valor = v;
	}

	public Variavel()
	{
		
	}
	public int buscaVariavel(String s)
	{

		for (int cont = 0; cont < Interpretador.variaveis.size(); cont++) {


			if (Interpretador.variaveis.get(cont).nome.equals(s))
				
				return cont;
		}
		
		return -1;
	}

}
