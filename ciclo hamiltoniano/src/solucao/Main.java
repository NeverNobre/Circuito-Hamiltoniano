package solucao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static int resposta[];		// cont�m uma sequencia de vertices que formam uma resposta
	static int graf[][];		// arranjo de arranjos para funcionar como matriz, contendo as conex�es entre v�rtices do grafo
								// NOTA: os indices zero ser�o desconsiderados 
	
	static int nVertices;		// inteiro que armazenar� o numero de v�rtices do grafo
	static List<String> respostas= new ArrayList<>();	//lista contendo todas as respostas geradas pelo c�digo
	
	public static void main(String[] args) {
		try {
			//configuraGrafo("src/solucao/grafo1.txt");
			//configuraGrafo("src/solucao/grafo2.txt");
			configuraGrafo("src/solucao/grafo3.txt");	// configura a 'matriz' de conex�es a partir de um arquivo com esses dados
		}
		catch(Exception e) {System.out.print(e.getMessage());}

		Hamiltonian(2);			//inicia da posi��o 2 da resposta, pois zero � desconsiderado e a posi��o 1 vai conter sempre 1
		
		System.out.println("Total circuitos hamiltonianos obtidos: " + respostas.size());
		respostas.forEach(System.out::println);
		//imprime resultados
	}
	
	public static void Hamiltonian(int k) {
		do {
		NextVertex(k);						// procura o pr�ximo v�rtice para esta posi��o
		if (resposta[k] == 0) return;		// se o v�rtice for zero, esgotaram-se os v�rtices, volte para a opera��o anterior
		
		if (k == nVertices) { 				// se a posi��o atual for a ultima, � uma resposta completa
			String resp="";
			for (int a = 1; a <= nVertices; a++)	// gera uma string para essa resposta
				resp = resp + resposta[a]+",";
											
			resp = resp + "1";
			if(!existeNaLista())			// adicione na lista se a resposta j� n�o existir l� invertida
				respostas.add(resp);
		}
		else Hamiltonian(k + 1);				//se a posi��o atual n�o for a ultima, v� para a pr�xima posi��o
		} while (true);
	}
	
	public static void NextVertex(int k) {
		do {boolean valido = false;
			resposta[k] = (resposta[k] + 1) % (nVertices + 1); 	// novo v�rtice � o pr�ximo v�rtice, a n�o ser que seja o ultimo
			
			if(resposta[k] == 0) return;						// se este v�rtice for zero, esgotaram-se os v�rtices, abandone o m�todo!
			if (graf[resposta[k - 1]][resposta[k]] !=0)			// se houver conex�o entre v�rtice e o da ultima posi��o, prossiga
				for(int j = 1; j < k; j++) 						// fa�a uma busca por v�rtices duplicados na resposta atual
					if (resposta[j]==resposta[k]) {valido=false; break;} else valido=true;
										
				if (valido)										// caso n�o haja duplicados, verifique o tamanho da resposta
					if (k < nVertices||(k == nVertices)&&graf[resposta[nVertices]][resposta[1]]!=0) {
						return;				// se a resposta tiver tamanho insuficiente ou o 
											// ultimo v�rtice tiver conex�o com o primeiro, volte para hamiltonian 
					}
		}while(true);
	}
	public static void configuraGrafo(String dir) throws Exception {
		BufferedReader ler= new BufferedReader(new FileReader(dir)); // Leia o arquivo no diret�rio
		
		nVertices = Integer.parseInt(ler.readLine().split(" ")[1]); // Separe "tamanho: " da informa��o e colete o numero de v�rtices 
		
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
		resposta[1]=1;												// Indice 1 recebe o v�rtice 1
		
		for(int a=2; a<=nVertices;a++) resposta[a]=0;				// As demais posi��es recebem v�rtice zero
	}
	// O seguinte m�todo gera uma resposta que seja igual � armazenada em resposta[],
	// mas com as posi��es invertidas, e em seguida, verifica se a resposta j� existe na lista de respostas
	public static boolean existeNaLista() {
		String resp="1,";
		for(int i=nVertices;i>1;i--) resp=resp+resposta[i]+",";
		resp=resp+1;

		if(respostas.contains(resp))
		return true;
		return false;
	}
}