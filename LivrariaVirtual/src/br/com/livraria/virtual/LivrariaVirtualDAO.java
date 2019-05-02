package br.com.livraria.virtual;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

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
				
				
				 System.out.println("informe o id do livro:");
                 li2.setId(teclado.nextInt());
                 
                 if(li2.getId()==Integer.parseInt(r)) {              //Integer.toString(li2.getId())==r
                	 
                 
                 
                 System.out.println("informe o estoque:");
                 li2.setEstoque(teclado.nextInt());
                 
                 System.out.println("informe o frete:");
                 li2.setFrete(teclado.nextFloat());
                 cadastrarLivroImpresso(li2);
                 break;
                 }else {
                	 System.out.println("Id inválido");
                	 break;
                 }
              
			case "eletronico":
				
				 System.out.println("informe o id do livro:");
                 le2.setId(teclado.nextInt());
                 
                 if(le2.getId()==Integer.parseInt(r)) {
                 
                 System.out.println("informe o tamanho:");
                 le2.setTamanho(teclado.nextInt());
                 
                 
                 cadastrarLivroEletronico(le2);
                 
                 break;
                 }else {
                	 System.out.println("Id inválido");
                	 break;
                 }
			case "ambos":
				System.out.println("informe o id do livro:");
                li2.setId(teclado.nextInt());
                
                if(li2.getId()==Integer.parseInt(r)) {
                
                System.out.println("informe o estoque:");
                li2.setEstoque(teclado.nextInt());
                
                System.out.println("informe o frete:");
                li2.setFrete(teclado.nextFloat());
                cadastrarLivroImpresso(li2);
                }else {
               	 System.out.println("Id inválido");
               	 break;
                }
                
             
                
                System.out.println("informe o id do livro:");
                le2.setId(teclado.nextInt());
                if(le2.getId()==Integer.parseInt(r)) {
                    
                
                System.out.println("informe o tamanho:");
                le2.setTamanho(teclado.nextInt());
                
                
                cadastrarLivroEletronico(le2);
                break;
                }else {
                  	 System.out.println("Id inválido");
                  	 break;
                   }
				
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

	
}
