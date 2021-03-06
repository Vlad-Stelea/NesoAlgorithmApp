package CreateImplementation;

import Utils.Constants;
import Utils.S3Utils;
import java.util.UUID;

public class S3ImplementationStorage implements IImplementationStorage{

    S3Utils s3Util;
    private final static String bucketLocation = Constants.BUCKET_LOCATION;

    public S3ImplementationStorage() {
       s3Util = new S3Utils();
    }

    @Override
    public String storeImplementation(String payload, String fileExtension) {
        return s3Util.storeFile(bucketLocation, generateImplementationStorageLocation(fileExtension), payload);
    }

    private static String generateImplementationStorageLocation(String fileExtension) {
        String fileName = UUID.randomUUID().toString();
        return "Implementations/" + fileName + fileExtension;
    }
}
