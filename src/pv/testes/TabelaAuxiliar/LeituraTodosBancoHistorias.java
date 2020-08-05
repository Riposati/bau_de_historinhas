package pv.testes.TabelaAuxiliar;

import java.util.List;
import pv.modelo.BauDeHistorinhas;
import pv.modelo.dao.HistoriaDAO;

/**
 *
 * @author Gustavo Riposati 
 */
public class LeituraTodosBancoHistorias {
    
    public static void main(String[] args) {
        
        HistoriaDAO dao = new HistoriaDAO();
        List<BauDeHistorinhas> aux = dao.trazBau();
        
        for(int i=0;i<aux.size();i++) {
//            System.out.print("\nCodigo da história  : " + aux.get(i).getIdHistoria()+ "\n");
//            System.out.print("Código do usuário : " + aux.get(i).getIdUsuario()+ "\n");
          
        }
        
    }
}


