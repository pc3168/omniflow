package br.com.pc.omniflow.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Value("${app.log.console.enabled:true}")
    private boolean consoleEnabled;

    @Value("${app.log.file.enabled:true}")
    private boolean fileEnabled;

    // Usamos o Logger do SLF4J (Logback)
    private static final Logger logger = LoggerFactory.getLogger(LogService.class);

    public void info(Class<?> clazz, String mensagem) {
        if (consoleEnabled || fileEnabled) {
            // O logger do SLF4J já sabe lidar com arquivo/console se configurado
            LoggerFactory.getLogger(clazz).info(mensagem);
        }
    }

    public void erro(Class<?> clazz, String mensagem, Throwable throwable) {
        // Você disse: erro imprime sempre, independente de estar ativo ou não
        Logger classLogger = LoggerFactory.getLogger(clazz);
        classLogger.error("!!! ERRO DETECTADO EM {}: {} !!!", clazz.getSimpleName(), mensagem);

        if (throwable != null) {
            classLogger.error("Detalhes da exceção: ", throwable);
        }
    }
}