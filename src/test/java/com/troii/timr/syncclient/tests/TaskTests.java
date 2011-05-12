package com.troii.timr.syncclient.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.troii.timr.timrsync.GetTasksResponse;
import com.troii.timr.timrsync.ObjectFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/basecontext.xml")
public class TaskTests {

	@Autowired
	private WebServiceTemplate webServiceTemplate;

	@Test
	public void getTasks() throws Exception {
		final ObjectFactory objectFactory = new ObjectFactory();
		final GetTasksResponse getTasksResponse = (GetTasksResponse) webServiceTemplate
				.marshalSendAndReceive(objectFactory.createGetTasksRequest(null));
		Assert.assertNotNull(getTasksResponse);
		Assert.assertTrue(!getTasksResponse.getTasks().isEmpty());
	}

}
