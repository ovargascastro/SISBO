package logic;
// Generated 22-sep-2019 1:11:19 by Hibernate Tools 4.3.1



/**
 * SboTbExistencia generated by hbm2java
 */
public class SboTbExistencia  implements java.io.Serializable {

     private SboTbExistenciaId id;
     private int idE;
     private AbaaTbDepartamento abaaTbDepartamento;
     private SboTbBodega sboTbBodega;
     private SboSicop sboTbSicop;
     private Double exisCant;

    public SboTbExistencia() {
    }

<<<<<<< HEAD
    public SboTbExistencia(SboTbExistenciaId id, AbaaTbDepartamento abaaTbDepartamento, SboTbBodega sboTbBodega, SboSicop sboTbSicop, Double exisCant) {
        this.id = id;
        this.abaaTbDepartamento = abaaTbDepartamento;
        this.sboTbBodega = sboTbBodega;
        this.sboTbSicop = sboTbSicop;
        this.exisCant = exisCant;
=======
    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
>>>>>>> f3176b462ff20101acc0708c97964fed1b7d7c42
    }

	
    public SboTbExistencia(SboTbExistenciaId id, AbaaTbDepartamento abaaTbDepartamento, SboTbBodega sboTbBodega) {
        this.id = id;
        this.abaaTbDepartamento = abaaTbDepartamento;
        this.sboTbBodega = sboTbBodega;
    }
    public SboTbExistencia(SboTbExistenciaId id, AbaaTbDepartamento abaaTbDepartamento, SboTbBodega sboTbBodega, Double exisCant) {
       this.id = id;
       this.abaaTbDepartamento = abaaTbDepartamento;
       this.sboTbBodega = sboTbBodega;
       this.exisCant = exisCant;
    }
   
    public SboTbExistenciaId getId() {
        return this.id;
    }
    
    public void setId(SboTbExistenciaId id) {
        this.id = id;
    }

    public AbaaTbDepartamento getAbaaTbDepartamento() {
        return abaaTbDepartamento;
    }

    public void setAbaaTbDepartamento(AbaaTbDepartamento abaaTbDepartamento) {
        this.abaaTbDepartamento = abaaTbDepartamento;
    }

    public SboTbBodega getSboTbBodega() {
        return this.sboTbBodega;
    }
    
    public void setSboTbBodega(SboTbBodega sboTbBodega) {
        this.sboTbBodega = sboTbBodega;
    }

    public SboSicop getSboTbSicop() {
        return sboTbSicop;
    }

    public void setSboTbSicop(SboSicop sboTbSicop) {
        this.sboTbSicop = sboTbSicop;
    }
    
    public Double getExisCant() {
        return this.exisCant;
    }
    
    public void setExisCant(Double exisCant) {
        this.exisCant = exisCant;
    }


}


