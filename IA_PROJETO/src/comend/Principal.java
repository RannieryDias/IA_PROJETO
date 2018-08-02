package comend;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.plaf.synth.SynthSeparatorUI;

public class Principal {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int id = 0;
		long inicio = 0;
		long fim = 0;
		long tempoDeExec = 0;
		float percentualDeAcerto = 0;
		BufferedReader buffer = new BufferedReader (new InputStreamReader(System.in));
		String entradaUsuario;
		
		Jogo [] jogos = KNN.lerArquivo();
		System.out.println("checando tamanho do vetor " + jogos.length);
//		int[] idsDosJogos = new int[jogos.length]; //IDs dos jogos para ser usado como gabarito
//		for(int i = 0; i < idsDosJogos.length; i++) {
//			
//		}
		
		
		int k = 417;
		KNN knn = new KNN();
		inicio = System.currentTimeMillis();
		
		
		System.out.println("Digite aqui o nome de um jogo");
		entradaUsuario = buffer.readLine();
		entradaUsuario = entradaUsuario.replaceAll(" ","_");
		System.out.println("nome inserido " + entradaUsuario);
		id = knn.converteNomeId(entradaUsuario, jogos);
		Jogo jogo = jogos[2]; 
		//knn.teste(k,id, knn.getjogos,jogo);
		
		fim = System.currentTimeMillis();
		
		tempoDeExec = fim - inicio;
		System.out.println("Tempo de Execução: " + tempoDeExec + "ms");
	}

	

}
