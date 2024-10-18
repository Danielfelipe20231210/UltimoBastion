/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.simulador.models;

import java.util.ArrayList;
import java.util.List;import java.util.Random;
;

public class Oleada {
    
    private int contadorMovimientos = 0;
    private List<Enemigo> enemigos;
    private Random random;
    private int numEnemigosMax; // Número máximo de enemigos a generar

    public Oleada() {
        enemigos = new ArrayList<>();
        random = new Random();
        numEnemigosMax = 100; // Número total de enemigos (35 Goblins, 25 Orcos, 15 Ogros, 10 Trolls)
    }

    public void moverEnemigos(Mapa mapa, Guerrero guerrero, Aldea aldea) {
    contadorMovimientos++; // Incrementar el contador en cada ciclo
    if (contadorMovimientos >= 5) { // Solo mover cada 5 ciclos
        for (Enemigo enemigo : enemigos) {
            enemigo.mover(mapa, aldea); // El enemigo se mueve hacia la aldea
            if (enemigo.getColumna() == 1) { // Si llega a la aldea
                enemigo.atacarAldea(aldea); // Ataca la aldea
            }
        }
        contadorMovimientos = 0; // Reiniciar el contador después de mover los enemigos
    }
    }
    

    public List<Enemigo> getEnemigos() {
        return enemigos;
    }

    public int getEnemigosRestantes() {
        int count = 0;
        for (Enemigo enemigo : enemigos) {
            if (!enemigo.estaDerrotado()) {
                count++;
            }
        }
        return count;
    }

    public boolean todosDerrotados() {
        for (Enemigo enemigo : enemigos) {
            if (!enemigo.estaDerrotado()) {
                return false;
            }
        }
        return true;
    }


    public void generarEnemigosPorIntervalo(int tiempo, Mapa mapa) {
    // Verificar si hay menos de 10 enemigos en el mapa antes de generar
    if (mapa.contarEnemigos() >= 10) {
        System.out.println("No se pueden generar más enemigos. Máximo alcanzado.");
        return; // Detiene la generación si ya hay 10 enemigos
    }
    
    // Verifica si quedan enemigos por generar y si es momento de generar uno nuevo
    if (numEnemigosMax > 0 && tiempo % 10 == 0) { // Generar cada 10 segundos
        Enemigo enemigo = crearEnemigoAleatorio(); // Crear enemigo aleatorio
        int fila = random.nextInt(5); // Fila aleatoria entre 0 y 4
        enemigos.add(enemigo); // Añadir enemigo a la lista
        mapa.colocarEnemigo(enemigo, fila, 9); // Colocar enemigo en la columna 9
        numEnemigosMax--; // Decrementar el número de enemigos restantes
        System.out.println("Enemigo generado: " + enemigo.getClass().getSimpleName());
    }
}
    

private Enemigo crearEnemigoAleatorio() {
        int fila = random.nextInt(5); // Fila aleatoria entre 0 y 4
        int tipoEnemigo = random.nextInt(4); // 4 tipos de enemigos: Goblin, Orco, Ogro, Trol
        switch (tipoEnemigo) {
            case 0:
                return new Goblin(fila, 9, 2, 1);
            case 1:
                return new Orco(fila, 9, 4, 2);
            case 2:
                return new Ogro(fila, 9, 6, 3);
            case 3:
                return new Trol(fila, 9, 10, 5);
            default:
                return new Goblin(fila, 9, 2, 1); // Por defecto un Goblin
        }
    }

}