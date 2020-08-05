package pv.testes.usuario;

import java.util.List;
import pv.modelo.Usuario;
import pv.modelo.dao.UsuarioDAO;

/**
 *
 * @author Gustavo Riposati 
 */
public class LeituraTodosBanco {
    
    public static void main(String[] args) {
        
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> usuarios = dao.buscarTodos();
        
        for(Usuario user : usuarios) {
            System.out.print("\nCodigo do usuário  : " + user.getId() + "\n");
            System.out.print("Nome do usuário    : " + user.getNome() + "\n");
            System.out.print("Senha do usuário : " + user.getSenha() + "\n");
        }
        
    }
}


