/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.simulador.models;

import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Carlos
 */
public class Guerrero {
    private String nombre;
    private int fila;
    private int columna;
    protected ImageIcon imagenGuerrero;

    public Guerrero(String nombre, int fila) {
        this.nombre = nombre;
        this.fila = fila;
        this.columna = 1;  // Guerrero siempre comienza en la primera columna
        // Cargar la imagen
        URL imageURL = getClass().getResource("/autonoma.simulador.imagenes/Guardian.png"); // Asegúrate de que esta ruta es correcta
        if (imageURL == null) {
            System.out.println("La imagen del guerrero no se encontró en la ruta especificada.");
        } else {
            imagenGuerrero = new ImageIcon(imageURL);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void atacarCuerpoACuerpo(Enemigo enemigo) {
        if (enemigo.getFila() == this.fila && enemigo.getColumna() == 1 && this.columna == 1) {
            int daño = 50;
            enemigo.recibirDaño(daño); // Guerrero ataca al enemigo si está en la segunda columna
            
            if (enemigo.estaDerrotado()) {
                System.out.println("El enemigo  ha sido derrotado.");
            } else {
                System.out.println("El enemigo tiene " + enemigo.getResistencia() + " puntos de vida restantes.");
            }
        } else {
            System.out.println("No hay enemigo en la posición para atacar.");
        }

        }
    
    
    public void moverArriba() {
        if (fila > 0) {
            fila--;
        }
    }

    public void moverAbajo() {
        if (fila < 4) {
            fila++;
        }
    }

    public ImageIcon getImagenGuerrero() {
        return imagenGuerrero;
    }
}