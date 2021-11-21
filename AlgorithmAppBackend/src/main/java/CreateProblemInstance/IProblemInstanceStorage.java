package CreateProblemInstance;

public interface IProblemInstanceStorage {
    /**
     * @param uuid the UUID of the problem instance to store
     * @param payload The data to store
     * @return the url of where the problem instance is stored
     */
    String storeProblemInstance(String uuid, String payload);
}
