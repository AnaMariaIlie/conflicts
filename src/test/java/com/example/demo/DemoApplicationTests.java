package com.example.demo;

import com.adobe.testing.s3mock.S3MockApplication;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Region;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	S3MockApplication s3Mock = com.adobe.testing.s3mock.S3MockApplication.start("--server.port=9090", "--http.port=9091");

	@Before
	public void init () {
		int httpsPort = s3Mock.getPort();
		int httpPort = s3Mock.getHttpPort();

		System.out.println(httpsPort  + " " + httpPort);

		final AmazonS3 httpAmazonS3 = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSCredentialsProvider() {
                    @Override
                    public AWSCredentials getCredentials() {
                        return null;
                    }

                    @Override
                    public void refresh() {

                    }
                }) // use any credentials here
				.withEndpointConfiguration(
						new AwsClientBuilder.EndpointConfiguration(
								"http://localhost:9090/",
								Region.US_Standard.getFirstRegionId()
						)).withPathStyleAccessEnabled(true).build();

		httpAmazonS3.createBucket("ana-buc" +
                "" +
                "" +
                "ket");
		httpAmazonS3.putObject("ana-ghfhdhdgbucket", "ome/ana/DemoApplication.java", new File("A:\\demo\\demo\\src\\main\\java\\com\\example\\demo\\DemoApplication.java"));


	}



	@Test
	public void contextLoads() {
	}

	@After
	public void after() {
		s3Mock.stop();
	}

}
