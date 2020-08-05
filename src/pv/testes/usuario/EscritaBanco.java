
package pv.testes.usuario;

import java.util.Scanner;
import pv.modelo.Usuario;
import pv.modelo.dao.UsuarioDAO;

/**
 *
 * @author Gustavo Riposati
 */
public class EscritaBanco {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        Usuario user = new Usuario();
        String valor;

        System.out.print("\nDigite o nome do Usuário: ");
        valor = s.nextLine();
        user.setNome(valor);
        System.out.print("Digite a senha do usuário: ");
        valor = s.nextLine();
        user.setSenha(valor);
        

        UsuarioDAO dao = new UsuarioDAO();
        dao.gravar(user);
    }
}


