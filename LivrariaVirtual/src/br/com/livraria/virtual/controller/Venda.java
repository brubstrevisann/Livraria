package br.com.livraria.virtual.controller;

import br.com.livraria.virtual.view.LivrariaVirtual;

public class Venda extends LivrariaVirtual{
	private int numero;
	private String cliente;
	private float valor;
	
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}

}
