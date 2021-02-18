package ro.btrl.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.btrl.demo.dao.ClientRepository;
import ro.btrl.demo.entity.Client;

@Service
public class ClientServiceImpl implements ClientsService {

	private ClientRepository clientRepository;
	
	@Autowired
	public ClientServiceImpl(ClientRepository theClientRepository) {
		clientRepository = theClientRepository;
	}
	
	@Override
	public List<Client> findAll() {
		return clientRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Client findById(int theId) {
		Optional<Client> result = clientRepository.findById(theId);
		
		Client theClient = null;
		
		if (result.isPresent()) {
			theClient = result.get();
		}
		
		return theClient;
	}

	@Override
	public void save(Client theClient) {
		clientRepository.save(theClient);
	}

	@Override
	public void deleteById(int theId) {
		clientRepository.deleteById(theId);
	}

	
	@Override
	public Client findBycnp(String cnp) {
		Optional<Client> result = clientRepository.findBycnp(cnp);
		
		Client theClient = null;
		
		if (result.isPresent()) {
			theClient = result.get();
		}
		
		return theClient;
	}
}






