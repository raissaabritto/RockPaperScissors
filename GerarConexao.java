/*
 
Projeto - Avaliação Final

Grupo:
Luís Eduardo Dalle Molle Bacelar Carvalho Santana - 22104765
Gabriel Carlete de Amorim - 22100699
Raissa Abate Britto - 22108822

*/

package projeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GerarConexao {
	
public static Connection criaConexao() throws SQLException {
		

		String url = "jdbc:mysql://localhost:3306/db_usuarios"; //Nome da base de dados
		String user = "root"; //nome do usuário do MySQL
		String password = "root"; //senha do MySQL

		
		Connection conexao = null;
		conexao = DriverManager.getConnection(url, user, password);
		return conexao;
	}
}