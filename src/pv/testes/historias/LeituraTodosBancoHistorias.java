package pv.testes.historias;

import java.util.List;
import pv.modelo.Historias;
import pv.modelo.dao.HistoriaDAO;

/**
 *
 * @author Gustavo Riposati 
 */
public class LeituraTodosBancoHistorias {
    
    public static void main(String[] args) {
        
        HistoriaDAO dao = new HistoriaDAO();
        List<Historias> historias = dao.buscarTodos();
        
        for(int i=0;i<historias.size();i++) {
            System.out.print("\nCodigo do usuário  : " + historias.get(i).getId() + "\n");
            System.out.print("Nome do usuário    : " + historias.get(i).getNomeHistoria()+ "\n");
          
        }
        
    }
}


