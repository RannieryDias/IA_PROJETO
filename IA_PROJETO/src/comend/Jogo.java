package comend;

public class Jogo {
	
	private int id;
	private int recommendationCount;
	private int [] atributos;
	private String nome;
	
	public int getRecommendationCount() {
		return recommendationCount;
	}

	public void setRecommendationCount(int recommendationCount) {
		this.recommendationCount = recommendationCount;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Jogo (int [] atributos) {
		this.atributos = atributos;
	}
	
	public int getRecommendationcount() {
		return recommendationCount;
	}


	public void setRecommendationcount(int recommendationcount) {
		this.recommendationCount = recommendationcount;
	}

	public int[] getAtributos() {
		return atributos;
	}

	public void setAtributos(int[] atributos) {
		this.atributos = atributos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public boolean equals(Jogo a) {
		int cont = 0;
		
		for(int i = 0; i<15; i++) {
			if(this.getAtributos()[i] == a.getAtributos()[i])
				cont++;
		}
		if(cont == 15)
		return true;
		else 
		return false;
		
	}
}
