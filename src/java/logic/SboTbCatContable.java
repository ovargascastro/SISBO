package logic;
// Generated 22-sep-2019 1:11:19 by Hibernate Tools 4.3.1



/**
 * SboTbCatContable generated by hbm2java
 */
public class SboTbCatContable  implements java.io.Serializable {


     private int cntIdPk;
     private String cntDesc;
     private int cntNivel;
     private String cntEst;
     private String cntCodi;
     

    public SboTbCatContable() {
    }

    public SboTbCatContable(int cntIdPk, String cntDesc, int cntNivel, String cntEst, String cntCodi) {
        this.cntIdPk = cntIdPk;
        this.cntDesc = cntDesc;
        this.cntNivel = cntNivel;
        this.cntEst = cntEst;
        this.cntCodi = cntCodi;
    }

    public int getCntNivel() {
        return cntNivel;
    }

    public void setCntNivel(int cntNivel) {
        this.cntNivel = cntNivel;
    }

    public String getCntEst() {
        return cntEst;
    }

    public void setCntEst(String cntEst) {
        this.cntEst = cntEst;
    }

    public String getCntCodi() {
        return cntCodi;
    }

    public void setCntCodi(String cntCodi) {
        this.cntCodi = cntCodi;
    }

	
    public SboTbCatContable(int cntIdPk) {
        this.cntIdPk = cntIdPk;
    }
    public SboTbCatContable(int cntIdPk, String cntDesc) {
       this.cntIdPk = cntIdPk;
       this.cntDesc = cntDesc;
    }
   
    public int getCntIdPk() {
        return this.cntIdPk;
    }
    
    public void setCntIdPk(int cntIdPk) {
        this.cntIdPk = cntIdPk;
    }
    public String getCntDesc() {
        return this.cntDesc;
    }
    
    public void setCntDesc(String cntDesc) {
        this.cntDesc = cntDesc;
    }




}


