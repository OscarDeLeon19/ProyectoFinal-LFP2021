package analisis_sintactico;

import analisis_lexico.Automata.Token;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Automata_De_Pila {

    LinkedList pila = new LinkedList();

    public Automata_De_Pila() {
    }
    /**
     * Inicia el analisis sintactico 
     * @param palabras La lista de palabras
     * @param tokens La lista de tokens
     * @param posiciones Las posiciones de los lexemas
     * @param AreaMovimientos El area de movimientos
     */
    public void analizar(ArrayList<String> palabras, ArrayList<Token> tokens, int[][] posiciones, JTextArea AreaMovimientos) {
        // Agrega los $ al final de cada fin de una estructura
        for (int i = 0; i < palabras.size(); i++) {
            if ("REPETIR".equals(palabras.get(i))) {
                int fin = 0;
                for (int j = i; j < palabras.size(); j++) {
                    if ("ESCRIBIR".equals(palabras.get(j))) {
                        fin++;
                    }
                    if (("FIN".equals(palabras.get(j)))) {
                        if (fin > 0) {
                            fin--;
                        } else {
                            palabras.add(j + 1, "$");
                            tokens.add(j + 1, Token.$);
                            i = j + 1;
                            j = palabras.size();
                        }
                    }
                }
            } else if ("SI".equals(palabras.get(i))) {
                int fin = 0;
                for (int j = i; j < palabras.size(); j++) {
                    if ("ESCRIBIR".equals(palabras.get(j))) {
                        fin++;
                    }
                    if (("FIN".equals(palabras.get(j)))) {
                        if (fin > 0) {
                            fin--;
                        } else {
                            palabras.add(j + 1, "$");
                            tokens.add(j + 1, Token.$);
                            i = j + 1;
                            j = palabras.size();
                        }
                    }
                }
            } else if ("FIN".equals(palabras.get(i))) {
                palabras.add(i + 1, "$");
                tokens.add(i + 1, Token.$);
            }
        }
        iniciarPila();
        int numero_token = 0;
        boolean errores = false;
        // Comienza los movientos del automata de pila
        while (numero_token < palabras.size()) {
            if (numero_token < palabras.size()) {

                if (pila.isEmpty()) {
                    iniciarPila();
                }

                Object objeto_actual = pila.getFirst();
                AreaMovimientos.append(" ");
                AreaMovimientos.append("\n");
                AreaMovimientos.append("Pila: " + pila);
                AreaMovimientos.append("\n");
                AreaMovimientos.append("Token Actual: " + tokens.get(numero_token));
                AreaMovimientos.append("\n");
                AreaMovimientos.append("Pila: " + objeto_actual);
                AreaMovimientos.append("\n");
                int valor = shift(palabras.get(numero_token), tokens.get(numero_token), objeto_actual);
                if (valor == 1) {
                    AreaMovimientos.append("Shift: " + objeto_actual + " con: " + palabras.get(numero_token));
                    AreaMovimientos.append("\n");
                }
                if (valor == 2) {
                    int posicion_actual = obtenerPosicionToken(palabras, numero_token);
                    JOptionPane.showMessageDialog(null, "Error Sintactico | Fila: " + posiciones[posicion_actual][0] + " | Columna: " + posiciones[posicion_actual][1]);
                    numero_token = palabras.size();
                    errores = true;
                }
                if (numero_token < palabras.size()) {
                    boolean variable = reduce(palabras.get(numero_token), tokens.get(numero_token), objeto_actual);
                    if (variable == true) {
                        AreaMovimientos.append("Reduce: " + palabras.get(numero_token) + " con: " + objeto_actual);
                        AreaMovimientos.append("\n");
                        numero_token++;
                    } else {
                        if (valor == 3) {
                            int posicion_actual = obtenerPosicionToken(palabras, numero_token);
                            JOptionPane.showMessageDialog(null, "Error Sintactico | Fila: " + posiciones[posicion_actual][0] + " | Columna: " + posiciones[posicion_actual][1]);
                            numero_token = palabras.size();
                            errores = true;
                        }
                    }
                }
            }
        }
        if (errores == false) {
            if (pila.isEmpty() == false) {
                JOptionPane.showMessageDialog(null, "La sintaxis no esta completa");
            } else {
                JOptionPane.showMessageDialog(null, "No hay errores, exportado resultados");
                Exportacion expo = new Exportacion();
                expo.exportarSalida(palabras, tokens);
            }
        }
    }
    /**
     * Obtiene la posicion de un lexema determinado
     * @param palabras La lista de palabras
     * @param actual El numero actual de lexema
     * @return El numero de posicion
     */
    public int obtenerPosicionToken(ArrayList<String> palabras, int actual) {
        int nueva_posicion = 0;
        for (int i = 0; i < actual; i++) {
            if ("$".equals(palabras.get(i))) {

            } else {
                nueva_posicion++;
            }
        }
        return nueva_posicion;
    }
    /**
     * Reinicia la pila
     */
    public void iniciarPila() {
        pila.clear();
        pila.add("S");
        pila.add("$");
    }
    /**
     * Realiza un reduce en el automata de pila
     * @param palabra La palabra actual
     * @param token El token actual
     * @param objeto_actual El objeto de la pila actual
     * @return Si se realizo el reduce o no
     */
    public boolean reduce(String palabra, Token token, Object objeto_actual) {
        boolean reduce = false;
        if (objeto_actual instanceof Token) {
            if (objeto_actual == token) {
                pila.removeFirst();
                reduce = true;
            }
        } else {
            if (palabra.equals(String.valueOf(objeto_actual))) {
                pila.removeFirst();
                reduce = true;
            }
        }
        return reduce;
    }
    /**
     * Realiza un reduce en el automata de pila
     * @param palabra La palabra actual
     * @param token El token actual
     * @param objeto_actual El objeto actual de la pila
     * @return El valor del shift
     */
    public int shift(String palabra, Token token, Object objeto_actual) {
        int valor = 0;
        if ("S".equals(String.valueOf(objeto_actual))) {
            if (token == Token.ESCRIBIR) {
                pila.removeFirst();
                pila.addFirst("ESCRITURA");
                valor = 1;
            } else if (token == Token.REPETIR) {
                pila.removeFirst();
                pila.addFirst("REPETICION");
                valor = 1;
            } else if (token == Token.SI) {
                pila.removeFirst();
                pila.addFirst("CONDICIONAL");
                valor = 1;
            } else if (token == Token.IDENTIFICADOR) {
                pila.removeFirst();
                pila.addFirst("ASIGNACION");
                valor = 1;
            } else if (token == Token.$) {
                pila.removeFirst();
                valor = 1;
            } else {
                valor = 2;
            }
        } else if ("ESCRITURA".equals(String.valueOf(objeto_actual))) {
            if (token == Token.ESCRIBIR) {
                pila.removeFirst();
                pila.addFirst("ESCRITURA");
                pila.addFirst(Token.FIN);
                pila.addFirst("L");
                pila.addFirst(Token.ESCRIBIR);
                valor = 1;
            } else if (token == Token.FIN) {
                pila.removeFirst();
                valor = 1;
            } else if (token == Token.$) {
                pila.removeFirst();
                valor = 1;
            } else {
                valor = 2;
            }
        } else if ("REPETICION".equals(String.valueOf(objeto_actual))) {
            if (token == Token.REPETIR) {
                pila.removeFirst();
                pila.addFirst(Token.FIN);
                pila.addFirst("ESCRITURA");
                pila.addFirst(Token.INICIAR);
                pila.addFirst(Token.IDENTIFICADOR);
                pila.addFirst(Token.REPETIR);
                valor = 1;
            } else {
                valor = 2;
            }
        } else if ("CONDICIONAL".equals(String.valueOf(objeto_actual))) {
            if (token == Token.SI) {
                pila.removeFirst();
                pila.addFirst(Token.FIN);
                pila.addFirst("ESCRITURA");
                pila.addFirst(Token.ENTONCES);
                pila.addFirst("CONDICION");
                pila.addFirst(Token.SI);
                valor = 1;
            } else {
                valor = 2;
            }
        } else if ("ASIGNACION".equals(String.valueOf(objeto_actual))) {
            if (token == Token.IDENTIFICADOR) {
                pila.removeFirst();
                pila.addFirst(Token.FIN);
                pila.addFirst("EXPRESION");
                pila.addFirst("=");
                pila.addFirst(Token.IDENTIFICADOR);
                valor = 1;
            } else {
                valor = 2;
            }
        } else if ("EXPRESION".equals(String.valueOf(objeto_actual))) {
            if (token == Token.OPERADOR) {
                if ("-".equals(palabra)) {
                    pila.removeFirst();
                    pila.addFirst("X'");
                    pila.addFirst("T");
                    valor = 1;
                } else {
                    valor = 2;
                }
            } else if (token == Token.IDENTIFICADOR || token == Token.NUMERO) {
                pila.removeFirst();
                pila.addFirst("X'");
                pila.addFirst("T");
                valor = 1;
            } else if (token == Token.AGRUPACION) {
                if ("(".equals(palabra)) {
                    pila.removeFirst();
                    pila.addFirst("X'");
                    pila.addFirst("T");
                    valor = 1;
                }
            } else {
                valor = 2;
            }
        } else if ("X'".equals(String.valueOf(objeto_actual))) {
            if (token == Token.FIN) {
                pila.removeFirst();
            } else if (token == Token.OPERADOR) {
                if ("+".equals(palabra)) {
                    pila.removeFirst();
                    pila.addFirst("X'");
                    pila.addFirst("T");
                    pila.addFirst("+");
                    valor = 1;
                } else if ("-".equals(palabra)) {
                    pila.removeFirst();
                    pila.addFirst("X'");
                    pila.addFirst("T");
                    pila.addFirst("-");
                    valor = 1;
                } else {
                    valor = 2;
                }
            } else if (token == Token.AGRUPACION) {
                if (")".equals(palabra)) {
                    pila.removeFirst();
                    valor = 1;
                } else {
                    valor = 2;
                }
            } else {
                valor = 2;
            }
        } else if ("T".equals(String.valueOf(objeto_actual))) {
            if (token == Token.NUMERO || token == Token.IDENTIFICADOR) {
                pila.removeFirst();
                pila.addFirst("T'");
                pila.addFirst("P");
                valor = 1;
            } else if (token == Token.OPERADOR) {
                if ("-".equals(palabra)) {
                    pila.removeFirst();
                    pila.addFirst("T'");
                    pila.addFirst("P");
                    valor = 1;
                } else {
                    valor = 2;
                }
            } else if (token == Token.AGRUPACION) {
                if ("(".equals(palabra)) {
                    pila.removeFirst();
                    pila.addFirst("T'");
                    pila.addFirst("P");
                    valor = 1;
                } else {
                    valor = 2;
                }
            } else {
                valor = 2;
            }
        } else if ("T'".equals(String.valueOf(objeto_actual))) {
            if (token == Token.FIN) {
                pila.removeFirst();
                valor = 1;
            } else if (token == Token.OPERADOR) {
                if ("+".equals(palabra)) {
                    pila.removeFirst();
                    valor = 1;
                } else if ("-".equals(palabra)) {
                    pila.removeFirst();
                    valor = 1;
                } else if ("*".equals(palabra)) {
                    pila.removeFirst();
                    pila.addFirst("T'");
                    pila.addFirst("P");
                    pila.addFirst("*");
                    valor = 1;
                } else if ("/".equals(palabra)) {
                    pila.removeFirst();
                    pila.addFirst("T'");
                    pila.addFirst("P");
                    pila.addFirst("/");
                    valor = 1;
                } else {
                    valor = 2;
                }
            } else if (token == Token.AGRUPACION) {
                if (")".equals(palabra)) {
                    pila.removeFirst();
                    valor = 1;
                } else {
                    valor = 2;
                }
            } else {
                valor = 2;
            }
        } else if ("P".equals(String.valueOf(objeto_actual))) {
            if (token == Token.IDENTIFICADOR || token == Token.NUMERO) {
                pila.removeFirst();
                pila.addFirst("E");
                valor = 1;
            } else if (token == Token.OPERADOR) {
                if ("-".equals(palabra)) {
                    pila.removeFirst();
                    pila.addFirst("E");
                    pila.addFirst("-");
                    valor = 1;
                } else {
                    valor = 2;
                }
            } else if (token == Token.AGRUPACION) {
                if ("(".equals(palabra)) {
                    pila.removeFirst();
                    pila.addFirst("E");
                    valor = 1;
                } else {
                    valor = 2;
                }
            } else {
                valor = 2;
            }
        } else if ("E".equals(String.valueOf(objeto_actual))) {
            if (token == Token.IDENTIFICADOR || token == Token.NUMERO) {
                pila.removeFirst();
                pila.addFirst("IDENTIFICADOR");
                valor = 1;
            } else if (token == Token.AGRUPACION) {
                if ("(".equals(palabra)) {
                    pila.removeFirst();
                    pila.addFirst(")");
                    pila.addFirst("EXPRESION");
                    pila.addFirst("(");
                    valor = 1;
                } else {
                    valor = 2;
                }
            } else {
                pila.removeFirst();
                valor = 1;
            }
        } else if ("CONDICION".equals(String.valueOf(objeto_actual))) {
            if (token == Token.VERDADERO) {
                pila.removeFirst();
                pila.addFirst(Token.VERDADERO);
                valor = 1;
            } else if (token == Token.FALSO) {
                pila.removeFirst();
                pila.addFirst(Token.FALSO);
                valor = 1;
            } else {
                valor = 2;
            }
        } else if ("IDENTIFICADOR".equals(String.valueOf(objeto_actual))) {
            if (token == Token.IDENTIFICADOR) {
                pila.removeFirst();
                pila.addFirst(Token.IDENTIFICADOR);
                valor = 1;
            } else if (token == Token.NUMERO) {
                pila.removeFirst();
                pila.addFirst(Token.NUMERO);
                valor = 1;
            } else {
                valor = 2;
            }
        } else if ("L".equals(String.valueOf(objeto_actual))) {
            if (token == Token.IDENTIFICADOR) {
                pila.removeFirst();
                pila.addFirst(Token.IDENTIFICADOR);
                valor = 1;
            } else if (token == Token.NUMERO) {
                pila.removeFirst();
                pila.addFirst(Token.NUMERO);
                valor = 1;
            } else if (token == Token.DECIMAL) {
                pila.removeFirst();
                pila.addFirst(Token.DECIMAL);
                valor = 1;
            } else if (token == Token.LITERAL) {
                pila.removeFirst();
                pila.addFirst(Token.LITERAL);
                valor = 1;
            } else {
                valor = 2;
            }
        } else {
            valor = 3;
        }
        return valor;
    }
}
