package github.sagubr.services;

import github.sagubr.entities.core.Issuer;
import github.sagubr.repositories.IssuerRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class IssuerService {

    @Inject
    IssuerRepository issuerRepository;

    public void save(Issuer issuer){
        issuerRepository.save(issuer);
    }

    public Issuer findByEnrolment(String enrolment){
        return issuerRepository.findByEnrolment(enrolment);
    }

    public boolean existsByEnrolment(String enrolment){return issuerRepository.existsByEnrolment(enrolment);}
}
