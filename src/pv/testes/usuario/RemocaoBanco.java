/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pv.testes.usuario;

import java.util.Scanner;
import pv.modelo.Usuario;
import pv.modelo.dao.UsuarioDAO;

/**
 *
 * @author Gustavo
 */
public class RemocaoBanco {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int codigo;

        System.out.print("\nDigite o codigo: ");
        codigo = s.nextInt();

        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.buscar(codigo);

        if (usuario != null) {

            System.out.print("\nCodigo do usuário : " + usuario.getId() + "\n");
            System.out.print("Nome do usuário   : " + usuario.getNome() + "\n");
            System.out.print("Senha do usuário: " + usuario.getSenha() + "\n");

            System.out.print("Deseja realmente remover esse usuário (S-Sim, N-Não)? ");
            s.skip("\n");
            String resultado = s.nextLine();

            if (resultado.equalsIgnoreCase("S")) {
                dao.remover(usuario);
            }
        } else {
            System.out.println("A busca não retornou resultados.");
        }

    }

}


