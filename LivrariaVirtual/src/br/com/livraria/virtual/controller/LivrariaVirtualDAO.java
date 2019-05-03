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
	        System.out.println("Fecho Saporra");
	        
	    }catch (SQLException e){
	        System.out.println("Como tu conseguiu fazer merda fechando o db, caralho? \n" +e);
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
			System.out.println("O livro é impresso, eletronico ou ambos?");
			String escolha = teclado.nextLine();
			switch (escolha) {
			
			case "impresso":
				
                 li2.setId(Integer.parseInt(r));
                 
                 
                 System.out.println("informe o estoque:");
                 li2.setEstoque(teclado.nextInt());
                 
                 System.out.println("informe o frete:");
                 li2.setFrete(teclado.nextFloat());
                 cadastrarLivroImpresso(li2);
                 break;
                 
              
			case "eletronico":
				
				 
                 le2.setId(Integer.parseInt(r));
                 
                 
                 
                 System.out.println("informe o tamanho:");
                 le2.setTamanho(teclado.nextInt());
                 
                 
                 cadastrarLivroEletronico(le2);
                 
                 break;
                 
               
			case "ambos":
				
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
				PreparedStatement prep3 = (PreparedStatement) conn.prepareStatement("SELECT a.*,  b.tamanho, c.frete,estoque "
						+ "FROM livro a, livroeletronico b,livroimpresso c "
						+ "WHERE a.livroId = b.livroId = c.livroId;"); 
				rs = prep3.executeQuery();
				String r="";
		     	while(rs.next()){
		    	  	r +=rs.getInt("livroId")
		    	  	+rs.getString("autores")
		    	  	+rs.getString("editora")
		    	  	+rs.getFloat("preco")
		    	  	+rs.getInt("numVendas")
		    	  	+rs.getInt("tamanho")
		    	  	+rs.getFloat("frete")
		    	  	+rs.getInt("estoque");
		    	  	
		      	}	
		     	System.out.println(r);
					
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	
}
