package fico.crm.process.application.port.in;

import java.io.InputStream;

public interface ProcessAdminUseCase {
    String deploy (String processCategory, String processName, String version, InputStream processInputStream);
}
