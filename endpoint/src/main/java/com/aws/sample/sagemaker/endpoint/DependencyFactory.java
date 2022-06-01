
package com.aws.sample.sagemaker.endpoint;

import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sagemaker.SageMakerClient;

/**
 * The module containing all dependencies required by the {@link App}.
 */
public class DependencyFactory {

    private DependencyFactory() {}

    /**
     * @return an instance of SageMakerClient
     */
    public static SageMakerClient sageMakerClient(String region) {
        return SageMakerClient.builder()
                       .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                       .region(Region.of(region))
                       .httpClientBuilder(UrlConnectionHttpClient.builder())
                       .build();
    }
}
