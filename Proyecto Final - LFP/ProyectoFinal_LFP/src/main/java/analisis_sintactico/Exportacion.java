package analisis_sintactico;

import analisis_lexico.Automata.Token;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import main.Guardar;

public class Exportacion {

    ArrayList<String> simbolos = new ArrayList<>();
    ArrayList<Integer> resultados = new ArrayList<>();
    Guardar guardar = new Guardar();
    Tabla tabla = new Tabla();
    public Exportacion() {
    }
    /**
     * Exporta la salida de el analisis sintactico
     * @param palabras La lista de palabras
     * @param tokens La lista de tokens
     */
    public void exportarSalida(ArrayList<String> palabras, ArrayList<Token> tokens) {
        String texto_total = "";
        for (int i = 0; i < palabras.size(); i++) {
            if (tokens.get(i) == Token.ESCRIBIR) {
                if (tokens.get(i + 1) == Token.LITERAL) {
                    texto_total = texto_total + palabras.get(i + 1).substring(1, palabras.get(i + 1).length() - 1) + "\n";
                } else if (tokens.get(i + 1) == Token.NUMERO || tokens.get(i + 1) == Token.DECIMAL) {
                    texto_total = texto_total + palabras.get(i + 1) + "\n";
                } else if (tokens.get(i + 1) == Token.IDENTIFICADOR) {
                    texto_total = texto_total + obtenerValorSimbolo(palabras.get(i + 1)) + "\n";
                }
                i = i+2;
            }
            if (tokens.get(i) == Token.IDENTIFICADOR) {
                int finalizacion = 0;
                int numeros = 0;
                for (int j = i + 2; j < palabras.size(); j++) {
                    if (tokens.get(j) == Token.FIN) {
                        finalizacion = j + 1;
                        j = palabras.size();
                    } else {
                        numeros++;
                    }
                }
                if (numeros == 1) {
                    agregarSimbolo(palabras.get(i), palabras.get(i + 2), tokens.get(i + 2));
                } else {
                    agregarSimbolo(palabras.get(i), "1", tokens.get(i + 2));
                }
                i = finalizacion;
            }
            if (tokens.get(i) == Token.REPETIR) {
                int repeticiones;
                if (tokens.get(i + 1) == Token.IDENTIFICADOR) {
                    repeticiones = obtenerValorSimbolo(palabras.get(i + 1));
                } else {
                    repeticiones = Integer.parseInt(palabras.get(i + 1));
                }
                int finalizacion = 0;
                String texto = "";
                for (int j = i + 3; j < palabras.size(); j++) {
                    if (tokens.get(j) == Token.ESCRIBIR) {
                        if (tokens.get(j + 1) == Token.LITERAL) {
                            texto = texto + (palabras.get(j + 1).substring(1, palabras.get(j + 1).length() - 1)) + "\n";
                        } else if (tokens.get(j + 1) == Token.NUMERO || tokens.get(j + 1) == Token.DECIMAL) {
                            texto = texto + palabras.get(j + 1) + " \n";
                        } else if (tokens.get(j + 1) == Token.IDENTIFICADOR) {
                            texto = texto + obtenerValorSimbolo(palabras.get(j + 1)) + "\n";
                        }
                    }
                    if (tokens.get(j) == Token.$) {
                        finalizacion = j;
                        j = palabras.size();
                    }
                }
                for (int j = 0; j < repeticiones; j++) {
                    texto_total = texto_total + texto;
                }
                i = finalizacion;
            }
            if (tokens.get(i) == Token.SI) {
                int finalizacion = 0;
                for (int j = i + 1; j < palabras.size(); j++) {
                    if (tokens.get(j) == Token.$) {
                        finalizacion = j;
                        j = palabras.size();
                    }
                }
                if (tokens.get(i + 1) == Token.VERDADERO) {
                    String texto = "";
                    for (int j = i + 3; j < palabras.size(); j++) {
                        if (tokens.get(j) == Token.ESCRIBIR) {
                            if (tokens.get(j + 1) == Token.LITERAL) {
                                texto = texto + (palabras.get(j + 1).substring(1, palabras.get(j + 1).length() - 1)) + "\n";
                            } else if (tokens.get(j + 1) == Token.NUMERO || tokens.get(j + 1) == Token.DECIMAL) {
                                texto = texto + palabras.get(j + 1) + " \n";
                            } else if (tokens.get(j + 1) == Token.IDENTIFICADOR) {
                                texto = texto + obtenerValorSimbolo(palabras.get(j + 1)) + "\n";
                            }
                        }
                        if (tokens.get(j) == Token.$) {
                            j = palabras.size();
                        }
                    }
                    texto_total = texto_total + texto;
                }
                i = finalizacion;
            }
        }
        guardar.exportarDocumento(texto_total);
        tabla.generarTabla(simbolos, resultados);
    }
    /**
     * Agraga un simbolo a la tabla de simbolos
     * @param palabra El identificador
     * @param valor El valor del identificador
     * @param token El token
     */
    public void agregarSimbolo(String palabra, String valor, Token token) {
        int resultado;
        if (token == Token.IDENTIFICADOR) {
            resultado = obtenerValorSimbolo(valor);
        } else {
            resultado = Integer.parseInt(valor);
        }

        boolean repetido = false;
        for (int i = 0; i < simbolos.size(); i++) {
            if (simbolos.get(i).equals(palabra)) {
                resultados.remove(i);
                resultados.add(i, resultado);
                repetido = true;
            }
        }
        if (repetido == false) {
            simbolos.add(palabra);
            resultados.add(resultado);
        }
    }
    /**
     * Obtiene el valor de un simbolo
     * @param id El identificador
     * @return El valor del simbolo
     */
    public int obtenerValorSimbolo(String id) {
        int valor = 0;
        boolean error = true;
        for (int i = 0; i < simbolos.size(); i++) {
            if (id.equals(simbolos.get(i))) {
                valor = resultados.get(i);
                error = false;
            }
        }
        if (error == true) {
            JOptionPane.showMessageDialog(null, "Error de Semantica, el identificador | " + id + " | no tiene un valor definido con anterioridad");
        }
        return valor;
    }

}
