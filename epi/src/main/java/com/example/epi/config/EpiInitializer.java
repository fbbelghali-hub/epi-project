package com.example.epi.config;


import com.example.epi.entity.Epi;
import com.example.epi.enums.TypeEPI;
import com.example.epi.repository.EpiRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EpiInitializer  implements CommandLineRunner {
    private final EpiRespository epiRepository;

    @Override
    public void run(String... args) {

        if (epiRepository.count() == 0) {

            Epi gilet = new Epi(TypeEPI.GILET);
            Epi chaussures = new Epi(TypeEPI.CHAUSSURES);
            Epi lunettes = new Epi(TypeEPI.LUNETTES);
            Epi gants = new Epi(TypeEPI.GANTS);

            epiRepository.save(gilet);
            epiRepository.save(chaussures);
            epiRepository.save(lunettes);
            epiRepository.save(gants);
        }
    }
}
