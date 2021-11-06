package tn.esprit.spring.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;


@Service
public class DepartementServiceImpl implements IDepartementService

{
	@Autowired
	DepartementRepository departementRepository;
	
	@Autowired
	EntrepriseRepository entrepriseRepository;
	
	Logger logger = LoggerFactory.getLogger(DepartementServiceImpl.class);

	

	@Override
	public void ajouterDepartement(Departement dept) {
	
		departementRepository.save(dept);
		
		

		
	}

	
		
		@Override
		public Departement getDepartement(int departemntId) {
			int startTime=(int)System.currentTimeMillis();
		
			departementRepository.findById(departemntId);
			
			int endTime=(int)System.currentTimeMillis();
			if((endTime-startTime >6)){
				logger.info("Timeout");
				
			}
			return null;
			
		}
		
	
	
	@Override
	public void deleteDepartementById(int departemntId) {
		Optional<Departement> deptest = departementRepository.findById(departemntId) ;
		
		if (deptest.isPresent()) {
			
			Departement deptt = deptest.get() ;
		
			departementRepository.delete(deptt);	}
	}
	@Override
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		  while ((depId !=0) && (entrepriseId !=0 )) { 
			  
		         if (entrepriseRepository.findById(entrepriseId).isPresent() && departementRepository.findById(depId).isPresent() ) {
		        	 
		         
				Entreprise entrepriseManagedEntity = entrepriseRepository.findById(entrepriseId).get();
		 
				Departement depManagedEntity = departementRepository.findById(depId).get();
				
				
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				departementRepository.save(depManagedEntity);
		
		 
	 
	}}
	}
	@Override
	public List<Departement> getAllDepartement() {
				return (List<Departement>) departementRepository.findAll();
	}

	
}


