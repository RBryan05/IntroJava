/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author Alexis Rauda
 */
public class pruebaConexion {
    public static void main (String[] args){
        Conexion con = new Conexion();
        con.conectar();
        if(con.conectar != null){
            System.out.println(" Conectado ");
        }else{
            System.out.println(" SIn conexion ");
        }
    }  
}
