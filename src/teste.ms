imprime "Ola mundo"

a = 2
imprime"{a}"

i = 0
enquanto i <= 10 :

	imprime "{i}"
	i++
fim

a = 1 + 1
b = 3 + a
imprime "A : {a}"
imprime "B: {b}"

a = 1
se a > 1 || 1 == 1:
	imprime "Maior"
fim
senao
	imprime "Menor"
fim
i = 0
j = i
enquanto i <= 3 :

	se i == 0 :
		imprime "Zero"
	fim
	senao
		imprime "Outro"
	fim

	i++
fim
se i == 3 :

	se j == 0 :
		imprime "Ok"
	fim
	senao
		// comentario
		imprime "Erro" 
	fim
fim
função nome :
		
	imprime "Var1: {a} ....  Var2: {i}"

	se i != 2 :

		imprime"I é igual a dois"	
	fim
	senao
		imprime"I diferente de dois"
	fim
fim

nome[]

