package one.microstream.training.s3.storage;

import one.microstream.enterprise.afs.aws.s3.types.S3Connector;
import one.microstream.enterprise.afs.blobstore.types.BlobStoreFileSystem;
import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;
import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;


public class DB
{
	public static EmbeddedStorageManager	storageManager;
	public final static DataRoot			root	= new DataRoot();
	
	static
	{
//		System.setProperty("aws.accessKeyId", "enter access key here");
//		System.setProperty("aws.secretAccessKey", "enter secret key here");
		
		SystemPropertyCredentialsProvider credentialsProvider = SystemPropertyCredentialsProvider.create();

        Region region = Region.EU_CENTRAL_1;
        S3Client s3 = S3Client.builder()
            .region(region)
            .credentialsProvider(credentialsProvider)
            .build();
		
//		Just to check if nativ AWS connection is working
//        ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
//        ListBucketsResponse listBucketsResponse = s3.listBuckets(listBucketsRequest);
//        listBucketsResponse.buckets().stream().forEach(x -> System.out.println(x.name()));
                
        BlobStoreFileSystem fileSystem = BlobStoreFileSystem.New(
        	S3Connector.Caching(s3)
        );
        
        String S3bucketname = "microstreamstoragedemo";
        storageManager = EmbeddedStorage.start(fileSystem.ensureDirectoryPath(S3bucketname));
	}
	
}
