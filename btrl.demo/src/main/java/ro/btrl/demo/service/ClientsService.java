package ro.btrl.demo.service;

import java.util.List;

import ro.btrl.demo.entity.Client;

public interface ClientsService {

	public List<Client> findAll();
	
	public Client findById(int theId);
	
	public Client findBycnp(String cnp);
		
	public void save(Client theClient);
	
	public void deleteById(int theId);

	
}
