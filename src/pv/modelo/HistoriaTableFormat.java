package pv.modelo;

import ca.odell.glazedlists.gui.TableFormat;

/**
 *
 * @author Gustavo
 */
public class HistoriaTableFormat implements TableFormat<Historias> {

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Nome da história";
            case 2:
                return "Nome do autor";
            case 3:
                return "Indicações";
            default:
                throw new IllegalStateException();
        }
    }

    public Object getColumnValue(Historias baseObject, int column) {
        switch (column) {
            case 0:
                return baseObject.getId();
            case 1:
                return baseObject.getNomeHistoria();
            case 2:
                return baseObject.getNomeAutor();
            case 3:
                return baseObject.getIndicada();
            
            default:
                throw new IllegalStateException();
        }
    }
}
