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

public class Orco extends Enemigo {
    
    private ImageIcon imagenEnemigo;
    private int resistencia = 4;  // Resistencia específica del Trol
    private int dañoAldea = 2; 
    
    public Orco(int fila, int columna, int resistencia, int dañoAldea) {
        super(fila, columna, 4, 2, 5); // Resistencia de 5, daño de 2
        // Cargar imagen del orco
        imagenEnemigo = new ImageIcon(getClass().getResource("/autonoma.simulador.imagenes/Orco.png"));
    }
    
    @Override
    public ImageIcon getImagen() {
        return imagenEnemigo; // Retorna la imagen del Goblin
    }
    // Método que permite al Trol atacar a la aldea
    @Override
    public void atacarAldea(Aldea aldea) {
        System.out.println("El Orco ataca la aldea infligiendo " + dañoAldea + " de daño.");
        aldea.recibirDaño(dañoAldea);
    }

    // El Trol tiene una habilidad especial: mayor resistencia al ataque cuerpo a cuerpo
    public void recibirAtaqueCuerpoACuerpo(int danio) {
        int daño =- resistencia;
        super.recibirDaño(daño);
        System.out.println("El Trol ha recibido " + daño + " de daño tras un ataque cuerpo a cuerpo.");
    }
}