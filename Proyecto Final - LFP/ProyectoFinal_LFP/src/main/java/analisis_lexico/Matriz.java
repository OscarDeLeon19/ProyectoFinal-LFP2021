package analisis_lexico;

public class Matriz {

    private int matriz[][] = new int[11][12];
    private int estado_actual;

    public Matriz() {
        estado_actual = 0;
        definirMatriz();
    }
    /**
     * Devuelve el estado actual
     * @return El estad actual
     */
    public int getEstado_actual() {
        return estado_actual;
    }
    /**
     * Asigna un nuevo estado
     * @param estado_actual 
     */
    public void setEstado_actual(int estado_actual) {
        this.estado_actual = estado_actual;
    }
    /**
     * Define la matriz del automata finito
     */
    public void definirMatriz() {

        matriz[0][0] = 1;
        matriz[1][0] = 3;
        matriz[2][0] = 1;
        matriz[3][0] = 2;
        matriz[4][0] = 3;
        matriz[5][0] = 6;
        matriz[6][0] = 6;
        matriz[7][0] = 7;
        matriz[8][0] = 7;
        matriz[9][0] = 9;
        matriz[10][0] = 10;

        matriz[0][1] = 1;
        matriz[1][1] = 1;
        matriz[2][1] = 1;
        matriz[3][1] = -1;
        matriz[4][1] = -1;
        matriz[5][1] = -1;
        matriz[6][1] = -1;
        matriz[7][1] = -1;
        matriz[8][1] = -1;
        matriz[9][1] = -1;
        matriz[10][1] = -1;

        matriz[0][2] = -1;
        matriz[1][2] = 3;
        matriz[2][2] = -1;
        matriz[3][2] = -1;
        matriz[4][2] = -1;
        matriz[5][2] = -1;
        matriz[6][2] = -1;
        matriz[7][2] = -1;
        matriz[8][2] = -1;
        matriz[9][2] = -1;
        matriz[10][2] = -1;

        matriz[0][3] = -1;
        matriz[1][3] = 3;
        matriz[2][3] = -1;
        matriz[3][3] = -1;
        matriz[4][3] = 3;
        matriz[5][3] = 4;
        matriz[6][3] = -1;
        matriz[7][3] = -1;
        matriz[8][3] = -1;
        matriz[9][3] = -1;
        matriz[10][3] = -1;

        matriz[0][4] = -1;
        matriz[1][4] = 5;
        matriz[2][4] = -1;
        matriz[3][4] = -1;
        matriz[4][4] = 5;
        matriz[5][4] = -1;
        matriz[6][4] = -1;
        matriz[7][4] = -1;
        matriz[8][4] = -1;
        matriz[9][4] = -1;
        matriz[10][4] = -1;

        matriz[0][5] = -1;
        matriz[1][5] = 5;
        matriz[2][5] = -1;
        matriz[3][5] = -1;
        matriz[4][5] = -1;
        matriz[5][5] = -1;
        matriz[6][5] = -1;
        matriz[7][5] = -1;
        matriz[8][5] = -1;
        matriz[9][5] = -1;
        matriz[10][5] = -1;

        matriz[0][6] = -1;
        matriz[1][6] = -1;
        matriz[2][6] = -1;
        matriz[3][6] = -1;
        matriz[4][6] = -1;
        matriz[5][6] = -1;
        matriz[6][6] = -1;
        matriz[7][6] = -1;
        matriz[8][6] = -1;
        matriz[9][6] = -1;
        matriz[10][6] = -1;

        matriz[0][7] = -1;
        matriz[1][7] = -1;
        matriz[2][7] = -1;
        matriz[3][7] = -1;
        matriz[4][7] = -1;
        matriz[5][7] = -1;
        matriz[6][7] = -1;
        matriz[7][7] = 8;
        matriz[8][7] = -1;
        matriz[9][7] = -1;
        matriz[10][7] = -1;

        matriz[0][8] = 8;
        matriz[1][8] = 8;
        matriz[2][8] = 8;
        matriz[3][8] = 8;
        matriz[4][8] = 8;
        matriz[5][8] = 8;
        matriz[6][8] = 8;
        matriz[7][8] = 8;
        matriz[8][8] = 8;
        matriz[9][8] = 8;
        matriz[10][8] = -1;

        matriz[0][9] = -1;
        matriz[1][9] = -1;
        matriz[2][9] = -1;
        matriz[3][9] = -1;
        matriz[4][9] = -1;
        matriz[5][9] = -1;
        matriz[6][9] = -1;
        matriz[7][9] = -1;
        matriz[8][9] = -1;
        matriz[9][9] = -1;
        matriz[10][9] = -1;

        matriz[0][10] = 10;
        matriz[1][10] = 10;
        matriz[2][10] = 10;
        matriz[3][10] = 10;
        matriz[4][10] = 10;
        matriz[5][10] = 10;
        matriz[6][10] = 10;
        matriz[7][10] = 10;
        matriz[8][10] = 10;
        matriz[9][10] = 10;
        matriz[10][10] = 11;

        matriz[0][11] = -1;
        matriz[1][11] = -1;
        matriz[2][11] = -1;
        matriz[3][11] = -1;
        matriz[4][11] = -1;
        matriz[5][11] = -1;
        matriz[6][11] = -1;
        matriz[7][11] = -1;
        matriz[8][11] = -1;
        matriz[9][11] = -1;
        matriz[10][11] = -1;
    }
    /**
     * Realiza un movimiento en el automata
     * @param caracter El numero del caracter
     */
    public void movimiento(int caracter) {
        if (caracter == -1) {
            estado_actual = -1;
        } else {
            if (estado_actual >= 0) {
                int estado_nuevo = matriz[caracter][estado_actual];
                estado_actual = estado_nuevo;
            }
        }
    }
    /**
     * Vuelve el estado actual a 0
     */
    public void reiniciarAutomata() {
        estado_actual = 0;
    }

}
