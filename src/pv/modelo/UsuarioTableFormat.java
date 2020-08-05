package pv.modelo;

import ca.odell.glazedlists.gui.TableFormat;

/**
 *
 * @author Gustavo
 */
public class UsuarioTableFormat implements TableFormat<Usuario> {

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Nome do Usuario";
            
            default:
                throw new IllegalStateException();
        }
    }

    public Object getColumnValue(Usuario baseObject, int column) {
        switch (column) {
            case 0:
                return baseObject.getId();
            case 1:
                return baseObject.getNome();
            
            default:
                throw new IllegalStateException();
        }
    }
}
