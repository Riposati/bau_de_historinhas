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
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import pv.modelo.Historias;
import pv.modelo.HistoriasLidas;
import pv.modelo.BauDeHistorinhas;
import pv.modelo.Usuario;
import pv.testes.usuario.EscritaBanco;
import pv.testes.usuario.LeituraBanco;
import pv.testes.usuario.LeituraTodosBanco;
import pv.testes.usuario.RemocaoBanco;
import pv.testes.usuario.UpdateBanco;

/**
 *
 * @author Gustavo Riposati
 * ATALHO PARA COMENTAR E DESCOMENTAR BLOCOS DO CÓDIGO - CTRL + SHIFT + C
 *
 */
public class HistoriaDAO {
    
    public void criaRelatorio(){
    Connection conexao = null;
        try {
            String jasper = "jasper/Relatorio Bau de Historinhas.jasper";
            String saida = "pdfs/Relatorio Bau de Historinhas.pdf";
            conexao = ConexaoFactory.getConexao();
            JasperPrint print = JasperFillManager.fillReport(jasper, null, conexao);
            JasperExportManager.exportReportToPdfFile(print, saida);
            JOptionPane.showMessageDialog(null, "Relatório criado querido usuário");
        } catch (JRException ex) {
//            System.out.println("Problemas na geracao do PDF." + "\n" + ex);
            
            JOptionPane.showMessageDialog(null, "Problemas na geracao do PDF." + "\n" + ex);

        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex) {
//                System.out.println("Problemas fechando a conexao com o BD");
            }
        }
        
    }

    public List<Historias> buscarHistoriasIneditas() {
        Connection conexao = ConexaoFactory.getConexao();
        ArrayList<Historias> historias = new ArrayList<>(10);
        Historias historia;
        try {
            String selecao = "select a.id , a.nome , a.Autor,b.id from historias a , historiasLidas b where a.id = b.id and  b.lidas = false order by a.id";
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery(selecao);

            while (rs.next()) {
                historia = new Historias();
                historia.setId(rs.getInt(1));
                historia.setNomeHistoria(rs.getString(2));
                historia.setNomeAutor(rs.getString(3));
                historias.add(historia);
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
        return historias;
    }

    public void DeletaHistoriaProUser(Historias historia, Usuario usuario) {

        Connection conexao = ConexaoFactory.getConexao();
        try {

            String insercao = "delete from auxiliar_usuario_historia where historia_id=? and usuario_id=?;";

            PreparedStatement pstmt = conexao.prepareStatement(insercao);

            pstmt.setInt(1, historia.getId());
            pstmt.setInt(2, usuario.getId());

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

    public List<HistoriasLidas> buscarTodasLidas() {
        Connection conexao = ConexaoFactory.getConexao();
        ArrayList<HistoriasLidas> historias = new ArrayList<>(10);
        HistoriasLidas historia;
        try {
            String selecao = "SELECT * FROM historiasLidas";
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery(selecao);

            while (rs.next()) {
                historia = new HistoriasLidas();
                historia.setIdHistoria(rs.getInt(1));
                historia.setLidas(rs.getBoolean(2));
                historias.add(historia);
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
        return historias;
    }

    public void gravaHistoriaLida(HistoriasLidas historia , Historias hist) {

        Connection conexao = ConexaoFactory.getConexao();
        try {

            String insercao = "INSERT INTO historiaslidas (lidas) VALUES(?)";

            PreparedStatement pstmt = conexao.prepareStatement(insercao);
            
            pstmt.setBoolean(1, historia.isLidas());

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

    ///--------------------------- aqui é a parte que traz o bau do user ----------------------------- ///
    public List<String> trazBauDoUsuario(String idDoUser) {

        Connection conexao = ConexaoFactory.getConexao();
        ArrayList<String> aux = new ArrayList<>(10);
        BauDeHistorinhas auxiliar;// = new BauDeHistorinhas();
        Historias h;
        Usuario u;
        try {
            String selecao = "select a.nome , b.nomeusuario \n"
                    + "from historias a, usuario b , auxiliar_usuario_historia c\n"
                    + "where a.id = c.historia_id and b.id = c.usuario_id and UPPER(b.nomeusuario) like'%" + idDoUser.toUpperCase() + "'";

            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery(selecao);

            while (rs.next()) {
                auxiliar = new BauDeHistorinhas();
                h = new Historias();
                h.setNomeHistoria(rs.getString(1));
                String v = h.getNomeHistoria();
                aux.add(v);
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
        return aux;
    }

    /*       parte que mostra o relacionamento entre as tabelas de histórias e usuarios que as tem          */
    public List<BauDeHistorinhas> trazBau() {

        Connection conexao = ConexaoFactory.getConexao();
        ArrayList<BauDeHistorinhas> aux = new ArrayList<>(10);
        BauDeHistorinhas auxiliar;// new BauDeHistorinhas();
        try {
            String selecao = "SELECT * FROM auxiliar_usuario_historia";
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery(selecao);

            while (rs.next()) {
                auxiliar = new BauDeHistorinhas();
                auxiliar.setIdHistoria(rs.getInt(1));
                auxiliar.setIdUsuario(rs.getInt(2));
                aux.add(auxiliar);
            }
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            //System.out.println("Ocorreu algum erro no acesso ao Banco.");
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
        return aux;

    }

    public void gravaHistoriaProUser(Historias historia, Usuario usuario) {

        Connection conexao = ConexaoFactory.getConexao();
        try {

            String insercao = "insert into auxiliar_usuario_historia values(?,?);";

            PreparedStatement pstmt = conexao.prepareStatement(insercao);

            pstmt.setInt(1, historia.getId());
            pstmt.setInt(2, usuario.getId());

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

    public void gravar(Historias historia) {
        Connection conexao = ConexaoFactory.getConexao();
        try {
            String insercao = "INSERT INTO historias (foto,nome, historia, autor,indicada) VALUES (? , ? , ? , ? , ?);";

            PreparedStatement pstmt = conexao.prepareStatement(insercao);
            pstmt.setString(1, historia.getFoto());
            pstmt.setString(2, historia.getNomeHistoria());
            pstmt.setString(3, historia.getHistoria());
            pstmt.setString(4, historia.getNomeAutor());
            pstmt.setInt(5, historia.getIndicada());

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

    ///---------------------------- aqui é a parte do buscar um usuário no banco ------------------ ///
    public Historias buscar(int codigo) {
        Connection conexao = ConexaoFactory.getConexao();
        Historias historia = null;
        try {
            String selecao = "SELECT * FROM historias WHERE id = ?";
            PreparedStatement pstmt = conexao.prepareStatement(selecao);
            pstmt.setInt(1, codigo);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                historia = new Historias();
                historia.setId(rs.getInt(1));
                historia.setFoto(rs.getString(2));
                historia.setNomeHistoria(rs.getString(3));
                historia.setHistoria(rs.getString(4));
                historia.setNomeAutor(rs.getString(5));
                historia.setIndicada(rs.getInt(6));

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
        return historia;
    }

    ///---------------------------- aqui é a parte que busca todos os registros do banco ------------------ ///
    public List<Historias> buscarTodos() {
        Connection conexao = ConexaoFactory.getConexao();
        ArrayList<Historias> historias = new ArrayList<>(10);
        Historias historia;
        try {
            String selecao = "SELECT * FROM historias order by id";
            Statement stmt = conexao.createStatement();

            ResultSet rs = stmt.executeQuery(selecao);

            while (rs.next()) {
                historia = new Historias();
                historia.setId(rs.getInt(1));
                historia.setFoto(rs.getString(2));
                historia.setNomeHistoria(rs.getString(3));
                historia.setHistoria(rs.getString(4));
                historia.setNomeAutor(rs.getString(5));
                historia.setIndicada(rs.getInt(6));
                historias.add(historia);
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
        return historias;
    }

    ///---------------------------- aqui é o fim da parte do buscar todos os registros no banco ------------------ ///
    public void remover(Historias historia) {
        Connection conexao = ConexaoFactory.getConexao();
        try {
            String remocaoLidas = "DELETE FROM historiasLidas WHERE id = ?";
            String remocao = "DELETE FROM historias WHERE id = ?";
            PreparedStatement pstmtLidas = conexao.prepareStatement(remocaoLidas);
            PreparedStatement pstmt = conexao.prepareStatement(remocao);
            pstmt.setInt(1, historia.getId());
            pstmtLidas.setInt(1,historia.getId());
            
            int remocoesLidas = pstmtLidas.executeUpdate();
            int remocoes = pstmt.executeUpdate();
            
            pstmt.close();

            if (remocoes != 1 && remocoesLidas !=1) {
//                System.out.println("Não foi possível executar a remoção.");
            } else {
//                System.out.println("Remoção efetuada com sucesso.");
            }
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
                Logger.getLogger(RemocaoBanco.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void atualizarLidas(HistoriasLidas historia) {

        Connection conexao = ConexaoFactory.getConexao();

        try {
            String update = "UPDATE historiasLidas SET lidas=? WHERE id=?";
            PreparedStatement pstmt = conexao.prepareStatement(update);
            pstmt.setBoolean(1, historia.isLidas());
            pstmt.setInt(2, historia.getIdHistoria());
            pstmt.executeUpdate();
//            System.out.println("Update da coluna lidas executado com sucesso.");
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

    public void atualizarIndicadas(Historias historia) {

        Connection conexao = ConexaoFactory.getConexao();

        try {
            String update = "UPDATE historias SET indicada=? WHERE id=?";
            PreparedStatement pstmt = conexao.prepareStatement(update);
            pstmt.setInt(1, historia.getIndicada());
            pstmt.setInt(2, historia.getId());

            pstmt.executeUpdate();
//            System.out.println("Update da coluna lidas executado com sucesso.");
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

    public void atualizar(Historias historia) {
        Connection conexao = ConexaoFactory.getConexao();

        try {
            String update = "UPDATE historias SET foto=?, nome=?, historia=? , autor=?";
            update += " WHERE id=?";
            PreparedStatement pstmt = conexao.prepareStatement(update);
            pstmt.setString(1, historia.getFoto());
            pstmt.setString(2, historia.getNomeHistoria());
            pstmt.setString(3, historia.getHistoria());
            pstmt.setString(4, historia.getNomeAutor());
            pstmt.setInt(5, historia.getId());

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
