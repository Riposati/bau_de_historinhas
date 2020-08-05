package pv.modelo.dao;

/**
 *
 * @author Gustavo Riposati 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoFactory {

    public static Connection getConexao() {
        String caminho = "jdbc:postgresql";
        String host = "localhost";
        String porta = "5432";
        String bd = "postgres";
        String login = "postgres";
        String senha = "admin";
        String url = caminho + "://" + host + ":" + porta + "/" + bd;

        Connection conexao = null;

        try {
//            System.out.println("Conectando ao banco.");
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(url, login, senha);
//            System.out.println("Conexão estabelecida.");
        } catch (SQLException e) {
            System.out.println("Ocorreu algum erro no acesso ao Banco.");
            while (e != null) {
//                System.out.println("SQL State: " + e.getSQLState());
//                System.out.println("Mensagem: " + e.getMessage());
//                System.out.println("Error Code: " + e.getErrorCode());
                e = e.getNextException();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexaoFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexao;
    }

    private ConexaoFactory() {
    }
}