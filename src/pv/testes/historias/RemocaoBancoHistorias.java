/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pv.testes.historias;

import java.util.Scanner;
import pv.modelo.Historias;
import pv.modelo.dao.HistoriaDAO;

/**
 *
 * @author Gustavo
 */
public class RemocaoBancoHistorias {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int codigo;

        System.out.print("\nDigite o codigo: ");
        codigo = s.nextInt();

        HistoriaDAO dao = new HistoriaDAO();
        Historias historia = dao.buscar(codigo);

        if (historia != null) {

            System.out.print("\nCodigo do usuário : " + historia.getId() + "\n");
           

            System.out.print("Deseja realmente remover esse usuário (S-Sim, N-Não)? ");
            s.skip("\n");
            String resultado = s.nextLine();

            if (resultado.equalsIgnoreCase("S")) {
                dao.remover(historia);
            }
        } else {
            System.out.println("A busca não retornou resultados.");
        }

    }

}


