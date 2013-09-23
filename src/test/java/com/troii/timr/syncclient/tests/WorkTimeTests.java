package com.troii.timr.syncclient.tests;

import com.troii.timr.timrsync.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeFactory;
import java.util.GregorianCalendar;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/basecontext.xml")
public class WorkTimeTests {

	@Autowired
	private WebServiceTemplate webServiceTemplate;

	@Test
	public void getWorkTimes() throws Exception {
		final ObjectFactory objectFactory = new ObjectFactory();
		final GetWorkTimesResponse getWorkTimesResponse = (GetWorkTimesResponse) webServiceTemplate
				.marshalSendAndReceive(objectFactory.createGetWorkTimesRequest(objectFactory
						.createWorkTimeQuery()));
		Assert.assertNotNull(getWorkTimesResponse);
		Assert.assertTrue(!getWorkTimesResponse.getWorkTimes().isEmpty());
	}

	@Test
	public void setWorkTimesStatus() throws Exception {
		final ObjectFactory objectFactory = new ObjectFactory();
		final WorkTimesStatusRequestType statusRequestType = objectFactory.createWorkTimesStatusRequestType();
		statusRequestType.getIds().add(13L);
		statusRequestType.setStatus(WorkTimeStatus.CLOSED);
		final JAXBElement<Boolean> response = (JAXBElement<Boolean>) webServiceTemplate.marshalSendAndReceive(objectFactory
				.createSetWorkTimesStatusRequest(statusRequestType));
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getValue());
	}

	@Test
	public void addWorkTime() throws Exception {
		final ObjectFactory objectFactory = new ObjectFactory();
		final WorkTime workTime = new WorkTime();
		workTime.setExternalUserId("WB");
		workTime.setExternalWorkItemId("OT");
		workTime.setStartTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
		workTime.setEndTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
		workTime.setDescription("timrsyncclient");
		final JAXBElement<Long> response = (JAXBElement<Long>) webServiceTemplate.marshalSendAndReceive(objectFactory.createSaveWorkTimeRequest(workTime));
		Assert.assertNotNull(response);
		Assert.assertTrue(response.getValue() > 0L);
	}

}
