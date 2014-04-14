/* 
PROGRAMA EM LINGUAGEM JAVA COM O OBJETIVO DE INTERPRETAR UMA LINGUAGEM PRÓPIA. UTILIZANDO SOMENTE DE VARIAVEIS DO TIPO FLOAT,LAÇOS,ANINHAMENTOS DE LAÇOS(IF,ELSE,WHILE). OBTEM LEITURA DO TECLADO, UTILIZA DE PRECEDÊNCIA PARA CALCULAR. 

DISCIPLINA:PROGRAMAÇÃO I
ALUNOS: SILVANA TRINDADE, MAURÍCIO ANDRÉ CINELLI
E-MAILS: syletri@gmail.com, mauricio.andre.cinelli@gmail.com
*/

/* IDENTIFICA O ARQUIVO TIPO .ms PARA FAZER A LEITURA */


import java.util.ArrayList;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.PriorityQueue;

class Interpretador
{

	public static ArrayList<Variavel> variaveis = new ArrayList<Variavel>();
	public static ArrayList<Funcao> funcoes = new ArrayList<Funcao>();

	public static void main(String args[])
	{
		String arquivo = args[0];
		Bloco programa = new Bloco();
		Scanner s;

		try
		{
			s = new Scanner(new File(arquivo));

		} catch (Exception e)
		{
			System.out.println("Arquivo não encontrado!");
			return;
		}

		while(s.hasNext())
		{
			programa.linhas.add(s.nextLine());
		}

		programa.executar(new ArrayList<String>());

	}
}
