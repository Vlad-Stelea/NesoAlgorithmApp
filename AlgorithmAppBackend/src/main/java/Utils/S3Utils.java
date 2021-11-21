package Utils;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class S3Utils {

    AmazonS3 s3;

    public S3Utils() {
        s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
    }

    public String storeFile(String bucketLocation, String fileLocation, String payload) {
        // Set up the parameters for the put operation
        ObjectMetadata metadata = new ObjectMetadata();
        byte[] decodedBytes = decodeBase64String(payload);
        InputStream implementationStream = new ByteArrayInputStream(decodedBytes);

        metadata.setContentLength(decodedBytes.length);
        PutObjectRequest request = new PutObjectRequest(bucketLocation, fileLocation, implementationStream, metadata);

        AccessControlList acl = setupAccess();
        request.setAccessControlList(acl);
        // Put the file into s3
        s3.putObject(request);

        // Get and return the url of the newly placed implementation
        return s3.getUrl(bucketLocation, fileLocation).toString();
    }

    public static AccessControlList setupAccess() {
        AccessControlList acl = new AccessControlList();
        acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
        return acl;
    }

    public static byte[] decodeBase64String(String encodedString) {
        byte [] bytes = encodedString.getBytes();
        bytes = Base64.decode(bytes);
        return bytes;
    }
}
