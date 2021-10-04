package idv.heimlich.Monitor.domain.controller.job.clean;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象清檔服務
 */
public abstract class AbstactCleanServiceImpl implements CleanService {
	
	private final static Logger LOG =LoggerFactory.getLogger(AbstactCleanServiceImpl.class);
	
	@Override
	final public void clean(CleanDTO dto) {
		try {
			this.myClean(dto);
		} catch (final Exception e) {
			LOG.error("clean fail dto:{}", ToStringBuilder.reflectionToString(dto), e);
		} finally {
		}

	}

	abstract void myClean(CleanDTO dto) throws Exception;

}
