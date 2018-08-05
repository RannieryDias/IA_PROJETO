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
		Jogo jogoUser;
		Jogo [] recomendados = new Jogo[3];
		
		
		BufferedReader buffer = new BufferedReader (new InputStreamReader(System.in));
		String entradaUsuario;
		
		Jogo [] jogos = KNN.lerArquivo();
		System.out.println("checando tamanho do vetor " + jogos.length);

		
		int k = 417; //PARAMETRO DO KNN
		
		KNN knn = new KNN();
		inicio = System.currentTimeMillis();
		knn.inicializaVetor(jogos);
		
		System.out.println("\r\n" + 
				":'######::'########:'########::::'###::::'##::::'##:::::::'###::::'########::'##::::'##:'####::'######:::'#######::'########::\r\n" + 
				"'##... ##:... ##..:: ##.....::::'## ##::: ###::'###::::::'## ##::: ##.... ##: ##:::: ##:. ##::'##... ##:'##.... ##: ##.... ##:\r\n" + 
				" ##:::..::::: ##:::: ##::::::::'##:. ##:: ####'####:::::'##:. ##:: ##:::: ##: ##:::: ##:: ##:: ##:::..:: ##:::: ##: ##:::: ##:\r\n" + 
				". ######::::: ##:::: ######:::'##:::. ##: ## ### ##::::'##:::. ##: ##:::: ##: ##:::: ##:: ##::. ######:: ##:::: ##: ########::\r\n" + 
				":..... ##:::: ##:::: ##...:::: #########: ##. #: ##:::: #########: ##:::: ##:. ##:: ##::: ##:::..... ##: ##:::: ##: ##.. ##:::\r\n" + 
				"'##::: ##:::: ##:::: ##::::::: ##.... ##: ##:.:: ##:::: ##.... ##: ##:::: ##::. ## ##:::: ##::'##::: ##: ##:::: ##: ##::. ##::\r\n" + 
				". ######::::: ##:::: ########: ##:::: ##: ##:::: ##:::: ##:::: ##: ########::::. ###::::'####:. ######::. #######:: ##:::. ##:\r\n" + 
				":......::::::..:::::........::..:::::..::..:::::..:::::..:::::..::........::::::...:::::....:::......::::.......:::..:::::..::\r\n" + 
				"");
		
		System.out.println("Digite aqui o nome de um jogo");
		entradaUsuario = buffer.readLine();
		entradaUsuario = entradaUsuario.replaceAll(" ","_");
		System.out.println("nome inserido " + entradaUsuario);
		jogoUser = (knn.converteNomeId(entradaUsuario, jogos));
		
		
		knn.recomendacao(k, knn.getJogos(), jogoUser);
		
		recomendados = knn.getRecomendados();
		
		NumberFormat formatter = decimal();
		
		System.out.println("\n");
		System.out.println("Os jogos que recomendamos são: ");
		byte cont = 1;
		for (byte i = 0; i < 3; i++) {
			System.out.println(cont + " " + recomendados[i].getNome() + " Com taxa de recomendacao: " + formatter.format(recomendados[i].getRecommendationCount()));
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
