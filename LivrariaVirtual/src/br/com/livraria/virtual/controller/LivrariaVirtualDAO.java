package br.com.livraria.virtual.controller;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import br.com.livraria.virtual.model.Eletronico;
import br.com.livraria.virtual.model.Impresso;
import br.com.livraria.virtual.model.Livro;

public class LivrariaVirtualDAO {
	Impresso li2 = new Impresso();
	Eletronico le2 = new Eletronico();
	Scanner teclado = new Scanner(System.in);
	private String url = "jdbc:mysql://localhost:3306/livrariavirtual?useSSL=false";
	private String usuario = "root";
	private String senha = "123456";
	private Connection conn;
	private PreparedStatement st;
    private ResultSet rs;

	

	
	public void conectar() {
		
		try {
			 conn = (Connection) DriverManager.getConnection(url,usuario,senha);
			System.out.println("Conexão estabelecida!!" + conn);
			
		} catch (SQLException sqle) {
			// TODO: handle exception
			
			System.out.println("Conexão falhou" +sqle);
		}
	}
	public void fechar(){
	    try{
	        conn.close();
	        System.out.println("Conexão encerrada.");
	        
	    }catch (SQLException e){
	        System.out.println("Deu ruim \n" +e);
	    }
	    
	}
	public void cadastrarLivro(Livro l) {
		try {
			
			PreparedStatement prep = (PreparedStatement) conn.prepareStatement("INSERT INTO livro (titulo,autores,editora,preco) values (?, ?, ?,?)");
			prep.setString(1,l.getTitulo());
			prep.setString(2,l.getAutores());
			prep.setString(3,l.getEditora());
			prep.setFloat(4,l.getPreco());
			prep.execute();
			PreparedStatement prep1 = (PreparedStatement) conn.prepareStatement("SELECT livroId FROM livro ORDER BY livroId DESC LIMIT 1 ");
			
		      rs=prep1.executeQuery();
		      String r="";
		     	while(rs.next()){
		    	  	r +=rs.getInt("livroId");
		      	}			
		    	  			
			System.out.println("Id do livro cadastrado: "+r);
			System.out.println("Selecione <1> para livro impresso. \nSelecione <2> para livros eletronicos. \nSelecione <3> para ambos.");
			int escolha = teclado.nextInt();
			switch (escolha) {
			
			case 1:
				
                 li2.setId(Integer.parseInt(r));
                 
                 
                 System.out.println("informe o estoque:");
                 li2.setEstoque(teclado.nextInt());
                 
                 System.out.println("informe o frete:");
                 li2.setFrete(teclado.nextFloat());
                 cadastrarLivroImpresso(li2);
                 break;
                 
              
			case 2:
				
				 
                 le2.setId(Integer.parseInt(r));
                 
                 
                 
                 System.out.println("informe o tamanho:");
                 le2.setTamanho(teclado.nextInt());
                 
                 
                 cadastrarLivroEletronico(le2);
                 
                 break;
                 
               
			case 3:
				
                li2.setId(Integer.parseInt(r));
                
               
                
                System.out.println("informe o estoque:");
                li2.setEstoque(teclado.nextInt());
                
                System.out.println("informe o frete:");
                li2.setFrete(teclado.nextFloat());
                cadastrarLivroImpresso(li2);
                
                
             
                
                System.out.println("informe o id do livro:");
                le2.setId(Integer.parseInt(r));
                
                    
                
                System.out.println("informe o tamanho:");
                le2.setTamanho(teclado.nextInt());
                
                
                cadastrarLivroEletronico(le2);
                break;
                
				
			  default:
                  System.out.println("Essa não é uma opção válida");
			}
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	public void cadastrarLivroImpresso(Impresso li) {
		try {
			PreparedStatement prep = (PreparedStatement) conn.prepareStatement("INSERT INTO livroimpresso(frete,estoque,livroid) values(?,?,?)  ");
			prep.setInt(3,li.getId());
			prep.setFloat(1,li.getFrete());
			prep.setInt(2,li.getEstoque());
			prep.execute();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	public void cadastrarLivroEletronico(Eletronico le) {
		try {
			PreparedStatement prep = (PreparedStatement) conn.prepareStatement("INSERT INTO livroeletronico(tamanho,livroid) values(?,?)  ");
			prep.setInt(2,le.getId());
			prep.setFloat(1,le.getTamanho());
			prep.execute();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

		public void listarLivros() {
			try {
				
				int escolha;
				System.out.println("Selecione <1> para livros Eletronicos. \nSelecione <2> para livros Impressos. \nSelecione <3> para ambos.");
				escolha = teclado.nextInt();
				
				switch(escolha){
					
					case 1:
						
						PreparedStatement prep4 = (PreparedStatement) conn.prepareStatement("SELECT a.*,  b.tamanho "
								+ "FROM livro a, livroeletronico b "
								+ "WHERE a.livroId = b.livroId ;"); 
						
						rs = prep4.executeQuery();
						String r="";
				     	while(rs.next()){
				    	  	r +=("LivroId: ")+rs.getInt("livroId")+("| ")
				    	  	+("Autores: ")+rs.getString("autores")+("| ")
				    	  	+("Editora: ")+rs.getString("editora")+("| ")
				    	  	+("Preço: ")+rs.getFloat("preco")+("| ")
				    	  	+("Tamanho: ")+rs.getInt("tamanho")+("\n");
				    		}System.out.println(r);
						
				
					break;
					
					case 2:
						
						PreparedStatement prep5 = (PreparedStatement) conn.prepareStatement("SELECT a.*,b.frete,estoque "
								+ "FROM livro a,livroimpresso b "
								+ "WHERE a.livroId = b.livroId;"); 
						
						rs = prep5.executeQuery();
						String r2="";
				     	while(rs.next()){
				     		r2 +=("LivroId: ")+rs.getInt("livroId")+("| ")
							    	 +("Autores: ")+rs.getString("autores")+("| ")
							    	 +("Editora: ")+rs.getString("editora")+("| ")
							     	 +("Preço: ")+rs.getFloat("preco")+("| ")
						    	     +("Frete: ")+rs.getFloat("frete")+("| ")
					    	   	  	 +("Estoque: ")+rs.getInt("estoque")+("\n");
				    		}System.out.println(r2);
				
					break;
					
					case 3:
						
						PreparedStatement prep3 = (PreparedStatement) conn.prepareStatement("SELECT a.*,  b.tamanho, c.frete,estoque "
								+ "FROM livro a, livroeletronico b,livroimpresso c "
								+ "WHERE a.livroId = b.livroId = c.livroId;"); 
						rs = prep3.executeQuery();
						String r3="";
				     	while(rs.next()){
				     		r3 +=("LivroId: ")+rs.getInt("livroId")+("| ")
						    	 +("Autores: ")+rs.getString("autores")+("| ")
						    	 +("Editora: ")+rs.getString("editora")+("| ")
						     	 +("Preço: ")+rs.getFloat("preco")+("| ")
						    	 +("Tamanho: ")+rs.getInt("tamanho")+("| ")
					    	     +("Frete: ")+rs.getFloat("frete")+("| ")
				    	   	  	 +("Estoque: ")+rs.getInt("estoque")+("\n");
				     		}
				    System.out.println(r3);
		    	  	break;
		    	  	
		    	  	default:
		    	  		System.out.println("Opção invalida men");
		    	  	
		      	}	
		     	
					
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		public void RealizarVenda(Venda f) {
			
			try {
				
			float preco,frete,valortotal,qt2;
			int qt;
			int id;
			int sub;
			int re;
			int livr;
			
			
					System.out.println("Selecione<1> para livro impresso. \nSelecione <2> para livros eletronicos.");
					livr = teclado.nextInt();
					
					switch(livr) {
					case 1:
						
						PreparedStatement prep5 = (PreparedStatement) conn.prepareStatement("SELECT a.*,b.frete,estoque "
								+ "FROM livro a,livroimpresso b "
								+ "WHERE a.livroId = b.livroId;"); 
						
						rs = prep5.executeQuery();
						String rl="";
				     	while(rs.next()){
				     		rl +=("Código do Livro: ")+rs.getInt("livroId")+("| ")
							    	 +("Autores: ")+rs.getString("autores")+("| ")
							    	 +("Editora: ")+rs.getString("editora")+("| ")
							     	 +("Preço: ")+rs.getFloat("preco")+("| ")
						    	     +("Frete: ")+rs.getFloat("frete")+("| ")
					    	   	  	 +("Estoque: ")+rs.getInt("estoque")+("\n");
				    		}System.out.println(rl);
						
						System.out.println("Qual o código do livro?");
						id = teclado.nextInt();
						System.out.println("Quantidade de livros para vender?");
						qt = teclado.nextInt();
						PreparedStatement prep1 = (PreparedStatement) conn.prepareStatement("SELECT livroimpressoId FROM livroimpresso WHERE livroId ="+id);
						
					      rs=prep1.executeQuery();
					      String r="";
					     	while(rs.next()){
					    	  	r +=rs.getInt("livroimpressoId");
					      	}	
							int livImID = Integer.parseInt(r);
						
						PreparedStatement prep7 = (PreparedStatement) conn.prepareStatement("SELECT estoque FROM livroimpresso WHERE livroId = "+id); 
						rs = prep7.executeQuery();
						String r6 = "";
						while(rs.next()) {
							r6+=rs.getInt("estoque");	
						}
					 
						sub=Integer.parseInt(r6);
						re=sub-qt;
						
						prep7.close();
						
						PreparedStatement prep8 = (PreparedStatement) conn.prepareStatement("SELECT a.preco, b.frete FROM livro a, livroimpresso b WHERE a.livroId = "+id+" AND b.livroId ="+id); 
						rs = prep8.executeQuery();
						String r2 = "";
						String r3 = "";
						while(rs.next()) {
							r2+=rs.getFloat("preco");
							r3+=rs.getFloat("frete");
						}
						preco = Float.parseFloat(r2);
						frete = Float.parseFloat(r3);
						qt2=(float) qt;
						
						valortotal = (preco*qt)+frete;
						System.out.println(valortotal);
						
						prep8.close();

						
					
					
					PreparedStatement prep10 = (PreparedStatement) conn.prepareStatement("INSERT INTO venda (cliente,livroId,valor,livroimpressoId) values (?,?,?,?)");
					prep10.setString(1,f.getCliente());
					prep10.setInt(2, id);
					prep10.setFloat(3,valortotal);
					prep10.setInt(4, livImID);
					
					prep10.execute();
					
					prep10.close();

					
			
					
						
					PreparedStatement prep20 = (PreparedStatement) conn.prepareStatement("UPDATE livroimpresso SET  estoque = "+re+" WHERE livroId= "+id);
					prep20.execute();
					
					break;
					case 2:
						PreparedStatement prep4 = (PreparedStatement) conn.prepareStatement("SELECT a.*,  b.tamanho "
								+ "FROM livro a, livroeletronico b "
								+ "WHERE a.livroId = b.livroId ;"); 
						
						rs = prep4.executeQuery();
						String rl2="";
				     	while(rs.next()){
				    	  	rl2 +=("Código do Livro: ")+rs.getInt("livroId")+("| ")
				    	  	+("Autores: ")+rs.getString("autores")+("| ")
				    	  	+("Editora: ")+rs.getString("editora")+("| ")
				    	  	+("Preço: ")+rs.getFloat("preco")+("| ")
				    	  	+("Tamanho: ")+rs.getInt("tamanho")+("\n");
				    		}System.out.println(rl2);
						
						
						System.out.println("Qual o código do livro?");
						id = teclado.nextInt();
						
						PreparedStatement prep9 = (PreparedStatement) conn.prepareStatement("SELECT preco from livro WHERE livroId = "+id);
						rs = prep9.executeQuery();
						String r4 = "";
						while(rs.next()) {
							r4+=rs.getFloat("preco");
							
						}
						float valEle = Float.parseFloat(r4);
						PreparedStatement prep12 = (PreparedStatement) conn.prepareStatement("SELECT livroeletronicoId FROM livroeletronico WHERE livroId ="+id);
						
					      rs=prep12.executeQuery();
					      String r8="";
					     	while(rs.next()){
					    	  	r8 +=rs.getInt("livroeletronicoId");
					      	}	
							int livEleID = Integer.parseInt(r8);
						
						PreparedStatement prep11 = (PreparedStatement) conn.prepareStatement("INSERT INTO venda (cliente,livroId,valor,livroeletronicoId) values (?,?,?,?)");
						prep11.setString(1,f.getCliente());
						prep11.setInt(2, id);
						prep11.setFloat(3,valEle);
						prep11.setInt(4,livEleID);
						
						prep11.execute();
						
						prep11.close();

						break;
					
						
						
						
						
					default:
					}
				
			
		
			
		}
			catch (Exception e) {
				// TODO: handle exception
			}
			
}
		
		public void ListarVendas() {
			try {
				PreparedStatement prep7 = (PreparedStatement) conn.prepareStatement("SELECT * FROM venda"); 
				
				rs = prep7.executeQuery();
				String r="";
		     	while(rs.next()){
		    	  	r +=("Código do Livro: ")+rs.getInt("livroId")+("| ")
		    	  	+("Cliente: ")+rs.getString("cliente")+("| ")
		    	  	+("Valor: ")+rs.getFloat("valor")+("| ")
		    	  	+("Numero da venda: ")+rs.getInt("numVendas")+("| ")
		    	  	+("Código do livro Impresso: ")+rs.getInt("livroimpressoID")+("| ")
		    	  	+("Código do livro Eletrônico: ")+rs.getInt("livroeletronicoId")+("\n");
		    		}System.out.println(r);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
}
