package model;

import java.util.Date;

public class Usuario {

	private String apelido;
	private String nome;
	private Date nascimento;
	private Genero genero;
	private Endereco endereco;
	private Integer pontuacao;
	
	
	
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Integer getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}
	
	@Override
	public String toString() {
		return "Jogador: " + " | Nome: " + nome  + " | Nascimento: " + nascimento
				+ " | Gênero: " + genero.toString()  + " | " + endereco.toString()  + " | Pontuação: " + pontuacao;
	}
}
