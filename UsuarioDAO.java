/*
 
Projeto - Avaliação Final

Grupo:
Luís Eduardo Dalle Molle Bacelar Carvalho Santana - 22104765
Gabriel Carlete de Amorim - 22100699
Raissa Abate Britto - 22108822

*/

package projeto;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UsuarioDAO
{
    public boolean checkLogin(final String login, final String senha) throws SQLException {
        final Connection con = GerarConexao.criaConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            stmt = con.prepareStatement("SELECT * FROM tb_infos WHERE login = ? and senha = ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
        
        }
        return check;
    }
}