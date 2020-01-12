package global.coda.hms.config;

import global.coda.hms.api.DoctorAPI;
import global.coda.hms.api.PatientAPI;
import global.coda.hms.exceptionmappers.BusinessException;
import global.coda.hms.exceptionmappers.SystemException;
import org.glassfish.jersey.server.ResourceConfig;


/**
 * @author VC
 */
public class JerseyConfig extends ResourceConfig {
    /**
     * constructor.
     */
    public JerseyConfig() {
        register(PatientAPI.class);
        register(DoctorAPI.class);
        register(BusinessException.class);
        register(SystemException.class);

    }
}
