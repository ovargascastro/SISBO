/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author oscar
 */
public class AbaaTbPersona {

    private int PersIdPK;
    private String PersCedu;
    private String PersNomb;
    private String PersApe1;
    private String PersApe2;
    private int PersSfun;
    private AbaaTbDepartamento departamento;

    public AbaaTbPersona() {
    }

    public AbaaTbPersona(int PersIdPK, String PersCedu, String PersNomb, String PersApe1, String PersApe2, int PersSfun, AbaaTbDepartamento departamento) {
        this.PersIdPK = PersIdPK;
        this.PersCedu = PersCedu;
        this.PersNomb = PersNomb;
        this.PersApe1 = PersApe1;
        this.PersApe2 = PersApe2;
        this.PersSfun = PersSfun;
        this.departamento = departamento;
    }

    public int getPersIdPK() {
        return PersIdPK;
    }

    public void setPersIdPK(int PersIdPK) {
        this.PersIdPK = PersIdPK;
    }

    public String getPersCedu() {
        return PersCedu;
    }

    public void setPersCedu(String PersCedu) {
        this.PersCedu = PersCedu;
    }

    public String getPersNomb() {
        return PersNomb;
    }

    public void setPersNomb(String PersNomb) {
        this.PersNomb = PersNomb;
    }

    public String getPersApe1() {
        return PersApe1;
    }

    public void setPersApe1(String PersApe1) {
        this.PersApe1 = PersApe1;
    }

    public String getPersApe2() {
        return PersApe2;
    }

    public void setPersApe2(String PersApe2) {
        this.PersApe2 = PersApe2;
    }

    public int getPersSfun() {
        return PersSfun;
    }

    public void setPersSfun(int PersSfun) {
        this.PersSfun = PersSfun;
    }

    public AbaaTbDepartamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(AbaaTbDepartamento departamento) {
        this.departamento = departamento;
    }
    
    
    
    
}
