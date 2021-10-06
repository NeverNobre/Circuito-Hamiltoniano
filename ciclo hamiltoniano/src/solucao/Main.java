package solucao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static int resposta[];
	static int graf[][];
	static int nVertices;
	static int j;
	static List<String> respostas= new ArrayList<>();
	
	public static void main(String[] args) {
		try {
			//configuraGrafo("src/solucao/grafo1.txt");
			//configuraGrafo("src/solucao/grafo2.txt");
			configuraGrafo("src/solucao/grafo3.txt");
		}
		catch(Exception e) {System.out.print(e.getMessage());}

		Hamiltonian(2);
		System.out.println("Total circuitos hamiltonianos obtidos: "+respostas.size());
		respostas.forEach(System.out::println);
	}
	
	public static void Hamiltonian(int k) {
		do {
		int ultimoInserido = NextVertex(k);
		if (resposta[k]==0) return;
		
		if (k==nVertices) { 
			int contador=0;
			String resp="";
			for (int a=1;a<=nVertices;a++) {
				if(resposta[a]==ultimoInserido) contador++;
				resp = resp+resposta[a]+",";
			}
			
			if (contador==1) {
					resp = resp+"1";
					if(!existeNaLista())
					respostas.add(resp);
			}
		}
		else Hamiltonian(k+1);
		} while (true);
		
	}
	
	public static int NextVertex(int k) {
		do {
			resposta[k]=(resposta[k]+1)%(nVertices+1);
			
			if(resposta[k]==0) return 0;			
			if (graf[resposta[k-1]][resposta[k]]!=0)
				for(j=1; j<k;j++) if (resposta[j]==resposta[k]) break;
			
				if (j==k)
					if (k<nVertices||(k==nVertices)&&graf[resposta[nVertices]][resposta[1]]!=0) {
						return resposta[k];
					}
		}while(true);
	}
	public static void configuraGrafo(String dir) throws Exception {
		BufferedReader ler= new BufferedReader(new FileReader(dir));
		
		int numeroDeVertices = Integer.parseInt(ler.readLine().split(" ")[1]);
		
		graf= new int[numeroDeVertices+1][numeroDeVertices+1];
		
		String numeros[];
		for (int i=1;i<=numeroDeVertices; i++) {
			numeros=ler.readLine().split(",");
			for(int j=1; j<=numeroDeVertices; j++)
				graf[i][j]=Integer.parseInt(numeros[j-1]);
		}
		ler.close();
		resposta=new int[numeroDeVertices+1];
		resposta[0]=0;
		resposta[1]=1;
		
		for(int a=2; a<=numeroDeVertices;a++) resposta[a]=0;
		
		nVertices=numeroDeVertices;		
	}
	public static boolean existeNaLista() {
		String resp="1,";
		
		for(int i=nVertices;i>1;i--) resp=resp+resposta[i]+",";
		resp=resp+1;

		if(respostas.contains(resp))
		return true;
		return false;
	}
}