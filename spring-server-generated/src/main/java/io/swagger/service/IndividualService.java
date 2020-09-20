package io.swagger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.api.NotFoundException;
import io.swagger.model.Individual;
import io.swagger.repository.IndividualRepository;

@Service
public class IndividualService {

	@Autowired
	IndividualRepository individualRepository;

	public Individual createIndividual(Individual individual)  {
		
			individualRepository.save(individual);
		
		return individual;
	}

	public Individual retrieveIndividual(String id) throws NotFoundException {

		Individual ind = individualRepository.findById(id).orElse(null);
		if (ind == null)
			throw new NotFoundException(404, "Individual ID is not found, please enter valid ID");
		return ind;

	}

	public Iterable<Individual> listIndividual() {

		return individualRepository.findAll();

	}

	public Individual patchIndividual(String id, String fullName) throws NotFoundException {

		Individual individualDBObj = retrieveIndividual(id);
		individualDBObj.setFullName(fullName);
		return individualRepository.save(individualDBObj);

	}

	public void deleteIndividual(String id) {

		individualRepository.deleteById(id);
		
		
	}
}
