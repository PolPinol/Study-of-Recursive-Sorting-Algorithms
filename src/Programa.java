import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Classe on guardem tots els algorismes d'ordenació
 */
public class Programa {

    private Json json;
    private ArrayList<Club> clubs;
    private ArrayList<Atleta> llistaAtletes;
    private ArrayList<Club> clubs2;
    private ArrayList<Atleta> llistaAtletes2;

    //Constructor de la classe
    public Programa(){
        this.json = new Json();
        this.clubs = new ArrayList<>();
        this.llistaAtletes = new ArrayList<>();
        this.clubs2 = new ArrayList<>();
        this.llistaAtletes2 = new ArrayList<>();
    }

    /**
     * Procediment que inicialitza la estructura interna llegint el fitxer JSON escollit i
     * creant totes les classes on es guarden les dades
     * @param file: fitxer JSON el qual llegirem i guardarem la seva informació
     */
    private void inicialitzar(String file){
        llegirJson(file);
        crearLlistaAtletes();
        inicialitzarEstatFisicAtletes();
        inicialitzarMitjanaEdatsClubs();
        crearCopiaLlistaAtletes();
        crearCopiaClubs();
    }

    /**
     * Procediment que llegeix el JSON escollit
     * @param file: JSON escollit per a ser llegit
     */
    private void llegirJson(String file){
        json.importarText(file);
        Gson gson = new Gson();
        this.clubs = gson.fromJson(this.json.getText(), new TypeToken<ArrayList<Club>>(){}.getType());
    }

    /**
     * Procediment que crea una llista de tots els atletes de tots els clubs
     */
    private void crearLlistaAtletes(){
        for (int i = 0; i < this.clubs.size(); i++) {
            this.llistaAtletes.addAll(this.clubs.get(i).getAtletes());
        }
    }

    /**
     * Procediment que fa la crida interna de la classe 'Club' per a que calculin
     * les mitjanes d'edat dels seus atletes
     */
    private void inicialitzarMitjanaEdatsClubs(){
        for (int i = 0; i < this.clubs.size(); i++) {
            this.clubs.get(i).setMitjaEdats();
        }
    }

    /**
     * Procediment que fa la crida interna de la classe 'Atleta' de que calculi
     * l'estat físic dels atletes mitjançant la equació estatFisic = distancia^3/temps
     */
    private void inicialitzarEstatFisicAtletes(){
        for (int i = 0; i < llistaAtletes.size(); i++) {
            this.llistaAtletes.get(i).setEstatFisic();
        }
    }

    /**
     * Procediment que crea una segona llista d'atletes
     */
    private void crearCopiaLlistaAtletes(){
        for (int i = 0; i < this.llistaAtletes.size(); i++) {
            this.llistaAtletes2.add(this.llistaAtletes.get(i).copy());
        }
    }

    /**
     * Procediment que crea una segona llista de clubs
     */
    private void crearCopiaClubs(){
        for (int i = 0; i < this.clubs.size(); i++) {
            this.clubs2.add(this.clubs.get(i).copy());
        }
    }

    /**
     * Procediment que fa les crides per a realitzar les proves que observen el correcte
     * funcionament dels algorismes d'ordenacio
     * @param file: Fitxer JSON amb el qual treballarem
     */
    public void executarProves(String file){
        inicialitzar(file);

        provaAlgorismeBucketSort();
        provaAlgorismeMergeSort();
        provaAlgorismeQuickSort();
    }

    /**
     * Procediment que fa les crides per a realitzar les proves del Bucket Sort
     */
    private void provaAlgorismeBucketSort(){
        System.out.print("Prova del Algorisme BucketSort...");

        ordenarClubsPerMitjaEdatsSort();
        ordenarClubsPerMitjaEdatsBucket();

        for (int i = 0; i < this.clubs2.size(); i++) {
            assert (this.clubs.get(i).equals(this.clubs2.get(i))): "Error en Algorisme BucketSort";
        }

        System.out.println("Correcte!");
    }

    /**
     * Procediment que realitza la ordenació per mitjana d'edat amb la crida sort()
     */
    private void ordenarClubsPerMitjaEdatsSort(){
        this.clubs2.sort(new Comparator<Club>() {
            public int compare(Club c1, Club c2) {
                float mitjaEdatsClub1 = Float.parseFloat(String.valueOf(c1.getMitjaEdats()));
                float mitjaEdatsClub2 = Float.parseFloat(String.valueOf(c2.getMitjaEdats()));
                return Float.compare(mitjaEdatsClub1, mitjaEdatsClub2);
            }
        });
    }

    /**
     * Procediment que construeix la llista ordenada per l'algorisme Bucket Sort
     */
    private void ordenarClubsPerMitjaEdatsBucket(){
        this.clubs = bucketSort(this.clubs, 0);
    }

    /**
     * Procediment que fa les crides per a realitzar les proves del Merge Sort
     */
    private void provaAlgorismeMergeSort() {
        System.out.print("Prova del Algorisme MergeSort...");

        ordenarLlistaAtletesPerNacionalitatSort();
        ordenarLlistaAtletesPerNacionalitatMerge();

        for (int i = 0; i < this.llistaAtletes.size(); i++) {
            assert (this.llistaAtletes.get(i).equals(this.llistaAtletes2.get(i))): "Error en Algorisme MergeSort";
        }
        System.out.println("Correcte!");
    }

    /**
     * Procediment que construeix la llista ordenada segons la nacionalitat mitjançant el
     * procediment sort()
     */
    private void ordenarLlistaAtletesPerNacionalitatSort(){
        this.llistaAtletes2.sort(new Comparator<Atleta>() {
            public int compare(Atleta a1, Atleta a2) {
                if (a1.getNacionalitat().compareTo(a2.getNacionalitat()) < 0) {
                    return -1;
                }
                if (a1.getNacionalitat().compareTo(a2.getNacionalitat()) > 0) {
                    return 1;
                }
                if ((a1.getNacionalitat().compareTo(a2.getNacionalitat()) == 0)) {
                    if (a1.getNom().compareTo(a2.getNom()) < 0) {
                        return -1;
                    }
                    if (a1.getNom().compareTo(a2.getNom()) > 0) {
                        return 1;
                    }
                }
                return 0;
            }
        });
    }

    /**
     * Procediment que realitza la ordenació per Nacionalitat usant el Merge Sort
     */
    private void ordenarLlistaAtletesPerNacionalitatMerge(){
        this.llistaAtletes = mergeSort(this.llistaAtletes, 0, this.llistaAtletes.size()-1);
    }

    /**
     * Procediment que fa les crides per a realitzar les proves del Quick Sort
     */
    private void provaAlgorismeQuickSort(){
        System.out.print("Prova del Algorisme QuickSort...");

        ordenarLlistaAtletesPerEstatFisicQuick();
        ordenarLlistaAtletesPerEstatFisicSort();

        float estatFisicAtleta1;
        float estatFisicAtleta2;

        for (int i = 0; i < this.llistaAtletes.size(); i++) {
            estatFisicAtleta1 = this.llistaAtletes.get(i).getEstatFisic();
            estatFisicAtleta2 = this.llistaAtletes2.get(i).getEstatFisic();
            assert (estatFisicAtleta1 == estatFisicAtleta2): "Error en Algorisme QuickSort";
        }
        System.out.println("Correcte!");
    }

    /**
     * Procediment que construeix la llista ordenada segons l'estat fisic mitjançant el
     * procediment sort()
     */
    private void ordenarLlistaAtletesPerEstatFisicSort(){
        this.llistaAtletes2.sort(new Comparator<Atleta>() {
            public int compare(Atleta s1, Atleta s2) {
                if (s1.getEstatFisic() - s2.getEstatFisic() > 0) {
                    return -1;
                }
                if (s1.getEstatFisic() - s2.getEstatFisic() < 0) {
                    return 1;
                }
                return 0;
            }
        });
    }

    /**
     * Procediment que crida el procediment per a realitzar el Quick Sort de la llista
     * d'atletes segons l'estat físic
     */
    private void ordenarLlistaAtletesPerEstatFisicQuick(){
        this.llistaAtletes = quickSort(this.llistaAtletes, 0, this.llistaAtletes.size()-1);
    }

    /**
     * Procediment que crida tots els algorismes d'ordenació i els mostra per pantalla
     * a l'usuari
     * @param file: Fitxer JSON amb el qual treballarem
     */
    public void observarLlistesOrdenades(String file) {
        inicialitzar(file);

        escriureCapsalera("Bucket");
        ordenarClubsPerMitjaEdatsBucket();
        escriureClubsOrdenatsPerMitjaEdats();

        escriureCapsalera("Merge");
        ordenarLlistaAtletesPerNacionalitatMerge();
        escriureLlistaAtletesOrdenatsPerNacionalitat();

        escriureCapsalera("Quick");
        ordenarLlistaAtletesPerEstatFisicQuick();
        escriureLlistaAtletesOrdenatsPerEstatFisic();
    }

    /**
     * Procediment que mostra per pantalla la capçalera corresponent al algorisme especificat
     * @param metode: Algorisme d'ordenació seleccionat
     */
    private void escriureCapsalera(String metode){
        if (metode.equals("Bucket")) {
            System.out.println("\n\n\n----------------------------------------------------------");
            System.out.println("METODE BUCKETSORT PER ORDENAR CLUBS PER LA MITJANA D'EDAT");
            System.out.println("----------------------------------------------------------");
            System.out.println("Ordenats - Menor a Major:");
            System.out.println("----------------------------------------------------------");
        } else if (metode.equals("Merge")) {
            System.out.println("\n\n\n----------------------------------------------------------");
            System.out.println("METODE MERGESORT PER ORDENAR ATLETES PER NACIONALITAT");
            System.out.println("----------------------------------------------------------");
            System.out.println("Ordenats - de A a la Z");
            System.out.println("----------------------------------------------------------");
        } else {
            System.out.println("\n\n\n----------------------------------------------------------");
            System.out.println("METODE QUICKSORT PER ORDENAR ATLETES PER ESTAT FISIC");
            System.out.println("----------------------------------------------------------");
            System.out.println("Ordenats - de Major a Menor");
            System.out.println("----------------------------------------------------------");
        }
    }

    /**
     * Procediment per a escriure per pantalla consecutivament cada posicio de la llista ordenada
     * per la mitjana d'edat dels clubs
     */
    private void escriureClubsOrdenatsPerMitjaEdats(){
        for (int i = 0; i < this.clubs.size(); i++) {
            System.out.print("Posicio " + (i+1));
            System.out.print("\t\t" + this.clubs.get(i).getNom());
            System.out.println(" - " + this.clubs.get(i).getMitjaEdats());
        }
    }

    /**
     * Procediment per a escriure per pantalla consecutivament cada posicio de la llista ordenada
     * per la nacionalitat i/o per el nom dels atletes
     */
    private void escriureLlistaAtletesOrdenatsPerNacionalitat(){
        for (int i = 0; i < this.llistaAtletes.size(); i++) {
            System.out.print("Posicio " + (i+1));
            System.out.print("\t\t" + this.llistaAtletes.get(i).getNacionalitat());
            System.out.print(" - " + this.llistaAtletes.get(i).getNom());
            System.out.println(" "+ this.llistaAtletes.get(i).getCognom());
        }
    }

    /**
     * Procediment per a escriure per pantalla consecutivament cada posicio de la llista ordenada
     * per l'estat físic dels atletes
     */
    private void escriureLlistaAtletesOrdenatsPerEstatFisic(){
        System.out.println("                Nom - EstatFisic - Dist - Temps");
        for (int i = 0; i < this.llistaAtletes.size(); i++) {
            System.out.print("Posicio " + (i+1));
            System.out.print("\t\t" + this.llistaAtletes.get(i).getNom());
            System.out.print(" "+ this.llistaAtletes.get(i).getCognom());
            System.out.print(" - " + this.llistaAtletes.get(i).getEstatFisic());
            System.out.print(" - "+ this.llistaAtletes.get(i).getDistancia());
            System.out.println(" - "+ this.llistaAtletes.get(i).getTemps());
        }
    }

    /**
     * Procediment que fa les crides per a calcular el temps d'execució dels algorismes
     * @param file: Fitxer JSON amb el qual treballarem
     */
    public void observarTempsExecucio(String file) {
        inicialitzar(file);

        calcularTempsExecucioAlgorismeBucket();
        calcularTempsExecucioAlgorismeMerge();
        calcularTempsExecucioAlgorismeQuick();
    }

    /**
     * Procediment que calcula el temps d'execució de l'algorisme Bucket Sort
     */
    private void calcularTempsExecucioAlgorismeBucket(){
        long startTime;
        long endTime;

        startTime = System.nanoTime();
        ordenarClubsPerMitjaEdatsBucket();
        endTime = System.nanoTime() - startTime;

        System.out.println("METODE BUCKETSORT");
        System.out.println("TEMPS = " + endTime + " DADES N = " + (this.clubs.size()));
    }

    /**
     * Procediment que calcula el temps d'execució de l'algorisme Merge Sort
     */
    private void calcularTempsExecucioAlgorismeMerge(){
        long startTime;
        long endTime;

        startTime = System.nanoTime();
        ordenarLlistaAtletesPerNacionalitatMerge();
        endTime = System.nanoTime() - startTime;

        System.out.println("METODE MERGESORT");
        System.out.println("TEMPS = " + endTime + " DADES N = " + (this.llistaAtletes.size()));
    }

    /**
     * Procediment que calcula el temps d'execució de l'algorisme Quick Sort
     */
    private void calcularTempsExecucioAlgorismeQuick(){
        long startTime;
        long endTime;

        startTime = System.nanoTime();
        ordenarLlistaAtletesPerEstatFisicQuick();
        endTime = System.nanoTime() - startTime;

        System.out.println("METODE QUICKSORT");
        System.out.println("TEMPS = " + endTime + " DADES N = " + (this.llistaAtletes.size()));
    }

    /**
     * Funció que retorna un ArrayList d'atletes ordenats segons la nacionalitat i/o nom de l'atleta
     * mitjançant l'algorisme Merge Sort
     * @param a: ArrayList d'atletes sense ordenar
     * @param i: Integer amb el valor inicial de l'array
     * @param j: Integer amb el valor final de l'array
     * @return a: ArrayList d'atletes ja ordenada
     */
    public ArrayList<Atleta> mergeSort(ArrayList<Atleta> a, int i, int j){
        int mig;

        if (i < j) {
            mig = (i + j) / 2;
            a = mergeSort(a, i, mig);
            a = mergeSort(a, mig + 1, j);
            a = merge(a, i, mig, j);
        }
        return a;
    }

    /**
     * Funció que torna a juntar les cel·les de l'ArrayList un cop estan separades de forma
     * individual i llavors ordena les cel·les a mesura que les va ajuntant.
     * Retorna array ja ordenat
     * @param a: ArrayList d'atletes sense ordenar
     * @param i: Integer amb el valor inicial de l'array
     * @param mig: Integer amb el valor mig de l'array
     * @param j: Integer amb el valor final de l'array
     * @return a: ArrayList d'atletes ja ordenada
     */
    private ArrayList<Atleta> merge(ArrayList<Atleta> a, int i, int mig, int j) {
        int l;
        int r;
        int cursor;
        int min;
        int k;
        int w;
        int len1, len2, len3, len4;
        ArrayList<Atleta> b = new ArrayList<>(a);

        l = i;
        r = mig + 1;
        cursor = i;

        while ((l <= mig) && (r <= j)) {
            k = 0;
            //no pasarnos del Index del chartAt - agafem el menor
            len1 = a.get(l).getNacionalitat().length();
            len2 = a.get(r).getNacionalitat().length();
            min = Math.min(len1, len2);
            while(k != min){
                if (a.get(l).getNacionalitat().charAt(k) == a.get(r).getNacionalitat().charAt(k)){
                    k++;
                } else if (a.get(l).getNacionalitat().charAt(k) < a.get(r).getNacionalitat().charAt(k)) {
                    b.set(cursor, a.get(l));
                    l++;
                    break;
                } else {
                    b.set(cursor, a.get(r));
                    r++;
                    break;
                }
            }
            //mateixa paraula - desempatar amb nom
            if (k == min) {
                if (len1 != len2){ //paraula2 continguda en paraula1
                    if (len1 > len2){
                        b.set(cursor, a.get(r));
                        r++;
                    } else {
                        b.set(cursor, a.get(l));
                        l++;
                    }
                } else { //paraula1 = paraula2
                    w = 0;
                    //no pasarnos del Index del chartAt - agafem el menor
                    len3 = a.get(l).getNom().length();
                    len4 = a.get(r).getNom().length();
                    min = Math.min(len3, len4);
                    while(w != min){
                        if (a.get(l).getNom().charAt(w) == a.get(r).getNom().charAt(w)){
                            w++;
                        } else if (a.get(l).getNom().charAt(w) < a.get(r).getNom().charAt(w)) {
                            b.set(cursor, a.get(l));
                            l++;
                            break;
                        } else {
                            b.set(cursor, a.get(r));
                            r++;
                            break;
                        }
                    }
                    if(w == min){ //paraula2 continguda a paraula1
                        if (len3 > len4){
                            b.set(cursor, a.get(r));
                            r++;
                        } else {
                            b.set(cursor, a.get(l));
                            l++;
                        }
                    }
                }
            }
            cursor++;
        }

        while ( l <= mig ) {
            b.set(cursor, a.get(l));
            l++;
            cursor++;
        }

        while ( r <= j ) {
            b.set(cursor, a.get(r));
            r++;
            cursor++;
        }

        return b;
    }

    /**
     * Funcio que ordena una ArrayList segons l'estat físic dels atletes
     * @param a: ArrayList d'atletes sense ordenar
     * @param i: Integer amb el valor inicial de l'array
     * @param j: Integer amb el valor final de l'array
     * @return a: ArrayList d'atletes ja ordenada
     */
    public ArrayList<Atleta> quickSort(ArrayList<Atleta> a, int i, int j){
        if (i < j) {
            ClassQuick classe = quick(a, i, j);
            classe.setA(quickSort(classe.getA(), i, classe.getJ()));
            classe.setA(quickSort(classe.getA(), classe.getI(), j));
            a = classe.getA();
        }

        return a;
    }

    /**
     * Funcio que ordena les cel·les d'un array comparant-les amb els valors del pivot i col·locant-les
     * a la seva esquerra si son de menor valor i a la dreta si son de major valor
     * @param a: ArrayList d'atletes sense ordenar
     * @param i: Integer amb el valor inicial de l'array
     * @param j: Integer amb el valor final de l'array
     * @return a: ArrayList d'atletes ja ordenada
     */
    private ClassQuick quick(ArrayList<Atleta> a, int i, int j) {
        int l;
        int r;
        int mig;
        float pivot;
        Atleta tmp;

        l = i;
        r = j;
        mig = (i + j) / 2;
        pivot = a.get(mig).getEstatFisic();

        while (l <= r) {
            while (a.get(l).getEstatFisic() > pivot) {
                l++;
            }
            while (a.get(r).getEstatFisic() < pivot) {
                r--;
            }

            if (l < r) {
                tmp = a.get(l);
                a.set(l, a.get(r));
                a.set(r, tmp);
                l++;
                r--;
            } else if(l == r){
                l++;
                r--;
            }

        }

        return new ClassQuick(a, l, r);
    }

    /**
     * Funcio que rep una ArrayList de clubs i la ordena segons la mitjana d'edat dels seus atletes.
     * Retorna la llista ja ordenada
     * @param clubs: ArrayList de clubs sense ordenar
     * @param n: char del String de la mitjana d'edat que estarem llegint
     * @return b: ArrayList de clubs ja ordenada
     */
    public ArrayList<Club> bucketSort(ArrayList<Club> clubs, int n){
        int decimalsZero;
        int caracter;
        float primer;
        float segon;
        Club temp;
        ArrayList<Club> b = new ArrayList<>();

        // Crear buckets buits
        ArrayList<Club>[] bucket = new ArrayList[10];
        for (int i = 0; i < 10; i++){
            bucket[i] = new ArrayList<>();
        }

        decimalsZero = 0;

        // Afegir elements als buckets
        for (int j = 0; j < clubs.size(); j++ ) {
            if (n == 2) n++; //es el punt del float
            if (clubs.get(j).getMitjaEdats().length() > n ) {
                caracter = Integer.parseInt(String.valueOf(clubs.get(j).getMitjaEdats().charAt(n)));
                bucket[caracter].add(clubs.get(j));
                decimalsZero = 1; //per acabar el programa necessitem que tots els decimals siguin 0
            } else {
                bucket[0].add(clubs.get(j));
            }
        }

        for (int i = 0; i < 10; i++) {
            if (bucket[i].size() > 2 && decimalsZero == 1) {
                bucket[i] = bucketSort(bucket[i],n + 1);
            } else if (bucket[i].size() == 2) {
                primer = Float.parseFloat(bucket[i].get(0).getMitjaEdats());
                segon = Float.parseFloat(bucket[i].get(1).getMitjaEdats());
                if (primer > segon) {
                    temp = bucket[i].get(0);
                    bucket[i].set(0,  bucket[i].get(1));
                    bucket[i].set(1,  temp);
                }
            }
        }

        // Obtenim el array ordenat
        for (int i = 0; i < 10; i++) {
            if(bucket[i].size() != 0){
                b.addAll(bucket[i]);
            }
        }
        return b;
    }
}