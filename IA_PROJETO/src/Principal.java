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
		
		
		int k = 417;
		KNN knn = new KNN();
		inicio = System.currentTimeMillis();
		knn.dividirJogos(jogos);
	}

}
