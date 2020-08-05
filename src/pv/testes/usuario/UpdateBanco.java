package pv.testes.usuario;

import java.util.Scanner;
import pv.modelo.Usuario;
import pv.modelo.dao.UsuarioDAO;

public class UpdateBanco {

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
            System.out.print("Senha do usuário : " + usuario.getSenha() + "\n");

            String resposta;
            System.out.print("Deseja alterar esse contato?");
            s.skip("\n");
            resposta = s.nextLine();
            if (resposta.equalsIgnoreCase("sim")
                    || resposta.equalsIgnoreCase("s")) {
                System.out.println("1 - Nome");
                System.out.println("2 - Senha");
                System.out.print("Opção: ");
                resposta = s.nextLine();

                switch (resposta) {
                    case "1":
                        System.out.print("Nome: ");
                        usuario.setNome(s.nextLine());
                        break;
                    case "2":
                        System.out.print("Senha: ");
                        usuario.setSenha(s.nextLine());
                        break;
                   
                }

                dao.atualizar(usuario);

            } else {
                System.out.println("A busca não retornou resultados.");
            }
        }
    }
}
