import java.util.Scanner;

/**
 * Classe on mostrem el menú d'interacció amb l'usuari i fem les crides corresponents
 * segons les seves seleccions.
 */
public class Menu {
    //Constructor de la classe
    public Menu(){

    }

    /**
     * Procediment que mostra els fitxers disponibles i les opcions del programa un cop
     * s'ha escollit el fitxer i fa les crides corresponents segons la opció de l'usuari
     */
    public void executarPrograma(){
        Programa programa = new Programa();
        String file;
        String opcio;

        escriureFitxersDisponibles();

        file = demanaOpcio();

        escriureOpcionsPrograma();

        opcio = demanaOpcio();

        switch (opcio) {
            case "1" -> programa.observarLlistesOrdenades(file);
            case "2" -> programa.executarProves(file);
            case "3" -> programa.observarTempsExecucio(file);
            default -> escriureMissatgeErrorInput();
        }
    }

    /**
     * Procediment que mostra els fitxers de datasets amb els que es pot treballar
     */
    private void escriureFitxersDisponibles(){
        System.out.println("\nFitxers disponibles: dataseetXS.json, " +
                "dataseetS.json, dataseetM.json, dataseetL.json, dataseetXL.json, dataseetXXL.json");
        System.out.print("Nom del fitxer: ");
    }

    /**
     * Procediment que mostra les diferents opcions que ofereix el programa
     */
    private void escriureOpcionsPrograma(){
        System.out.println("\nOpcio 1: Observar Llistes Ordenades");
        System.out.println("Opcio 2: Provar Algorismes");
        System.out.println("Opcio 3: Observar Temps Execucio\n");
        System.out.print("Opcio: ");
    }

    /**
     * Procediment que mostra missetge d'input erroni
     */
    private void escriureMissatgeErrorInput() {
        System.out.println("\nInput Incorrecte!");
    }

    /**
     * Procediment que demana la opció escollida a l'usuari
     * @return opcio: Opcio escollida per l'usuari
     */
    private String demanaOpcio(){
        String opcio;
        Scanner scanner = new Scanner(System.in);
        opcio = scanner.nextLine();
        return opcio;
    }
}
