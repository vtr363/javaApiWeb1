package br.com.senac.service;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import br.com.senac.entity.Aluno;
import br.com.senac.repository.AlunoRepository;

class AlunoServiceTest {
	
	private static final Integer id = 1; 
	private static final String nome = "Thiago";
	private static final String sobrenome = "Farrula";
	private static final String email = "ThFarrula@gmail.com";
	
	private Aluno aluno;
	private Optional<Aluno> alunoOptional;
	
	@InjectMocks
	private AlunoService alunoService;
	
	@Mock
	private AlunoRepository alunoRepository;
	
	@Mock
	private ModelMapper mapper;
	
	private void startAluno() {
		aluno = new Aluno(id, nome, sobrenome, email);
		alunoOptional = Optional.of(new Aluno(id,nome,sobrenome,email));
	}
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		startAluno();
	}
	
	@Test
	void whenFindByIdThenReturnAnAlunoInstance() {
		Mockito.when(alunoRepository.findById(Mockito.anyInt())).thenReturn(alunoOptional);
		Aluno response = alunoService.getAlunoById(id);
		Assertions.assertEquals(Aluno.class, response.getClass());
		Assertions.assertEquals(id, response.getId());
		Assertions.assertEquals(nome, response.getNome());
		Assertions.assertEquals(sobrenome, response.getSobreNome());
		Assertions.assertEquals(email, response.getEmail());
	}
	@Test
	void whenFindAllThenReturnListOfAluno() {
		Mockito.when(alunoRepository.findAll()).thenReturn(List.of(aluno));
		List<Aluno> response = alunoService.buscarTodosAlunos();
		Assertions.assertNotNull(response);
		Assertions.assertEquals(1, response.size());
		Assertions.assertEquals(id, response.get(0).getId());
		Assertions.assertEquals(nome, response.get(0).getNome());
		Assertions.assertEquals(sobrenome, response.get(0).getSobreNome());
		Assertions.assertEquals(email, response.get(0).getEmail());
	}
	@Test
	void whenCreateThenReturnSucces() {
		Mockito.when(alunoRepository.save(Mockito.any())).thenReturn(aluno);
		Aluno response = alunoService.salvar(aluno);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(Aluno.class, response.getClass());
		Assertions.assertEquals(id, response.getId());
		Assertions.assertEquals(nome, response.getNome());
		Assertions.assertEquals(sobrenome, response.getSobreNome());
		Assertions.assertEquals(email, response.getEmail());
	}
	
	@Test
	void testSalvar() {
		fail("Not yet implemented");
	}

	@Test
	void testBuscarTodosAlunos() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAlunoById() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteAluno() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateAluno() {
		fail("Not yet implemented");
	}

}
