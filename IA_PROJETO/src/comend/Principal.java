package comend;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import javax.swing.plaf.synth.SynthSeparatorUI;

public class Principal {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int id = 0;
		long inicio = 0;
		long fim = 0;
		long tempoDeExec = 0;
		Jogo jogoUser = null;
		Jogo temporario = null;
		Jogo [] recomendados = new Jogo[3];
		Jogo [] recomendadosk1 = new Jogo[3];
		Jogo [] recomendadosk2 = new Jogo[3];;
		Jogo jogoPlayers = null;
		Scanner scanner = new Scanner(System.in);
		BufferedReader buffer = new BufferedReader (new InputStreamReader(System.in));
		String entradaUsuario = null;
		String atributosComparados;
		NumberFormat formatter = decimal();
		
		
		
		Jogo [] jogos = KNN.lerArquivo();
		
		int k = 17; //PARAMETRO DO KNN
		
		//inicializa KNN e e preenche o vetor com Jogos
		KNN knn = new KNN();
		inicio = System.currentTimeMillis();
		knn.inicializaVetor(jogos);
		
		String [] nomesJogosPlayers = new String[3];
		
		
		//Preenche os usuários com jogos e suas recomendações
		
		Jogador player1 = new Jogador();
		player1.setNome("Malu");
		nomesJogosPlayers[0] = "Left_4_Dead_2";
		nomesJogosPlayers[1] = "Trine_2";
		nomesJogosPlayers[2] = "Lara_Croft_and_the_guardian_of_light";
		player1.setNomesDosJogos(nomesJogosPlayers);
		jogoPlayers = knn.converteNomeId(nomesJogosPlayers[0], jogos);
		jogoPlayers = knn.converteNomeId(nomesJogosPlayers[1], jogos);
		jogoPlayers = knn.converteNomeId(nomesJogosPlayers[2], jogos);
		knn.recomendacao(k, knn.getJogos(), jogoPlayers);	
		recomendados = knn.getRecomendados();
		
		System.out.println("Os jogos que recomendamos para "+ player1.getNome() + " são: ");
		byte cont = 1;
		for (byte i = 0; i < 3; i++) {
			System.out.println(cont + " - " + recomendados[i].getNome() + " Com taxa de recomendacao: " + formatter.format(recomendados[i].getRecommendationCount()));
			for(byte j = 0; j < 15; j++)
				System.out.println(recomendados[i].getAtributos()[j]);
			cont++;
		}
		System.out.println("Pressione Enter para continuar!");
		scanner.nextLine();
		
		
		
		Jogador player2 = new Jogador();
		player2.setNome("Daniel");
		nomesJogosPlayers[0] = "Dota_2";
		nomesJogosPlayers[1] = "Counter-Strike:_Global_Offensive";
		nomesJogosPlayers[2] = "Magicka";
		player2.setNomesDosJogos(nomesJogosPlayers);
		jogoPlayers = knn.converteNomeId(nomesJogosPlayers[0], jogos);
		jogoPlayers = knn.converteNomeId(nomesJogosPlayers[1], jogos);
		jogoPlayers = knn.converteNomeId(nomesJogosPlayers[2], jogos);
		knn.recomendacao(k, knn.getJogos(), jogoPlayers);	
		recomendados = knn.getRecomendados();
	
		System.out.println("Os jogos que recomendamos para "+ player2.getNome() + " são: ");
		cont = 1;
		for (byte i = 0; i < 3; i++) {
			System.out.println(cont + " - " + recomendados[i].getNome() + " Com taxa de recomendacao: " + formatter.format(recomendados[i].getRecommendationCount()));
			for(byte j = 0; j < 15; j++)
				System.out.println(recomendados[i].getAtributos()[j]);
			cont++;
		}
		System.out.println("Pressione Enter para continuar!");
		scanner.nextLine();
		
		
		
		Jogador player3 = new Jogador();
		player3.setNome("Marcos");
		nomesJogosPlayers[0] = "Killing_Floor";
		nomesJogosPlayers[1] = "Team_Fortress_2";
		nomesJogosPlayers[2] = "Final_Fantasy_9";
		player3.setNomesDosJogos(nomesJogosPlayers);
		knn.recomendacao(k, knn.getJogos(), jogoPlayers);	
		recomendados = knn.getRecomendados();
	
		System.out.println("Os jogos que recomendamos para "+ player3.getNome() + " são: \n");
		cont = 1;
		for (byte i = 0; i < 3; i++) {
			System.out.println(cont + " - " + recomendados[i].getNome() + " Com taxa de recomendacao: " + formatter.format(recomendados[i].getRecommendationCount()));
			for(byte j = 0; j < 15; j++)
				System.out.println(recomendados[i].getAtributos()[j]);
			cont++;
		}
		System.out.println("Pressione Enter para continuar!");
		scanner.nextLine();
		
		
		System.out.println("\n");
		System.out.println("Digite aqui o nome de um jogo: \r");
		
		entradaUsuario = buffer.readLine();
		entradaUsuario = entradaUsuario.replaceAll(" ","_");
		
		byte flag = 0;
		
		while(flag == 0) {
			temporario = knn.converteNomeId(entradaUsuario, jogos);
			if( temporario != null) {
				jogoUser = (knn.converteNomeId(entradaUsuario, jogos));
				flag = 1;
			}else {
				System.out.println("\n");
				System.out.println("Digite aqui o nome de um jogo");
				
				entradaUsuario = buffer.readLine();
				entradaUsuario = entradaUsuario.replaceAll(" ","_");
				System.out.println("nome inserido " + entradaUsuario);
			}
		}
		
		System.out.println( jogoUser.getNome() );
		
		for (byte i = 0; i < 15; i++) {
			System.out.println(jogoUser.getAtributos()[i]);
		}
		
		knn.recomendacao(k = 4, knn.getJogos(), jogoUser);
		recomendadosk1 = knn.getRecomendados();
		knn.recomendacao(k = 32, knn.getJogos(), jogoUser);
		recomendadosk2 = knn.getRecomendados();
		
		

		
		//imprime o primeiro K
		System.out.println("\n");
		System.out.println("Os jogos que recomendamos para K = 4 são: \n");
		cont = 1;
		for (byte i = 0; i < 3; i++) {
			System.out.println("\n" + cont + " - " + recomendadosk1[i].getNome() + " Com taxa de recomendacao: " + formatter.format(recomendadosk1[i].getRecommendationCount()));
			for(byte j = 0; j < 15; j++)
				System.out.println(recomendadosk1[i].getAtributos()[j]);
			cont++;
		}
		System.out.println("Pressione Enter para continuar!");
		scanner.nextLine();
		
		
		//imprimeo segundo K
		System.out.println("\n");
		System.out.println("Os jogos que recomendamos para K = 32 são: \n");
		cont = 1;
		for (byte i = 0; i < 3; i++) {
			System.out.println("\n" + cont + " - " + recomendadosk2[i].getNome() + " Com taxa de recomendacao: " + formatter.format(recomendadosk2[i].getRecommendationCount()));
			for(byte j = 0; j < 15; j++)
				System.out.println(recomendadosk2[i].getAtributos()[j]);
			cont++;
		}
		
		
		
		fim = System.currentTimeMillis();
		
		tempoDeExec = fim - inicio;
		System.out.println("Tempo de Execução: " + tempoDeExec + "ms");
	}

	 public static NumberFormat decimal() {
	        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ROOT);
	        symbols.setDecimalSeparator(',');
	        symbols.setGroupingSeparator('.');
	        return new DecimalFormat("#0,000", symbols);
	    }
	 

}
