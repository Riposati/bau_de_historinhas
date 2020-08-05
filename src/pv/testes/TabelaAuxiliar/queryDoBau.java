/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pv.testes.TabelaAuxiliar;

import java.util.List;
import java.util.Scanner;
import pv.modelo.dao.HistoriaDAO;

/**
 *
 * @author Gustavo
 */
public class queryDoBau {

    public static void main(String[] args) {
        Scanner r = new Scanner(System.in);
        HistoriaDAO dao = new HistoriaDAO();
        String v;
        System.out.println("informe o nome do user");
        v = r.nextLine();
        
        List<String>aux = dao.trazBauDoUsuario(v);

        for (int i = 0; i < aux.size(); i++) {
            System.out.print("\nNome da historinha  : " + aux.get(i) + "\n");

        }
    }
}
