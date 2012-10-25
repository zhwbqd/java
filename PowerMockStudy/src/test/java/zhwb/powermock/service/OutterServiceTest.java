package zhwb.powermock.service;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(OutterService.class)
public class OutterServiceTest {
	@Test
	public void testPrintList() throws Exception {
		OutterService outSvc = new OutterService();

		List<String> mockReturn = new ArrayList<String>();
		mockReturn.add("this is mock");

		InnerService innSvc = PowerMock.createMock(InnerService.class);

		PowerMock.expectNew(InnerService.class).andReturn(innSvc);
		EasyMock.expect(innSvc.innerExecute("Test")).andReturn(mockReturn);

		PowerMock.replay(innSvc, InnerService.class);
		outSvc.printList();
		PowerMock.verify(innSvc, InnerService.class);
	}
}
