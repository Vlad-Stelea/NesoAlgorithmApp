package CreateProblemInstance;

public interface IProblemInstanceStorage {
    /**
     * Stores a problem instance
     * @param payload the data to store
     * @return the url of where the problem instance was stored
     */
    String storeProblemInstance(String payload);
}
