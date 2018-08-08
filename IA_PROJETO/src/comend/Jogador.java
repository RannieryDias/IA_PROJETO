package comend;

import java.util.Arrays;

public class Jogador {
	private String nome;
	private String[] nomesDosJogos = new String[3];
	private Jogo jogosRecomendados[] = new Jogo[3];
	
	

	public String[] getNomesDosJogos() {
		return nomesDosJogos;
	}
	public void setNomesDosJogos(String[] nomesDosJogos) {
		this.nomesDosJogos = nomesDosJogos;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Jogo[] getJogosRecomendados() {
		return jogosRecomendados;
	}
	public void setJogosRecomendados(Jogo[] jogos) {
		this.jogosRecomendados = jogos;
	}
	
	
	
	@Override
	public String toString() {
		return "Jogador [nome=" + nome + ", jogos=" + Arrays.toString(jogosRecomendados) + "]";
	}
	
}
