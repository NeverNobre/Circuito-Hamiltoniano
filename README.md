# Ciclo-Hamiltoniano-Estrutura de dados
Alunos:
-Vitor Cordovil Padilha-@vitorpadilha18
-
# Definição de Problema
Este trabalho tem como objetivo escolher um problema recorrente quando lidamos com grafos,o problema escolhido foi o de ciclo-hamiltoniano, o trabalho não deve ser usado bibliotecas na sua solução,exceto para se montar o grafo.

# Aplicações Práticas do problema

# algoritmo de solução
Este código resolve um problema NP-Completo do Ciclo Hamiltoniano em um grafo não orientado.se insere um grafo vértice por vértice, informando para cada um deles as arestas que o ligam a outro.Depois de se inserir as informações o programa pergunta vertice a vertice qual ele é adjacente. Enumera automaticamente os vertices com os números de 0 ao número de vertices digitado.Em seguida, ele começa pelo 0 e pergunta a quais outros vertices ele está ligado. deve-se informar os vértices ligados todos de uma vez, separados por espaço.o algoritmo então gera uma lista contendo todas as combinações possíveis, sem repetir dentro de cada combinação para obedecer a regra de não visitar duas vezes o mesmo vértice, cada combinação sendo outra lista. Portanto é gerada uma lista de listas.Geradas todas as combinações, o algoritmo então passa a iterar sobre cada uma delas tentando encontrar um ciclo possível na ordem da combinação da vez. Ele assume o primeiro elemento como o início do ciclo e parte do segundo. Então ele verifica se o vertice atual  possui uma aresta com o anterior.Caso constate que há uma aresta entre eles, então ele passa para o próximo elemento (vertice N+1) e repete o processo.Um Ciclo hamiltoniano é detectado ao se chegar ao fim da combinação e, por consequência, ter adicionado todos os vértices anteriores ao último. Para isso verifica-se constantemente se a quantidade de vértices no caminho feito é igual ao número total de vértices do grafo
