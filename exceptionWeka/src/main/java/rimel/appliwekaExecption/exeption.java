package rimel.appliwekaExecption;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;


public class exeption {
    static ArrayList<String> myExecption = new ArrayList<>();
    static int cpt = 0;
    static BufferedWriter writer;
    public exeption() throws IOException {
        writer = new BufferedWriter(new FileWriter(new File( "C:\\Users\\chennouf\\Desktop\\RIMEL\\exceptionWeka\\src\\main\\java\\rimel\\results\\result.txt"))); // a changer avec votre Path

    }


    //toto
    public static void main(String[] args) throws IOException {  // TODO Lire plus algo en parcourant un repertoire
        exeption exep = new exeption();
        parcourirRepertoire("C:\\Users\\chennouf\\Desktop\\RIMEL\\exceptionWeka\\classifiers"); // a changer avec votre Path
        writer.close();
    }





    private static int CountExeption(String path) {
        int nbTrouves = 0;
        int compteur = 0;
        myExecption = new ArrayList<>();
        try
        {
            String nomFichier = path;

            String ligne = "";
            BufferedReader lecteurFichier = null;

            lecteurFichier = new BufferedReader(new FileReader(nomFichier));
            while ((ligne = lecteurFichier.readLine()) != null)
            {

                if(compteur>3){
                    compteur = 0;
                }

                if(compteur>=1){
                    myExecption.add(ligne);
                    compteur++;
                }

                if (ligne.contains("catch"))
                {
                    compteur++;
                    nbTrouves++;
                    myExecption.add("\n"+ligne);
                }

                if (ligne.contains("WekaException"))
                {
                    myExecption.add("\n"+ligne);
                    compteur++;
                }

            }
            lecteurFichier.close();
        }
        catch (java.io.FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nbTrouves;
    }



    public static void write(String fileName,String path){
        try {
            //String new fileName = fileName.substring(0,chemin.lenght-1);)
            writer.write("\n \n Fichier : " + fileName);
            writer.write("\n nombres Exeptions : " + CountExeption(path));
            writer.write("\n les exception sont : ");
            for(int i=0;i<myExecption.size();i++){
                writer.write( "\n"+myExecption.get(i));
            }
            //writer.close();
            cpt++;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public static void parcourirRepertoire(String pathname) {
        File repertoire = new File(pathname);
        File[] files = repertoire.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                parcourirRepertoire(files[i].getAbsolutePath());
            } else {
                if(files[i].getName().contains(".java")){
                    write(files[i].getName(), files[i].getAbsolutePath());
                }
            }
        }
    }


}
