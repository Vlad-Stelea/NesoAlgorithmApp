package CreateProblemInstance;

public interface IProblemInstanceStorage {
    /**
     * Stores a problem instance
     * @param name the name of the problem instance to store
     * @param payload the data to store
     * @return the url of where the problem instance was stored
     */
    String storeProblemInstance(String name, String payload);
}
