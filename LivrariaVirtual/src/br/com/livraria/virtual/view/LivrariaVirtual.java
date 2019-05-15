package br.com.livraria.virtual.view;

import java.util.Scanner;

import br.com.livraria.virtual.controller.LivrariaVirtualDAO;
import br.com.livraria.virtual.controller.Venda;
import br.com.livraria.virtual.model.Eletronico;
import br.com.livraria.virtual.model.Impresso;

public class LivrariaVirtual {

	final int MAX_IMPRESSOS = 10;
	final int MAX_ELETRONICOS = 20;
	final int MAX_VENDAS = 50;
	private int numimpressos;
	private int numeletronicos;
	private int numVendas;
	
	public int getNumimpressos() {
		return numimpressos;
	}

	public void setNumimpressos(int numimpressos) {
		this.numimpressos = numimpressos;
	}

	public int getNumeletronicos() {
		return numeletronicos;
	}

	public void setNumeletronicos(int numeletronicos) {
		this.numeletronicos = numeletronicos;
	}

	public int getNumVendas() {
		return numVendas;
	}

	public void setNumVendas(int numVendas) {
		this.numVendas = numVendas;
	}

	public int getMAX_IMPRESSOS() {
		return MAX_IMPRESSOS;
	}

	public int getMAX_ELETRONICOS() {
		return MAX_ELETRONICOS;
	}

	public int getMAX_VENDAS() {
		return MAX_VENDAS;
	}

	public static void main(String[] args)  {
		
		Scanner teclado = new Scanner(System.in);
		Impresso li = new Impresso();
		Eletronico le = new Eletronico();
		Venda ve = new Venda();
		
		int a = 0;
		
		
		LivrariaVirtualDAO livraDAO = new LivrariaVirtualDAO();
		
		
		 livraDAO.conectar();
		 while (a < 1) {
		
		System.out.println("Digite <1> para cadastrar um livro");
        System.out.println("Digite <2> para realizar uma venda");
        System.out.println("Digite <3> para listar os livros cadastrados");
        System.out.println("Digite <4> para listar as vendas");
        System.out.println("Digite <5> para sair do programa");
        int escolha = teclado.nextInt();
            
          switch (escolha) {    
               case 1: 
                  
                System.out.println("Informe o titulo do livro:");
                 li.setTitulo(teclado.nextLine());
                 
                 String lixo3=teclado.nextLine();
                               
                 System.out.println("Informe os autores");
                 li.setAutores(teclado.nextLine());
                
        
                 System.out.println("Informe a editora do livro:");
                 li.setEditora(teclado.nextLine());
                 String lixo4 = teclado.nextLine();
                 
        		
                 System.out.println("informe o preço do livro:");
                 li.setPreco(teclado.nextFloat());
                 livraDAO.cadastrarLivro(li);
                
                   break;
               case 2:
            	   System.out.println("Digite o nome do cliente.");
                   ve.setCliente(teclado.next());
                   
                   livraDAO.RealizarVenda(ve);
                   break;
               
               case 3:
                  livraDAO.listarLivros();
                   
                   break;
                   
               case 4:
                livraDAO.ListarVendas();
                   
                   
                   break;
               case 5:
            	   livraDAO.fechar();
            	   a++;
            	   System.exit(0);              
                    break;
                   
               default:
                   System.out.println("Essa não é uma opção valida");
                   
           }
		 }
	
	
		
	}
}
