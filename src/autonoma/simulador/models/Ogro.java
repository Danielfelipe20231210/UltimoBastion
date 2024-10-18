/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.simulador.models;

import javax.swing.ImageIcon;

/**
 *
 * @author Carlos
 */

    // Constructor

public class Ogro extends Enemigo {
    
    private ImageIcon imagenEnemigo;
    private int resistencia = 7;  // Resistencia específica del Trol
    private int dañoAldea = 3; 
    
    public Ogro(int fila, int columna, int resistencia, int dañoAldea) {
        super(fila, columna, 7, 3, 5); // Resistencia de 10, daño de 3
        // Cargar imagen del ogro
        imagenEnemigo = new ImageIcon(getClass().getResource("/autonoma.simulador.imagenes/Ogro.png"));
    }
   
    @Override
    public ImageIcon getImagen() {
        return imagenEnemigo; // Retorna la imagen del Goblin
    }
    // Método que permite al Trol atacar a la aldea
    @Override
    public void atacarAldea(Aldea aldea) {
        System.out.println("El Ogro ataca la aldea infligiendo " + dañoAldea + " de daño.");
        aldea.recibirDaño(dañoAldea);
    }

    // El Trol tiene una habilidad especial: mayor resistencia al ataque cuerpo a cuerpo
    public void recibirAtaqueCuerpoACuerpo(int danio) {
        int daño =- resistencia;
        super.recibirDaño(daño);
        System.out.println("El Trol ha recibido " + daño + " de daño tras un ataque cuerpo a cuerpo.");
    }
}