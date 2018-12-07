/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.t3b.asistencias.dto;

import java.util.Objects;

/**
 *
 * @author danie
 */
public class UsuarioDTO {
    
    public int idusuario = 0;
    public String login = "";
    public int idperfil = 0;
    public String perfil = "";
    public int idestatus = 0;
    public String estatus = "";
    public String fechaultimoacc = "";
    public String ipultimoacc = "";
    public int idempleado = 0;
    public int idtienda = 0;
    public int iddistrito = 0;
    public int idzona = 0;
    public int idregion = 0;
    public String empleado = "";

    public UsuarioDTO() {
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(int idperfil) {
        this.idperfil = idperfil;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public int getIdestatus() {
        return idestatus;
    }

    public void setIdestatus(int idestatus) {
        this.idestatus = idestatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getFechaultimoacc() {
        return fechaultimoacc;
    }

    public void setFechaultimoacc(String fechaultimoacc) {
        this.fechaultimoacc = fechaultimoacc;
    }

    public String getIpultimoacc() {
        return ipultimoacc;
    }

    public void setIpultimoacc(String ipultimoacc) {
        this.ipultimoacc = ipultimoacc;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public int getIdtienda() {
        return idtienda;
    }

    public void setIdtienda(int idtienda) {
        this.idtienda = idtienda;
    }

    public int getIddistrito() {
        return iddistrito;
    }

    public void setIddistrito(int iddistrito) {
        this.iddistrito = iddistrito;
    }

    public int getIdzona() {
        return idzona;
    }

    public void setIdzona(int idzona) {
        this.idzona = idzona;
    }

    public int getIdregion() {
        return idregion;
    }

    public void setIdregion(int idregion) {
        this.idregion = idregion;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioDTO other = (UsuarioDTO) obj;
        if (this.idusuario != other.idusuario) {
            return false;
        }
        if (this.idperfil != other.idperfil) {
            return false;
        }
        if (this.idestatus != other.idestatus) {
            return false;
        }
        if (this.idempleado != other.idempleado) {
            return false;
        }
        if (this.idtienda != other.idtienda) {
            return false;
        }
        if (this.iddistrito != other.iddistrito) {
            return false;
        }
        if (this.idzona != other.idzona) {
            return false;
        }
        if (this.idregion != other.idregion) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.perfil, other.perfil)) {
            return false;
        }
        if (!Objects.equals(this.estatus, other.estatus)) {
            return false;
        }
        if (!Objects.equals(this.fechaultimoacc, other.fechaultimoacc)) {
            return false;
        }
        if (!Objects.equals(this.ipultimoacc, other.ipultimoacc)) {
            return false;
        }
        if (!Objects.equals(this.empleado, other.empleado)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" + "idusuario=" + idusuario + ", login=" + login + ", idperfil=" + idperfil + ", perfil=" + perfil + ", idestatus=" + idestatus + ", estatus=" + estatus + ", fechaultimoacc=" + fechaultimoacc + ", ipultimoacc=" + ipultimoacc + ", idempleado=" + idempleado + ", idtienda=" + idtienda + ", iddistrito=" + iddistrito + ", idzona=" + idzona + ", idregion=" + idregion + ", empleado=" + empleado + '}';
    }
    
    
    
}
