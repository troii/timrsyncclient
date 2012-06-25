package com.troii.timr.syncclient.tests;

import com.troii.timr.timrsync.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/basecontext.xml")
public class ProjectTimeTests {

	@Autowired
	private WebServiceTemplate webServiceTemplate;

	@Test
	public void getProjectTimes() throws Exception {
		final ObjectFactory objectFactory = new ObjectFactory();
		final GetProjectTimesResponse getProjectTimesResponse = (GetProjectTimesResponse) webServiceTemplate
				.marshalSendAndReceive(objectFactory.createGetProjectTimesRequest(objectFactory
						.createProjectTimeQuery()));
		Assert.assertNotNull(getProjectTimesResponse);
		Assert.assertTrue(!getProjectTimesResponse.getProjectTimes().isEmpty());
	}

	@Test
	public void lockProjectTimes() throws Exception {
		final ObjectFactory objectFactory = new ObjectFactory();
		final LockProjectTimesRequestType lock = objectFactory.createLockProjectTimesRequestType();
		lock.getIds().add(1L);
		final Object response = webServiceTemplate.marshalSendAndReceive(objectFactory
				.createLockProjectTimesRequest(lock));
		Assert.assertNotNull(response);
	}

	@Test
	public void setProjectTimesStatus() throws Exception {
		final ObjectFactory objectFactory = new ObjectFactory();
		final ProjectTimesStatusRequestType statusRequestType = objectFactory.createProjectTimesStatusRequestType();
		statusRequestType.getIds().add(2L);
		statusRequestType.setStatus(ProjectTimeStatus.CLEARED);
		final Object response = webServiceTemplate.marshalSendAndReceive(objectFactory
				.createSetProjectTimesStatusRequest(statusRequestType));
		Assert.assertNotNull(response);
	}

}
