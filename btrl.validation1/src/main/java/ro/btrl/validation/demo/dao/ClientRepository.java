package ro.btrl.validation.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.btrl.validation.demo.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	Optional<Client> findBycnp(String cnp);

}
