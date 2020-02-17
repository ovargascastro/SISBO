/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author Jeaustin
 */
public class AbaaTbRolxPermiso {
    private int Rol_x_Perm_id_PK;
    private AbaaTbPermisos Rol_id_FK;
    private AbaaTbRol Perm_id_FK;

    public AbaaTbRolxPermiso() {
    }

    public AbaaTbRolxPermiso(int Rol_x_Perm_id_PK, AbaaTbPermisos Rol_id_FK, AbaaTbRol Perm_id_FK) {
        this.Rol_x_Perm_id_PK = Rol_x_Perm_id_PK;
        this.Rol_id_FK = Rol_id_FK;
        this.Perm_id_FK = Perm_id_FK;
    }

    public int getRol_x_Perm_id_PK() {
        return Rol_x_Perm_id_PK;
    }

    public void setRol_x_Perm_id_PK(int Rol_x_Perm_id_PK) {
        this.Rol_x_Perm_id_PK = Rol_x_Perm_id_PK;
    }

    public AbaaTbPermisos getRol_id_FK() {
        return Rol_id_FK;
    }

    public void setRol_id_FK(AbaaTbPermisos Rol_id_FK) {
        this.Rol_id_FK = Rol_id_FK;
    }

    public AbaaTbRol getPerm_id_FK() {
        return Perm_id_FK;
    }

    public void setPerm_id_FK(AbaaTbRol Perm_id_FK) {
        this.Perm_id_FK = Perm_id_FK;
    }

    @Override
    public String toString() {
        return "AbaaTbRolxPermiso{" + "Rol_x_Perm_id_PK=" + Rol_x_Perm_id_PK + ", Rol_id_FK=" + Rol_id_FK + ", Perm_id_FK=" + Perm_id_FK + '}';
    }

    
    
}
