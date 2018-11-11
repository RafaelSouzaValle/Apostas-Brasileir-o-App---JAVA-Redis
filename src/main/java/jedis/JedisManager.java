package jedis;

import java.util.ArrayList;
import java.util.List;

import model.Campeonato;
import model.Partida;
import model.Rodada;
import model.Usuario;
import redis.clients.jedis.Jedis;

public class JedisManager {

	// Conecta no Redis do localhost
	public static Jedis jedis = new Jedis("localhost");

	// Registra dado no servidor Redis
	public static void gravarString(String chave, String valor) {
		jedis.set(chave, valor);
	}

	public static void salvaCampeonato(Campeonato campeonato) {

		// Remove os registros do campeonato anterior do Banco de Dados
		jedis.flushDB();

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
				String[] valores = { partida.getNomeTime1(), partida.getNomeTime2(), partida.getResultado() };

				String chave = numRodada + "" + numPartida;
				// Salva os times e o resultado no Banco de dados
				jedis.lpush(chave, valores[2]);
				jedis.lpush(chave, valores[1]);
				jedis.lpush(chave, valores[0]);

				numPartida++;
			}
			numRodada++;
		}
	}

	// Carrega os dados armazenados no Banco de Dados e
	// retorna uma instaância de Campeonato com contendo as informações
	public static Campeonato carregaCampeonatoSalvo() {
		// Cria uma instância de Campeonato para receber
		// as informações extraídas do BD
		Campeonato campeonato = new Campeonato();

		// Lê as rodadas do campeonato
		for (int i = 0; i < Campeonato.getQuantidadeRodadas(); i++) {

			Rodada rodada = new Rodada();

			// Lê as partidas da rodada atual
			for (int j = 0; j < Rodada.getQuantidadePartidas(); j++) {
				String chave = i + "" + j;

				// Lista que recebe os Strings lidos do banco de dados
				List<String> listaDados = new ArrayList<String>();

				listaDados.add(jedis.lrange(chave, 0, 2).toString());

				// Separa cada String retornado pelo
				// servidor, o separa em 3 partes e
				// atribui cada um atributo de uma nova partida
				for (String stringPartida : listaDados) {

					// Array de Strings que recebe em cada índice
					// uma parte de um String retornado
					String[] arrayPartida = stringPartida.split(", ");

					// Atribui cada parte do Array a um String
					String time1 = arrayPartida[0].substring(1);
					String time2 = arrayPartida[1];
					String resultado = arrayPartida[2].substring(0, arrayPartida[2].length() - 1);

					// Atribui os Strings aos atributos de cada partida
					Partida partida = new Partida();
					partida.setNomeTime1(time1);
					partida.setNomeTime2(time2);
					partida.setResultado(resultado);

					// Adiciona a partida na rodada
					rodada.getPartidas().add(partida);

					// Imprime uma partida (apenas para testes)
//					System.out.println(
//							"Rodada " + (i + 1) + " | Partida " + (j + 1)
//							+ " Time 1: " + time1
//							+ " X Time 2: " + time2
//							+ " Resultado deu: " + resultado);
				}

			}

			// Adiciona uma rodada no campeonato
			campeonato.getRodadas().add(rodada);
		}

		// Retorna o campeonato
		return campeonato;
	}

	public static void salvaUsuario(Usuario usuario) {

		String apelido = usuario.getApelido();

		jedis.rpush(apelido, usuario.getApelido());
		jedis.rpush(apelido, usuario.getNome());
		jedis.rpush(apelido, usuario.getNascimento().toString());
		jedis.rpush(apelido, usuario.getGenero().toString());
		jedis.rpush(apelido, usuario.getEndereco().toString());
		jedis.rpush(apelido, usuario.getPontuacao().toString());

	}

	public static Usuario carregaUsuarioSalvo(String apelido) {

		List<String> dadosUsuario = jedis.lrange(apelido, 0, 5);
		
		for (String string : dadosUsuario) {
			System.out.println();
		}
		
		return null;

	}

}
