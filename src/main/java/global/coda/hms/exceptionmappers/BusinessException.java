package global.coda.hms.exceptionmappers;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * The type Business exception.
 *
 * @author kutty
 */
@Provider
public class
BusinessException extends Exception implements ExceptionMapper<BusinessException> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new Business exception.
	 */
	public BusinessException() {
		super("Bad Request");
	}

	/**
	 * Instantiates a new Business exception.
	 *
	 * @param string the string
	 */
	public BusinessException(String string) {
		super(string);
	}

	/**
	 * Instantiates a new Business exception.
	 *
	 * @param exception the exception
	 */
	public BusinessException(UserNotFoundException exception) {

    }

    /**
	 *
	 */
	@Override
	public Response toResponse(BusinessException exception) {
		return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity(exception.getMessage())
				.type(MediaType.TEXT_PLAIN).build();
	}

}
