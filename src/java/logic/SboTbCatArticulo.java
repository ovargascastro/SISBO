package logic;
// Generated 22-sep-2019 1:11:19 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * SboTbCatArticulo generated by hbm2java
 */
public class SboTbCatArticulo  implements java.io.Serializable {


    private int catIdPk;
    private SboTbSubFamilia sboTbSubFamilia;
    private String catCodSicop;
    private String catDesc;
    private Integer catMaxi;
    private Integer catMini;
    private Set<SboTbArticulo> sboTbArticulos = new HashSet<SboTbArticulo>(0);
    private String artCat_Estado;



    public SboTbCatArticulo() {
    }

	
    public SboTbCatArticulo(int catIdPk) {
        this.catIdPk = catIdPk;
    }
    public SboTbCatArticulo(int catIdPk, SboTbSubFamilia sboTbSubFamilia, String catCodSicop, String catDesc, Integer catMaxi, Integer catMini, Set<SboTbArticulo> sboTbArticulos, Set<SboTbLimiteDpto> sboTbLimiteDptos) {
       this.catIdPk = catIdPk;
       this.sboTbSubFamilia = sboTbSubFamilia;
       this.catCodSicop = catCodSicop;
       this.catDesc = catDesc;
       this.catMaxi = catMaxi;
       this.catMini = catMini;
       this.sboTbArticulos = sboTbArticulos;
      // this.sboTbLimiteDptos = sboTbLimiteDptos;
    }
   
    public int getCatIdPk() {
        return this.catIdPk;
    }
    
    public void setCatIdPk(int catIdPk) {
        this.catIdPk = catIdPk;
    }
    public SboTbSubFamilia getSboTbSubFamilia() {
        return this.sboTbSubFamilia;
    }
    
    public void setSboTbSubFamilia(SboTbSubFamilia sboTbSubFamilia) {
        this.sboTbSubFamilia = sboTbSubFamilia;
    }
    public String getCatCodSicop() {
        return this.catCodSicop;
    }
    
    public void setCatCodSicop(String catCodSicop) {
        this.catCodSicop = catCodSicop;
    }
    public String getCatDesc() {
        return this.catDesc;
    }
    
    public void setCatDesc(String catDesc) {
        this.catDesc = catDesc;
    }
    public Integer getCatMaxi() {
        return this.catMaxi;
    }
    
    public void setCatMaxi(Integer catMaxi) {
        this.catMaxi = catMaxi;
    }
    public Integer getCatMini() {
        return this.catMini;
    }
    
    public void setCatMini(Integer catMini) {
        this.catMini = catMini;
    }
    public Set<SboTbArticulo> getSboTbArticulos() {
        return this.sboTbArticulos;
    }
    
    public void setSboTbArticulos(Set<SboTbArticulo> sboTbArticulos) {
        this.sboTbArticulos = sboTbArticulos;
    }

    public void setArtCat_Estado(String artCat_Estado) {
        this.artCat_Estado = artCat_Estado;
    }



    public String getArtCat_Estado() {
    return artCat_Estado;
    }
        

//    public Set<SboTbLimiteDpto> getSboTbLimiteDptos() {
//        return this.sboTbLimiteDptos;
//    }
//    
//    public void setSboTbLimiteDptos(Set<SboTbLimiteDpto> sboTbLimiteDptos) {
//        this.sboTbLimiteDptos = sboTbLimiteDptos;
//
//    }




}


