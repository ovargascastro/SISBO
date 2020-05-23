package logic;

public class SboTbLimiteDpto implements java.io.Serializable {

    private SboTbLimiteDptoId id;
    private AbaaTbDepartamento abaaTbDepartamento;
    private SboSicop sboSicop;
    private Integer limite;

    public SboTbLimiteDpto() {
    }

    public SboTbLimiteDpto(AbaaTbDepartamento abaaTbDepartamento, SboSicop sboSicop, Integer limite) {
        this.abaaTbDepartamento = abaaTbDepartamento;
        this.sboSicop = sboSicop;
        this.limite = limite;
    }

    public AbaaTbDepartamento getAbaaTbDepartamento() {
        return abaaTbDepartamento;
    }

    public void setAbaaTbDepartamento(AbaaTbDepartamento abaaTbDepartamento) {
        this.abaaTbDepartamento = abaaTbDepartamento;
    }

    public SboSicop getSboSicop() {
        return sboSicop;
    }

    public void setSboSicop(SboSicop sboSicop) {
        this.sboSicop = sboSicop;
    }

    public Integer getLimite() {
        return limite;
    }

    public void setLimite(Integer limite) {
        this.limite = limite;
    }
}
