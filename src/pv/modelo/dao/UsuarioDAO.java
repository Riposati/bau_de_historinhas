package pv.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pv.modelo.Usuario;
import pv.testes.usuario.EscritaBanco;
import pv.testes.usuario.LeituraBanco;
import pv.testes.usuario.LeituraTodosBanco;
import pv.testes.usuario.RemocaoBanco;
import pv.testes.usuario.UpdateBanco;

/**
 *
 * @author Gustavo Riposati 
 */
public class UsuarioDAO {   
    
    public void gravar(Usuario user) {
        Connection conexao = ConexaoFactory.getConexao();
        try {
            String insercao = "INSERT INTO usuario (nomeusuario, senha , email) VALUES (?, ?, ?);";

            PreparedStatement pstmt = conexao.prepareStatement(insercao);

            pstmt.setString(1, user.getNome());
            pstmt.setString(2, user.getSenha());
            pstmt.setString(3, user.getEmail());
            
            int resultado = pstmt.executeUpdate();

            if (resultado != 1) {
//                System.out.println("A inserção não foi feita corretamente.");
            } else {
//                System.out.println("\nInserção bem sucedida.");
            }
            pstmt.close();

        } catch (SQLException e) {
//            System.out.println("Ocorreu algum erro no acesso ao Banco.");
            while (e != null) {
//                System.out.println("SQL State: " + e.getSQLState());
//                System.out.println("Mensagem: " + e.getMessage());
//                System.out.println("Error Code: " + e.getErrorCode());
                e = e.getNextException();
            }
        } finally {
//            System.out.println("Terminando conexão.");
            try {
                if (conexao != null) {
                    conexao.close();
                }
//                System.out.println("Conexão terminada.");
            } catch (SQLException ex) {
                Logger.getLogger(EscritaBanco.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Usuario buscar(int codigo) {
        Connection conexao = ConexaoFactory.getConexao();
        Usuario usuario = null;
        try {
            String selecao = "SELECT * FROM usuario WHERE id = ?";
            PreparedStatement pstmt = conexao.prepareStatement(selecao);
            pstmt.setInt(1, codigo);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setNome(rs.getString(2));
                usuario.setSenha(rs.getString(3));
            } else {
//                System.out.println("A busca não retornou resultados.");
            }
            rs.close();
            pstmt.close();

        } catch (SQLException e) {
//            System.out.println("Ocorreu algum erro no acesso ao Banco.");
            while (e != null) {
//                System.out.println("SQL State: " + e.getSQLState());
//                System.out.println("Mensagem: " + e.getMessage());
//                System.out.println("Error Code: " + e.getErrorCode());
                e = e.getNextException();
            }
        } finally {
//            System.out.println("Terminando conexão.");
            try {
                if (conexao != null) {
                    conexao.close();
                }
//                System.out.println("Conexão terminada.");
            } catch (SQLException ex) {
                Logger.getLogger(LeituraBanco.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuario;
    }
    
    public List<Usuario> buscarTodos() {
        Connection conexao = ConexaoFactory.getConexao();
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>(10);
        Usuario usuario;
        try {
            String selecao = "SELECT * FROM usuario";
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery(selecao);

            while (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setNome(rs.getString(2));
                usuario.setSenha(rs.getString(3));
                usuario.setEmail(rs.getString(4));
                usuarios.add(usuario);
            }
            rs.close();
            stmt.close();

        } catch (SQLException e) {
//            System.out.println("Ocorreu algum erro no acesso ao Banco.");
            while (e != null) {
//                System.out.println("SQL State: " + e.getSQLState());
//                System.out.println("Mensagem: " + e.getMessage());
//                System.out.println("Error Code: " + e.getErrorCode());
                e = e.getNextException();
            }
        } finally {
//            System.out.println("Terminando conexão.");
            try {
                if (conexao != null) {
                    conexao.close();
                }
//                System.out.println("Conexão terminada.");
            } catch (SQLException ex) {
                Logger.getLogger(LeituraTodosBanco.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuarios;
    }
    
    public void remover(Usuario usuario) {
        Connection conexao = ConexaoFactory.getConexao();

        try {
            String remocao = "DELETE FROM usuario WHERE id = ?";
            PreparedStatement pstmt = conexao.prepareStatement(remocao);
            pstmt.setInt(1, usuario.getId());

            int remocoes = pstmt.executeUpdate();
            pstmt.close();

            if (remocoes != 1) {
//                System.out.println("Não foi possível executar a remoção.");
            } else {
//                System.out.println("Remoção efetuada com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu algum erro no acesso ao Banco.");
            while (e != null) {
//                System.out.println("SQL State: " + e.getSQLState());
//                System.out.println("Mensagem: " + e.getMessage());
//                System.out.println("Error Code: " + e.getErrorCode());
                e = e.getNextException();
            }
        } finally {
//            System.out.println("Terminando conexão.");
            try {
                if (conexao != null) {
                    conexao.close();
                }
//                System.out.println("Conexão terminada.");
            } catch (SQLException ex) {
                Logger.getLogger(RemocaoBanco.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void atualizar(Usuario usuario) {
        Connection conexao = ConexaoFactory.getConexao();

        try {
            String update = "UPDATE usuario SET nomeusuario=?, senha=? , email = ?";
            update += " WHERE id=?";
            PreparedStatement pstmt = conexao.prepareStatement(update);
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getSenha());
             pstmt.setString(3, usuario.getEmail());
            pstmt.setInt(4, usuario.getId());
            pstmt.executeUpdate();
//            System.out.println("Update executado com sucesso.");
            pstmt.close();
        } catch (SQLException e) {
//            System.out.println("Ocorreu algum erro no acesso ao Banco.");
            while (e != null) {
//                System.out.println("SQL State: " + e.getSQLState());
//                System.out.println("Mensagem: " + e.getMessage());
//                System.out.println("Error Code: " + e.getErrorCode());
                e = e.getNextException();
            }
        } finally {
//            System.out.println("Terminando conexão.");
            try {
                if (conexao != null) {
                    conexao.close();
                }
//                System.out.println("Conexão terminada.");

            } catch (SQLException ex) {
                Logger.getLogger(UpdateBanco.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}


