import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


public class KNN {
	private Jogo[] jogosTreinamento;
	private Jogo[] jogosTeste;
	
	public KNN() {
			
	}
	
	
	//Leitura do dataset carregando no vetor de jogos do KNN
	public static Jogo[] lerArquivo() throws FileNotFoundException,IOException {
		FileReader file = new FileReader("artefatos\\dataset.txt");
		BufferedReader arq = new BufferedReader(file);
		
		//exemplar serve para identificar quando termina um exemplar e começa outro
		String linha = "";
		String jogoComClasse[];
		Jogo jogos[] = new Jogo[4845];
		boolean attributes []= new boolean[15]; 
		int j = 0;
		int boolAux = 0;
		int aux1 = 0;
		Jogo jogo =  null;
		
		while ((linha = arq.readLine()) != null) {
			jogoComClasse = linha.split(",");
			attributes  = new boolean[15];
			for(int i = 0; i < 15 ; i++) {
//				boolAux = jogoComClasse[i] ? 1 : 0;
//				attributes = boolAux;	
			}
		}
		
		return jogos;
	}
	
	
	//método que divide o dataset
	public void dividirJogos(Jogo[] jogos) {
		Jogo[] treino = new Jogo[3230];
		Jogo[] teste = new Jogo[1615];
		int[] id = new int[4845]; //força pra que não seja setado classe como null
		int [] numerosAleatorios = new int [4845];
		int indiceTreino = 0;
		int indiceTeste = 0;
		Random rand = new Random();
		int numeroAleatorioGerado;
		int flag = 0;
		boolean parada = false;
		
		
		//divisao
		flag = 0;
		parada = false;
		int tamanhoVet = 0;
		
		//preenche o vetor com -1 para fazer a validação do vetor
		for(int i = 0; i < 4845; i++) {
			numerosAleatorios[i] = -1;
		}
		
		//pega numeros aleatorios
		while (parada != true) {
			numeroAleatorioGerado = rand.nextInt(4845);
			
			//verifica se o numero sorteado já aconteceu antes
			for(int i = 0; i < 4845; i++) {
				if (numerosAleatorios[i] == numeroAleatorioGerado) {
					flag = 1;
				}
			}
			
			//se a flag for igual a 0 significa que não houve numero repetido, então o numero é salvo no vetor
			if(flag == 0) {
				for(int i = 0; i < 4845; i++) {
					if (numerosAleatorios[i] == -1) {
						numerosAleatorios[i] = numeroAleatorioGerado;
						tamanhoVet++;
					}
				}
			}
			
			//checa se o vetor foi preenchido
			if(tamanhoVet == 4845) {
				
				parada = true;
				
			}
			//seta a flag para 0 novamente
			flag = 0;
			
			
			//preenche o vetor de treino com os numeros sorteados
			if(parada == true) {
				for(int i = 0; i < 3230; i++) {
					id[indiceTreino] = jogos[numerosAleatorios[i]].getId(); //ANTES DE MEXER COM AS IMAGENS SALVA LOGO A CLASSE DAS MESMAS
					treino[indiceTreino] = jogos[numerosAleatorios[i]];
					System.out.println(jogos[numerosAleatorios[i]]);
					indiceTreino++;
				}
				for(int i = 3230; i < 4845; i++) {
					teste[indiceTeste] = jogos[numerosAleatorios[i]];
					teste[indiceTeste].setId(0);
					indiceTeste++;
				}
			}
		}
		
		
	}
	
	
	//calculo de distancia euclidiana
	
	public double distanciaEuclidianaPonderada(Jogo jogoA, Jogo jogoB) {
		int bool1 = 0;
		int bool2 = 0;
		double soma = 0;
		double sub = 0;
		double resultado = 0;
		
		for(int i = 0; i < 15; i++) {
			bool1 = jogoA.getAtributos()[i] ? 1 : 0;
			bool2 = jogoB.getAtributos()[i] ? 1 : 0;
			sub = bool1 - bool2;
			soma += Math.pow(sub, 2);
			sub = 0;
			bool1 = 0;
			bool2 = 0;
		}
		
		resultado = Math.sqrt(soma);
		
		return resultado;
	}

	// ponderamento da distancia Euclidiana
	private double ponderamento(double resultado, Jogo jogoA, Jogo jogoB) {
		double w, temp = 0, resultadoPonderado = 0;
		int bool1 = 0;
		int bool2 = 0;
		
				// obtendo o valor do peso
		w = 1 / resultado;

		// somatório da distancia euclidiana aplicando o peso
		for (int i = 0; i < 256; i++) {
			temp += (w * (Math.pow(((bool1 = jogoA.getAtributos()[i] ? 1 : 0) - (bool2 = jogoB.getAtributos()[i]? 1 : 0)), 2)));
		}

		// raiz do ponderamento
		resultadoPonderado = Math.sqrt(temp);
		return resultadoPonderado;
	}
	

	// Metodo para classificação dos jogos - OBS USAR UM NUMERO IMPAR PARA O K
	// caso contrário será setado para  o próximo ímpar automaticamente
		public String classificacao(int k, Jogo[] jogoTreinamento, Jogo jogo) {

			String retorno = "NAO CLASSIFICADO";
			int maior;
			int treinamento = jogoTreinamento.length;
			double[] dist = new double[treinamento];
			double[] menoresdist = new double[treinamento];
			
			//verifica se k é par, se sim se torna ímpar
			if (k% 2 == 0)
				k++;
	       
			//faz o calculo da distancia euclindiana ponderada
			for (int i = 0; i < treinamento; i++) {
				double d = this.distanciaEuclidianaPonderada(jogoTreinamento[i], jogo);
				dist[i] = d;
				menoresdist[i] = d;
			}
			
			return retorno;
		}
	
	public Jogo[] getJogosTreinamento() {
		return jogosTreinamento;
	}


	public void setJogosTreinamento(Jogo[] jogosTreinamento) {
		this.jogosTreinamento = jogosTreinamento;
	}


	public Jogo[] getJogosTeste() {
		return jogosTeste;
	}


	public void setJogosTeste(Jogo[] jogosTeste) {
		this.jogosTeste = jogosTeste;
	}
	
	
}
