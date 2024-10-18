/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.simulador.main;

import autonoma.simulador.models.*;
import autonoma.simulador.views.VentanaPrincipal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

/**
 *
 * @author Carlos
 */
public class JuegoGUI extends JFrame {
    
    private Oleada oleada;
    private Aldea aldea;
    private Mapa mapa;
    private Guerrero guerrero;
    private Enemigo[] enemigos;
    private JLabel etiquetaVidaAldea;
    private Juego juego;
    private JPanel panelJuego;
    private JLabel[][] mapaVisual;
    private ImageIcon imagenGuerrero;
    private ImageIcon imagenGoblin;
    private ImageIcon imagenOrco;
    private ImageIcon imagenOgro;
    private ImageIcon imagenTrol;
    private Timer timer;
    
    private Timer temporizadorEnemigos;
    private int contadorEnemigosGenerados = 0;
    private static final int TIEMPO_GENERACION = 3000;

    public JuegoGUI(Juego juego) {
        
        this.oleada = juego.getOleada();
        this.mapa = juego.getMapa();
        this.juego = juego;
        this.aldea = juego.getAldea();
        this.guerrero = juego.getGuerrero();
        setTitle("Simulador de Defensa");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        juego.generarEnemigo();

        // Cargar imagen del guerrero
        imagenGuerrero = new ImageIcon(getClass().getResource("/autonoma.simulador.imagenes/Guardian.png"));
        // Cargar imágenes de enemigos
        imagenGoblin = new ImageIcon(getClass().getResource("/autonoma.simulador.imagenes/Goblin.png"));
        imagenOrco = new ImageIcon(getClass().getResource("/autonoma.simulador.imagenes/Orco.png"));
        imagenOgro = new ImageIcon(getClass().getResource("/autonoma.simulador.imagenes/Ogro.png"));
        imagenTrol = new ImageIcon(getClass().getResource("/autonoma.simulador.imagenes/Trol.png"));

        // Verificar que las imágenes se cargaron correctamente
        if (imagenGuerrero == null || imagenGoblin == null || imagenOrco == null || imagenOgro == null || imagenTrol == null) {
            System.err.println("Error al cargar una o más imágenes. Verifica las rutas.");
            System.exit(1);  // Salir si no se pueden cargar las imágenes
        }

        // Inicializar el panel del juego
        panelJuego = new JPanel(new GridLayout(5, 10));
        mapaVisual = new JLabel[5][10];
        inicializarMapaVisual();

        // Añadir el panel al JFrame
        add(panelJuego, BorderLayout.CENTER);

        // Instrucciones de teclas
        JLabel instrucciones = new JLabel("Usa las flechas del teclado para mover al guerrero.");
        instrucciones.setHorizontalAlignment(SwingConstants.CENTER);
        add(instrucciones, BorderLayout.SOUTH);

        // Escuchador de eventos del teclado
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        juego.getGuerrero().moverArriba();
                        break;
                    case KeyEvent.VK_DOWN:
                        juego.getGuerrero().moverAbajo();
                        break;
                }
                actualizarMapa();
                iniciarTemporizador();
            }
        });

        // Para asegurarse de que los eventos del teclado funcionen
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        // Iniciar la actualización inicial del mapa visual
        actualizarMapa();
        
        // Añadir etiqueta de vida de la aldea
        etiquetaVidaAldea = new JLabel("Vida de la aldea: " + juego.getAldea().getVida());
        add(etiquetaVidaAldea, BorderLayout.NORTH);
    
        // Inicializa el temporizador para generar enemigos
       new javax.swing.Timer(1000, e -> oleada.moverEnemigos(mapa, guerrero, aldea)).start();
        temporizadorEnemigos = new Timer(TIEMPO_GENERACION, e -> juego.generarEnemigo());
        temporizadorEnemigos.setInitialDelay(TIEMPO_GENERACION); // Espera 3 segundos antes de iniciar
        temporizadorEnemigos.start();

    }
    
    // Método para iniciar el temporizador
    private void iniciarTemporizador() {
        timer = new Timer(1000, new ActionListener() {
            int tiempo = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                tiempo++;
                // Genera enemigos en intervalos
   
                    oleada.generarEnemigosPorIntervalo(tiempo, mapa); // Usa la instancia de Oleada
                    // Mueve a todos los enemigos hacia la aldea
            for (Enemigo enemigo : juego.getOleada().getEnemigos()) {
                if (!enemigo.estaDerrotado()) { // Solo mover enemigos que no estén derrotados
                    enemigo.mover(mapa, aldea); // Mueve el enemigo hacia la izquierda
                }
            }
                    actualizarMapa();
                    // Actualiza la vida de la aldea y verifica si el juego ha terminado
                
            }
        });
        timer.start();
    }
    
    // Método para inicializar el mapa visual (grid de JLabels)
    private void inicializarMapaVisual() {
        for (int fila = 0; fila < 5; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                mapaVisual[fila][columna] = new JLabel();
                mapaVisual[fila][columna].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                mapaVisual[fila][columna].setHorizontalAlignment(SwingConstants.CENTER);
                panelJuego.add(mapaVisual[fila][columna]);
            }
        }
    }
    
    

    // Método para limpiar el mapa visual
    private void limpiarMapaVisual() {
        for (int fila = 0; fila < 5; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                mapaVisual[fila][columna].setIcon(null);
            }
        }
    }

    
  public void actualizarMapa() {
        limpiarMapaVisual(); // Limpia el mapa antes de actualizar
        
        // Mostrar el guerrero en su posición
        int posicionGuerrero = juego.getGuerrero().getFila();
        
        // Asegúrate de que la posición del guerrero sea válida
        if (posicionGuerrero >= 0 && posicionGuerrero < mapaVisual.length) {
            mapaVisual[posicionGuerrero][0].setIcon(imagenGuerrero); // Imagen del guerrero en la columna 0
        }

        // Iterar sobre los enemigos para mostrar su posición en el mapa
        for (Enemigo enemigo : juego.getOleada().getEnemigos()) {
            if (!enemigo.estaDerrotado()) {
                int fila = enemigo.getFila();
                int columna = enemigo.getColumna();
                
                if (mapa.contarEnemigos() >= 10) {
            System.out.println("No se pueden generar más enemigos. Máximo alcanzado.");
            return; // Detiene la generación si ya hay 10 enemigos
                }
                // Verifica límites antes de mostrar el enemigo
                if (fila >= 0 && fila < mapaVisual.length && columna >= 0 && columna < mapaVisual[0].length) {
                    // Muestra la imagen del enemigo en su posición correspondiente
                    if (enemigo instanceof Goblin) {
                        mapaVisual[fila][columna].setIcon(imagenGoblin);
                    } else if (enemigo instanceof Orco) {
                        mapaVisual[fila][columna].setIcon(imagenOrco);
                    } else if (enemigo instanceof Ogro) {
                        mapaVisual[fila][columna].setIcon(imagenOgro);
                    } else if (enemigo instanceof Trol) {
                        mapaVisual[fila][columna].setIcon(imagenTrol);
                    }
                } else {
                    System.out.println("Posición del enemigo fuera de límites: Fila: " + fila + ", Columna: " + columna);
                }
            }
        }

        // Actualiza la vista
        repaint();
    }
    
    private void cargarImagenes() {
        imagenGoblin = new ImageIcon(getClass().getResource("/autonoma.simulador.imagenes/Goblin.png"));
        imagenOrco = new ImageIcon(getClass().getResource("/autonoma.simulador.imagenes/Orco.png"));
        imagenOgro = new ImageIcon(getClass().getResource("/autonoma.simulador.imagenes/Ogro.png"));
        imagenTrol = new ImageIcon(getClass().getResource("/autonoma.simulador.imagenes/Trol.png"));
}
    
    public void cambiarImagenAtaque() {
        imagenGuerrero = new ImageIcon(getClass().getResource("/autonoma.simulador.imagenes/corte.png"));
    }

    public void restaurarImagen() {
        imagenGuerrero = new ImageIcon(getClass().getResource("/autonoma.simulador.imagenes/Guardian.png"));
    }
    
    
    public void iniciar() {
        System.out.println("Iniciando la GUI...");
        setVisible(true);
        juego.iniciarJuego(); // Inicia el juego
        actualizarMapa(); // Asegúrate de actualizar el mapa después de iniciar el juego
    }
    
    private void iniciarJuego() {
        juego.iniciarJuego(); // Inicia el juego
        // Actualizar el mapa y el tiempo aquí (puedes usar un Timer para eso)
    }

    // Método main para ejecutar la aplicación
    public static void main(String[] args) {
 
        Juego juego = new Juego();
        
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(juego);
        ventanaPrincipal.setVisible(true);
//        Juego juego = new Juego();
//        JuegoGUI gui = new JuegoGUI(juego);
//        gui.iniciar();
    }
}