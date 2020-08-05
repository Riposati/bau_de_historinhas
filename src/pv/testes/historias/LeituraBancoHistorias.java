package pv.testes.historias;

import java.util.Scanner;
import pv.modelo.Historias;
import pv.modelo.dao.HistoriaDAO;

/**
 *
 * @author Gustavo Riposati
 */
public class LeituraBancoHistorias {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int codigo;

        System.out.print("\nDigite o codigo: ");
        codigo = s.nextInt();

        HistoriaDAO dao = new HistoriaDAO();
        Historias historia = dao.buscar(codigo);

        if (historia != null) {
            System.out.print("\nId da História  : " + historia.getId()+ "\n");
            System.out.print("Imagem da historinha : " + historia.getFoto() + "\n");
            System.out.print("Nome da história   : " + historia.getNomeHistoria()+ "\n");
            System.out.print("Historia : " + historia.getHistoria()+ "\n");
            System.out.println("Autor da história : " + historia.getNomeAutor());
            System.out.println("Indicações desta história : " + historia.getIndicada());
        }

    }
}

    

