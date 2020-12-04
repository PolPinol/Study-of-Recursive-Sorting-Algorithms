import java.io.*;
import java.lang.*;

/**
 * Classe utilitzada per a llegir els fitxers JSON
 */
public class Json {

    private String text;

    public Json(){
    }

    /**
     * Procediment el qual importa el text del fitxer JSON
     * @param nombreArchivo: Nom el fitxer JSON que es desitja importar
     */
    public void importarText(String nombreArchivo) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(nombreArchivo);
            bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder("");
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                stringBuilder.append(linea);
            }

            this.text = stringBuilder.toString();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fileReader != null)
                    fileReader.close();
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getText() {
        return text;
    }

}
