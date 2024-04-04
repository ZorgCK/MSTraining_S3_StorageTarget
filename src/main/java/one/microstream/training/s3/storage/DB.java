package one.microstream.training.s3.storage;

import org.eclipse.store.afs.aws.s3.types.S3Connector;
import org.eclipse.store.afs.blobstore.types.BlobStoreFileSystem;
import org.eclipse.store.storage.embedded.types.EmbeddedStorage;
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager;

import one.microstream.training.s3.utils.S3Utils;


public class DB
{
	public static EmbeddedStorageManager	storageManager;
	public final static DataRoot			root	= new DataRoot();
	
	static
	{
		BlobStoreFileSystem S3Filesystem = BlobStoreFileSystem.New(
			S3Connector.Caching(S3Utils.getS3Client()));
		
		System.setProperty("aws.region", "eu-central-1");
		
		//@formatter:off
		storageManager = EmbeddedStorage.start(S3Filesystem.ensureDirectoryPath("microstreamstoragedemo"));
	}
	
}
