package com.aws.sample.sagemaker.endpoint;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.sagemaker.SageMakerClient;
import software.amazon.awssdk.services.sagemaker.model.CreateEndpointConfigRequest;
import software.amazon.awssdk.services.sagemaker.model.ProductionVariant;
import software.amazon.awssdk.services.sagemaker.model.DeploymentConfig;
import software.amazon.awssdk.services.sagemaker.model.CreateEndpointRequest;
import software.amazon.awssdk.services.sagemaker.model.CreateEndpointConfigResponse;
import software.amazon.awssdk.services.sagemaker.model.CreateEndpointResponse;
import software.amazon.awssdk.services.sagemaker.model.SageMakerException;

/*
 * Lambda function entry point. You can change to use other pojo type or implement
 * a different RequestHandler.
 *
 * @see <a href=https://docs.aws.amazon.com/lambda/latest/dg/java-handler.html>Lambda Java Handler</a> for more information
 */
public class CreateEndpoint {

    public static void createSagemakerEndpoint(SageMakerClient sageMakerClient, String modelName){

        try{

            ProductionVariant productionVariant = ProductionVariant.builder()
                                              .initialInstanceCount(1)
                                              .initialVariantWeight(1.0f)
                                              .instanceType("ml.c5.xlarge")
                                              .variantName("var1")
                                              .modelName(modelName)
                                              .build();

            CreateEndpointConfigRequest endpointConifgRequest = CreateEndpointConfigRequest.builder()
                                                                .endpointConfigName(modelName+"-config")
                                                                .productionVariants(productionVariant)
                                                                .build();
            CreateEndpointRequest endpointReqeust = CreateEndpointRequest.builder()
                                                    .endpointConfigName(modelName+"-config")
                                                    .endpointName(modelName+"-endpoint")
                                                    .build();

            CreateEndpointConfigResponse response1 = sageMakerClient.createEndpointConfig(endpointConifgRequest);
            System.out.println("The ARN of the endpoint config is " +response1.endpointConfigArn() );
            CreateEndpointResponse response2 = sageMakerClient.createEndpoint(endpointReqeust);
            System.out.println("The ARN of the endpoint is " +response2.endpointArn() );

        } catch (SageMakerException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        
    }
}
