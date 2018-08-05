package comend;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class KNN {

	private Jogo[] vetorJogos;
	Jogo[] recomendados = new Jogo[3];
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
		
	//Preenche vetor com todos os jogos do dataset
	
	public void inicializaVetor(Jogo[] jogos) {
		this.setJogos(jogos);
	}
	
	//Converte o nome digitado pelo usuário em ID
	@SuppressWarnings("unlikely-arg-type")
	public Jogo converteNomeId(String nome, Jogo[] jogos) {
			int id = 0;
			int i = 0;
			int aux = 0;
			Jogo jogoEncontrado = null;
			
			while (i < jogos.length) {
				if (nome.equalsIgnoreCase(jogos[i].getNome()) != false) {
					aux = i;
				}
				i++;
			}
			if(aux !=  0) {
				jogoEncontrado = jogos[aux];
				System.out.println("Jogo econtrado: ID " + jogoEncontrado.getId() + " Nome " + jogoEncontrado.getNome());
				return jogoEncontrado;
			}
			else {
				System.out.println("Desculpe, jogo não encontrado, tente outro!");
				return null;
			}	
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

	// Metodo para classifica��o das imagens - OBS USAR UM NUMERO IMPAR PARA O K
	public Jogo[] recomendacao(int k, Jogo[] jogosComparacao, Jogo jogo) {

		int maior;
		int tamanhoVetor = jogosComparacao.length;
		int indRecomenda = 0;
		double[] dist = new double[tamanhoVetor];
		double[] menoresdist = new double[tamanhoVetor];

		
			
		// verifica se o k é par, caso seja, passa a ser impar
		if (k % 2 == 0)
			k++;

		//chama o método da distancia Euclidiana
		for (int i = 0; i < tamanhoVetor; i++) {
			double d = this.distanciaEuclidianaPonderada(jogosComparacao[i], jogo);
			dist[i] = d;
			menoresdist[i] = d;
		}
		
		// pega as k menores distancias e verifica qual a classe da imagem para
		// no final classificar
		int maximo = 0, medio = 0, minimo = 0, temp = 0 ;
		
		//método que define os 3 jogos com maiores taxas de recomendação
		for(int i = 0; i < k; i++) {
			for(int j = 0; j < dist.length; j++) {
				if(Double.compare(menoresdist[i], dist[j]) == 0) {
					for(int aux = 0; aux < 3; aux++) {
						
						//maxima recomendacao
						if(jogosComparacao[j].getRecommendationCount() > minimo && jogosComparacao[j].getRecommendationCount() > medio && jogosComparacao[j].getRecommendationCount() > maximo) {
							if(jogosComparacao[j].getId() != jogo.getId()) {
								System.out.println("Jogo com maxima recomendacao: " + jogosComparacao[j].getNome());
								minimo = medio;
								temp = maximo;
								medio = temp;
	             				this.recomendados[1] = recomendados[0];
								this.recomendados[0] = jogosComparacao[j];
								System.out.println("valor medio " + medio);
								maximo = jogosComparacao[j].getRecommendationCount();
							}
						}
												
						//media recomendacao
						else if(jogosComparacao[j].getRecommendationCount() > minimo && jogosComparacao[j].getRecommendationCount() > medio && jogosComparacao[j].getRecommendationCount() < maximo) {
							System.out.println("Jogo com media recomendacao: " + jogosComparacao[j].getNome());
							this.recomendados[1] = jogosComparacao[j];
							minimo = medio;
							medio = jogosComparacao[j].getRecommendationcount();
						}
						
						//minima recomendacao
						else if (jogosComparacao[j].getRecommendationCount() > minimo && jogosComparacao[j].getRecommendationCount() < medio && jogosComparacao[j].getRecommendationCount() < maximo) {
							System.out.println("Jogo com menor recomendacao: " + jogosComparacao[j].getNome());
							this.recomendados[2] = jogosComparacao[j];
							minimo = jogosComparacao[j].getRecommendationcount();
						}
					}	
				}
			}
		}
		
		maior = Integer.MIN_VALUE;
		
		
		return recomendados;
	}
	
	// Calcula a distancia de Manhattan
	public double distanciaManhattan(Jogo jogoA, Jogo jogoB) {
		double soma = 0; 
		double subtracao = 0;
		
		for(int i = 0; i < 15; i++) {
			subtracao = jogoA.getAtributos()[i] - jogoB.getAtributos()[i];
			soma += subtracao;
		}
		
		return soma;
	}
	
	//Calcula a distancia de Manhattan ponderada
	public double distanciaManhattanPonderada(Jogo jogoA, Jogo jogoB) {
		double ponderado = 0;
		double dist = 0;
		double w = 0;
		
		dist = distanciaManhattan(jogoA, jogoB);
		w = 1 / dist;
		
		for (int i = 0; i < 15; i++) {
			ponderado += (w*(jogoA.getAtributos()[i]- jogoB.getAtributos()[i]));	
		}
		return ponderado;
	}
	
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
		for (int i = 0; i < 15; i++) {
			temp += (w * (Math.pow(((bool1 = jogoA.getAtributos()[i]) - (bool2 = jogoB.getAtributos()[i])), 2)));
		}
	
		// raiz do ponderamento
		resultadoPonderado = Math.sqrt(temp);
		return resultadoPonderado;
	}
	
	//	Distancia de Hamming 
	public int distanciaHamming(Jogo jogoA,Jogo jogoB) {
		int similaridade = 0;
		
		for (int i = 0; i < 15; i++) {
			similaridade += (jogoA.getAtributos()[i]==jogoB.getAtributos()[i]? 1 : 0); 
		}
		
		return similaridade;
	}
	 
	//gets e sets
	public void setJogos(Jogo[] vetorJogos) {
		this.vetorJogos = vetorJogos;
	}

	public Jogo[] getJogos() {
		return vetorJogos;
	}

	public Jogo[] getRecomendados() {
		return recomendados;
	}

	public void setRecomendados(Jogo[] recomendados) {
		this.recomendados = recomendados;
	}
	
	
}
