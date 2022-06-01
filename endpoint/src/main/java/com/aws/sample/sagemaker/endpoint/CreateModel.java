
/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.aws.sample.sagemaker.endpoint;

import software.amazon.awssdk.services.sagemaker.SageMakerClient;
import software.amazon.awssdk.services.sagemaker.model.ContainerDefinition;
import software.amazon.awssdk.services.sagemaker.model.ContainerMode;
import software.amazon.awssdk.services.sagemaker.model.CreateModelRequest;
import software.amazon.awssdk.services.sagemaker.model.CreateModelResponse;
import software.amazon.awssdk.services.sagemaker.model.SageMakerException;
import java.util.Map;
import java.util.HashMap;



public class CreateModel {


    public static void createSagemakerModel(SageMakerClient sageMakerClient,
                                            String dataUrl,
                                            String image,
                                            String modelName,
                                            String executionRoleArn,
                                            String region) {
        try {
            Map<String, String> env = new HashMap<>();
            env.put("SAGEMAKER_CONTAINER_LOG_LEVEL", "10");
            env.put("SAGEMAKER_PROGRAM", "mnist.py");
            env.put("SAGEMAKER_REGION", region);
            ContainerDefinition containerDefinition = ContainerDefinition.builder()
                    .modelDataUrl(dataUrl)
                    .image(image)
                    .mode(ContainerMode.SINGLE_MODEL)
                    .environment(env)
                    .build();

            CreateModelRequest modelRequest = CreateModelRequest.builder()
                    .modelName(modelName)
                    .executionRoleArn(executionRoleArn)
                    .primaryContainer(containerDefinition)
                    .build();

            CreateModelResponse response = sageMakerClient.createModel(modelRequest);
            System.out.println("The ARN of the model is " +response.modelArn() );

        } catch (SageMakerException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}