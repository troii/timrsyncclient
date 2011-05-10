package com.troii.timrsyncclient;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.troii.timr.timrsync.GetTasksResponse;
import com.troii.timr.timrsync.ObjectFactory;
import com.troii.timr.timrsync.Task;

public class TimrSyncClientDemo {

	public static void main(final String[] args) {
		final ApplicationContext context = new ClassPathXmlApplicationContext("basecontext.xml");
		final WebServiceTemplate webServiceTemplate = (WebServiceTemplate) context.getBean("webServiceTemplate");
		final ObjectFactory objectFactory = new ObjectFactory();
		final GetTasksResponse getTasksResponse = (GetTasksResponse) webServiceTemplate
				.marshalSendAndReceive(objectFactory.createGetTasksRequest(null));
		for (final Task task : getTasksResponse.getTasks()) {
			System.out.println(task.getName());
		}
	}

}
