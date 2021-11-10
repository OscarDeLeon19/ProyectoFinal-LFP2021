
package analisis_lexico;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

public class Resaltador extends DefaultHighlighter.DefaultHighlightPainter {
    
    Highlighter.HighlightPainter resaltador;

        public Resaltador(Color c, Highlighter.HighlightPainter resaltador) {
            super(c);
            this.resaltador = resaltador;
        }
        /**
         * Quita los subrayados del area de texto
         * @param AreaTexto El area de texto de donde se quitaran los subrayados
         */
        public void removerSubrayado(JTextArea AreaTexto) {
            Highlighter higlighter = AreaTexto.getHighlighter();
            Highlighter.Highlight[] subrayados = higlighter.getHighlights();

            for (int i = 0; i < subrayados.length; i++) {
                    higlighter.removeHighlight(subrayados[i]);                
            }
        }
        /**
         * Agrega un subrayado al area de texto donde coincida con la palabra buscada
         * @param AreaTexto
         * @param texto 
         */
        public void resaltar(JTextArea AreaTexto, String texto) {

            removerSubrayado(AreaTexto);
            try {
                Highlighter higlighter = AreaTexto.getHighlighter();
                Document doc = AreaTexto.getDocument();
                String text = doc.getText(0, doc.getLength());
                int pos = 0;

                while ((pos = text.toUpperCase().indexOf(texto.toUpperCase(), pos)) >= 0) {
                    higlighter.addHighlight(pos, pos + texto.length(), resaltador);
                    pos += texto.length();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    
}
