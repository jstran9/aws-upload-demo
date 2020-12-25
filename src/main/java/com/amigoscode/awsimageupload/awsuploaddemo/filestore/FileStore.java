package com.amigoscode.awsimageupload.awsuploaddemo.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStore {

    private final AmazonS3 s3;

    @Autowired
    public FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }

    /**
     * Saves passed in meta data to an s3 bucklet.
     * @param path Consists of a bucket name and any subfolders that may be present.
     * @param fileName The name of the file to be created.
     * @param optionalMetaData The meta data that we can pass to store images or files. Content-Type, Content-Length, etc.
     * @param inputStream The contents that gets saved to the s3 bucket.
     */
    public void save(String path,
                     String fileName,
                     Optional<Map<String, String>> optionalMetaData,
                     InputStream inputStream) {
        ObjectMetadata metadata = new ObjectMetadata();
        optionalMetaData.ifPresent(map -> {
            if(!map.isEmpty()) {
                map.forEach(metadata::addUserMetadata);
//                map.forEach((key, value) -> objectMetadata.addUserMetadata(key, value));
            }
        });
        try {
            s3.putObject(path, fileName, inputStream, metadata);
        } catch(AmazonServiceException e) {
            throw new IllegalStateException("Failed to store file to s3: ", e);
        }
    }
}
