package global.coda.hms.exceptionmappers;


import global.coda.hms.applicationconstants.ResponseStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author kutty
 *
 * Thrown in case of system errors
 *
 */
@Provider
public class SystemException extends Exception implements ExceptionMapper<SystemException> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(SystemException.class);


	/**
	 *
	 */
	@Override
	public Response toResponse(SystemException exception) {
		LOGGER.error(exception.getMessage());
		return Response.status(Integer.parseInt(ResponseStatus.SERVER_ERROR)).entity("Something bad occurred")
				.type("text/plain").build();
	}

}
