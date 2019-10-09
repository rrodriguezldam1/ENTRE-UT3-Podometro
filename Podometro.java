/**
 * La clase modela un sencillo podómetro que registra información
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @author    - Ramón Rodríguez Lucas - 
 * 
 */
public class Podometro {
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    private String marca;
    private double altura;
    private char sexo;
    private double longitudZancada;
    private int totalPasosLaborables;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private int tiempo;
    private int caminatasNoche;

    /**
     * Inicializa el podómetro con la marca indicada por el parámetro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        sexo = MUJER;
    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = queAltura;
        sexo = queSexo;
        if (sexo == HOMBRE){
            longitudZancada = Math.ceil(altura * ZANCADA_HOMBRE);
        }
        else {
            longitudZancada = Math.floor(altura * ZANCADA_MUJER);
        }
    }

    /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    pasos - el nº de pasos caminados
     *    dia - nº de día de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *    horaInicio – hora de inicio de la caminata
     *    horaFina – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el podómetro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
    int horaFin) {
        String diaSemana = "";
        double distancia = pasos * longitudZancada;
        tiempo += ((horaFin / 100) * 60 + horaFin % 100) - ((horaInicio / 100) * 60 + horaInicio % 100);
        totalDistanciaSemana += distancia;
        switch (dia){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: diaSemana = "Laborable";
            break;
            case 6: diaSemana = "Sábado";
            break;
            case 7: diaSemana = "Domingo";
        }
        if (diaSemana == "Laborable"){
            totalPasosLaborables += pasos;
        }
        else if (diaSemana == "Sábado") {
            totalPasosSabado += pasos;
            totalDistanciaFinSemana += distancia;
        }
        else {
            totalPasosDomingo += pasos;
            totalDistanciaFinSemana += distancia;
        }
        if (horaInicio >= 2100){
            caminatasNoche++;
        }
    }

    /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        String sex = "";
        if (sexo == HOMBRE){
            sex = "HOMBRE";
        }
        else {
            sex = "MUJER";
        }
        System.out.println("Configuración del podómetro" +
            "\n*****************************" +
            "\nAltura: " + (altura / 100) + " mtos" +
            "\nSexo: " + sex +
            "\nLongitud zancada: " + (longitudZancada / 100) + " mtos");
    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        int horas = tiempo / 60;
        int minutos = tiempo % 60;
        System.out.println("\nEstadísticas" +
            "\n*****************************" +
            "\nDistancia recorrida toda la semana: " + (totalDistanciaSemana / 100000) + " Km" +
            "\nDistancia recorrida fin de semana: " + (totalDistanciaFinSemana / 100000) + " Km" +
            "\n" +
            "\nNº pasos días laborables: " + totalPasosLaborables +
            "\nNº pasos SÁBADO: " + totalPasosSabado +
            "\nNº pasos DOMINGO: " + totalPasosDomingo +
            "\n" +
            "\nNº caminatas realizadas a partir de las 21h.: " + caminatasNoche +
            "\n" +
            "\nTiempo total caminado en la semana: " + horas + "h. y " + minutos + "m.");
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        String diaMasPasos = "";
        if (totalPasosLaborables > totalPasosSabado && totalPasosLaborables > totalPasosDomingo) {
            diaMasPasos = "LABORABLES";
        }
        if (totalPasosSabado > totalPasosLaborables && totalPasosSabado > totalPasosDomingo) {
            diaMasPasos = "SÁBADO";
        }
        if (totalPasosDomingo > totalPasosLaborables && totalPasosDomingo > totalPasosSabado ) {
            diaMasPasos = "DOMINGO";
        }
        return diaMasPasos;
    }

    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no varía
     *  
     */    
    public void reset() {
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }
}
