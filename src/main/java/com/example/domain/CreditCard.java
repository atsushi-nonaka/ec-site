package com.example.domain;

/**
 * クレジットカードAPIからの返答を格納するドメイン.
 * 
 * @author nonaa
 *
 */
public class CreditCard {
	/** 成功or失敗 */
	private String status;
	/** 有効期限の有無 */
	private String message;
	/** エラーの種類 */
	private String error_code;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	@Override
	public String toString() {
		return "CreditCard [status=" + status + ", message=" + message + ", error_code=" + error_code + "]";
	}

}
