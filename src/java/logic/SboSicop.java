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
public class SboSicop {
    
    private int sicopId;
    private String sicopCodiInden;
    private String sicopCodiClas;
    private String sicopDesc;

    public SboSicop() {
    }

    public SboSicop(int sicopId, String sicopDesc) {
        this.sicopId = sicopId;
        this.sicopDesc = sicopDesc;
    }

    public SboSicop(int sicopId, String sicopCodiInden, String sicopCodiClas, String sicopDesc) {
        this.sicopId = sicopId;
        this.sicopCodiInden = sicopCodiInden;
        this.sicopCodiClas = sicopCodiClas;
        this.sicopDesc = sicopDesc;
    }

    public int getSicopId() {
        return sicopId;
    }

    public void setSicopId(int sicopId) {
        this.sicopId = sicopId;
    }

    public String getSicopCodiInden() {
        return sicopCodiInden;
    }

    public void setSicopCodiInden(String sicopCodiInden) {
        this.sicopCodiInden = sicopCodiInden;
    }

    public String getSicopCodiClas() {
        return sicopCodiClas;
    }

    public void setSicopCodiClas(String sicopCodiClas) {
        this.sicopCodiClas = sicopCodiClas;
    }

    public String getSicopDesc() {
        return sicopDesc;
    }

    public void setSicopDesc(String sicopDesc) {
        this.sicopDesc = sicopDesc;
    }
    
    
    
    
    
}
