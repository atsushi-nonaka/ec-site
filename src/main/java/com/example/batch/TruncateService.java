package com.example.batch;

import org.springframework.batch.core.ExitStatus;

public interface TruncateService {
	ExitStatus execute();
}
