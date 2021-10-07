# Ciclo-Hamiltoniano-Estrutura de dados
Alunos:

- Vitor Cordovil Padilha - Usuário - @vitorpadilha18

- Breno Lucas dos Anjos Silva  -  Usuário: @NeverNobre

# Definição de Problema
O ciclo-hamiltoniano é um caminho que permite passar por todos os vértices de um grafo, sem repetir nenhum, passando somente uma vez por cada vértice. Há alguns problemas para definir se um grafo é hamiltoniano, por exemplo se ele é completo em np, ou se obtém uma boa condição necessária prar ser existente, ou seja, se está em co-NP. Ele tambem pode ser chamado de grafo rastreável.
A entrada deste algorítmo deve ser o grafo e suas interconexões, e a saída deve ser todos os ciclos que passem por todos os vértices como exposto acima.

# Aplicações Práticas do problema
Um problema que envolve caminhos hamiltonianos é o problema do caixeiro viajante, em que um caixeiro deseja visitar um conjunto de N cidades (vértices), passando por cada cidade exatamente uma vez e retornando à cidade de origem, fazendo o caminho de menor tamanho possível.Em 2009 conseguiu-se uma resolução para este problema utilizando-se de bactérias na implementação do algoritmo, que historicamente costuma ter um custo de tempo de computação exponencial.

Outra aplicação é o problema do ônibus e os alunos. O ônibus deve passar por todos os alunos mas busca-se o caminho que passe por todas as casas uma só vez e retorne à escola.

# algoritmo de solução
O algorítmo fará backtracking para alcançar cada resposta. Assim como no problema das N-rainhas, a cada nova posição válida na resposta, começa um nova busca através dessa ramificação.
A entrada dos dados pode ser feita alterando o arquivo no diretório src\solucao\grafo3.txt, ou alterando o diretório no próprio código para outro arquivo.
A entrada deve conter: na primeira linha, informação para a quantidade de vértices, no formato "tamanho: k", onde k é o número de vértices; nas demais linhas, a configuração da matriz correspondente às conexões entre cada vértice do grafo, atribuindo valor 1 para uma conexão, e valor 0 quando não houver conexão. Da esquerda para a direita e de cima para baixo para representar a matriz para melhor vizualização humana.
O arquivo deve conter k+1 linhas de acordo com a descrição acima.

![imagem1](https://user-images.githubusercontent.com/89496407/136306817-8828140f-d665-458e-a47b-a68ef643ec53.JPG)

O código usa backtracking para a resolução do problema do circuito hamiltoniano.
Inicia-se pela classe Main, as variáveis globais criadas são int resposta[], um array que será a tentativa atual da resposta; int graf[][], um array de arrays, representando a matriz com as conexões do grafo; int nVertices, que irá armazenar a quantidade de vértices do grafo; e uma lista String respostas para armazenar as respostas se forem válidas,(ou seja, se obedecerem às regras de um circuito hamiltoniano).

O método main chama o método configuraGrafo(String diretorio). Esse método inicializa a variavel graf[][] e int nVertices ao ler o número de vértices do grafo usando um leitor de arquivo, e todas as conexões entre os vértices, e ainda inicializa o array resposta[], com no indice zero tendo valor zero e no indice 1 tendo valor 1. O código desconsiderará a posição 0 e tratará o array como se começasse no índice 1, portanto o array deve ter tamanho nVertices+1.

Ao voltar ao método main, é chamado Hamiltonian(int k) que é um método recursivo, com o parametro 2 pois iremos comparar as respostas formadas a partir da segunda posição do arranjo respostas (lembre-se que estamos tratando o array como se começasse em 1), que levará à comparação das respostas formadas a partir da terceira posição... Até que k==nVertices.

O laço em Hamiltonian começa chamando o método NextVertex(k), que inicia outro laço que só será interrompido apenas quando encontrar uma resposta válida que ou tenha menos que nVertices, ou que tenha nVertices e a ultima resposta seja um vértice que se conecta ao primeiro. Antes dessa decisão, se a posição em resposta[k] conter valor inteiro zero, significa que esgotaram as tentativas desta etapa de backtracking, então retorna ao método Hamiltonian. Verifica-se também a conexão no grafo entre o vértice na posição k da resposta e o da posição anterior e , caso não haja, não haverá atualização no boolean valido = false, ou seja, a resposta é descartada e o próximo vértice é testado. Além disso, há um laço que percorre com int j=1 da primeira posição de resposta até a posição k-1, caso haja um item duplicado na resposta, boolean valido recebe false, e o laço se encerra, se não houver item duplicado, boolean valido recebe true, então a resposta é válida neste caso.

![imagem3](https://user-images.githubusercontent.com/89496407/136310993-eaec247b-e43a-4528-8e84-e8f4d0c300af.png)

Ao retornar para Hamiltonian, se o ultimo vértice gerado por NextVertex for zero, esgotaram-se as tentativas de backtracking, retorne imediatamente. Caso a ultima posição de resposta manipulada por NextVertex seja igual nVertices, é gerada uma String com a sequencia atual da resposta (EX: 1,3,7,...,14,1), e chama-se o método estaNaLista para ver se a resposta já existe na lista, porém invertida, e caso não haja, é inserida a nova resposta. Do contrário, caso a ultima posição manipulada não seja igual a nVertices, então é Chamado Hamiltonian(k+1), para iniciar a manipulação da posição seguinte da resposta.
Após o primeiro Hamiltonian finalmente terminar (note que ele é o ultimo), o resultado aparece na tela da seguinte maneira: "Total circuitos hamiltonianos obtidos: k", onde k é o total de respostas, seguido de k linhas com todos os ciclos hamiltonianos encontrados.

![imagem2](https://user-images.githubusercontent.com/89496407/136306859-cd1a44c6-255d-4c58-ab20-bf6503ec378d.JPG)

Nota: O método main resolve apenas um ciclo hamiltoniano por execução, mas é possível reutilizar as linhas de main para criar um novo método que use apenas o diretório do arquivo contendo os dados do grafo. Assim, pode-se resolver multiplos ciclos hamiltonianos para vários grafos de uma só vez pelo método main.
