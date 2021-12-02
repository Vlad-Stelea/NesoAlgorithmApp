package CreateImplementation;

public interface IImplementationStorage {
    /**
     * @param name the name of the implementation to store
     * @param payload The data to store
     * @return the url of where the implementation is stored
     */
    String storeImplementation(String name, String payload);
}
