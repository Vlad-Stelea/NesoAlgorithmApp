package CreateProblemInstance;

import Utils.Constants;
import Utils.S3Utils;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;

public class S3ProblemInstanceStorage implements IProblemInstanceStorage{

    S3Utils s3Util;
    private final static String bucketLocation = Constants.BUCKET_LOCATION;

    public S3ProblemInstanceStorage() {
        s3Util = new S3Utils();
    }

    @Override
    public String storeProblemInstance(String payload, String fileExtension) {
        return s3Util.storeFile(bucketLocation, generateProblemInstanceStorageLocation(fileExtension), payload);
    }

    private static String generateProblemInstanceStorageLocation(String fileExtension) {
        String fileName = UUID.randomUUID().toString();
        return "ProblemInstances/" + fileName + fileExtension;
    }
}
