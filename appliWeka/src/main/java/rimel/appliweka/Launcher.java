package rimel.appliweka;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import weka.classifiers.Classifier;
import weka.core.Capabilities;
import weka.core.Capabilities.Capability;

/**
 *
 * @author Maxime Dejeux
 */
public class Launcher {

    public static void main(String[] args) throws Exception {
        String inputModel = "";

        int num_parameters = args.length;
        for (int i = 0; i < num_parameters; i++) {
            switch (args[i]) {
                case "-mdl":
                    inputModel = args[++i];
                    break;
            }
        }

        //---------------------------------Chargement du model------------------------------
        Classifier cls = (Classifier) weka.core.SerializationHelper.read(inputModel);

        String result = "";
        result += "Algorithme " + cls.toString() + "\n";
        result += "Capabilities\n";
        Capabilities capa = cls.getCapabilities();
        result += capa.toString() + "\n";
        result += "Other capabilities\n";
        Capabilities otherCapa = capa.getOtherCapabilities();
        result += otherCapa.toString() + "\n";
        saveResult("Result", result);
    }

    private static void saveResult(String pathOutput, String test) {
        File file = null;
        if (pathOutput.isEmpty()) {
            System.out.println("Erreur : Il n'y a pas de chemin pour pour le fichier test");
        } else {
            file = new File(pathOutput);
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            writer.write(test);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Erreur: impossible de créer le fichier '"
                    + pathOutput + "'");
        }
    }
}
