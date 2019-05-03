package br.com.livraria.virtual;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Conexao {
	String url = "jdbc:mysql://localhost:3306/livrariavirtual?useSSL=false";
	String usuario = "root";
	String senha = "123456";
	
	
	public void conectar() {
		
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection(url,usuario,senha);
			System.out.println("Conexão estabelecida!!" + conn);
			
		} catch (SQLException sqle) {
			// TODO: handle exception
			
			System.out.println("Conexão falhou" +sqle);
		}
		
		
	}
	

	
}
