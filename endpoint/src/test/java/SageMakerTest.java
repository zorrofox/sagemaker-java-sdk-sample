import com.aws.sample.sagemaker.endpoint.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import software.amazon.awssdk.services.sagemaker.SageMakerClient;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SageMakerTest {

    private static SageMakerClient sageMakerClient;

    private static String image = "";
    private static String modelDataUrl = "";
    private static String executionRoleArn = "";
    private static String modelName = "";
    private static String region = "";



    @BeforeAll
    public static void setUp() throws IOException {


        

        try (InputStream input = SageMakerTest.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            // Populate the data members required for all tests
            image = prop.getProperty("image");
            modelDataUrl = prop.getProperty("modelDataUrl");
            executionRoleArn = prop.getProperty("executionRoleArn");
            modelName = prop.getProperty("modelName");
            region = prop.getProperty("region");

            sageMakerClient = DependencyFactory.sageMakerClient(region);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    @Order(1)
    public void whenInitializingAWSService_thenNotNull() {
        assertNotNull(sageMakerClient);
        System.out.println("Test 1 passed");
    }

    @Test
    @Order(2)
    public void CreateModel() {

        CreateModel.createSagemakerModel(sageMakerClient, 
                                         modelDataUrl, 
                                         image, 
                                         modelName,
                                         executionRoleArn, 
                                         region);
        System.out.println("Test 2 passed");
    }

    @Test
    @Order(3)
    public void CreateSagemakerEndpoint() {

        CreateEndpoint.createSagemakerEndpoint(sageMakerClient, modelName);
        System.out.println("Test 3 passed");
    }

}
