/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.t3b.asistencias.controllers;

import com.t3b.asistencias.db.IQryUsuarios;
import com.t3b.asistencias.dto.UsuarioDTO;
import com.t3b.asistencias.utils.Config;
import dnn.nominae.modulobdconexion.db.Consulta;
import dnn.nominae.modulobdconexion.db.utils.Conexion;
import dnn.nominae.modulobdconexion.dto.CampoDTO;
import dnn.nominae.modulobdconexion.dto.ColumnaDTO;
import dnn.nominae.modulobdconexion.dto.QryRespDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author danie
 */
@ManagedBean
public class MenuPrincipalController implements Serializable{
    
    private String txtLogin = "";
    private String txtContrasena = "";
    private String txtMsg = "";
    private String idEmpleado = "";
    private UsuarioDTO usuario = null;
    
    private Map<String, Object> sesionMap = null;
    private FacesContext context;
    
    
    public MenuPrincipalController(){
        context = FacesContext.getCurrentInstance();
        sesionMap = context.getExternalContext().getSessionMap();        
        txtLogin = sesionMap.get("txtLogin").toString();
        idEmpleado = sesionMap.get("idEmpleado").toString();
        txtContrasena = sesionMap.get("txtContrasena").toString();
        
        cargaInfoEmpleado();
    }
    
    
    /**
     * Carga las credenciales del usuario
     */
    private void cargaInfoEmpleado(){
        Connection conn = null;
        Conexion conex = null;
        try{
            if(!Config.estaCargada){
                Config.cargaConfig();
            }
            conex = new Conexion();
            conex.creaConexion(Config.host, Config.user, Config.pass, Config.port, Config.name, Config.driv, Config.surl);
            conn = conex.getConexion();
            if(conn != null){
                ArrayList<Object> paramsIn = new ArrayList();
                paramsIn.add(txtLogin);
                QryRespDTO resp = new Consulta().ejecutaSelectSP(conn, IQryUsuarios.NOM_FN_OBTIENE_INFO_USUARIOWEB, paramsIn);
                System.out.println("resp: " + resp.getRes() + "-" + resp.getMsg());
                if(resp.getRes() == 1){
                    ArrayList<ColumnaDTO> listaColumnas = resp.getColumnas();
                    for(HashMap<String, CampoDTO> fila : resp.getDatosTabla()){
                        usuario = new UsuarioDTO();
                        
                        for(ColumnaDTO col: listaColumnas){
                            switch(col.getIdTipo()){
                                case java.sql.Types.INTEGER:
                                    usuario.getClass().getField(col.getEtiqueta()).set(usuario
                                            , fila.get(col.getEtiqueta()).getValor()==null?"0":Integer.parseInt(fila.get(col.getEtiqueta()).getValor().toString().replaceAll(",", "").replaceAll("$", "").replaceAll(" ", "")));
                                    break;
                                    
                                case java.sql.Types.DOUBLE:
                                    usuario.getClass().getField(col.getEtiqueta()).set(usuario
                                            , fila.get(col.getEtiqueta()).getValor()==null?"0":Double.parseDouble(fila.get(col.getEtiqueta()).getValor().toString()));
                                    break;
                                    
                                case java.sql.Types.FLOAT:
                                    usuario.getClass().getField(col.getEtiqueta()).set(usuario
                                            , fila.get(col.getEtiqueta()).getValor()==null?"0":Float.parseFloat(fila.get(col.getEtiqueta()).getValor().toString()));
                                    break;
                                    
                                case java.sql.Types.DECIMAL:
                                    usuario.getClass().getField(col.getEtiqueta()).set(usuario
                                            , fila.get(col.getEtiqueta()).getValor()==null?"0":Float.parseFloat(fila.get(col.getEtiqueta()).getValor().toString()));
                                    break;
                                    
                                case java.sql.Types.VARCHAR:
                                    usuario.getClass().getField(col.getEtiqueta()).set(usuario
                                            , fila.get(col.getEtiqueta()).getValor()==null?"":fila.get(col.getEtiqueta()).getValor().toString());
                                    break;
                                    
                                case java.sql.Types.NUMERIC:
                                    usuario.getClass().getField(col.getEtiqueta()).set(usuario
                                            , fila.get(col.getEtiqueta()).getValor()==null?"0":Float.parseFloat(fila.get(col.getEtiqueta()).getValor().toString()));
                                    break;
                                    
                                default:
                                    usuario.getClass().getField(col.getEtiqueta()).set(usuario
                                            , fila.get(col.getEtiqueta()).getValor()==null?"":fila.get(col.getEtiqueta()).getValor().toString());
                                    break;
                            }   
                        }
                    }
                    sesionMap.put("UsuarioDTO", usuario);
                }else{
                    context.getExternalContext().getApplicationMap().clear();
                    context.getExternalContext().redirect("index.xhtml");
                }
            }
        }catch(Exception ex){
            System.out.println("Excepcion en la cargaInfoEmpleado: " + ex);
        }finally{
            try{
                conn.close();
            }catch(Exception ex){
                
            }
        }
    }

    public String getTxtLogin() {
        return txtLogin;
    }

    public void setTxtLogin(String txtLogin) {
        this.txtLogin = txtLogin;
    }

    public String getTxtContrasena() {
        return txtContrasena;
    }

    public void setTxtContrasena(String txtContrasena) {
        this.txtContrasena = txtContrasena;
    }

    public String getTxtMsg() {
        return txtMsg;
    }

    public void setTxtMsg(String txtMsg) {
        this.txtMsg = txtMsg;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public Map<String, Object> getSesionMap() {
        return sesionMap;
    }

    public void setSesionMap(Map<String, Object> sesionMap) {
        this.sesionMap = sesionMap;
    }

    public FacesContext getContext() {
        return context;
    }

    public void setContext(FacesContext context) {
        this.context = context;
    }
    
    
    
}
