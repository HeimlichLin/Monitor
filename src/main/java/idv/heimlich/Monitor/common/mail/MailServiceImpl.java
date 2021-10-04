package idv.heimlich.Monitor.common.mail;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idv.heimlich.Monitor.domain.bean.MailqueDo;
import idv.heimlich.Monitor.domain.dao.MailqueDAO;
import idv.heimlich.Monitor.domain.dao.impl.MailqueDAOImpl;
import idv.heimlich.Monitor.domain.service.GetNumberService;
import idv.heimlich.Monitor.domain.service.impl.GetNumberServiceImpl;

/**
 * 信件服務
 */
public class MailServiceImpl implements MailService {
	
	private final static Logger LOG = LoggerFactory.getLogger(MailServiceImpl.class);
	private transient GetNumberService getNumberService = GetNumberServiceImpl.get();

	private transient MailqueDAO mailqueDAO = MailqueDAOImpl.INSTANCE;

	private static MailService INSTANCT = new MailServiceImpl();

	public static MailService get() {
		return INSTANCT;
	}
	
	@Override
	public String create(MailCreateDTO mailCreateDTO) {
//		DoXdaoSession doXdaoSession = XdaoSessionManager.getDoXdaoSession();
//		final String id = this.getNumberService.getCount(GetNumberType.YYYYMMDD12N);
//		final MailquePo mailquePo = new MailquePo();
//		mailquePo.setMailId(id);
//		mailquePo.setCreateDate(YYYYMMDDHHMMSSUtils.getText());
//		mailquePo.setFilePath(mailCreateDTO.getFilePath());
//		mailquePo.setMailBcc(mailCreateDTO.getMailBcc());
//		mailquePo.setMailCc(mailCreateDTO.getMailCc());
//		mailquePo.setMailFrom(mailCreateDTO.getMailFrom());
//		mailquePo.setMailTo(mailCreateDTO.getMailTo());
//		mailquePo.setMessage(mailCreateDTO.getMessage());
//		mailquePo.setPgmId(mailCreateDTO.getPgmId());
//		mailquePo.setRemarks(mailCreateDTO.getRemarks());
//		mailquePo.setSendDate(null);
//		mailquePo.setSubject(mailCreateDTO.getSubject());
//		mailquePo.setStatus(MailqueStatus.W.name());
//		mailqueDAO.insert(doXdaoSession, mailquePo);
//		return id;
		return null;
	}

	@Override
	public void send() {
		List<MailqueDo> mails = mailqueDAO.queryMails();
//
//		for (MailquePo mailquePo : mails) {
//			MailFace face = MailqueWarpPo.newMailFace(mailquePo);
//			MailBean mailBean = new MailBean(face);
//			try {
//				mailBean.sendMail();
//				mailqueDAO.update(mailquePo.getMailId(), MailqueStatus.C);
//			} catch (MessagingException e) {
//				LOG.error("sendamil error", e);
//				if (mailquePo.getStatus().equals(MailqueStatus.R.name())) {
//					mailqueDAO.update(mailquePo.getMailId(), MailqueStatus.E);
//				} else {
//					mailqueDAO.update(mailquePo.getMailId(), MailqueStatus.R);
//				}
//
//			}
//		}
	}

}
