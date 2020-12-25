package com.amigoscode.awsimageupload.awsuploaddemo.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amigoscode.awsimageupload.awsuploaddemo.utility.CustomFileReader;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.amigoscode.awsimageupload.awsuploaddemo.constants.FileConstants.*;

@Configuration
public class AmazonConfig {

    /*
     * instantiates an instance of Amazon S3 so we can inject into other classes.
     */
    @Bean
    public AmazonS3 s3() {

        CustomFileReader customFileReader = new CustomFileReader();
        JSONObject credentials = customFileReader.getObjects(AWS_CREDENTIALS_FILE_LOCATION);

        String accessKey = customFileReader.getContentFromKey(credentials, AWS_SECRET_KEY);
        String accessKeyId = customFileReader.getContentFromKey(credentials, AWS_SECRET_KEY_ID);
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, accessKeyId);

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
