package com.example.batch;

import org.springframework.batch.core.ExitStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class TruncateServiceImpl implements TruncateService{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Stepの処理クラスです.
	 * @return 処理が完了したときに返す値（COMPLETED）
	 */
	public ExitStatus execute() {
		jdbcTemplate.execute("TRUNCATE TABLE items");
		return ExitStatus.COMPLETED;

	}
}
