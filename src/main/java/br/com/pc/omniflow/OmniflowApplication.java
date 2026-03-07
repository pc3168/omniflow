package br.com.pc.omniflow;

import br.com.pc.omniflow.service.cadastro.NfeXmlService;
import br.com.pc.omniflow.service.parser.NfeParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OmniflowApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OmniflowApplication.class, args);
	}

	@Autowired
	NfeXmlService nfeXmlService;

	@Autowired
	NfeParserService nfeParserService;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("-------------------------INICIO--------------------------");
//		testeService.teste(1L);
//		testeService.teste2(1L);
		java.io.File file = new java.io.File("xml");

		System.out.println(file.getAbsolutePath());
		nfeXmlService.importar(1L, file);

		nfeParserService.processarPendentes(1L);

	}
}
