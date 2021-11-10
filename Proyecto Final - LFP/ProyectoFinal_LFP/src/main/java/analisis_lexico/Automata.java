package analisis_lexico;

import analisis_sintactico.Automata_De_Pila;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Automata {

    Matriz matriz = new Matriz();
    Error error = new Error();

    public Automata() {
    }
    /**
     * Lista de los diferentes tokens reconocidos con la aplicacion
     */
    public enum Token {
        IDENTIFICADOR,
        NUMERO,
        DECIMAL,
        OPERADOR,
        SIGNO,
        COMENTARIO,
        AGRUPACION,
        LITERAL,
        ESCRIBIR,
        FIN,
        REPETIR,
        INICIAR,
        SI,
        VERDADERO,
        FALSO,
        ENTONCES,
        $,
        ERROR,
    }

    /**
     * Analiza caracter por caracter del texto de entrada.
     * @param texto El texto de entrada
     * @param AreaMovmientos El area donde se mostraran los movimienots del automata de pilas
     */
    public void analizar(String texto, JTextArea AreaMovmientos) {
        error.limpiar();
        ArrayList<Token> tokens = new ArrayList<>();
        texto = texto + " ";
        ArrayList<String> palabras = new ArrayList<>();
        String nueva_palabra = "";
        for (int i = 0; i < texto.length(); i++) {
            if (matriz.getEstado_actual() == 8) {
                //Ciclo para guardar comentarios en el lenguaje
                for (int j = i; j < texto.length(); j++) {
                    int no_caracter = obtenerCaracter(texto.charAt(j));
                    if ("\n".equals(String.valueOf(texto.charAt(j))) || j == texto.length() - 1) {
                        agregarPalabra(nueva_palabra, palabras);
                        nueva_palabra = "";

                        crearToken(tokens, false);
                        matriz.reiniciarAutomata();
                        i = j;
                        j = texto.length() + 2;
                    } else {
                        if (no_caracter != 11) {
                            matriz.movimiento(no_caracter);
                        }
                        nueva_palabra = nueva_palabra + texto.charAt(j);
                    }
                }
            } else {
                if ("\n".equals(String.valueOf(texto.charAt(i))) | "\t".equals(String.valueOf(texto.charAt(i)))) {
                    agregarPalabra(nueva_palabra, palabras);
                    boolean reservada = comprobarReservada(nueva_palabra, tokens);
                    nueva_palabra = "";

                    crearToken(tokens, reservada);
                    matriz.reiniciarAutomata();
                } else {
                    int no_caracter = obtenerCaracter(texto.charAt(i));
                    if (no_caracter == 11) {
                        agregarPalabra(nueva_palabra, palabras);
                        boolean reservada = comprobarReservada(nueva_palabra, tokens);
                        nueva_palabra = "";

                        crearToken(tokens, reservada);
                        matriz.reiniciarAutomata();
                    } else if (no_caracter == 9) {

                        agregarPalabra(nueva_palabra, palabras);
                        boolean reservada = comprobarReservada(nueva_palabra, tokens);
                        nueva_palabra = "";
                        crearToken(tokens, reservada);
                        matriz.reiniciarAutomata();

                        agregarPalabra(String.valueOf(texto.charAt(i)), palabras);
                        matriz.movimiento(no_caracter);
                        crearToken(tokens, false);
                        matriz.reiniciarAutomata();
                    } else if (no_caracter == 10) {
                        nueva_palabra = nueva_palabra + '"';
                        matriz.movimiento(no_caracter);
                        for (int j = i + 1; j < texto.length(); j++) {
                            no_caracter = obtenerCaracter(texto.charAt(j));
                            if ("\n".equals(String.valueOf(texto.charAt(j))) || j == texto.length() - 1 || no_caracter == 10) {
                                if (no_caracter == 10) {
                                    matriz.movimiento(no_caracter);
                                    nueva_palabra = nueva_palabra + texto.charAt(j);
                                }
                                agregarPalabra(nueva_palabra, palabras);
                                nueva_palabra = "";

                                crearToken(tokens, false);
                                matriz.reiniciarAutomata();
                                i = j;
                                j = texto.length() + 2;
                            } else {
                                if (no_caracter != 11) {
                                    matriz.movimiento(no_caracter);
                                }
                                nueva_palabra = nueva_palabra + texto.charAt(j);
                            }
                        }
                    } else if (no_caracter == 12) {
                        agregarPalabra(nueva_palabra, palabras);
                        boolean reservada = comprobarReservada(nueva_palabra, tokens);
                        nueva_palabra = "";

                        crearToken(tokens, reservada);
                        matriz.reiniciarAutomata();

                        if (texto.charAt(i + 1) == 'n' || texto.charAt(i + 1) == 't' || texto.charAt(i + 1) == 'r' || texto.charAt(i + 1) == 'f') {
                            i++;
                        } else {
                            System.out.println("fallo");
                            nueva_palabra = nueva_palabra + texto.charAt(i) + texto.charAt(i + 1);
                            System.out.println(nueva_palabra);
                            agregarPalabra(nueva_palabra, palabras);
                            matriz.setEstado_actual(-1);
                            crearToken(tokens, false);
                            matriz.reiniciarAutomata();
                            nueva_palabra = "";
                            i++;
                        }
                    } else {
                        matriz.movimiento(no_caracter);
                        nueva_palabra = nueva_palabra + texto.charAt(i);
                    }
                }
            }
        }
        int[][] posiciones = obtenerPosicion(texto, palabras);
        comprobarErrores(palabras, tokens, posiciones, AreaMovmientos);
    }

    /**
     * Comprueba los errores del analisis lexico
     * @param palabras La lista de palabras
     * @param tokens La lista de tokens
     * @param posiciones Las posiciones de los lexemas
     * @param AreaMovimientos El area donde se mostraran los movimientos
     */
    public void comprobarErrores(ArrayList<String> palabras, ArrayList<Token> tokens, int[][] posiciones, JTextArea AreaMovimientos) {
        boolean errores = false;
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i) == Token.ERROR) {
                errores = true;
                String nuevo_error = "Lexema: " + palabras.get(i) + " | Token: " + tokens.get(i) + " | Fila: " + posiciones[i][0] + " | Columna: " + posiciones[i][1];
                error.agregarError(nuevo_error);
            }
        }
        if (errores == true) {
            error.setVisible(true);
        } else {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Deseas ver el reporte de tokens?");
            if (confirmacion == JOptionPane.YES_OPTION) {
                Reporte reporte = new Reporte(palabras, tokens, posiciones);
            }
            Automata_De_Pila auto = new Automata_De_Pila();
            auto.analizar(palabras, tokens, posiciones, AreaMovimientos);
        }
    }
    /**
     * Obtiene las posiciones de los lexemas
     * @param texto El texto de entrada
     * @param palabras Las lista de palabras
     * @return El array de posiciones
     */
    public int[][] obtenerPosicion(String texto, ArrayList<String> palabras) {
        int[][] posiciones = new int[palabras.size()][2];
        int fila = 1;
        int columna = 1;
        int palabra = 0;
        for (int i = 0; i < texto.length(); i++) {
            String lexema = "";
            lexema = palabras.get(palabra);
            if ("\n".equals(texto.substring(i, i + 1))) {
                fila++;
                columna = 0;
            }
            if (lexema.length() <= texto.length()) {
                if (texto.substring(i, i + lexema.length()).equals(lexema)) {
                    posiciones[palabra][0] = fila;
                    posiciones[palabra][1] = columna;
                    palabra++;
                    if (palabra == palabras.size()) {
                        i = texto.length();
                    } else {
                        i += lexema.length() - 1;
                        columna += lexema.length() - 1;
                    }
                }
                columna++;
            }
        }
        return posiciones;
    }
    /**
     * Crea un nuevo token segun el numero de estado
     * @param tokens La lista de tokens
     * @param reservada Comprueba si la palabra es una reservada o un identificador
     */
    public void crearToken(ArrayList<Token> tokens, boolean reservada) {
        int estado = matriz.getEstado_actual();
        Token nuevo_token = null;
        if (estado == 1) {
            if (reservada == false) {
                nuevo_token = Token.IDENTIFICADOR;
            }
        } else if (estado == 2) {
            nuevo_token = Token.OPERADOR;
        } else if (estado == 3) {
            nuevo_token = Token.NUMERO;
        } else if (estado == 4) {
            nuevo_token = Token.ERROR;
        } else if (estado == 5) {
            nuevo_token = Token.DECIMAL;
        } else if (estado == 6) {
            nuevo_token = Token.SIGNO;
        } else if (estado == 7) {
            nuevo_token = Token.OPERADOR;
        } else if (estado == 8) {
            nuevo_token = Token.COMENTARIO;
        } else if (estado == 9) {
            nuevo_token = Token.AGRUPACION;
        } else if (estado == 10) {
            nuevo_token = Token.ERROR;
        } else if (estado == 11) {
            nuevo_token = Token.LITERAL;
        } else if (estado == -1) {
            nuevo_token = Token.ERROR;
        }
        if (nuevo_token != null) {
            tokens.add(nuevo_token);
        }
    }
    /**
     * Agrega una palabra al listado de palabras
     * @param nueva_palabra La nueva palabra 
     * @param palabras La lista de palabras
     */
    public static void agregarPalabra(String nueva_palabra, ArrayList<String> palabras) {
        if (!"".equals(nueva_palabra)) {
            palabras.add(nueva_palabra);
        }
    }
    /**
     * Comprueba si la nueva palabra es una palabra reservada
     * @param palabra La nueva palabra
     * @param tokens La Lista de Tokens
     * @return Si la palabra es reservada o no
     */
    public boolean comprobarReservada(String palabra, ArrayList<Token> tokens) {
        Token nuevo_token = null;
        if ("ESCRIBIR".equals(palabra)) {
            nuevo_token = Token.ESCRIBIR;
            tokens.add(nuevo_token);
            return true;
        } else if ("FIN".equals(palabra)) {
            nuevo_token = Token.FIN;
            tokens.add(nuevo_token);
            return true;
        } else if ("REPETIR".equals(palabra)) {
            nuevo_token = Token.REPETIR;
            tokens.add(nuevo_token);
            return true;
        } else if ("INICIAR".equals(palabra)) {
            nuevo_token = Token.INICIAR;
            tokens.add(nuevo_token);
            return true;
        } else if ("SI".equals(palabra)) {
            nuevo_token = Token.SI;
            tokens.add(nuevo_token);
            return true;
        } else if ("VERDADERO".equals(palabra)) {
            nuevo_token = Token.VERDADERO;
            tokens.add(nuevo_token);
            return true;
        } else if ("FALSO".equals(palabra)) {
            nuevo_token = Token.FALSO;
            tokens.add(nuevo_token);
            return true;
        } else if ("ENTONCES".equals(palabra)) {
            nuevo_token = Token.ENTONCES;
            tokens.add(nuevo_token);
            return true;
        } else {
            return false;
        }
    }
    /**
     * Obtiene un numero a partir de un caracter en especifico
     * @param caracter El caracter a comprobar
     * @return El numero de caracter
     */
    public int obtenerCaracter(char caracter) {
        if (Character.isLetter(caracter)) {
            return 0;
        } else if (Character.isDigit(caracter)) {
            if (caracter == '0') {
                return 4;
            } else {
                return 1;
            }
        } else if (caracter == '_') {
            return 2;
        } else if (caracter == '-') {
            return 3;
        } else if (caracter == '.') {
            return 5;
        } else if (caracter == '<') {
            return 6;
        } else if (caracter == '>') {
            return 6;
        } else if (caracter == ':') {
            return 6;
        } else if (caracter == ',') {
            return 6;
        } else if (caracter == ';') {
            return 6;
        } else if (caracter == '=') {
            return 6;
        } else if (caracter == '\'') {
            return 6;
        } else if (caracter == '/') {
            return 7;
        } else if (caracter == '+') {
            return 8;
        } else if (caracter == '*') {
            return 8;
        } else if (caracter == '%') {
            return 8;
        } else if (caracter == '(') {
            return 9;
        } else if (caracter == ')') {
            return 9;
        } else if (caracter == '"') {
            return 10;
        } else if (Character.isSpaceChar(caracter)) {
            return 11;
        } else if (caracter == '\\') {
            return 12;
        } else {
            return -1;
        }
    }
}
