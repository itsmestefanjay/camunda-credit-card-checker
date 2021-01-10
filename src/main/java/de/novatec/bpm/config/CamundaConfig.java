package de.novatec.bpm.config;

import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.spring.boot.starter.configuration.impl.AbstractCamundaConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamundaConfig extends AbstractCamundaConfiguration {

    private final Logger logger = LoggerFactory.getLogger(CamundaConfig.class);

    @Override
    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        processEngineConfiguration.setInitializeTelemetry(false);
        processEngineConfiguration.setTelemetryReporterActivate(false);
        logger.info("Telemetry disabled");
    }
}
