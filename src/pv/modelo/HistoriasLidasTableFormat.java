/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pv.modelo;

import ca.odell.glazedlists.gui.TableFormat;

/**
 *
 * @author Gustavo
 */
public class HistoriasLidasTableFormat implements TableFormat<Historias>{
   

    @Override
    public int getColumnCount() {
        return 3;
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
            default:
                throw new IllegalStateException();
        }
    }
}


