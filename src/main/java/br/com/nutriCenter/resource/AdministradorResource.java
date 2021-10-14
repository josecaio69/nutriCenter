package br.com.nutriCenter.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriCenter.exception.ObjectNotFoundException;
import br.com.nutriCenter.model.Administrador;
import br.com.nutriCenter.services.AdministradorService;

@RestController
@RequestMapping(value = "/api")
public class AdministradorResource {

	@Autowired
	private AdministradorService servico;

	@PostMapping("/admin")
	public ResponseEntity<Administrador> create(@RequestBody Administrador adm) {
		try {
			Administrador newAdm = this.servico.create(adm);
			return new ResponseEntity<>(newAdm, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/admins")
	public ResponseEntity<List<Administrador>> findAll() {
		try {
			List<Administrador> list = this.servico.findAll();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception error) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/admin/{id}")
	public ResponseEntity<Optional<Administrador>> findById(@PathVariable(value = "id") long id) {
		try {
			var adm = this.servico.findById(id);
			return new ResponseEntity<>(adm, HttpStatus.OK);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/admin")
	public ResponseEntity<Administrador> delete(@RequestBody Administrador adm) {
		try {
			this.servico.delete(adm);
			return new ResponseEntity<>(adm, HttpStatus.NO_CONTENT);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/admin/{id}")
	public ResponseEntity<Administrador> deletePeloId(@PathVariable(value = "id") long id) {
		try {
			this.servico.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/admin")
	public ResponseEntity<Administrador> update(@RequestBody Administrador adm) {
		try {
			this.servico.update(adm);
			return new ResponseEntity<>(adm, HttpStatus.OK);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
