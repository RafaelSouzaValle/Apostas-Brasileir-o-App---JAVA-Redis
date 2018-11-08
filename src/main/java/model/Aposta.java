package model;

public class Aposta {

	// Recebe a pontuação total obtida com a aposta
	private int pontuacao;
	
	// Recebe a instância de uma campeonato
	private Campeonato campeonatoApostado;
	
	//Cria um campeonato sorteando jogos e resultados para cada rodada
	public Campeonato criaCampeonato() {
		
		campeonatoApostado = new Campeonato();
		
		campeonatoApostado.executaCampeonato();
		
		return campeonatoApostado;
	}

	//Compara campeonato do Banco de dados com o da aposta
	//e calcula a pontuação obtida
	public void calculaPontuacao() {
		//TODO -- Aqui entrará o método
	}
	
}
