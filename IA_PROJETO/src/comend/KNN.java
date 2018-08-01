package comend;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Arrays;


public class KNN {
	private Jogo[] jogosTreinamento;
	private Jogo[] jogosTeste;
	
	public KNN() {
			
	}
	
	
	//Leitura do dataset carregando no vetor de jogos do KNN
	public static Jogo[] lerArquivo() throws FileNotFoundException,IOException {
		FileReader file = new FileReader("artefatos\\dataset.txt");
		BufferedReader arq = new BufferedReader(file);
		
		String linha = "";
		String jogoCompleto[];
		String nome = "";
		Jogo jogos[] = new Jogo[4845];
		int attributes []= new int[15];
		int id = 0;
		int indiceRecomenda = 0;
		int j = 0;
		int boolAux = 0;
		Jogo jogo =  null;
		
		while ((linha = arq.readLine()) != null) {
			jogoCompleto = linha.split(",");
			attributes  = new int[15];
			for(int i = 0; i < 15 ; i++) {
				
				//converte de booleano para inteiro
				if(jogoCompleto[i].equals("true")) {
					boolAux = 1;					
				}
				else if(jogoCompleto[i].equals("false")) {
					boolAux = 0;
				}

				attributes[i] = boolAux;	
				boolAux = 0;
			}
			indiceRecomenda = Integer.parseInt(jogoCompleto[15]);
			id = Integer.parseInt(jogoCompleto[16]);
			
			jogo = new Jogo(attributes);
			jogo.setRecommendationCount(indiceRecomenda);
			jogo.setId(id);
			jogo.setNome(jogoCompleto[17]);
			
			jogos[j] = jogo;
			j++;
			jogo = null;
		}
		
		jogo = null;
		
		arq.close();
		file.close();
		return jogos;
	}
	
	
	
	//Converte o nome digitado pelo usuário em ID
	@SuppressWarnings("unlikely-arg-type")
	public int converteNomeId(String nome, Jogo[] jogos) {
		int id = 0;
		int i = 0;
		int aux = 0;
		while (i < jogos.length) {
			if (nome.equals(jogos[i].getNome()) != false) {
				aux = i;
			}
			i++;
		}
		id = jogos[aux].getId();
		System.out.println("ID do jogo digitadO: " + id);
		
		return id;
	}
	
	
	//converte id do jogo recomendado em nome
	public String convertIdNome(int id, Jogo[] jogos) {
		String nome = null;
		int i = 0;
		int aux = 0;
		
		while (i < jogos.length) {
			if (id == jogos[i].getId()) {
				nome = jogos[i].getNome();
			}
		}
		
		return nome;
	}
	
	
	//recebe o valor k, a id do jogo digitado pelo usuário, o vetor com todos os 
	//jogos do dataset, e o jogo em si a ser comparado
	public Jogo teste(int k, int id, Jogo[] jogosComparar, Jogo jogo) {
		int aux = jogosComparar.length;
		int indRecomend = 0;
		int x = 0;
		Jogo recomendado = null;
		Jogo []similares = new Jogo[x];
		int[] idsJogosSimilares = new int [x];
		double[] dist = new double[aux];
		double[] menoresDist = new double[aux];
		
		
		//verifica se k é par, se sim se torna ímpar
		if (k% 2 == 0)
			k++;
			
		for(int i = 0; i < aux; i++) {
			double d = this.distanciaEuclidianaPonderada(jogosComparar[i], jogo);
			dist[i] = d;
		}
		
		//checa checa as distancias mais similares ao jogo inserido e preenche as IDs destes no vetor idsJogosSimilares
		for(int i = 0; i< k; i++) {
			for (int j = 0; j < dist[i]; j++) {
				if(Double.compare(menoresDist[j], dist[j]) == 0){
					idsJogosSimilares[x] = (int) menoresDist[j]; 
					x++;
				}
			}
		}
		
		return recomendado;
	}
	
	/*//método que divide o dataset
	public void preencheVetor(Jogo[] jogos) {
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
					indiceTreino++;
				}
				for(int i = 3230; i < 4845; i++) {
					teste[indiceTeste] = jogos[numerosAleatorios[i]];
					teste[indiceTeste].setId(0);
					indiceTeste++;
				}
			}
		}
		
		
	}*/
	
	

	// Metodo para classificação dos jogos - OBS USAR UM NUMERO IMPAR PARA O K
	// caso contrário será setado para  o próximo ímpar automaticamente
/*		public String classificacao(int k, Jogo[] jogoTreinamento, Jogo jogo) {

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
			
			for(int i = 0; i< k; i++) {
				for (int j = 0; j < dist[i]; j++) {
					if(Double.compare(menoresdist[j], dist[j]) == 0){
						
					}
				}
			}
			
			
			return retorno;
		}*/
	
	
	
	//calculo de distancia euclidiana
			public double distanciaEuclidianaPonderada(Jogo jogoA, Jogo jogoB) {
			int aux1 = 0;
			int aux2 = 0;
			double soma = 0;
			double sub = 0;
			double resultado = 0;
			
			for(int i = 0; i < 15; i++) {
				aux1 = jogoA.getAtributos()[i];
				aux2 = jogoB.getAtributos()[i];
				sub = aux1 - aux2;
				soma += Math.pow(sub, 2);
				sub = 0;
				aux1 = 0;
				aux2 = 0;
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
				temp += (w * (Math.pow(((bool1 = jogoA.getAtributos()[i]) - (bool2 = jogoB.getAtributos()[i])), 2)));
			}

			// raiz do ponderamento
			resultadoPonderado = Math.sqrt(temp);
			return resultadoPonderado;
		}
	
		
	//Pega as k menores distancias e o que tiver maior indice de recomendação
	//para enfim recomendar
		
	//Arrays.sort();
		

		
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
