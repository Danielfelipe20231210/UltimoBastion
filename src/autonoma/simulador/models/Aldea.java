/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.simulador.models;

/**
 *
 * @author Carlos
 */
public class Aldea {
    
    private boolean destruida;
    private int vida;

    public Aldea(int vida) {
        this.vida = vida;
        this.destruida = false;
    }

   

    public void recibirDaño(int dañoAldea) {
        vida -= dañoAldea; // Resta el daño a la salud de la aldea
        if (vida <= 0) {
            // Lógica para cuando la aldea es destruida
            destruida = true;
            System.out.println("La aldea ha sido destruida.");
        }
    }

    public boolean estaDestruida() {
        System.out.println("La aldea ha sido destruida.");
        return destruida || vida <= 0; // La aldea se considera destruida si su vida es 0 o menos
    }

    public int getVida() {
        return vida;
    }

    public int getFilaDeAtaque() {
        return 1; // Cambia esto según la lógica de tu juego (por ejemplo, la fila donde se realiza el ataque)
    }
}