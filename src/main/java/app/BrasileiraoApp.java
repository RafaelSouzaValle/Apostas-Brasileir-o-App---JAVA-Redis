package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import jedis.JedisManager;
import model.Aposta;
import model.Campeonato;
import model.Endereco;
import model.Genero;
import model.Usuario;

public class BrasileiraoApp {

	public static void main(String[] args) throws ParseException {

		//Cria um usuario
		Usuario usuario = new Usuario("apelido1", "nome1");
		String nascimento = "10/10/1986";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		usuario.setNascimento(sdf.parse(nascimento));
		usuario.setGenero(Genero.Masculino);
		Endereco endereco = new Endereco("Brasil", "RJ", "Rio de Janeiro", "Rua qualquer" , "complem. nenhum" , "00000-000");
		usuario.setEndereco(endereco);
		usuario.setPontuacao(0);
		
		//Salva usuário
		JedisManager.salvaUsuario(usuario);
		
		//Cria uma aposta num campeonato
		Campeonato c1 = new Campeonato();
		
		//Executa aposta
		c1.executaCampeonato();
		
		//Cria um campeonato
		Campeonato campeonato = new Campeonato();

		//Executa o campeonato
		campeonato.executaCampeonato();
		
		//Atribui pontuação ao usuário
		usuario.setPontuacao(Aposta.calculaPontuacao(campeonato, c1));
		
		//imprime usuário com pontuação
		System.out.println(usuario.toString());
		
		//imprime o campeonato
//		c1.imprimeCampeonato();
		
		//Salva o campeonato principal no banco de dados
		JedisManager.salvaCampeonato(campeonato);
		
		//Recupera o campeonato salvo no Banco e o imprime
		JedisManager.carregaCampeonatoSalvo().imprimeCampeonato();
		
	}

}
