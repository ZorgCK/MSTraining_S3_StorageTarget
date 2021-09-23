package one.microstream.training.s3.storage;

import java.net.URL;
import java.util.Optional;

import io.micronaut.core.io.ResourceResolver;
import io.micronaut.core.io.scan.ClassPathResourceLoader;
import one.microstream.enterprise.afs.aws.s3.types.S3Connector;
import one.microstream.enterprise.afs.blobstore.types.BlobStoreFileSystem;
import one.microstream.storage.embedded.configuration.types.EmbeddedStorageConfiguration;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;


public class DB
{
	public static EmbeddedStorageManager	storageManager;
	public final static DataRoot			root	= new DataRoot();
	
	static
	{
		ClassPathResourceLoader loader = new ResourceResolver().getLoader(ClassPathResourceLoader.class).get();
		Optional<URL> resource = loader.getResource("microstream.xml");
		
		System.setProperty("aws.region", "eu-central-1");
		
		S3Client client = S3Client.builder().region(Region.EU_CENTRAL_1).build();
		
		BlobStoreFileSystem fileSystem = BlobStoreFileSystem.New(
			S3Connector.Caching(client)
		);
		
		storageManager = EmbeddedStorageConfiguration.load(
			resource.get().getPath()).createEmbeddedStorageFoundation().createEmbeddedStorageManager(root).start();
		
		
	}
	
}
