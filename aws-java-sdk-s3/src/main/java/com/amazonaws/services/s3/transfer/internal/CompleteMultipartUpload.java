/*
 * Copyright 2010-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.services.s3.transfer.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.CompleteMultipartUploadResult;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.model.UploadResult;

/**
 * Initiates a complete multi-part upload request for a
 * TransferManager multi-part parallel upload.
 */
public class CompleteMultipartUpload implements Callable<UploadResult> {

    /** The upload id associated with the multi-part upload. */
    private final String uploadId;

    /**
     * The reference to underlying Amazon S3 client to be used for initiating
     * requests to Amazon S3.
     */
    private final AmazonS3 s3;

    /** The reference to the request initiated by the user. */
    private final PutObjectRequest putObjectRequest;

    /** The futures of threads that upload individual parts. */
    private final List<Future<PartETag>> futures;

    /**
     * The eTags of the parts that had been successfully uploaded before
     * resuming a paused upload.
     */
    private final List<PartETag> eTagsBeforeResume;

    /** The monitor to which the upload progress has to be communicated. */
    private final UploadMonitor monitor;

    public CompleteMultipartUpload(String uploadId, AmazonS3 s3,
            PutObjectRequest putObjectRequest, List<Future<PartETag>> futures,
            List<PartETag> eTagsBeforeResume, UploadMonitor monitor) {
        this.uploadId = uploadId;
        this.s3 = s3;
        this.putObjectRequest = putObjectRequest;
        this.futures = futures;
        this.eTagsBeforeResume = eTagsBeforeResume;
        this.monitor = monitor;
    }

    @Override
    public UploadResult call() throws Exception {
        CompleteMultipartUploadResult completeMultipartUploadResult = s3
                .completeMultipartUpload(new CompleteMultipartUploadRequest(
                        putObjectRequest.getBucketName(), putObjectRequest
                                .getKey(), uploadId, collectPartETags()));
        UploadResult uploadResult = new UploadResult();
        uploadResult.setBucketName(putObjectRequest
                .getBucketName());
        uploadResult.setKey(putObjectRequest.getKey());
        uploadResult.setETag(completeMultipartUploadResult.getETag());
        uploadResult.setVersionId(completeMultipartUploadResult.getVersionId());

        monitor.uploadComplete();

        return uploadResult;
    }

    /**
     * Collects the Part ETags for initiating the complete multi-part upload
     * request. This is blocking as it waits until all the upload part threads
     * complete.
     */
    private List<PartETag> collectPartETags() {

        final List<PartETag> partETags = new ArrayList<PartETag>();
        partETags.addAll(eTagsBeforeResume);
        for (Future<PartETag> future : futures) {
            try {
                partETags.add(future.get());
            } catch (Exception e) {
                throw new AmazonClientException(
                        "Unable to complete multi-part upload. Individual part upload failed : "
                                + e.getCause().getMessage(), e.getCause());
            }
        }
        return partETags;
    }
}
