package app;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jedis.JedisManager;
import model.Aposta;
import model.Campeonato;
import model.Endereco;
import model.Genero;
import model.Usuario;

public class BrasileiraoApp {

	public static void main(String[] args) throws ParseException {

		// Cria um usuario
		Usuario usuario = new Usuario("apelido1", "nome1");
		LocalDate d = LocalDate.parse("10/10/1986", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		usuario.setNascimento(d);
		usuario.setGenero(Genero.Masculino);
		Endereco endereco = new Endereco("Brasil", "RJ", "Rio de Janeiro", "Rua qualquer", "complem. nenhum",
				"00000-000");
		usuario.setEndereco(endereco);
		usuario.setPontuacao(0);
		
		// Cria a aposta para o usuário
		usuario.executaAposta();

		// Cria um campeonato
		Campeonato campeonato = new Campeonato();

		// Executa o campeonato
		campeonato.executaCampeonato();

		// Atribui pontuação ao usuário
		usuario.setPontuacao(Aposta.calculaPontuacao(campeonato, usuario.getAposta().getCampeonatoApostado()));

		// Salva usuário
		JedisManager.salvaUsuario(usuario);

		// imprime objeto usuário com pontuação
//		System.out.println(usuario.toString());

		// Carrega usuário salvo no banco de dados
		System.out.println(JedisManager.carregaUsuarioSalvo("apelido1"));
		
		// imprime o campeonato apostado pelo usuário à partir da instância
//		System.out.println("Resultados apostados:");
//		c1.imprimeCampeonato();

		// Salva o campeonato principal no banco de dados
		JedisManager.salvaCampeonato(campeonato);

		// Recupera o campeonato salvo no Banco e o imprime
		System.out.println("Resultados do Campeonato:");
		JedisManager.carregaCampeonatoSalvo().imprimeCampeonato();
	}

}
