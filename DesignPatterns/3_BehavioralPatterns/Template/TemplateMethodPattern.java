// ───────────────────────────────────────────────────────────
// 1) Base Class defining the template method
// ───────────────────────────────────────────────────────────

abstract class ModelTrainer{
    // the template method - final so subclasses cant change the sequence

    public final void trainPipeline(String dataPath){
        loadData(dataPath);
        preprocessData();
        trainModel();       // subclass specific
        evaluateModel();    // sublass specific
        saveModel();        // subclass specific or default
    }

    protected void loadData(String path){
        System.out.println("[Common] loading dataset from " + path);
        // Example -> Read CSV, images, etc
    }

    protected void preprocessData(){
        System.out.println("[Common] Splitting into train/test and normalizing");
    }

    protected abstract void trainModel();
    protected abstract void evaluateModel();

    // Provide a default save, but subclasses can override if needed
    protected void saveModel(){
        System.out.println("[Common] Saving model to disk as default format");
    }
}

// ───────────────────────────────────────────────────────────
// 2) Concrete Subclasses: Neural Network
// ───────────────────────────────────────────────────────────

class NeuralNetworkTrainer extends ModelTrainer{
    @Override 
    protected void trainModel(){
        System.out.println("[Neuralnet] Training Neural Network for 100 epochs");
        // pseudo-code: forward/backward passes, gradient descent
    }

    @Override 
    protected void evaluateModel(){
        System.out.println("[NeuralNet] Evaluating accuracy and loss on validation set");
    }

    @Override 
    protected void saveModel(){
        System.out.println("[NeuralNet] serializing network weights to .h5 file");
    }
} 

// ───────────────────────────────────────────────────────────
// 3) Concrete Subclass: Decision Tree
// ───────────────────────────────────────────────────────────

class DecisionTreeTrainer extends ModelTrainer{
    // Use the default preprocessData() (train / test split + normalize)

    @Override
    protected void trainModel(){
        System.out.println("[DecisionTree] Building decision tree with max_depth = 5");
        // Pseudo-Code : Recursive Splitting on Features
    }

    @Override 
    protected void evaluateModel(){
        System.out.println("[DecisionTree] Computing classification report (precision / recall)");
    }

    // Use the default saveModel()
}

// ───────────────────────────────────────────────────────────
// 4) Usage
// ───────────────────────────────────────────────────────────

public class TemplateMethodPattern {
    public static void main(String[] args){
        System.out.println("==== Neural Network Training ====");
        ModelTrainer nnTrainer = new NeuralNetworkTrainer();
        nnTrainer.trainPipeline("Data/images/");

        System.out.println("==== Decision Network Training ====");
        ModelTrainer dtTrainer = new DecisionTreeTrainer();
        dtTrainer.trainPipeline("data/iris.csv");
    }
}
