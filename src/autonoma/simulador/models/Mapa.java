/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.simulador.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Carlos
 */

public class Mapa {

    private Enemigo[][] mapa; 
    private List<Enemigo> enemigos;

    public Mapa() {

        enemigos = new ArrayList<>();
        mapa = new Enemigo[5][10]; // 5 filas, 10 columnas
    }

    // Método para colocar un enemigo en una posición del mapa
    public boolean colocarEnemigo(Enemigo enemigo, int fila, int columna) {
        // Asegúrate de que las coordenadas estén dentro de los límites
        if (fila < 0 || fila >= 5 || columna < 0 || columna >= 10) {
            return false; // Fuera de límites
        }
        
        // Verifica si ya hay un enemigo en la posición deseada
        if (mapa[fila][columna] != null) {
            return false; // La posición ya está ocupada
        }
        
        // Coloca al enemigo en el mapa
        mapa[fila][columna] = enemigo; // Asegúrate de que matriz esté bien definida
        enemigo.setPosicion(fila, columna); // Método para actualizar la posición del enemigo
        return true; // Colocación exitosa
    }

    
     public boolean puedeAgregarEnemigo() {
    // Suponiendo que el mapa tiene 5 filas y 10 columnas
    for (int fila = 0; fila < 5; fila++) {
        // Verifica si la columna 9 (donde se agregan los enemigos) está ocupada
        if (mapa[fila][9] != null) { // Suponiendo que matriz es tu representación del mapa
            return false; // Si ya hay un enemigo en la columna 9, no se puede agregar más
        }
    }
    return true; // Si no hay enemigos en la columna 9, se puede agregar
}


    public Enemigo getEnemigo(int fila, int columna) {
        if (fila >= 0 && fila < 5 && columna >= 0 && columna < 10) {
            return mapa[fila][columna]; // Retorna el enemigo en la posición dada
        } else {
            System.out.println("Posición fuera del rango del mapa");
            return null; // Retorna null si la posición está fuera del rango
        }
    }

    public void eliminarEnemigo(int fila, int columna) {
        
            mapa[fila][columna] = null; // Elimina el enemigo de la posición dada
       
    }
    
    // Método para verificar si una casilla está ocupada
    public boolean estaOcupada(int fila, int columna) {
        return mapa[fila][columna] != null;
    }

    // Obtener el enemigo en una casilla
    public Enemigo obtenerEnemigo(int fila, int columna) {
        return mapa[fila][columna];
    }
    
    public int contarEnemigos() {
    int contador = 0;
    
    // Iterar sobre todas las filas y columnas del mapa
    for (int fila = 0; fila < fila; fila++) {
        for (int columna = 0; columna < columna; columna++) {
            if (mapa[fila][columna] instanceof Enemigo) {
                contador++; // Contar si hay un enemigo en esta posición
            }
        }
    }
    
    return contador;
}
    

    public void imprimirMapa() {
        for (int i = 0; i < mapa.length; i++) {
            System.out.println(Arrays.toString(mapa[i]));
        }
    }

}