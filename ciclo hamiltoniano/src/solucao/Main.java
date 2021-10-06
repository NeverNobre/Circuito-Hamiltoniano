package solucao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static int resposta[];		// contém uma sequencia de vertices que formam uma resposta
	static int graf[][];		// arranjo de arranjos para funcionar como matriz, contendo as conexões entre vértices do grafo
								// NOTA: os indices zero serão desconsiderados 
	
	static int nVertices;		// inteiro que armazenará o numero de vértices do grafo
	static List<String> respostas= new ArrayList<>();	//lista contendo todas as respostas geradas pelo código
	
	public static void main(String[] args) {
		try {
			//configuraGrafo("src/solucao/grafo1.txt");
			//configuraGrafo("src/solucao/grafo2.txt");
			configuraGrafo("src/solucao/grafo3.txt");	// configura a 'matriz' de conexões a partir de um arquivo com esses dados
		}
		catch(Exception e) {System.out.print(e.getMessage());}

		Hamiltonian(2);			//inicia da posição 2 da resposta, pois zero é desconsiderado e a posição 1 vai conter sempre 1
		
		System.out.println("Total circuitos hamiltonianos obtidos: " + respostas.size());
		respostas.forEach(System.out::println);
		//imprime resultados
	}
	
	public static void Hamiltonian(int k) {
		do {
		NextVertex(k);						// procura o próximo vértice para esta posição
		if (resposta[k] == 0) return;		// se o vértice for zero, esgotaram-se os vértices, volte para a operação anterior
		
		if (k == nVertices) { 				// se a posição atual for a ultima, é uma resposta completa
			String resp="";
			for (int a = 1; a <= nVertices; a++)	// gera uma string para essa resposta
				resp = resp + resposta[a]+",";
											
			resp = resp + "1";
			if(!existeNaLista())			// adicione na lista se a resposta já não existir lá invertida
				respostas.add(resp);
		}
		else Hamiltonian(k + 1);				//se a posição atual não for a ultima, vá para a próxima posição
		} while (true);
	}
	
	public static void NextVertex(int k) {
		do {boolean valido = false;
			resposta[k] = (resposta[k] + 1) % (nVertices + 1); 	// novo vértice é o próximo vértice, a não ser que seja o ultimo
			
			if(resposta[k] == 0) return;						// se este vértice for zero, esgotaram-se os vértices, abandone o método!
			if (graf[resposta[k - 1]][resposta[k]] !=0)			// se houver conexão entre vértice e o da ultima posição, prossiga
				for(int j = 1; j < k; j++) 						// faça uma busca por vértices duplicados na resposta atual
					if (resposta[j]==resposta[k]) {valido=false; break;} else valido=true;
										
				if (valido)										// caso não haja duplicados, verifique o tamanho da resposta
					if (k < nVertices||(k == nVertices)&&graf[resposta[nVertices]][resposta[1]]!=0) {
						return;				// se a resposta tiver tamanho insuficiente ou o 
											// ultimo vértice tiver conexão com o primeiro, volte para hamiltonian 
					}
		}while(true);
	}
	public static void configuraGrafo(String dir) throws Exception {
		BufferedReader ler= new BufferedReader(new FileReader(dir)); // Leia o arquivo no diretório
		
		nVertices = Integer.parseInt(ler.readLine().split(" ")[1]); // Separe "tamanho: " da informação e colete o numero de vértices 
		
		graf= new int[nVertices+1][nVertices+1];					// Inicialize a falsa matriz 
		
		String numeros[];											// Array que vai conter os numeros de cada linha 
		for (int i=1;i<=nVertices; i++) {
			numeros=ler.readLine().split(",");						// Atualize o arranjo com a nova linha do arquivo
			for(int j=1; j<=nVertices; j++)
				graf[i][j]=Integer.parseInt(numeros[j-1]);
		}
		ler.close();
		resposta=new int[nVertices+1];								// Inicialize o array da resposta
		resposta[0]=0;												// Indice zero desconsiderado
		resposta[1]=1;												// Indice 1 recebe o vértice 1
		
		for(int a=2; a<=nVertices;a++) resposta[a]=0;				// As demais posições recebem vértice zero
	}
	// O seguinte método gera uma resposta que seja igual à armazenada em resposta[],
	// mas com as posições invertidas, e em seguida, verifica se a resposta já existe na lista de respostas
	public static boolean existeNaLista() {
		String resp="1,";
		for(int i=nVertices;i>1;i--) resp=resp+resposta[i]+",";
		resp=resp+1;

		if(respostas.contains(resp))
		return true;
		return false;
	}
}