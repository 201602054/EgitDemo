package com.webserver.exception;
/**
 * �������쳣
 * ��HttpRequest��������ʱ���ִ�����Ϊ������ʱ�׳����쳣
 * 
 * @author Tedu
 *
 */
public class EmptyRequestException extends Exception {
	private static final long serialVersionUID = 1L;//�汾��

	/**
	 * д��Exception���еĹ��췽��
	 */
	public EmptyRequestException() {
		super();
		
	}
	public EmptyRequestException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public EmptyRequestException(String message, Throwable cause) {
		super(message, cause);
	}
	public EmptyRequestException(String message) {
		super(message);
	}

	public EmptyRequestException(Throwable cause) {
		super(cause);
	}
	
	
	
}
