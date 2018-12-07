/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.t3b.asistencias.db;

/**
 *
 * @author danie
 */
public interface IQryUsuarios {
    
    public static String NOM_SP_INICIO_SESION           = " { call NOM_SP_INICIO_SESION(?,?,?,?,?,?,?) } ";
    public static String NOM_FN_OBTIENE_INFO_USUARIOWEB = " { call NOM_FN_OBTIENE_INFO_USUARIOWEB(?) } ";
    
    
}
