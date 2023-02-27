import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Reine {

    private static int solutions, nbSolution;
    public static int[] echiquier;

    private static void backtracking(int n) {
        solutions = 0;
        nbSolution = 1;
        echiquier = new int[n];
        placerReine(0, n);
    }

    private static void heuristique1(int n) {

    }

    private static void heuristique2(int n) {
        
    }

    private static void heuristique3(int n) {
        
    }

    private static boolean estEmprise(int index) {
        for (int i = 0; i < index; i++) {
            // Ligne
            if (echiquier[i] == echiquier[index]) {
                return true;
            }
            // Diagonale
            if ((index - i) == Math.abs(echiquier[index] - echiquier[i])) {
                return true;
            }
        }
        return false;
    }

    private static void placerReine(int courant, int n) {
        if (courant == n) {
            System.out.println(afficherEchiquier(n));
            solutions++;
            return;
        }
        for (int i = 0; i < n; i++) {
            echiquier[courant] = i;
            if (!estEmprise(courant)) {
                placerReine(courant + 1, n);
            }
        }
    }

    private static String afficherSeparateur(int n) {
        String retour = "";
        for(int j=0; j<n; j++) {
            for(int i=0; i<n; i++) {
                if((i+1)%n==0) {
                    retour += "┼";
                }
            }
            retour += "───";
        }
        retour += "┼\n";
        return retour;
    }

    private static String afficherEchiquier(int n) {
        afficherSolutions();
        String retour = "\n";
        for(int j=0; j<n; j++) {
            retour += afficherSeparateur(n);
            for(int i=0; i<n; i++) {
                if(echiquier[j]!=i) {
                    retour += "│   ";
                } else {
                    retour += "│ ♛ ";
                }
            }
            retour += "│\n";
        }
        retour += afficherSeparateur(n);
        nbSolution++;
        return retour;
    }

    private static void afficherSolutions() {
        System.out.print("Solution n°" + nbSolution +  " : [ ");
        for (int v : echiquier) {
            System.out.printf(v + "%s", " ");
        }
        System.out.print("]\n");
    }

    private static long tempsExecution(int index) {
        long debut = System.currentTimeMillis();
        backtracking(index);
        long fin = System.currentTimeMillis();
        long temps = fin - debut;
        return temps;
    }

    private static int nbSolutions(int index) {
        backtracking(index);
        return solutions;
    }

    /**
     * Méthode d'enregistrement des données dans un fichier
     * Comparaison du temps moyen d'exécution d'une méthode de résolution du pb des n Reines en fonction de n
     * @param temps Tableau de réels contenant des temps d'execution
     * @param dossier Nom de dossier
     * @param sousDossier Nom du sous-dossier
     * @param fichier Nom du fichier texte
     */    

    private static void saveDataN(long[] temps, String dossier, String sousDossier, String fichier) {
        long[] tab = temps;
        try {
            // On créé un chemin vers le dossier
            String folderPath = System.getProperty("user.dir") + File.separator + dossier + File.separator + sousDossier;
            // On créé un chemin vers le fichier
            String filePath = folderPath + File.separator + fichier;
            System.out.println("Ecriture du fichier " + filePath + " ...");
            // On créé un nouveau fichier d'écriture
            FileWriter fileWriter = new FileWriter(filePath);
            // On créé un nouveau fichier texte
            StringBuilder txt = new StringBuilder();
            // On écrit dans le fichier texte
            txt.append("# n temps").append("\n");
            for (int i = 1; i < tab.length; i+=1) {
                txt.append(i).append(" ").append(tab[i]).append("\n");
            }
            // On écrit dans le fichier d'écriture avec le fichier texte
            fileWriter.write(txt.toString());
            fileWriter.close();
            System.out.println("Le fichier " + fichier + " a bien été créé.");
        } catch (IOException e) {
            System.out.println("Une erreur s'est produite.");
            e.printStackTrace();
        }
    }

    /**
     * Méthode d'enregistrement des données dans un fichier
     * Comparaison du temps moyen d'exécution d'une méthode de résolution du pb des n Reines en fonction de nombre de solutions
     * @param temps Tableau de réels contenant des temps d'execution
     * @param valeurs Tableau d'entiers contenant le nombre de solutions en fonction de n
     * @param dossier Nom de dossier
     * @param sousDossier Nom du sous-dossier
     * @param fichier Nom du fichier texte
     */

    private static void saveDataNBSolutions(long[] temps, int[] valeurs, String dossier, String sousDossier, String fichier) {
        long[] tab1 = temps;
        int[] tab2 = valeurs;
        try {
            // On créé un chemin vers le dossier
            String folderPath = System.getProperty("user.dir") + File.separator + dossier + File.separator + sousDossier;
            // On créé un chemin vers le fichier
            String filePath = folderPath + File.separator + fichier;
            System.out.println("Ecriture du fichier " + filePath + " ...");
            // On créé un nouveau fichier d'écriture
            FileWriter fileWriter = new FileWriter(filePath);
            // On créé un nouveau fichier texte
            StringBuilder txt = new StringBuilder();
            // On écrit dans le fichier texte
            txt.append("# nb temps").append("\n");
            for (int i = 1; i < tab1.length; i+=1) {
                txt.append(tab2[i]).append(" ").append(tab1[i]).append("\n");
            }
            // On écrit dans le fichier d'écriture avec le fichier texte
            fileWriter.write(txt.toString());
            fileWriter.close();
            System.out.println("Le fichier " + fichier + " a bien été créé.");
        } catch (IOException e) {
            System.out.println("Une erreur s'est produite.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("╠════════════════════════════ START ════════════════════════════╣");

        System.out.println("\n╠══════════════ Choix de la taille de l'échiquier ══════════════╣\n");

        try (Scanner saisie = new Scanner(System.in)) {
            System.out.print("➢ Veuillez saisir la valeur de 'n' (taille de l'échiquier) : ");
            int n = saisie.nextInt();
            System.out.println("\n➢ On a n = " + n);

            System.out.println("\n╠══════════════════ Algorithme de backtracking ═════════════════╣\n");

            System.out.println("➢ Résolution du problème des N Reines via la méthode de backtracking ...");

            System.out.println("\n├───────────── Affichage des différentes solutions ─────────────┤\n");

            System.out.println("➢ Voici les différentes solutions que la méthode de backtracking nous propose pour n = " + n + "\n");

            long debut = System.currentTimeMillis();
            backtracking(n);
            long fin = System.currentTimeMillis();
            long temps = fin - debut;

            System.out.println("├──────── Nombre de solutions au problème des N Reines ─────────┤\n");

            System.out.println("➢ Nombre de solutions possibles : " + solutions);

            System.out.println("\n├─────────────── Temps d'exécution du programme ────────────────┤\n");

            System.out.println("➢ Temps d'exécution : " + temps + " ms");

            System.out.println("\n├────── Temps d'exécution du backtracking en fonction de n ─────┤\n");

            System.out.println("➢ Création d'un graphe gnuplot pour comparer le temps d'exécution de la méthode de backtracking en fonction de n :\n");
            int nb = 15;
            long[] tempsExecution1 = new long[nb];
            for (int i = 1; i < nb; i++) {
                tempsExecution1[i] = tempsExecution(i);
            }
            saveDataN(tempsExecution1, "NReine/data", "backtracking", "comparaisonExecutionBacktrackingN.dat");

            System.out.println("\n├── Temps d'exécution du backtracking en f. du nb de solutions ─┤\n");

            System.out.println("➢ Création d'un graphe gnuplot pour comparer le temps d'exécution de la méthode de backtracking en fonction de n :\n");

            long[] tempsExecution2 = new long[nb];
            int[] nbSolutions = new int[nb];
            for (int i = 0; i < nb; i++) {
                tempsExecution2[i] = tempsExecution(i);
                nbSolutions[i] = nbSolutions(i);
                solutions=0;
            }
            saveDataNBSolutions(tempsExecution2, nbSolutions, "NReine/data", "backtracking", "comparaisonExecutionBacktrackingNbSolutions.dat");
        }

        System.out.println("\n╠═════════════════════════════ END ═════════════════════════════╣");
    }
}