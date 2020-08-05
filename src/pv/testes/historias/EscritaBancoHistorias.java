
package pv.testes.historias;

import java.util.Scanner;
import pv.modelo.Historias;
import pv.modelo.dao.HistoriaDAO;

/**
 *
 * @author Gustavo Riposati
 */
public class EscritaBancoHistorias {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        Historias historias = new Historias();
        String valor;

        System.out.print("Digite o caminho da foto: ");
        valor = s.nextLine();
        historias.setFoto(valor);
        System.out.print("\nDigite o nome da historinha: ");
        valor = s.nextLine();
        historias.setNomeHistoria(valor);
        
        System.out.print("Digite a Historia: ");
        valor = s.nextLine();
        historias.setHistoria(valor);
        
        System.out.print("Digite o nome do autor da Historia: ");
        valor = s.nextLine();
        historias.setNomeAutor(valor);
        
        System.out.print("Digite um valor pro campo indicada: ");
        int valor2 = s.nextInt();
        historias.setIndicada(valor2);
        
       /* System.out.print("Digite se a Historia já foi lida antes: ");
        boolean v = s.nextBoolean();
        historias.setHistoriaLida(v);
       */

        HistoriaDAO dao = new HistoriaDAO();
        dao.gravar(historias);
    }
}


