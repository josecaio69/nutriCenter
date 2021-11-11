package br.com.nutriCenter.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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
/**
 * @author José Caio
 *
 */

@RestController
@RequestMapping(value = "/api/admin")
public class AdministradorResource {

	@Autowired
	private AdministradorService servico;
	
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping()
	public ResponseEntity<Administrador> create(@RequestBody @Valid Administrador adm) {
		try {
			Administrador newAdm = this.servico.create(adm);
			return new ResponseEntity<>(newAdm, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("getAll")
	public ResponseEntity<List<Administrador>> findAll() {
		try {
			List<Administrador> list = this.servico.findAll();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception error) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
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

	@DeleteMapping()
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

	@DeleteMapping("{id}")
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

	@PutMapping()
	public ResponseEntity<Administrador> update(@RequestBody @Valid Administrador adm) {
		try {
			this.servico.update(adm);
			return new ResponseEntity<>(adm, HttpStatus.OK);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Administrador> updateById(@PathVariable(value = "id") long id,
			@RequestBody @Valid Administrador adm) {
		try {
			this.servico.updateById(id, adm);
			return new ResponseEntity<>(adm, HttpStatus.OK);
		} catch (ObjectNotFoundException error) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception erro) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
