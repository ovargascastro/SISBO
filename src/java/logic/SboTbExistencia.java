package logic;

import java.util.Date;

public class SboTbExistencia implements java.io.Serializable {

    private int id;
    private SboTbBodega sboTbBodega;
    private int SboTbEsta;
    private SboTbArticulo articulo;
    private Date existFingr;

    public SboTbExistencia() {
    }

    public SboTbExistencia(int id, SboTbBodega sboTbBodega, int SboTbEsta, SboTbArticulo articulo) {
        this.id = id;
        this.sboTbBodega = sboTbBodega;
        this.SboTbEsta = SboTbEsta;
        this.articulo = articulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SboTbBodega getSboTbBodega() {
        return sboTbBodega;
    }

    public void setSboTbBodega(SboTbBodega sboTbBodega) {
        this.sboTbBodega = sboTbBodega;
    }

    public int getSboTbEsta() {
        return SboTbEsta;
    }

    public void setSboTbEsta(int SboTbEsta) {
        this.SboTbEsta = SboTbEsta;
    }

    public SboTbArticulo getArticulo() {
        return articulo;
    }

    public void setArticulo(SboTbArticulo articulo) {
        this.articulo = articulo;
    }

    public Date getExistFingr() {
        return existFingr;
    }

    public void setExistFingr(Date existFingr) {
        this.existFingr = existFingr;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 05c8306a054258bed22b5579cd8a6e055ad1e814
