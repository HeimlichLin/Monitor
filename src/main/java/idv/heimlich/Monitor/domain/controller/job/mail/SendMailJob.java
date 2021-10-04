package idv.heimlich.Monitor.domain.controller.job.mail;

import idv.heimlich.Monitor.common.mail.MailService;
import idv.heimlich.Monitor.common.mail.MailServiceImpl;

public class SendMailJob {

	private MailService mailService = MailServiceImpl.get();

	public void doSend() {
		this.mailService.send();
	}
	
}
