package rimel.appliweka;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.classifiers.bayes.NaiveBayesMultinomialText;
import weka.classifiers.bayes.NaiveBayesMultinomialUpdateable;
import weka.classifiers.bayes.NaiveBayesSimple;
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.classifiers.bayes.net.BIFReader;
import weka.classifiers.bayes.net.BayesNetGenerator;
import weka.classifiers.bayes.net.EditableBayesNet;
import weka.classifiers.functions.GaussianProcesses;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.PLSClassifier;
import weka.classifiers.functions.RBFClassifier;
import weka.classifiers.functions.RBFNetwork;
import weka.classifiers.functions.RBFRegressor;
import weka.classifiers.functions.SGD;
import weka.classifiers.functions.SGDText;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.SMOreg;
import weka.classifiers.functions.SimpleLinearRegression;
import weka.classifiers.functions.SimpleLogistic;
import weka.classifiers.functions.VotedPerceptron;
import weka.classifiers.lazy.IB1;
import weka.classifiers.lazy.IBk;
import weka.classifiers.lazy.KStar;
import weka.classifiers.lazy.LWL;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.meta.AdditiveRegression;
import weka.classifiers.meta.AttributeSelectedClassifier;
import weka.classifiers.meta.Bagging;
import weka.classifiers.meta.CVParameterSelection;
import weka.classifiers.meta.ClassificationViaClustering;
import weka.classifiers.meta.ClassificationViaRegression;
import weka.classifiers.meta.CostSensitiveClassifier;
import weka.classifiers.meta.Dagging;
import weka.classifiers.meta.Decorate;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.meta.GridSearch;
import weka.classifiers.meta.IterativeClassifierOptimizer;
import weka.classifiers.meta.LogitBoost;
import weka.classifiers.meta.MultiBoostAB;
import weka.classifiers.meta.MultiClassClassifier;
import weka.classifiers.meta.MultiClassClassifierUpdateable;
import weka.classifiers.meta.MultiScheme;
import weka.classifiers.meta.OrdinalClassClassifier;
import weka.classifiers.meta.RacedIncrementalLogitBoost;
import weka.classifiers.meta.RandomCommittee;
import weka.classifiers.meta.RandomSubSpace;
import weka.classifiers.meta.RandomizableFilteredClassifier;
import weka.classifiers.meta.RotationForest;
import weka.classifiers.meta.Stacking;
import weka.classifiers.meta.Vote;
import weka.classifiers.meta.WeightedInstancesHandlerWrapper;
import weka.classifiers.misc.HyperPipes;
import weka.classifiers.misc.InputMappedClassifier;
import weka.classifiers.misc.SerializedClassifier;
import weka.classifiers.misc.VFI;
import weka.classifiers.pmml.consumer.GeneralRegression;
import weka.classifiers.pmml.consumer.NeuralNetwork;
import weka.classifiers.rules.ConjunctiveRule;
import weka.classifiers.rules.DTNB;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.JRip;
import weka.classifiers.rules.M5Rules;
import weka.classifiers.rules.NNge;
import weka.classifiers.rules.OneR;
import weka.classifiers.rules.PART;
import weka.classifiers.rules.Prism;
import weka.classifiers.rules.Ridor;
import weka.classifiers.rules.ZeroR;
import weka.classifiers.trees.ADTree;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.HoeffdingTree;
import weka.classifiers.trees.Id3;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.LADTree;
import weka.classifiers.trees.LMT;
import weka.classifiers.trees.M5P;
import weka.classifiers.trees.NBTree;
import weka.classifiers.trees.REPTree;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.trees.RandomTree;
import weka.classifiers.trees.lmt.LogisticBase;
import weka.core.Capabilities;

/**
 *
 * @author Maxime Dejeux
 */
public class Launcher {

    private static Map<String, Classifier> mapcls;

    public static void main(String[] args) throws Exception {
        String inputModel = "";
        mapcls = new HashMap<>();

        mapcls.put("ADTree", new ADTree());
        mapcls.put("AdaBoostM1", new AdaBoostM1());
        mapcls.put("AdditiveRegression", new AdditiveRegression());
        mapcls.put("AttributeSelectedClassifier", new AttributeSelectedClassifier());
        mapcls.put("BIFReader", new BIFReader());
        mapcls.put("Bagging", new Bagging());
        mapcls.put("BayesNet", new BayesNet());
        mapcls.put("BayesNetGenerator", new BayesNetGenerator());
        mapcls.put("CVParameterSelection", new CVParameterSelection());
        mapcls.put("ClassificationViaClustering", new ClassificationViaClustering());
        mapcls.put("ClassificationViaRegression", new ClassificationViaRegression());
        mapcls.put("ConjunctiveRule", new ConjunctiveRule());
        mapcls.put("CostSensitiveClassifier", new CostSensitiveClassifier());
        mapcls.put("DTNB", new DTNB());
        mapcls.put("Dagging", new Dagging());
        mapcls.put("DecisionStump", new DecisionStump());
        mapcls.put("DecisionTable", new DecisionTable());
        mapcls.put("Decorate", new Decorate());
        mapcls.put("EditableBayesNet", new EditableBayesNet());
        mapcls.put("FilteredClassifier", new FilteredClassifier());
        mapcls.put("GaussianProcesses", new GaussianProcesses());
        mapcls.put("GridSearch", new GridSearch());
        mapcls.put("HoeffdingTree", new HoeffdingTree());
        mapcls.put("HyperPipes", new HyperPipes());
        mapcls.put("IB1", new IB1());
        mapcls.put("IBk", new IBk());
        mapcls.put("Id3", new Id3());
        mapcls.put("InputMappedClassifier", new InputMappedClassifier());
        mapcls.put("IterativeClassifierOptimizer", new IterativeClassifierOptimizer());
        mapcls.put("J48", new J48());
        mapcls.put("JRip", new JRip());
        mapcls.put("KStar", new KStar());
        mapcls.put("LADTree", new LADTree());
        mapcls.put("LMT", new LMT());
        mapcls.put("LWL", new LWL());
        mapcls.put("LibSVM", new LibSVM());
        mapcls.put("LinearRegression", new LinearRegression());
        mapcls.put("Logistic", new Logistic());
        mapcls.put("LogisticBase", new LogisticBase());
        mapcls.put("LogitBoost", new LogitBoost());
        mapcls.put("M5P", new M5P());
        mapcls.put("M5Rules", new M5Rules());
        mapcls.put("MultiBoostAB", new MultiBoostAB());
        mapcls.put("MultiClassClassifier", new MultiClassClassifier());
        mapcls.put("MultiClassClassifierUpdateable", new MultiClassClassifierUpdateable());
        mapcls.put("MultiScheme", new MultiScheme());
        mapcls.put("MultilayerPerceptron", new MultilayerPerceptron());
        mapcls.put("NBTree", new NBTree());
        mapcls.put("NNge", new NNge());
        mapcls.put("NaiveBayes", new NaiveBayes());
        mapcls.put("NaiveBayesMultinomial", new NaiveBayesMultinomial());
        mapcls.put("NaiveBayesMultinomialText", new NaiveBayesMultinomialText());
        mapcls.put("NaiveBayesMultinomialUpdateable", new NaiveBayesMultinomialUpdateable());
        mapcls.put("NaiveBayesSimple", new NaiveBayesSimple());
        mapcls.put("NaiveBayesUpdateable", new NaiveBayesUpdateable());
        mapcls.put("OneR", new OneR());
        mapcls.put("OrdinalClassClassifier", new OrdinalClassClassifier());
        mapcls.put("PART", new PART());
        mapcls.put("PLSClassifier", new PLSClassifier());
        mapcls.put("Prism", new Prism());
        mapcls.put("RBFClassifier", new RBFClassifier());
        mapcls.put("RBFNetwork", new RBFNetwork());
        mapcls.put("RBFRegressor", new RBFRegressor());
        mapcls.put("REPTree", new REPTree());
        mapcls.put("RacedIncrementalLogitBoost", new RacedIncrementalLogitBoost());
        mapcls.put("RandomCommittee", new RandomCommittee());
        mapcls.put("RandomForest", new RandomForest());
        mapcls.put("RandomSubSpace", new RandomSubSpace());
        mapcls.put("RandomTree", new RandomTree());
        mapcls.put("RandomizableFilteredClassifier", new RandomizableFilteredClassifier());
        mapcls.put("Ridor", new Ridor());
        mapcls.put("RotationForest", new RotationForest());
        mapcls.put("SGD", new SGD());
        mapcls.put("SGDText", new SGDText());
        mapcls.put("SMO", new SMO());
        mapcls.put("SMOreg", new SMOreg());
        mapcls.put("SerializedClassifier", new SerializedClassifier());
        mapcls.put("SimpleLinearRegression", new SimpleLinearRegression());
        mapcls.put("SimpleLogistic", new SimpleLogistic());
        mapcls.put("Stacking", new Stacking());
        mapcls.put("VFI", new VFI());
        mapcls.put("Vote", new Vote());
        mapcls.put("VotedPerceptron", new VotedPerceptron());
        mapcls.put("WeightedInstancesHandlerWrapper", new WeightedInstancesHandlerWrapper());
        mapcls.put("ZeroR", new ZeroR());

        int num_parameters = args.length;
        for (int i = 0; i < num_parameters; i++) {
            switch (args[i]) {
                case "-mdl":
                    inputModel = args[++i];
                    break;
            }
        }

        //---------------------------------Chargement du model------------------------------
        /*Classifier cls = (Classifier) weka.core.SerializationHelper.read(inputModel);

         String result = "";
         result += "Algorithme " + cls.toString() + "\n";
         result += "Capabilities\n";
         Capabilities capa = cls.getCapabilities();
         result += capa.toString() + "\n";
         result += "Other capabilities\n";
         Capabilities otherCapa = capa.getOtherCapabilities();
         result += otherCapa.toString() + "\n";
         saveResult("Result", result);*/
        String result = "";
        for (Entry<String,Classifier> entry : mapcls.entrySet()) {
            Classifier c = entry.getValue();
            result += "Algorithme " + entry.getKey() + "\n";
            result += "Capabilities\n";
            Capabilities capa = c.getCapabilities();
            result += capa.toString();
            result += "Other capabilities\n";
            Capabilities otherCapa = capa.getOtherCapabilities();
            result += otherCapa.toString() + "\n";
        }
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
