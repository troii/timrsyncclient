package com.troii.timr.syncclient.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.troii.timr.timrsync.GetCarsResponse;
import com.troii.timr.timrsync.ObjectFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/basecontext.xml")
public class CarTests {

	@Autowired
	private WebServiceTemplate webServiceTemplate;

	@Test
	public void getCars() throws Exception {
		final ObjectFactory objectFactory = new ObjectFactory();
		final GetCarsResponse getCarsResponse = (GetCarsResponse) webServiceTemplate
				.marshalSendAndReceive(objectFactory.createGetCarsRequest(null));
		Assert.assertNotNull(getCarsResponse);
		Assert.assertTrue(!getCarsResponse.getCars().isEmpty());
	}

}
