package model;

public class Aposta {

	// Recebe a pontua��o total obtida com a aposta
	private int pontuacao;
	
	// Recebe a inst�ncia de uma campeonato
	private Campeonato campeonatoApostado;
	
	//Cria um campeonato sorteando jogos e resultados para cada rodada
	public Campeonato criaCampeonato() {
		
		campeonatoApostado = new Campeonato();
		
		campeonatoApostado.executaCampeonato();
		
		return campeonatoApostado;
	}

	//Compara campeonato do Banco de dados com o da aposta
	//e calcula a pontua��o obtida
	public void calculaPontuacao() {
		//TODO -- Aqui entrar� o m�todo
	}
	
}
