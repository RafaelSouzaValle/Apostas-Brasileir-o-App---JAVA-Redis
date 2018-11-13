package model;

import java.util.List;

public class Aposta {

	// Recebe a pontuação total obtida com a aposta
	private int pontuacao;

	// Recebe a instância de uma campeonato
	private Campeonato campeonatoApostado;

	// Sorteia times e resultados dos jogos de uma aposta
	public Campeonato criaAposta() {

		campeonatoApostado = new Campeonato();

		campeonatoApostado.executaCampeonato();

		return campeonatoApostado;
	}

	// Compara campeonato do Banco de dados com o da aposta
	// e calcula a pontuação obtida
	public int calculaPontuacao(Campeonato campeonato, Campeonato aposta) {

		// Recebe a lista de rodadas do campeonato passado como argumento
		List<Rodada> rodadas = campeonato.getRodadas();

		int numRodada = 0;
		// Lê as rodadas do campeonato
		for (Rodada rodada : rodadas) {

			// Recebe a lista de partidas da rodada atual
			List<Partida> partidas = rodada.getPartidas();

			int numPartida = 0;
			// Lê as partidas da rodada atual
			for (Partida partida : partidas) {

				// Recebe resulrado das partidas atuais
				String resultado = partida.getResultado();

				String resultadoApostado = aposta.getRodadas().get(numRodada).getPartidas().get(numPartida)
						.getResultado();

				if (resultado.equals(resultadoApostado)) {
					pontuacao++;
				}
				numPartida++;
			}
			numRodada++;
		}
		return pontuacao;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public Campeonato getCampeonatoApostado() {
		return campeonatoApostado;
	}

	public void setCampeonatoApostado(Campeonato campeonatoApostado) {
		this.campeonatoApostado = campeonatoApostado;
	}
}
