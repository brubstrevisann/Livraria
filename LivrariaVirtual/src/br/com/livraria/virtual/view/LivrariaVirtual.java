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
		
		
		
		
		LivrariaVirtualDAO livraDAO = new LivrariaVirtualDAO();
		
		
		
		
		/*System.out.println("Digite <1> para cadastrar um livro");
        System.out.println("Digite <2> para realizar uma venda");
        System.out.println("Digite <3> para listar os livros cadastrados");
        System.out.println("Digite <4> para listar as vendas");
        System.out.println("Digite <5> para sair do programa");
        int escolha = teclado.nextInt();
          */  
           //switch (escolha) {    
             //  case 1:
            	  
            	//  System.out.println("Impresso ou eletrônico ?");
            	 // String a = teclado.nextLine();
            	  
            	 //if(a=="impresso") { 
		ve.setCliente("Robertin");
                  livraDAO.conectar();
                  livraDAO.RealizarVenda(ve);
                  //livraDAO.listarLivros();
                  
                  
                  
                /*System.out.println("Informe o titulo do livro:");
                 li.setTitulo(teclado.nextLine());
                 
                 //String lixo3=teclado.nextLine();
                               
                 System.out.println("Informe os autores");
                 li.setAutores(teclado.nextLine());
                
        
                 System.out.println("Informe a editora do livro:");
                 li.setEditora(teclado.nextLine());
        		
        		
        
                 System.out.println("informe o preço do livro:");
                 li.setPreco(teclado.nextFloat());
                 livraDAO.cadastrarLivro(li);
                */
                 
                 /* System.out.println("informe o id do livro:");
                  li.setId(teclado.nextInt());
                  
                  System.out.println("informe o estoque:");
                  li.setEstoque(teclado.nextInt());
                  
                  System.out.println("informe o frete:");
                  li.setFrete(teclado.nextFloat());
                  livraDAO.cadastrarLivroImpresso(li);
                  */
                  
            	 //}//else if(a=="eletronico"){
            		/* System.out.println("Informe o titulo do livro:");
                     le.setTitulo(teclado.nextLine());
                     
                     String lixo3=teclado.nextLine();
                                   
                     System.out.println("Informe os autores");
                     le.setAutores(teclado.nextLine());
                    
                     String lixo=teclado.nextLine();
            
                     System.out.println("Informe a editora do livro:");
                     le.setEditora(teclado.nextLine());
            
                     String lixo2=teclado.nextLine();
            
                     System.out.println("informe o preço do livro:");
                     le.setPreco(teclado.nextFloat());
                     //livraDAO.
                }
                
                livraDAO.conectar();
        
                 
                 livraDAO.fechar();
                   break;
               case 2:
                   //System.out.println(livraDAO.Listar());
                   break;
               
               case 3:
                   System.out.println("Digite o ID");
                   //r.setId(teclado.nextInt());
                   
                   //livroDAO.RemoverporID(r);
                   
                   break;
                   
               case 4:
                 //  System.out.println("Escolha um ID para atualizar o preço:");
                  // p.setId(teclado.nextInt());
                  // System.out.println("Escolha o novo preço:");
                  // p.setPreco(teclado.nextFloat());
                   
                  // funcDAO.AtualizarPrecoporID(p);
                   
                   
                   break;
               case 5:
            	   System.exit(0);              
                    break;
                   
               default:
                   System.out.println("Essa não é uma opção valida");
                   
           }
	*/
	
		
	}
}
