/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.t3b.asistencias.controllers;

import com.t3b.asistencias.db.IQryUsuarios;
import com.t3b.asistencias.utils.Config;
import dnn.nominae.modulobdconexion.db.Consulta;
import dnn.nominae.modulobdconexion.db.utils.Conexion;
import dnn.nominae.modulobdconexion.dto.QryRespDTO;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author danie
 */


@ManagedBean
public class InicioSesionController implements Serializable{
    
    private String txtLogin = "";
    private String txtContrasena = "";
    private String txtMsg = "";
    
    public InicioSesionController(){
        
    }
    
    
    public void iniciarSesion(){
        Connection conn = null;
        Conexion conex = null;
        FacesContext context = FacesContext.getCurrentInstance();
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
                paramsIn.add(this.toMD5(txtContrasena));
                paramsIn.add("-");
                ArrayList<Integer> paramsOut = new ArrayList();
                paramsOut.add(java.sql.Types.INTEGER);
                paramsOut.add(java.sql.Types.VARCHAR);
                paramsOut.add(java.sql.Types.INTEGER);
                paramsOut.add(java.sql.Types.INTEGER);
                QryRespDTO resp = new Consulta().ejecutaSP(conn, IQryUsuarios.NOM_SP_INICIO_SESION, paramsIn, paramsOut);
                System.out.println("resp: " + resp.getRes() + "-" + resp.getMsg());
                if(resp.getRes() == 1){
                    int res = Integer.parseInt(resp.getParamOut().get(0).getValor().toString());
                    txtMsg = resp.getParamOut().get(1).getValor().toString();                    
                    context.addMessage(null, new FacesMessage(res==1?FacesMessage.SEVERITY_INFO:FacesMessage.SEVERITY_ERROR, "Modulo de Asistencias", txtMsg));
                    
                    if(res == 1){
                        Map<String, Object> sesionMap = context.getExternalContext().getSessionMap();                    
                        sesionMap.clear();
                        sesionMap.put("txtLogin", txtLogin);
                        sesionMap.put("txtContrasena", txtContrasena);
                        sesionMap.put("idEmpleado", resp.getParamOut().get(3).getValor().toString());
                        context.getExternalContext().redirect("menuPrincipal.xhtml");
                    }
                }else{
                    txtMsg = resp.getMsg();
                }
            }
        }catch(Exception ex){
            System.out.println("Excepcion en el inicio de sesion: " + ex.getMessage());
        }finally{
            try{
                conn.close();
            }catch(Exception ex){
                
            }
        }
    }
    
    public String toMD5(String aux){
        String salida = "";
        try{
            byte[] bym = aux.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(aux.getBytes());
            byte[] td = md.digest();
            BigInteger bi = new BigInteger(1, td);
            salida = bi.toString(16);
        }catch(Exception ex){
            System.out.println("Excepcion en la conversion a MD5");
            salida = "";
        }
        return salida;
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
    
    
    
}
