/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisis_lexico;

import analisis_lexico.Automata.Token;
import java.util.ArrayList;

public class Reporte extends javax.swing.JFrame {

    public Reporte(ArrayList<String> palabras, ArrayList<Token> tokens, int[][] posiciones) {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        agregarTokens(palabras, tokens, posiciones);
        contarTokens(tokens);
        contarLexemas(palabras);
    }

    /**
     * Agrega un toquen al area de texto de tokens
     *
     * @param palabras Las palabras que se agregaran
     * @param tokens Los tipos de tokens que se obtuvieron
     * @param posiciones Las posiciones de los tokens
     */
    public void agregarTokens(ArrayList<String> palabras, ArrayList<Token> tokens, int[][] posiciones) {
        for (int i = 0; i < palabras.size(); i++) {
            AreaReporte.append("Token: " + tokens.get(i) + " | Lexema: " + palabras.get(i) + " | Fila: " + posiciones[i][0] + " | Columna: " + posiciones[i][1]);
            AreaReporte.append("\n");
        }

    }

    /**
     * Realiza el conteo de lexemas encontrados en el analisis
     *
     * @param palabras Las palabras del analisis
     */
    public void contarLexemas(ArrayList<String> palabras) {
        ArrayList<String> palabras_diferentes = new ArrayList<>();
        for (int i = 0; i < palabras.size(); i++) {
            boolean repetido = false;
            for (int j = i - 1; j >= 0; j--) {
                if (palabras.get(i).equals(palabras.get(j))) {
                    repetido = true;
                }
            }
            if (repetido == false) {
                palabras_diferentes.add(palabras.get(i));
            }
        }
        int[] cantidades = new int[palabras_diferentes.size()];
        for (int i = 0; i < palabras_diferentes.size(); i++) {
            for (int j = 0; j < palabras.size(); j++) {
                if (palabras_diferentes.get(i).equals(palabras.get(j))) {
                    cantidades[i]++;
                }
            }
            AreaRecuentoLexemas.append(palabras_diferentes.get(i) + ": " + cantidades[i]);
            AreaRecuentoLexemas.append("\n");
        }
    }

    /**
     * Cuenta todos los tokens encontrados durante el analisis
     *
     * @param tokens Los tokens encontrados
     */
    public void contarTokens(ArrayList<Token> tokens) {
        int identificador = 0;
        int numero = 0;
        int decimal = 0;
        int operador = 0;
        int signo = 0;
        int comentario = 0;
        int agrupacion = 0;
        int literal = 0;
        int reservada = 0;
        int escribir = 0;
        int fin = 0;
        int repetir = 0;
        int iniciar = 0;
        int si = 0;
        int verdadero = 0;
        int falso = 0;
        int entonces = 0;
        
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i) == Token.IDENTIFICADOR) {
                identificador++;
            }
            if (tokens.get(i) == Token.NUMERO) {
                numero++;
            }
            if (tokens.get(i) == Token.DECIMAL) {
                decimal++;
            }
            if (tokens.get(i) == Token.SIGNO) {
                signo++;
            }
            if (tokens.get(i) == Token.OPERADOR) {
                operador++;
            }
            if (tokens.get(i) == Token.AGRUPACION) {
                agrupacion++;
            }
            if (tokens.get(i) == Token.COMENTARIO) {
                comentario++;
            }
            if (tokens.get(i) == Token.LITERAL) {
                literal++;
            }
            if (tokens.get(i) == Token.ESCRIBIR) {
                escribir++;
            }
            if (tokens.get(i) == Token.FIN) {
                fin++;
            }
            if (tokens.get(i) == Token.REPETIR) {
                repetir++;
            }
            if (tokens.get(i) == Token.INICIAR) {
                iniciar++;
            }
            if (tokens.get(i) == Token.SI) {
                si++;
            }
            if (tokens.get(i) == Token.VERDADERO) {
                verdadero++;
            }
            if (tokens.get(i) == Token.FALSO) {
                falso++;
            }
            if (tokens.get(i) == Token.ENTONCES) {
                entonces++;
            }
        }

        AreaRecuentoTokens.append("Cantidad de lexemas:");
        AreaRecuentoTokens.append("\n");
        AreaRecuentoTokens.append("Identificador: " + identificador);
        AreaRecuentoTokens.append("\n");
        AreaRecuentoTokens.append("Numero: " + numero);
        AreaRecuentoTokens.append("\n");
        AreaRecuentoTokens.append("Decimal: " + decimal);
        AreaRecuentoTokens.append("\n");
        AreaRecuentoTokens.append("Signo: " + signo);
        AreaRecuentoTokens.append("\n");
        AreaRecuentoTokens.append("Operador: " + operador);
        AreaRecuentoTokens.append("\n");
        AreaRecuentoTokens.append("Agrupacion: " + agrupacion);
        AreaRecuentoTokens.append("\n");
        AreaRecuentoTokens.append("Comentario: " + comentario);
        AreaRecuentoTokens.append("\n");
        AreaRecuentoTokens.append("Literal: " + literal);
        AreaRecuentoTokens.append("\n");
        AreaRecuentoTokens.append("Escribir: " + escribir);
        AreaRecuentoTokens.append("\n");
        AreaRecuentoTokens.append("Fin: " + fin);
        AreaRecuentoTokens.append("\n");
        AreaRecuentoTokens.append("Repetir: " + repetir);
        AreaRecuentoTokens.append("\n");
        AreaRecuentoTokens.append("Iniciar: " + iniciar);
        AreaRecuentoTokens.append("\n");
        AreaRecuentoTokens.append("Si: " + si);
        AreaRecuentoTokens.append("\n");
        AreaRecuentoTokens.append("Verdadero: " + verdadero);
        AreaRecuentoTokens.append("\n");
        AreaRecuentoTokens.append("Falso: " + falso);
        AreaRecuentoTokens.append("\n");
        AreaRecuentoTokens.append("Entonces: " + entonces);
        AreaRecuentoTokens.append("\n");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        AreaReporte = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        AreaRecuentoTokens = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        AreaRecuentoLexemas = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte");

        jLabel1.setText("Reportes");

        AreaReporte.setColumns(20);
        AreaReporte.setRows(5);
        AreaReporte.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        AreaReporte.setEnabled(false);
        jScrollPane1.setViewportView(AreaReporte);

        AreaRecuentoTokens.setColumns(20);
        AreaRecuentoTokens.setRows(5);
        AreaRecuentoTokens.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        AreaRecuentoTokens.setEnabled(false);
        jScrollPane2.setViewportView(AreaRecuentoTokens);

        jLabel2.setText("Reporte de Tokens");

        jLabel3.setText("Recuento de Tokens");

        jLabel4.setText("Recuento de Lexemas");

        AreaRecuentoLexemas.setColumns(20);
        AreaRecuentoLexemas.setRows(5);
        AreaRecuentoLexemas.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        AreaRecuentoLexemas.setEnabled(false);
        jScrollPane3.setViewportView(AreaRecuentoLexemas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(354, 354, 354)
                .addComponent(jLabel1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(135, 135, 135))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AreaRecuentoLexemas;
    private javax.swing.JTextArea AreaRecuentoTokens;
    private javax.swing.JTextArea AreaReporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
