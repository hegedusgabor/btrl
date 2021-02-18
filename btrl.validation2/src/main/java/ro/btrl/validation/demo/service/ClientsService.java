package ro.btrl.validation.demo.service;

import java.util.List;

import ro.btrl.validation.demo.entity.Client;

public interface ClientsService {

	public List<Client> findAll();

	public Client findBycnp(String cnp);

	
}
