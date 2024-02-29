package com.tcc.reforma.reforma;



import com.tcc.reforma.reforma.repository.ProfissaoRepository;
import com.tcc.reforma.reforma.repository.ProfissionalRepository;
import com.tcc.reforma.reforma.repository.ProjetoRepository;
import com.tcc.reforma.reforma.repository.UsuarioRepository;
import com.tcc.reforma.reforma.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ReformaApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(ReformaApplication.class, args);

	}

	@Autowired
	private ProfissionalRepository profissional;
	@Autowired
	private UsuarioRepository usuario;

	@Autowired
	private ProfissaoRepository profissao;

	@Autowired
	private ProjetoRepository projeto;

	@Autowired
	private S3Service s3Service;

	@Override
	public void run(String... args) throws Exception {

		//PROFISSIONAIS
		/*Usuario usu1 = new Usuario("Usuario1", TipoUsuario.PESSOA_FISICA, "059442551145",
				"email1@contato.com", new BCryptPasswordEncoder().encode("senha1"),
				"contato1", "contato1", "DF", "Samambaia");

		Usuario usu2 = new Usuario("Usuario2", TipoUsuario.PESSOA_FISICA, "099172551183",
				"email2@contato.com", new BCryptPasswordEncoder().encode("senha2"),
				  "contato2", "contato2", "SP", "São Paulo");

		Usuario usu3 = new Usuario("Usuario3", TipoUsuario.PESSOA_JURIDICA, "9032840923480923",
				"email3@contato.com", new BCryptPasswordEncoder().encode("senha3"),
				 "contato3", "contato3", "PA", "Belém");


		//CLIENTES
		Usuario cli1 = new Usuario("Cliente1", TipoUsuario.PESSOA_FISICA, "111442551912",
				"email7@contato.com", new BCryptPasswordEncoder().encode("senha4"),
				 "contato1", "contato1", "DF", "Taguatinga");

		Usuario cli2 = new Usuario("Cliente2", TipoUsuario.PESSOA_FISICA, "333172551107",
				"email8@contato.com", new BCryptPasswordEncoder().encode("senha5"),
				 "contato2", "contato2", "RJ", "Rio de Janeiro");

		Usuario cli3 = new Usuario("Cliente3", TipoUsuario.PESSOA_JURIDICA, "7772840923480222",
				"email9@contato.com", new BCryptPasswordEncoder().encode("senha6"),
				 "contato3", "contato3", "BH", "Porto Seguro");



		usuario.saveAll(Arrays.asList(usu1, usu2, usu3, cli1, cli2, cli3));

		//DADOS DOS PROFISSIONAIS
		Profissional pro1 = new Profissional("Descrição de habilidades1", Arrays.asList("Ceilandia", "Taguatinga"), true, usu1);
		Profissional pro2 = new Profissional("Descrição de habilidades2", Arrays.asList("São Paulo"), false, usu2);
		Profissional pro3 = new Profissional("Descrição de habilidades3", Arrays.asList("Belém"), true, usu3);

		profissional.saveAll(Arrays.asList(pro1, pro2, pro3));
		usu1.setProfissional(pro1);
		usu2.setProfissional(pro2);
		usu3.setProfissional(pro3);
		usuario.saveAll(Arrays.asList(usu1, usu2, usu3));

		//PROFISSAO DOS PROFISSIONAIS
		Profissao p1 = new Profissao(Ocupacao.ARQUITETO, "10XX/HZ", pro1);
		Profissao p2 = new Profissao(Ocupacao.DSGN_INTERIORES, "", pro1);
		Profissao p3 = new Profissao(Ocupacao.PEDREIRO, "", pro2);
		Profissao p4 = new Profissao(Ocupacao.ELETRICISTA, "", pro2);
		Profissao p5 = new Profissao(Ocupacao.ENGENHEIRO, "10XX/HZ", pro3);


		//profissao.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		 final String lorem1 = "Nulla in ultrices lectus. Fusce ultrices libero in sem pellentesque placerat. " +
				"Aliquam venenatis tortor urna, vel posuere risus pharetra eu. Sed eu purus lectus." +
				" Nunc massa metus, fringilla vel dictum a, cursus in ligula. Duis sed tortor auctor, ";

		 final String lorem2 = "Curabitur id mi quis eros euismod convallis at nec quam. Ut faucibus quam id nunc" +
				 "efficitur, ac iaculis justo dignissim. Nam sed blandit lorem. Praesent tempus diam vitae lectus " +
				 "vestibulum, ac fringilla metus cursus.";

		//PROJETOS
		Projeto proj1 = new Projeto(cli1, "Reforma banheiro", lorem1, "São Paulo", "São Paulo", LocalDate.now());
		Projeto proj2 = new Projeto(cli1, "Reforma quarto", lorem2, "Distrito Federal", "Samambaia", LocalDate.now());
		Projeto proj3 = new Projeto(cli2, "Levantar parede", lorem1, "Bahia","Porto Seguro", LocalDate.now());
		Projeto proj4 = new Projeto(cli3, "Troca de piso", lorem2, "Amazonas", "Manaus", LocalDate.now());

		Projeto proj5 = new Projeto(cli2, "Segundo andar", lorem1, "Tocantins", "Palmas", LocalDate.now());
		Projeto proj6 = new Projeto(cli3, "Pintar paredes", lorem2, "Distrito Federal", "Taguatinga", LocalDate.now());

		projeto.saveAll(Arrays.asList(proj1, proj2, proj3, proj4, proj5, proj6));

		proj1.setProfissional(pro1);
		proj3.setProfissional(pro1);
		proj2.setProfissional(pro2);
		proj4.setProfissional(pro3);

		projeto.saveAll(Arrays.asList(proj1, proj2, proj3, proj4));*/

	}

}
