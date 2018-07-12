import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class KNN {
	
	
	public KNN() {
			
		}
	
	public static Jogo[] lerArquivo() throws FileNotFoundException,IOException {
		FileReader file = new FileReader("dataset.txt");
		BufferedReader arq = new BufferedReader(file);
		return null;
	}
}
