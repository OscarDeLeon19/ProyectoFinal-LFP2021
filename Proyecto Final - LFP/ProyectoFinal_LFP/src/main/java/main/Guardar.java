
package main;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Guardar {

    File fichero;
    
    public Guardar() {
    }
    /**
     * Guarda el contenido del JtextArea en un archivo de texto, si ya hay uno guardado, no es necesario crear un nuevo archivo de texto.
     * @param texto El texto del JTextArea
     */
    public void guardar(String texto){
        try {
            FileWriter escribir;
            PrintWriter linea;
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo de Texto", "txt");

            JFileChooser seleccionar = new JFileChooser();
            seleccionar.setAcceptAllFileFilterUsed(false);
            seleccionar.addChoosableFileFilter(filtro);
            if (fichero == null) {
                int Guardar = seleccionar.showDialog(null, "Guardar");
                if (Guardar == JFileChooser.APPROVE_OPTION) {
                    fichero = seleccionar.getSelectedFile();
                    if (fichero.getName().endsWith(".txt")) {
                        if (fichero.exists()) {
                            int opcion = JOptionPane.showConfirmDialog(null, "Deseas sobrescribir");
                            if (opcion == JOptionPane.YES_OPTION) {
                                fichero.createNewFile();
                                escribir = new FileWriter(fichero, false);
                                escribir.write(texto);
                                escribir.close();
                                JOptionPane.showMessageDialog(null, "El archivo se guardo");
                            } else {
                                JOptionPane.showMessageDialog(null, "No se guardo el archivo");
                            }
                        } else {
                            fichero.createNewFile();
                            escribir = new FileWriter(fichero, false);
                            escribir.write(texto);
                            escribir.close();
                            JOptionPane.showMessageDialog(null, "El archivo se guardo");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No es un archivo .txt");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar");
                }
            } else {
                fichero.createNewFile();
                escribir = new FileWriter(fichero, false);
                escribir.write(texto);
                escribir.close();
                JOptionPane.showMessageDialog(null, "El archivo se guardo");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
    }
    /**
     * Guarda un nuevo archivo de texto pudiento ingresar el nombre nuevo
     * @param texto El texto del JTextArea
     */
    public void guardarComo(String texto){
        try {
            FileWriter escribir;
            PrintWriter linea;
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo de Texto", "txt");

            JFileChooser seleccionar = new JFileChooser();
            seleccionar.setAcceptAllFileFilterUsed(false);
            seleccionar.addChoosableFileFilter(filtro);
            int Guardar = seleccionar.showDialog(null, "Guardar");
            if (Guardar == JFileChooser.APPROVE_OPTION) {
                fichero = seleccionar.getSelectedFile();
                if (fichero.getName().endsWith(".txt")) {
                    if (fichero.exists()) {
                        int opcion = JOptionPane.showConfirmDialog(null, "Deseas sobrescribir");
                        if (opcion == JOptionPane.YES_OPTION) {
                            fichero.createNewFile();
                            escribir = new FileWriter(fichero, false);
                            escribir.write(texto);
                            escribir.close();
                            JOptionPane.showMessageDialog(null, "El archivo se guardo");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se guardo el archivo");
                        }
                    } else {
                        fichero.createNewFile();
                        escribir = new FileWriter(fichero, false);
                        escribir.write(texto);
                        escribir.close();
                        JOptionPane.showMessageDialog(null, "El archivo se guardo");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No es un archivo .txt");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
    }
    
    /**
     * Exporta el texto de salida de la aplicacion en un arhivo de texto
     * @param texto El texto de salida
     */
    public void exportarDocumento(String texto){
        try {
            File nuevo_fichero;
            FileWriter escribir;
            PrintWriter linea;
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo de Texto", "txt");
            JFileChooser seleccionar = new JFileChooser();
            seleccionar.setAcceptAllFileFilterUsed(false);
            seleccionar.addChoosableFileFilter(filtro);
            int Guardar = seleccionar.showDialog(null, "Guardar");
            if (Guardar == JFileChooser.APPROVE_OPTION) {
                nuevo_fichero = seleccionar.getSelectedFile();
                if (nuevo_fichero.getName().endsWith(".txt")) {
                    if (nuevo_fichero.exists()) {
                        int opcion = JOptionPane.showConfirmDialog(null, "Deseas sobrescribir");
                        if (opcion == JOptionPane.YES_OPTION) {
                            nuevo_fichero.createNewFile();
                            escribir = new FileWriter(nuevo_fichero, false);
                            escribir.write(texto);
                            escribir.close();
                            JOptionPane.showMessageDialog(null, "El archivo se guardo");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se guardo el archivo");
                        }
                    } else {
                        nuevo_fichero.createNewFile();
                        escribir = new FileWriter(nuevo_fichero, false);
                        escribir.write(texto);
                        escribir.close();
                        JOptionPane.showMessageDialog(null, "El archivo se guardo");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No es un archivo .txt");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
    }
    
    
}
