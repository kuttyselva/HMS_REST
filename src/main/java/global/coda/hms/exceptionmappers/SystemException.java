package global.coda.hms.exceptionmappers;


import global.coda.hms.applicationconstants.ResponseStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * The type System exception.
 *
 * @author kutty  Thrown in case of system errors
 */
@Provider
public class SystemException extends Exception implements ExceptionMapper<SystemException> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(SystemException.class);

	/**
	 * Instantiates a new System exception.
	 *
	 * @param message the message
	 */
	public SystemException(String message) {
    	super(message);
    }

	public SystemException() {

	}


	/**
	 *
	 */
	@Override
	public Response toResponse(SystemException exception) {
		LOGGER.error(exception.getMessage());
		return Response.status(Integer.parseInt(ResponseStatus.SERVER_ERROR)).entity(exception.getMessage())
				.type("text/plain").build();
	}

}
