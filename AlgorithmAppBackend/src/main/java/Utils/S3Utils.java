package Utils;

import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class S3Utils {

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