package br.com.pc.omniflow.service;

import br.com.pc.omniflow.domain.repository.EntidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TesteService {

    @Autowired
    EntidadeRepository entidadeRepository;

    public void teste(){
        entidadeRepository.findAll();
    }
}
