
package main;

import analisis_lexico.Automata;
import analisis_lexico.Resaltador;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class Principal extends javax.swing.JFrame {

    Automata automata = new Automata();
    Highlighter.HighlightPainter resaltador = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
    Resaltador resalt = new Resaltador(Color.YELLOW, resaltador);
    Guardar guardar = new Guardar();
    File fichero;

    /**
     * Constructor de la clase pricipal Incluye el evente CaretListener, que
     * contiene el evento para obtener la fila y columna del indicador
     */
    public Principal() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);

        ItemCortar.addActionListener(new DefaultEditorKit.CutAction());
        ItemCopiar.addActionListener(new DefaultEditorKit.CopyAction());
        ItemPegar.addActionListener(new DefaultEditorKit.PasteAction());
        
        AreaTexto.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                int pos = e.getDot();
                try {
                    int row = AreaTexto.getLineOfOffset(pos) + 1;
                    int col = pos - AreaTexto.getLineStartOffset(row - 1) + 1;
                    LabelPos.setText("Fila: " + row + " Columna: " + col);
                } catch (BadLocationException exc) {
                    System.out.println(exc);
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        AreaTexto = new javax.swing.JTextArea();
        BotonAnalizar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        AreaMovimientos = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        LabelPos = new javax.swing.JLabel();
        BotonBuscar = new javax.swing.JButton();
        TextoBusqueda = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuArchivo = new javax.swing.JMenu();
        ItemNuevo = new javax.swing.JMenuItem();
        ItemAbrir = new javax.swing.JMenuItem();
        ItemGuardar = new javax.swing.JMenuItem();
        ItemGuardarComo = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        ItemCortar = new javax.swing.JMenuItem();
        ItemCopiar = new javax.swing.JMenuItem();
        ItemPegar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        ItemAcercaDe = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Analizador");

        AreaTexto.setColumns(20);
        AreaTexto.setRows(5);
        jScrollPane1.setViewportView(AreaTexto);

        BotonAnalizar.setText("Analizar");
        BotonAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAnalizarActionPerformed(evt);
            }
        });

        AreaMovimientos.setColumns(20);
        AreaMovimientos.setRows(5);
        AreaMovimientos.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        AreaMovimientos.setEnabled(false);
        jScrollPane2.setViewportView(AreaMovimientos);

        jLabel1.setText("Movimientos del automata de pila");

        LabelPos.setText("Fila: Columna: ");

        BotonBuscar.setText("Buscar");
        BotonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBuscarActionPerformed(evt);
            }
        });

        MenuArchivo.setText("Archivo");

        ItemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ItemNuevo.setText("Nuevo");
        ItemNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemNuevoActionPerformed(evt);
            }
        });
        MenuArchivo.add(ItemNuevo);

        ItemAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ItemAbrir.setText("Abrir");
        ItemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemAbrirActionPerformed(evt);
            }
        });
        MenuArchivo.add(ItemAbrir);

        ItemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ItemGuardar.setText("Guardar");
        ItemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemGuardarActionPerformed(evt);
            }
        });
        MenuArchivo.add(ItemGuardar);

        ItemGuardarComo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ItemGuardarComo.setText("Guardar Como");
        ItemGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemGuardarComoActionPerformed(evt);
            }
        });
        MenuArchivo.add(ItemGuardarComo);

        jMenuBar1.add(MenuArchivo);

        jMenu1.setText("Editar");

        ItemCortar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ItemCortar.setText("Cortar");
        jMenu1.add(ItemCortar);

        ItemCopiar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ItemCopiar.setText("Copiar");
        jMenu1.add(ItemCopiar);

        ItemPegar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ItemPegar.setText("Pegar");
        jMenu1.add(ItemPegar);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Acerca de");

        ItemAcercaDe.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ItemAcercaDe.setText("Acerca de");
        ItemAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemAcercaDeActionPerformed(evt);
            }
        });
        jMenu2.add(ItemAcercaDe);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(LabelPos))
                        .addGap(0, 670, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BotonAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotonBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TextoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonAnalizar)
                    .addComponent(BotonBuscar)
                    .addComponent(TextoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelPos)
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metodo para abrir un archivo de texto y agregarlo al JtextArea del
     * programa
     *
     * @param evt
     */
    private void ItemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemAbrirActionPerformed
        AreaTexto.setText(null);
        File file = null;
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo de Texto", "txt");
        JFileChooser filechooser = new JFileChooser();
        filechooser.setAcceptAllFileFilterUsed(false);
        filechooser.addChoosableFileFilter(filtro);
        filechooser.showOpenDialog(null);
        file = filechooser.getSelectedFile();
        try {
            FileReader lector = new FileReader(file);
            BufferedReader tuberia = new BufferedReader(lector);
            String linea = "";

            while ((linea = tuberia.readLine()) != null) {
                AreaTexto.append(linea);
                AreaTexto.append("\n");
            }
        } catch (IOException e) {
            AreaTexto.setText("Error en el archivo");
        }
    }//GEN-LAST:event_ItemAbrirActionPerformed
    /**
     * Metodo para crear un nuevo archivo de texto en el programa
     *
     * @param evt
     */
    private void ItemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemNuevoActionPerformed
        AreaTexto.setText(null);
        AreaMovimientos.setText(null);
    }//GEN-LAST:event_ItemNuevoActionPerformed
    /**
     * Metodo para exportar el texto del JtextArea a un archivo de textó
     *
     * @param evt
     */
    private void ItemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemGuardarActionPerformed
        guardar.guardar(AreaTexto.getText());
    }//GEN-LAST:event_ItemGuardarActionPerformed
    /**
     * Boton para obenter el texto del JtextArea y analisar los tokens que
     * contengan
     *
     * @param evt
     */
    private void BotonAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAnalizarActionPerformed
        AreaMovimientos.setText(null);
        String texto = AreaTexto.getText();
        automata.analizar(texto, AreaMovimientos);

    }//GEN-LAST:event_BotonAnalizarActionPerformed
    /**
     * Metodo para abrir el objeto Highlighter y poder subrayar las palabras
     * buscadas
     *
     * @param evt
     */
    private void BotonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBuscarActionPerformed
        if (TextoBusqueda.getText().equals("")) {
            resalt.removerSubrayado(AreaTexto);
        } else {
            resalt.resaltar(AreaTexto, TextoBusqueda.getText());
        }
    }//GEN-LAST:event_BotonBuscarActionPerformed

    private void ItemGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemGuardarComoActionPerformed
        guardar.guardarComo(AreaTexto.getText());
    }//GEN-LAST:event_ItemGuardarComoActionPerformed

    /**
     * Abre una ventana de informacion del estudiante que desarrollo la aplicacion
     * @param evt 
     */
    private void ItemAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemAcercaDeActionPerformed
        Informacion informacion = new Informacion();
    }//GEN-LAST:event_ItemAcercaDeActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AreaMovimientos;
    private javax.swing.JTextArea AreaTexto;
    private javax.swing.JButton BotonAnalizar;
    private javax.swing.JButton BotonBuscar;
    private javax.swing.JMenuItem ItemAbrir;
    private javax.swing.JMenuItem ItemAcercaDe;
    private javax.swing.JMenuItem ItemCopiar;
    private javax.swing.JMenuItem ItemCortar;
    private javax.swing.JMenuItem ItemGuardar;
    private javax.swing.JMenuItem ItemGuardarComo;
    private javax.swing.JMenuItem ItemNuevo;
    private javax.swing.JMenuItem ItemPegar;
    private javax.swing.JLabel LabelPos;
    private javax.swing.JMenu MenuArchivo;
    private javax.swing.JTextField TextoBusqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
