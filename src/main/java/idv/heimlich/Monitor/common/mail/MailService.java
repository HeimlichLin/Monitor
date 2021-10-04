package idv.heimlich.Monitor.common.mail;

public interface MailService {
	
	String create(MailCreateDTO mailCreateDTO);

	void send();

}
