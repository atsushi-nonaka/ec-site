package com.example.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JobStartEndListener extends JobExecutionListenerSupport{
	
	private final JdbcTemplate template;
	
	@Autowired
	public JobStartEndListener(JdbcTemplate template) {
		this.template = template;
	}
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		super.beforeJob(jobExecution);
		System.out.println("Start");
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		super.afterJob(jobExecution);
		System.out.println("Finish");
	}
}