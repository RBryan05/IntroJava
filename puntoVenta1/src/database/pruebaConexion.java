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
    public static void main(String[] args){
        Conexion con = Conexion.getInstance(); // Use static method
        con.conectar(); // Connect to the database
        if(con.conectar != null){ // Check if connection is successful
            System.out.println("Conectado");
        }else{
            System.out.println("Sin conexion");
        }
    }
}
