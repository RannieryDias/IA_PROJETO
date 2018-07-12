import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class KNN {
	private Jogo[] conjuntoTreinamento;
	private Jogo[] conjuntoTeste;
	
	public KNN() {
			
	}
	
	
	//Leitura do dataset carregando no vetor de jogos do KNN
	public static Jogo[] lerArquivo() throws FileNotFoundException,IOException {
		FileReader file = new FileReader("artefatos\\dataset.txt");
		BufferedReader arq = new BufferedReader(file);
		
		//exemplar serve para identificar quando termina um exemplar e começa outro
		String exemplar = "";
		Jogo jogos[] = new Jogo[4845];
		boolean attributes []= new boolean[15]; 
		int j = 0;
		Jogo jogo =  null;
		int aux1 = 0;
		
		while ((exemplar = arq.readLine()) != null) {
			
		}
		
		return jogos;
	}
	
	public void dividirJogos(Jogo[] jogos) {
		Jogo[] treino = new Jogo[3230];
		Jogo[] teste = new Jogo[1615];
		int indiceTreino = 0;
		int indiceTeste = 0;
		
		//TODO definir critério para divisão do dataset
		
	}
	
	public double distanciaEuclidiana(Jogo jogo1, Jogo jogo2) {
		double soma = 0;
		double subtracao = 0;
		double resultado = 0;
		
		for(int i = 0; i < 15; i++) {
			// TODO resolver a distancia de manhattan com booleanos
			/*
			 * sub = jogo1 - jogo2;
			 * soma += Math.pow(sub, 2);
			 * sub = 0;
			 * */
		}
		
		resultado = Math.sqrt(soma);
		
		return resultado;
	}
	
	
	
}
