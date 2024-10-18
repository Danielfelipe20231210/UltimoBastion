/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.simulador;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Carlos
 */
public class ReproductorMusica {
    private Clip clip;
    

    public ReproductorMusica(String rutaArchivo) {
        try {
            // Cargar el archivo de sonido
            File archivoSonido = new File(rutaArchivo);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivoSonido);
            
            // Obtener el clip y abrir el flujo de audio
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error al cargar el archivo de sonido: " + e.getMessage());
        }
    }

    // Reproducir el archivo en bucle
    public void reproducirEnBucle() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    // Reproducir el archivo una vez
    public void reproducir() {
        if (clip != null) {
            clip.start();
        }
    }

    // Detener la reproducción
    public void detener() {
        if (clip != null) {
            clip.stop();
        }
    }
}
