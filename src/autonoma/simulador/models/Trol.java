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

public class Trol extends Enemigo {
    private ImageIcon imagenEnemigo;
    private int resistencia = 10;  // Resistencia específica del Trol
    private int dañoAldea = 5; 
    
    public Trol(int fila, int columna, int resistencia, int dañoAldea) {
        super(fila, columna, 10, 5, 5); // Resistencia de 30, daño de 5
        // Cargar imagen del trol
        
        imagenEnemigo = new ImageIcon(getClass().getResource("/autonoma.simulador.imagenes/Trol.png"));
    }
    
    @Override
    public ImageIcon getImagen() {
        return imagenEnemigo; // Retorna la imagen del Goblin
    }
    // Método que permite al Trol atacar a la aldea
    @Override
    public void atacarAldea(Aldea aldea) {
        System.out.println("El Trol ataca la aldea infligiendo " + dañoAldea + " de daño.");
        aldea.recibirDaño(dañoAldea);
        
        reproducirSonidoAtaque();
        mostrarEfectoVisualAtaque();

    }

    // Métodos adicionales para reproducir sonido y mostrar efectos visuales
    private void reproducirSonidoAtaque() {
        // Código para reproducir sonido
    }

    private void mostrarEfectoVisualAtaque() {
        // Código para mostrar imagen de ataque
    }
    
    // El Trol tiene una habilidad especial: mayor resistencia al ataque cuerpo a cuerpo
    public void recibirAtaqueCuerpoACuerpo(int danio) {
        int daño =- resistencia;
        super.recibirDaño(daño);
        System.out.println("El Trol ha recibido " + daño + " de daño tras un ataque cuerpo a cuerpo.");
    }
}