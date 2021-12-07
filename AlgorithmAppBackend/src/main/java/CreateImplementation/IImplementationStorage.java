package CreateImplementation;

public interface IImplementationStorage {
    /**
     * @param payload The data to store
     * @param fileExtension The file extension of the data
     * @return the url of where the implementation is stored
     */
    String storeImplementation(String payload, String fileExtension);
}
