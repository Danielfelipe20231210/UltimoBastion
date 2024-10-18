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
public abstract class Enemigo {
    private int fila;
    private int columna;
    private int dañoAldea;
    private int resistencia;  // Indica cuántos golpes puede recibir antes de ser derrotado
    private boolean derrotado;
    protected ImageIcon imagenEnemigo;
    protected Mapa mapa;
    private int velocidad; // Velocidad del enemigo (cuanto mayor, más rápido)
    private int contadorMovimiento;;

    
    public Enemigo(int fila, int columna, int resistencia, int dañoAldea , int velocidad) {
        this.fila = fila;
        this.columna = columna;
        this.dañoAldea = dañoAldea;
        this.resistencia = resistencia;
        this.derrotado = false;
        this.mapa = mapa;
        this.velocidad = velocidad; // La velocidad define qué tan rápido se moverá
        this.contadorMovimiento = 0;
        
        
        URL imageURL = getClass().getResource("/autonoma.simulador.imagenes/Guardian.png"); // Asegúrate de que esta ruta es correcta
        if (imageURL == null) {
            System.out.println("La imagen del guerrero no se encontró en la ruta especificada.");
        } else {
            imagenEnemigo = new ImageIcon(imageURL);
        }
    }
    
     public void mover(Mapa mapa, Aldea aldea) 
     {
         // Verificar si hay menos de 10 enemigos en el mapa antes de mover
    if (mapa.contarEnemigos() >= 10) {
        System.out.println("No se pueden generar más enemigos. Máximo alcanzado.");
        return; // Detiene el movimiento si ya hay 10 enemigos
    }
    
         contadorMovimiento++;
         if (contadorMovimiento >= velocidad) {
        // Verifica si la casilla al frente (columna 0) está vacía
        if (columna > 1 && !mapa.estaOcupada(fila, columna - 1)) {
    // Mover enemigo hacia la izquierda
    mapa.eliminarEnemigo(fila, columna);
    columna--; 
            mapa.colocarEnemigo(this, fila, columna);
            }
        } else if (columna == 1) {
            // Si la casilla está ocupada o el enemigo ha llegado a la aldea (columna 0)
            aldea.recibirDaño(dañoAldea); // Método para que la aldea reciba daño
            System.out.println("¡El enemigo ha atacado la aldea!");
        } else {
            System.out.println("El enemigo no puede moverse porque la casilla está ocupada o está en el límite del mapa.");
        }
    }
   
     public void setPosicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }
     
    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
    
    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    public int getDaño() {
        return dañoAldea;
    }

    public int getResistencia() {
        return resistencia;
    }
    
    public void setFila(int fila) {
        this.fila = fila;
    }

    public void atacarAldea() {
        // Lógica para atacar la aldea
        System.out.println("¡El enemigo ha atacado la aldea!");
    }
    
    public void recibirDaño(int daño) {
        resistencia -= daño;
        if (this.resistencia <= 0) {
            estaDerrotado();
            morir();
        }
        
    }

    public boolean estaDerrotado() {
        boolean derrotado = resistencia <= 0;
            if (derrotado) {
        System.out.println("¡El enemigo ha sido derrotado!");
        }
            return derrotado;
    }
    
    public void morir() {
    mapa.eliminarEnemigo(fila, columna); // Elimina al enemigo de su posición en el mapa
     // Elimina al enemigo de la lista de enemigos activos
    }

    public ImageIcon getImagen() {
    return imagenEnemigo;
}
    
    public boolean enColumnaAtacable() {
        return columna == 1 || columna == 2;
    }
    
    // Método que combina el movimiento y el ataque
    public void moverYAtacar(Mapa mapa, Aldea aldea) {
        mover(mapa, aldea);  // Mover al enemigo

        // Si está en columna atacable, ataca a la aldea
        if (enColumnaAtacable()) {
            atacarAldea(aldea);  // Atacar si está en la columna adecuada
        }
    }
    
    public abstract void atacarAldea(Aldea aldea);
    
    public boolean estaEnRangoAtaque(Guerrero guerrero) {
        return guerrero.getFila() == fila && columna <= 1; // Rango de ataque
    }

}