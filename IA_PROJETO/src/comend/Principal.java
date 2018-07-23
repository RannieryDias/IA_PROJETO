package comend;
import java.io.IOException;
import java.io.IOException;
import java.util.Random;
import javax.swing.plaf.synth.SynthSeparatorUI;

public class Principal {

	public static void main(String[] args) throws NumberFormatException, IOException {
		long inicio = 0;
		long fim = 0;
		long tempoDeExec = 0;
		float percentualDeAcerto = 0;
		
		Jogo [] jogos = KNN.lerArquivo();
		int[] idsDosJogos = new int[jogos.length]; //IDs dos jogos para ser usado como gabarito
		for(int i = 0; i < idsDosJogos.length; i++) {
			
		}
		
		
		
		int k = 417;
		KNN knn = new KNN();
		inicio = System.currentTimeMillis();
		knn.dividirJogos(jogos);
		
		
		
		
		fim = System.currentTimeMillis();
		
		
//		System.out.println("Total de Acertos: " + acertos);
//		percentualDeAcerto = (acertos * 100)/(knn.s().length);
//		System.out.println("Percentual de Acerto: " + percentualDeAcerto + "%");		
		tempoDeExec = fim - inicio;
		System.out.println("Tempo de Execução: " + tempoDeExec + "ms");
	}

	

}
