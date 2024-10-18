/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.simulador.models;

import autonoma.simulador.sonido.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Carlos
 */
public class Juego {
    
    private int tiempo = 0;
    private Mapa mapa;
    private Guerrero guerrero;
    private Aldea aldea;
    private Oleada oleada;
    private List<Enemigo> enemigos;
    private Timer timer;
    private Timer temporizadorEnemigos;
    private int contadorEnemigosGenerados = 0;
    private int tiempoRestante; // Tiempo restante en segundos
    private int totalEnemigos;
    private boolean juegoTerminado;
    private JLabel[][] mapaGraficado;
    private ReproductorMusica musicaFondo;
    private ReproductorMusica sonidoAtaque;

    public Juego() {
        mapa = new Mapa();
        guerrero = new Guerrero("Rol", 1); // Guerrero en la fila 1, columna 1
        aldea = new Aldea(100); // La aldea empieza con 100 puntos de vida
        oleada = new Oleada();
        this.enemigos = new ArrayList<>();
        
        tiempoRestante = 180;
        inicializarTemporizador();
        totalEnemigos = 100;
        juegoTerminado = false;
        musicaFondo = new ReproductorMusica(getClass().getResource("/autonoma.simulador.sonido/musicaDeFondo.wav").getPath());
        sonidoAtaque = new ReproductorMusica(getClass().getResource("/autonoma.simulador.sonido/EfectoDeAtaque.wav").getPath());
    }
    

    public void iniciarJuego() {
        // Reproducir música al iniciar el juego
        musicaFondo.reproducirLoop();
        
        oleada.generarEnemigosPorIntervalo(tiempo,mapa); // Genera la primera oleada de enemigos
        iniciarTemporizador();    // Comienza el temporizador de 3 minutos
        
    }
    
    private void iniciarTemporizador() {
    timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!juegoTerminado) {
                actualizarJuego();
            } else {
                timer.stop(); // Detiene el temporizador si el juego ha terminado
            }
        }
    });
    temporizadorEnemigos = new Timer(3000, e -> generarEnemigo());
        temporizadorEnemigos.start();
}
    
    public void actualizarJuego(){
        tiempoRestante--;
        tiempo++;

        // Generar enemigos por intervalo de tiempo
        oleada.generarEnemigosPorIntervalo(tiempo, mapa);
        
        // Mover enemigos y verificar si llegan a la aldea
        oleada.moverEnemigos(mapa, guerrero, aldea);
        
        for (Enemigo enemigo : enemigos) {
            enemigo.moverYAtacar(mapa, aldea);  // Mover al enemigo y atacar si está en la columna correcta
        }
        
        // Verificar si el guerrero está en la misma fila que algún enemigo
        verificarCombates();

        // Verificar si todos los enemigos han sido derrotados o si el tiempo se acabó
        if (oleada.todosDerrotados() || aldea.getVida() <= 0 || tiempoRestante <= 0) {
            terminarJuego();
        }
    }
    
    public void verificarCombates() {
    for (int columna = 2; columna <= 3; columna++) { // Guerreros atacan en columnas 2 y 3
        Enemigo enemigo = mapa.getEnemigo(guerrero.getFila(), columna);
        if (enemigo != null && !enemigo.estaDerrotado()) {
            sonidoAtaque.reproducirLoop(); // Cambiar a reproducir una vez
            guerrero.atacarCuerpoACuerpo(enemigo);
            if (enemigo.estaDerrotado()) {
                mapa.eliminarEnemigo(guerrero.getFila(), columna);
                actualizarVistaEnemigo(guerrero.getFila(), columna);
            }
        }
    }
}
    private void actualizarVistaEnemigo(int fila, int columna) {
    // Esto asume que tienes algún tipo de array o matriz de componentes visuales que representan el mapa
    JLabel etiqueta = mapaGraficado[fila][columna]; // Obtener la etiqueta o componente en la posición
    etiqueta.setIcon(null); // Elimina el ícono del enemigo, haciéndolo desaparecer
    etiqueta.repaint(); // Refresca la interfaz gráfica
}
    
    private void terminarJuego() {
        musicaFondo.detener();
        juegoTerminado = true;

        if (aldea.getVida() <= 0) {
            System.out.println("La aldea ha sido destruida. ¡Has perdido!");
        } else if (oleada.todosDerrotados()) {
            System.out.println("¡Has derrotado a todos los enemigos! ¡Has ganado!");
        } else if (tiempoRestante <= 0) {
            System.out.println("El tiempo ha terminado. ¡Fin del juego!");
        }
    }
    
    public void moverGuerrero(String direccion) {
        if (direccion.equals("arriba")) {
            guerrero.moverArriba();
        } else if (direccion.equals("abajo")) {
            guerrero.moverAbajo();
        }
    }
    
     private void inicializarTemporizador() {
        temporizadorEnemigos = new Timer(3000, e -> generarEnemigo());
        temporizadorEnemigos.start();
    }
     
    public void generarEnemigo() {
        if (contadorEnemigosGenerados < 85) { // Cambia este número según la cantidad total de enemigos
            if (mapa.puedeAgregarEnemigo()) { // Verificar si se puede agregar un enemigo
                Enemigo nuevoEnemigo;
                if (contadorEnemigosGenerados < 35) {
                    nuevoEnemigo = new Goblin(1, 9, 1, 1);
                } else if (contadorEnemigosGenerados < 60) {
                    nuevoEnemigo = new Orco(1, 9, 4, 2);
                } else if (contadorEnemigosGenerados < 75) {
                    nuevoEnemigo = new Ogro(1, 9, 6, 3);
                } else {
                    nuevoEnemigo = new Trol(1, 9, 10, 5);
                }

                // Colocar el nuevo enemigo en el mapa en la última columna (columna 9) de la fila correspondiente
                int filaParaColocar = nuevoEnemigo.getFila(); // Asegúrate de que el enemigo tenga su fila asignada correctamente
                int columnaParaColocar = 7; // La columna en la que quieres colocar al enemigo

                if (mapa.colocarEnemigo(nuevoEnemigo, filaParaColocar, columnaParaColocar)) {
                    enemigos.add(nuevoEnemigo); // Agregar el nuevo enemigo a la lista si se colocó con éxito
                    contadorEnemigosGenerados++; // Incrementar el contador
                    System.out.println("Enemigo generado: " + nuevoEnemigo.getClass().getSimpleName()); // Mensaje de depuración
                } else {
                    System.out.println("No se pudo colocar el enemigo en el mapa.");
                }
            } else {
                System.out.println("Máximo de enemigos alcanzado. Esperando a que uno sea derrotado.");
            }
        } else {
            temporizadorEnemigos.stop(); // Detener el temporizador cuando se generen todos los enemigos
            System.out.println("Todos los enemigos generados.");
        }
    }
    
    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public Aldea getAldea() {
        return aldea;
    }


    public Guerrero getGuerrero() {
        return guerrero;
    }

    public Oleada getOleada() {
        return oleada;
    }

    public Mapa getMapa() {
        return mapa;
    }
    
    
}