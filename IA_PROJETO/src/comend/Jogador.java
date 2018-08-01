package comend;

import java.util.Arrays;

public class Jogador {
	private String nome;
	private Jogo jogos[] = new Jogo[5];
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Jogo[] getJogos() {
		return jogos;
	}
	public void setJogos(Jogo[] jogos) {
		this.jogos = jogos;
	}
	
	
	
	@Override
	public String toString() {
		return "Jogador [nome=" + nome + ", jogos=" + Arrays.toString(jogos) + "]";
	}
	
}
