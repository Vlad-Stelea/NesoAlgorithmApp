package CreateProblemInstance;

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

public class S3ProblemInstanceStorage implements IProblemInstanceStorage {

    AmazonS3 s3;
    private final static String bucketLocation = "nesoalgorithmapp";

    public S3ProblemInstanceStorage() {
        s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
    }

    @Override
    public String storeProblemInstance(String uuid, String payload) {
        ObjectMetadata metadata = new ObjectMetadata();
        byte[] decodedBytes = S3Utils.decodeBase64String(payload);
        InputStream problemInstanceStream = new ByteArrayInputStream(decodedBytes);

        String fileName = generateProblemInstanceStorageLocation();
        metadata.setContentLength(decodedBytes.length);
        PutObjectRequest request = new PutObjectRequest(bucketLocation, fileName, problemInstanceStream, metadata);

        AccessControlList acl = S3Utils.setupAccess();
        request.setAccessControlList(acl);
        // Put the file into s3
        s3.putObject(request);

        // Get and return the url of the newly stored problem instance
        return s3.getUrl(bucketLocation, fileName).toString();
    }

    private static String generateProblemInstanceStorageLocation() {
        String fileName = UUID.randomUUID().toString();
        return "ProblemInstances/" + fileName;
    }

}
