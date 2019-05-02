package br.com.livraria.virtual;

public class Livro extends Venda {
	private int id;
	private String titulo;
	private String autores;
	private String editora;
	private float preco;
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public String getAutores() {
		return autores;
	}



	public void setAutores(String autores) {
		this.autores = autores;
	}



	public String getEditora() {
		return editora;
	}



	public void setEditora(String editora) {
		this.editora = editora;
	}



	public float getPreco() {
		return preco;
	}



	public void setPreco(float preco) {
		this.preco = preco;
	}



	public String toString() {
		return "0";
	}
}
