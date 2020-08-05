package pv.testes.historias;

import java.util.Scanner;
import pv.modelo.Historias;
import pv.modelo.dao.HistoriaDAO;

public class UpdateBancoHistorias {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int codigo;

        System.out.print("\nDigite o codigo: ");
        codigo = s.nextInt();

        HistoriaDAO dao = new HistoriaDAO();
        Historias historia = dao.buscar(codigo);

        if (historia != null) {
            System.out.print("Imagem da historinha : " + historia.getFoto() + "\n");
            System.out.print("Nome da história   : " + historia.getNomeHistoria() + "\n");
            System.out.print("Historia : " + historia.getHistoria() + "\n");
            System.out.println("Autor da história : " + historia.getNomeAutor());
            System.out.println("Indicações desta história : " + historia.getIndicada());

            String resposta;
            System.out.print("Deseja alterar esse contato?");
            s.skip("\n");
            resposta = s.nextLine();
            if (resposta.equalsIgnoreCase("sim")
                    || resposta.equalsIgnoreCase("s")) {
                System.out.println("1 - Foto");
                System.out.println("2 - Nome");
                System.out.println("3 - Historia");
                System.out.println("4 - Autor");
                System.out.println("5 - Indicações");
                System.out.print("Opção: ");
                resposta = s.nextLine();

                switch (resposta) {
                    case "1":
                        System.out.print("Foto: ");
                        historia.setFoto(s.nextLine());
                        break;
                    case "2":
                        System.out.print("Nome: ");
                        historia.setNomeHistoria(s.nextLine());
                        break;
                    case "3":
                        System.out.print("Historia: ");
                        historia.setHistoria(s.nextLine());
                        break;

                    case "4":
                        System.out.print("Autor: ");
                        historia.setNomeAutor(s.nextLine());
                        break;

                    case "5":
                        System.out.print("Indicações: ");
                        historia.setIndicada(s.nextInt());
                        break;

                }

                dao.atualizar(historia);

            } else {
                System.out.println("A busca não retornou resultados.");
            }
        }
    }
}
