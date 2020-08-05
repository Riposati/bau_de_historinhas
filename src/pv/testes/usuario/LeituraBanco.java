package pv.testes.usuario;

import java.util.Scanner;
import pv.modelo.Usuario;
import pv.modelo.dao.UsuarioDAO;

/**
 *
 * @author Gustavo Riposati
 */
public class LeituraBanco {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int codigo;

        System.out.print("\nDigite o codigo: ");
        codigo = s.nextInt();

        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.buscar(codigo);

        if (usuario != null) {
            System.out.print("\nCodigo  : " + usuario.getId() + "\n");
            System.out.print("Nome do usuário   : " + usuario.getNome() + "\n");
            System.out.print("Senha do usuário : " + usuario.getSenha() + "\n");
        }

    }
}

    

