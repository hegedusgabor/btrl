package ro.btrl.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.btrl.demo.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	public List<Client> findAllByOrderByLastNameAsc();
	
	public Client findByFirstName(String name); 
	
	Optional<Client> findBycnp(String cnp);

	
}
