package com.troii.timr.syncclient.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.troii.timr.timrsync.GetProjectTimesResponse;
import com.troii.timr.timrsync.LockProjectTimesRequestType;
import com.troii.timr.timrsync.ObjectFactory;

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
		lock.getIds().add(34L);
		final Object response = webServiceTemplate.marshalSendAndReceive(objectFactory
				.createLockProjectTimesRequest(lock));
		Assert.assertNotNull(response);
	}

}
