package com.amigoscode.awsimageupload.awsuploaddemo.bucket;

public enum BucketName {

    PROFILE_IMAGE("amigoscode-image-upload-todd");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
