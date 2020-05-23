package logic;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SboTbArticulo implements java.io.Serializable {

    private int artIdPk;
    private AbaaProyectos abaaProyectos;
    private AbaaTbDepartamento abaaTbDepartamento;
    private SboTbCatArticulo sboTbCatArticulo;
    private SboTbOrdenCompra sboTbOrdenCompra;
    private Double artPrecio;
    private Integer artCant;
    private Integer artCantRest;
    private Date artFingr;
    private Date artFvenc;
    private String artDesc;
    private String artMode;
    private String artNumeSeri;
    private String artMarc;
    private String artNumeFact;
    private String artCodiPresup;
    private String artCodiCont;
    private String artCodContGast;
    private String artCodContExis;
    private String artCodContSal;
    private Boolean artEsAc;
    private String artUnidadMedida;
    private SboSicop sboSicop;
    //private Set<AbaaTbOcproyecto> abaaTbOcproyectos = new HashSet<AbaaTbOcproyecto>(0);
    private Set<SboTbSolixArti> sboTbSolixArtis = new HashSet<SboTbSolixArti>(0);
    private Set<SboTbExistencia> sboTbExistencias = new HashSet<SboTbExistencia>(0);
    private Integer cantSolArt;
    private String artiTipoIngr;
    private Double artPrecioActual;
    public SboTbArticulo() {
    }

    public SboTbArticulo(int artIdPk) {
        this.artIdPk = artIdPk;
    }

    public SboTbArticulo(int artIdPk, AbaaProyectos abaaProyectos, AbaaTbDepartamento abaaTbDepartamento, SboTbCatArticulo sboTbCatArticulo, SboTbOrdenCompra sboTbOrdenCompra, Double artPrecio, Integer artCant, Integer artCantRest, Date artFingr, Date artFvenc, String artDesc, String artMode, String artNumeSeri, String artMarc, String artNumeFact, String artCodiPresup, String artCodiCont, String artCodContGast, String artCodContExis, String artCodContSal, Boolean artEsAc, String artUnidadMedida, SboSicop sboSicop, Integer cantSolArt, String artiTipoIngr,Double artPrecioActual) {
        this.artIdPk = artIdPk;
        this.abaaProyectos = abaaProyectos;
        this.abaaTbDepartamento = abaaTbDepartamento;
        this.sboTbCatArticulo = sboTbCatArticulo;
        this.sboTbOrdenCompra = sboTbOrdenCompra;
        this.artPrecio = artPrecio;
        this.artCant = artCant;
        this.artCantRest = artCantRest;
        this.artFingr = artFingr;
        this.artFvenc = artFvenc;
        this.artDesc = artDesc;
        this.artMode = artMode;
        this.artNumeSeri = artNumeSeri;
        this.artMarc = artMarc;
        this.artNumeFact = artNumeFact;
        this.artCodiPresup = artCodiPresup;
        this.artCodiCont = artCodiCont;
        this.artCodContGast = artCodContGast;
        this.artCodContExis = artCodContExis;
        this.artCodContSal = artCodContSal;
        this.artEsAc = artEsAc;
        this.artUnidadMedida = artUnidadMedida;
        this.sboSicop = sboSicop;
        this.cantSolArt = cantSolArt;
        this.artiTipoIngr=artiTipoIngr;
        this.artPrecioActual=artPrecioActual;
    }

    public int getArtIdPk() {
        return this.artIdPk;
    }

    public void setArtIdPk(int artIdPk) {
        this.artIdPk = artIdPk;
    }

    public AbaaProyectos getAbaaProyectos() {
        return this.abaaProyectos;
    }

    public void setAbaaProyectos(AbaaProyectos abaaProyectos) {
        this.abaaProyectos = abaaProyectos;
    }

    public AbaaTbDepartamento getAbaaTbDepartamento() {
        return this.abaaTbDepartamento;
    }

    public void setAbaaTbDepartamento(AbaaTbDepartamento abaaTbDepartamento) {
        this.abaaTbDepartamento = abaaTbDepartamento;
    }

    public SboTbCatArticulo getSboTbCatArticulo() {
        return this.sboTbCatArticulo;
    }

    public void setSboTbCatArticulo(SboTbCatArticulo sboTbCatArticulo) {
        this.sboTbCatArticulo = sboTbCatArticulo;
    }

    public SboTbOrdenCompra getSboTbOrdenCompra() {
        return this.sboTbOrdenCompra;
    }

    public void setSboTbOrdenCompra(SboTbOrdenCompra sboTbOrdenCompra) {
        this.sboTbOrdenCompra = sboTbOrdenCompra;
    }

    public Double getArtPrecio() {
        return this.artPrecio;
    }

    public void setArtPrecio(Double artPrecio) {
        this.artPrecio = artPrecio;
    }

    public Integer getArtCant() {
        return this.artCant;
    }

    public void setArtCant(Integer artCant) {
        this.artCant = artCant;
    }

    public Integer getArtCantRest() {
        return this.artCantRest;
    }

    public void setArtCantRest(Integer artCantRest) {
        this.artCantRest = artCantRest;
    }

    public Date getArtFingr() {
        return artFingr;
    }

    public void setArtFingr(Date artFingr) {
        this.artFingr = artFingr;
    }

    public Date getArtFvenc() {
        return artFvenc;
    }

    public void setArtFvenc(Date artFvenc) {
        this.artFvenc = artFvenc;
    }

    

    public String getArtDesc() {
        return this.artDesc;
    }

    public void setArtDesc(String artDesc) {
        this.artDesc = artDesc;
    }

    public String getArtMode() {
        return this.artMode;
    }

    public void setArtMode(String artMode) {
        this.artMode = artMode;
    }

    public String getArtNumeSeri() {
        return this.artNumeSeri;
    }

    public void setArtNumeSeri(String artNumeSeri) {
        this.artNumeSeri = artNumeSeri;
    }

    public String getArtMarc() {
        return this.artMarc;
    }

    public void setArtMarc(String artMarc) {
        this.artMarc = artMarc;
    }

    public String getArtNumeFact() {
        return this.artNumeFact;
    }

    public void setArtNumeFact(String artNumeFact) {
        this.artNumeFact = artNumeFact;
    }

    public String getArtCodiPresup() {
        return this.artCodiPresup;
    }

    public void setArtCodiPresup(String artCodiPresup) {
        this.artCodiPresup = artCodiPresup;
    }

    public String getArtCodiCont() {
        return this.artCodiCont;
    }

    public void setArtCodiCont(String artCodiCont) {
        this.artCodiCont = artCodiCont;
    }

    public String getArtCodContGast() {
        return this.artCodContGast;
    }

    public void setArtCodContGast(String artCodContGast) {
        this.artCodContGast = artCodContGast;
    }

    public String getArtCodContExis() {
        return this.artCodContExis;
    }

    public void setArtCodContExis(String artCodContExis) {
        this.artCodContExis = artCodContExis;
    }

    public String getArtCodContSal() {
        return this.artCodContSal;
    }

    public void setArtCodContSal(String artCodContSal) {
        this.artCodContSal = artCodContSal;
    }

    public Boolean getArtEsAc() {
        return this.artEsAc;
    }

    public void setArtEsAc(Boolean artEsAc) {
        this.artEsAc = artEsAc;
    }

  //  public Set<AbaaTbOcproyecto> getAbaaTbOcproyectos() {
    //    return this.abaaTbOcproyectos;
   // }

  //  public void setAbaaTbOcproyectos(Set<AbaaTbOcproyecto> abaaTbOcproyectos) {
  //      this.abaaTbOcproyectos = abaaTbOcproyectos;
   // }

    public Set<SboTbSolixArti> getSboTbSolixArtis() {
        return this.sboTbSolixArtis;
    }

    public void setSboTbSolixArtis(Set<SboTbSolixArti> sboTbSolixArtis) {
        this.sboTbSolixArtis = sboTbSolixArtis;
    }

    public Set<SboTbExistencia> getSboTbExistencias() {
        return this.sboTbExistencias;
    }

    public void setSboTbExistencias(Set<SboTbExistencia> sboTbExistencias) {
        this.sboTbExistencias = sboTbExistencias;
    }

    public String getArtUnidadMedida() {
        return artUnidadMedida;
    }

    public void setArtUnidadMedida(String artUnidadMedida) {
        this.artUnidadMedida = artUnidadMedida;
    }

    public Integer getCantSolArt() {
        return cantSolArt;
    }

    public void setCantSolArt(Integer cantSolArt) {
        this.cantSolArt = cantSolArt;
    }

    public SboSicop getSboSicop() {
        return sboSicop;
    }

    public void setSboSicop(SboSicop sboSicop) {
        this.sboSicop = sboSicop;
    }    

    public String getArtiTipoIngr() {
        return artiTipoIngr;
    }

    public void setArtiTipoIngr(String artiTipoIngr) {
        this.artiTipoIngr = artiTipoIngr;
    }

    public Double getArtPrecioActual() {
        return artPrecioActual;
    }

    public void setArtPrecioActual(Double artPrecioActual) {
        this.artPrecioActual = artPrecioActual;
    }
    
}
