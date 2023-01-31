package Prueba;

import Class.ConexionBD;

public class Prueba {
    public static void main(String[] args){
        ConexionBD db = new ConexionBD();
        db.getConnection();
    }
}
