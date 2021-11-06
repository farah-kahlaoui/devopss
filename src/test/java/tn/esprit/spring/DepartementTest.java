package tn.esprit.spring;


import static org.junit.Assert.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.AfterClass;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.services.IDepartementService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartementTest {



@Autowired
IDepartementService deptService;

@Autowired
DepartementRepository departementRepository;

//


static Logger logger = LoggerFactory.getLogger(DepartementTest.class);

@BeforeClass 
public static void intro() {
	logger.info("les tests unitaires seront lancés")  ;
}

@Test(timeout=6000)
public void testgetDepartement() {
Departement dep = new Departement("devopss");
deptService.ajouterDepartement(dep);
deptService.getDepartement(dep.getId());
assertNotNull(dep.getId());
logger.info("voila le departement demandé : {} ", dep);
}

@Test(timeout=6000)
public void testajouterDepartement() {

	try {
Departement dep = new Departement("devops");
deptService.ajouterDepartement(dep);
assertNotNull(dep.getId());
logger.info("l'ajout est effectué avec succés : {} ", dep); }

catch (Exception e) {
	logger.info("l'ajout ne peut pas etre effectuer", e);
}

}





//@Test(timeout=3000)
//public void testaffecterDepartementAEntreprise() {
//	
//	Departement dep = new Departement("devops");
//	Entreprise en = new Entreprise("sagem","rse");
//	deptService.ajouterDepartement(dep);
//	
//	deptService.affecterDepartementAEntreprise(dep.getId(), en.getId());
//	assertNotNull(dep.getEntreprise());
//}
//}




@Test(timeout=6000)
public void testgetAllDepartement() {
	
	List<Departement> departements =  deptService.getAllDepartement();
	assertThat(departements).size().isNotEqualTo(null);
	logger.info("la liste des departements : " , departements);
}



@Test(timeout=6000)
public void testdeleteDepartementById() {

Departement depa = new Departement();

depa.setName("Programmation"); 
deptService.ajouterDepartement(depa);

deptService.deleteDepartementById(depa.getId());
assertNull(deptService.getDepartement(depa.getId()));
logger.info("la suppression est effectuée avec succés " ); 

}

@AfterClass
 public static  void finale() {
	logger.info("c'est la fin des tests") ;
}


}











