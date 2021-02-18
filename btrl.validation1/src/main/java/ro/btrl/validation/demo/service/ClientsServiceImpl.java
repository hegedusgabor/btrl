package ro.btrl.validation.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.btrl.validation.demo.dao.ClientRepository;
import ro.btrl.validation.demo.entity.Client;
import ro.btrl.validation.demo.exceptions.ClientNotFoundException;

@Service
public class ClientsServiceImpl implements ClientsService {

	private ClientRepository clientRepository;
	
	@Autowired
	public ClientsServiceImpl(ClientRepository theClientRepository) {
		clientRepository = theClientRepository;
	}
	
	@Override
	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	
	
	@Override
	public Client findBycnp(String cnp) {
		Optional<Client> result = clientRepository.findBycnp(cnp);
		
		Client theClient = null;
		
		if (result.isPresent()) {
			theClient = result.get();
		}
		else {
			// we didn't find the client
			throw new ClientNotFoundException("Did not find client with cnp - " + cnp);
		}
		
		return theClient;
	}



}






