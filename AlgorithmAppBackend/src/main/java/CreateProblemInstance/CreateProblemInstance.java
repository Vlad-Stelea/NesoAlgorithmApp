package CreateProblemInstance;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;

import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import db.ProblemInstanceDAO;

import java.io.ByteArrayInputStream;

public class CreateProblemInstance implements RequestHandler<CreateProblemInstanceRequest, CreateProblemInstanceResponse> {

    public LambdaLogger logger = null;
    CreateProblemInstanceHandler handler;
    private AmazonS3 s3 = null;

    public CreateProblemInstance() {
        handler = new CreateProblemInstanceHandler(new ProblemInstanceDAO());
    }

    private boolean uploadDatasetToS3(String uuid, byte[] datasetContents) {
        logger.log("attempting to attach to s3 in uploadDatasetToS3");

        if(s3 == null) {
            logger.log("attach to S3 request from uploadDatasetToS3");
            s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
            logger.log("attach to S3 successful");
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(datasetContents);
        ObjectMetadata omd = new ObjectMetadata();
        omd.setContentLength(datasetContents.length);

        s3.putObject(new PutObjectRequest("nesoalgorithmapp", "datasets/" + uuid, bais, omd).withCannedAcl(CannedAccessControlList.PublicRead));

        return true;
    }

    @Override
    public CreateProblemInstanceResponse handleRequest(CreateProblemInstanceRequest req, Context context) {
        logger = context.getLogger();
        logger.log("Loading Java Lambda handler to create Problem Instance...");
        logger.log("Create Problem Instance request: " + req.toString());

        // upload the contents of our dataset to S3 for access later
        uploadDatasetToS3(req.getProbInstanceUUID(), java.util.Base64.getDecoder().decode(req.getEncodedDatasetContents()));

        // record the S3 URL in our request so it can be handled and added to the database properly
        req.setEncodedDatasetContents("s3://nesoalgorithmapp.s3.us-east-2.amazonaws.com/datasets/" + req.getProbInstanceUUID());
        return handler.handle(req);
    }

}
