# Amazon SageMaker Java code examples

This README discusses how to run and test the Java code examples for Amazon SageMaker.

## Running the Amazon SageMaker Java files

**IMPORTANT**

The Java examples perform AWS operations for the account and AWS Region for which you've specified credentials, and you may incur AWS service charges by running them. See the [AWS Pricing page](https://aws.amazon.com/pricing/) for details about the charges you can expect for a given service and operation.

Some of these examples perform *destructive* operations on AWS resources, such as deleting a model by running the **DeleteModel** example. **Be very careful** when running an operation that deletes or modifies AWS resources in your account. It's best to create separate test-only resources when experimenting with these examples.

To run these examples, you can setup your development environment to use Apache Maven or Gradle to configure and build AWS SDK for Java projects. For more information, 
see [Get started with the AWS SDK for Java 2.x](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html).

**Only the JDK 1.8 has been tested!**

 ## Prerequisite

- Install JRE 1.8
- Upload the [model.tar.gz](model.tar.gz) file to your SageMaker S3 bucket and markdown the S3 URL which will use to modified the [config.properties](endpoint/src/main/resources/config.properties) file.

 ## Testing the Amazon SageMaker Java files

You can test the Java code examples for SageMaker by running a test file named **SageMakerTest**. This file uses JUnit 5 to run the JUnit tests and is located in the **src/test/java** folder. For more information, see [https://junit.org/junit5/](https://junit.org/junit5/).

You can run the JUnit tests from a Java IDE, such as IntelliJ, or from the command line by using Maven. As each test runs, you can view messages that inform you if the various tests succeed or fail. For example, the following message informs you that Test 3 passed.

	Test 3 passed

**WARNING**: _Running these JUnit tests manipulates real Amazon resources and may incur charges on your account._

 ### Properties file
Before running the SageMaker JUnit tests, you must define values in the **config.properties** file located in the **resources** folder. This file contains values that are required to run the JUnit tests. For example, you define a model name used in the tests. If you do not define all values, the JUnit tests fail.

Define these values to successfully run the JUnit tests:

- **image** - The registry path of the Docker image that contains the training algorithm. It's will be `727897471807.dkr.ecr.cn-north-1.amazonaws.com.cn/pytorch-inference:1.8.0-cpu-py3` in Beijing region.
- **modelDataUrl** - The Amazon S3 path where the model artifacts, which result from model training, are stored. **MUST USE YOUR OWN SAGEMAKER S3 URI!**
- **executionRoleArn** - The Amazon Resource Name (ARN) of the IAM role that SageMaker uses. **MUST BE SET!**
- **modelName** - The model name used in various tests.
- **region** - Which region deploy, which we will use Beijing region.


**Note**: To set up the model data and other requirements needed for the unit tests, follow [Build, train, and deploy a machine learning model](https://aws.amazon.com/getting-started/hands-on/build-train-deploy-machine-learning-model-sagemaker/).

### Command line
To run the JUnit tests from the command line, you can use the following command.

	cd endpoint && ../mvn test

You will see output from the JUnit tests, as shown here.

	[INFO] -------------------------------------------------------
	[INFO]  T E S T S
	[INFO] -------------------------------------------------------
	[INFO] Running SageMakerTest
    Test 1 passed
	The ARN of the model is arn:aws-cn:sagemaker:cn-north-1:12345678900:model/java-sdk-pytorch-model
	Test 2 passed
	The ARN of the endpoint config is arn:aws-cn:sagemaker:cn-north-1:12345678900:endpoint-config/java-sdk-pytorch-model-config
	The ARN of the endpoint is arn:aws-cn:sagemaker:cn-north-1:12345678900:endpoint/java-sdk-pytorch-model-endpoint
	Test 3 passed
	Done!
	[INFO] Results:
	[INFO]
	[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0
	[INFO]
	INFO] --------------------------------------------
	[INFO] BUILD SUCCESS
	[INFO]--------------------------------------------
	[INFO] Total time:  12.003 s
	[INFO] Finished at: 2020-02-10T14:25:08-05:00
	[INFO] --------------------------------------------

### Unsuccessful tests

If you do not define the correct values in the properties file, your JUnit tests are not successful. You will see an error message such as the following. You need to double-check the values that you set in the properties file and run the tests again.

	[INFO]
	[INFO] --------------------------------------
	[INFO] BUILD FAILURE
	[INFO] --------------------------------------
	[INFO] Total time:  19.038 s
	[INFO] Finished at: 2020-02-10T14:41:51-05:00
	[INFO] ---------------------------------------
	[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:2.22.1:test (default-test) on project SageMakerServiceIntegrationTest:  There are test failures.
	[ERROR];

### The reuslt and clear up

Please find the created resource in the SageMaker console according to the ARN. And please clear up all the resource created by the testing manually.