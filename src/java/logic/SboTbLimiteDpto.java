package logic;
// Generated 22-sep-2019 1:11:19 by Hibernate Tools 4.3.1



/**
 * SboTbLimiteDpto generated by hbm2java
 */
public class SboTbLimiteDpto  implements java.io.Serializable {


     private SboTbLimiteDptoId id;
     private AbaaTbDepartamento abaaTbDepartamento;
     private SboSicop sboSicop;
     private Integer limiteDptoLimite;

    public SboTbLimiteDpto() {
    }

	
    public SboTbLimiteDpto(SboTbLimiteDptoId id, AbaaTbDepartamento abaaTbDepartamento, SboSicop sbosicop) {
        this.id = id;
        this.abaaTbDepartamento = abaaTbDepartamento;
        this.sboSicop = sbosicop;
    }
    public SboTbLimiteDpto(SboTbLimiteDptoId id, AbaaTbDepartamento abaaTbDepartamento, SboSicop sbosicop, Integer limiteDptoLimite) {
       this.id = id;
       this.abaaTbDepartamento = abaaTbDepartamento;
       this.sboSicop = sbosicop;
       this.limiteDptoLimite = limiteDptoLimite;
    }
   
    public SboTbLimiteDptoId getId() {
        return this.id;
    }
    
    public void setId(SboTbLimiteDptoId id) {
        this.id = id;
    }
    public AbaaTbDepartamento getAbaaTbDepartamento() {
        return this.abaaTbDepartamento;
    }
    
    public void setAbaaTbDepartamento(AbaaTbDepartamento abaaTbDepartamento) {
        this.abaaTbDepartamento = abaaTbDepartamento;
    }
    public SboSicop getSboSicop() {
        return this.sboSicop;
    }
    
    public void setSboSicop(SboSicop sbosicop) {
        this.sboSicop = sbosicop;
    }
    public Integer getLimiteDptoLimite() {
        return this.limiteDptoLimite;
    }
    
    public void setLimiteDptoLimite(Integer limiteDptoLimite) {
        this.limiteDptoLimite = limiteDptoLimite;
    }




}


