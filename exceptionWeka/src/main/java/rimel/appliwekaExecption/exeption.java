package rimel.appliwekaExecption;

import java.io.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class exeption {
    static ArrayList<String> myExecption = new ArrayList<>();
    static int nbError = 0;
    static int nbWekaExecption = 0;
    static int nbJavaExeption =0;
    static BufferedWriter writer;
    static JSONObject obj;
    static JSONArray execptionsFiles;

    public exeption() throws IOException {
        writer = new BufferedWriter(new FileWriter(new File( "C:\\Users\\chennouf\\Desktop\\RIMEL\\exceptionWeka\\src\\main\\java\\rimel\\results\\result.json"))); // a changer avec votre Path
        obj = new JSONObject();
        execptionsFiles = new JSONArray();
    }


    //toto
    public static void main(String[] args) throws IOException {  // TODO Lire plus algo en parcourant un repertoire
        exeption exep = new exeption();
        parcourirRepertoire("C:\\Users\\chennouf\\Desktop\\RIMEL\\exceptionWeka\\classifiers"); // a changer avec votre Path
        obj.put("nbExecptionsTotal",nbError );
        obj.put("nbExecptionsWeka", nbWekaExecption);
        obj.put("nbExecptionsJava", nbJavaExeption);
        obj.put("myDataExecptions", execptionsFiles);

        writer.write(obj.toJSONString());
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
                    nbError++;
                    nbJavaExeption++;
                    compteur++;
                    nbTrouves++;
                    myExecption.add("\n"+ligne);
                }

                if (ligne.contains("WekaException"))
                {
                    nbError++;
                    nbWekaExecption++;
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

        JSONObject objFile = new JSONObject();
        JSONArray dataExceptions = new JSONArray();

        for(int i=0;i<myExecption.size();i++){
            dataExceptions.add(myExecption.get(i));
        }

        objFile.put("Fichier",fileName);
        objFile.put("nbExeptions",CountExeption(path));
        objFile.put("dataExceptions", dataExceptions );
        execptionsFiles.add(objFile);

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
