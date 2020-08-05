package pv.modelo;

/**
 *
 * @author Gustavo Riposati
 */
public class Historias {

 
    private int id;
    private String foto;
    private String nomeHistoria;
    private String Historia;
    private String nomeAutor;
    private int indicada;
    //private boolean historiaLida;

    /**
     * @return the id
     */
   
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * @return the nomeHistoria
     */
    public String getNomeHistoria() {
        return nomeHistoria;
    }

    /**
     * @param nomeHistoria the nomeHistoria to set
     */
    public void setNomeHistoria(String nomeHistoria) {
        this.nomeHistoria = nomeHistoria;
    }

    /**
     * @return the Historia
     */
    public String getHistoria() {
        return Historia;
    }

    /**
     * @param Historia the Historia to set
     */
    public void setHistoria(String Historia) {
        this.Historia = Historia;
    }

    /**
     * @return the nomeAutor
     */
    public String getNomeAutor() {
        return nomeAutor;
    }

    /**
     * @param nomeAutor the nomeAutor to set
     */
    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    /**
     * @return the indicada
     */
    public int getIndicada() {
        return indicada;
    }

    /**
     * @param indicada the indicada to set
     */
    public void setIndicada(int indicada) {
        this.indicada = indicada;
    }

    /*public boolean isHistoriaLida() {
        return historiaLida;
    }

    public void setHistoriaLida(boolean historiaLida) {
        this.historiaLida = historiaLida;
    }
    */
    
    @Override
    public String toString() {
        return nomeHistoria;
    }

    /**
     * @return the historiaIndicada
     */
    
}
