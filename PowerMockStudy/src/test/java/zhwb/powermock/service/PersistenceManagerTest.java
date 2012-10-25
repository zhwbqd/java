package zhwb.powermock.service;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PersistenceManager.class)
public class PersistenceManagerTest {

	@Test
	public void testCreateDirectoryStructure_ok() throws Exception {
		final String path = "directoryPath";
		File fileMock = PowerMock.createMock(File.class);

		PersistenceManager tested = new PersistenceManager();

		PowerMock.expectNew(File.class, path).andReturn(fileMock);

		EasyMock.expect(fileMock.exists()).andReturn(false);
		EasyMock.expect(fileMock.mkdirs()).andReturn(true);

		PowerMock.replay(fileMock, File.class);

		assertTrue(tested.createDirectoryStructure(path));

		PowerMock.verify(fileMock, File.class);
	}
}
