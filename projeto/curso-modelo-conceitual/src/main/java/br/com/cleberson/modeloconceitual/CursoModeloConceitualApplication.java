package br.com.cleberson.modeloconceitual;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cleberson.modeloconceitual.domain.Categoria;
import br.com.cleberson.modeloconceitual.domain.Cidade;
import br.com.cleberson.modeloconceitual.domain.Cliente;
import br.com.cleberson.modeloconceitual.domain.Endereco;
import br.com.cleberson.modeloconceitual.domain.Estado;
import br.com.cleberson.modeloconceitual.domain.ItemPedido;
import br.com.cleberson.modeloconceitual.domain.Pagamento;
import br.com.cleberson.modeloconceitual.domain.PagamentoComBoleto;
import br.com.cleberson.modeloconceitual.domain.PagamentoComCartao;
import br.com.cleberson.modeloconceitual.domain.Pedido;
import br.com.cleberson.modeloconceitual.domain.Produto;
import br.com.cleberson.modeloconceitual.domain.enumarations.EnumEstadoSigla;
import br.com.cleberson.modeloconceitual.domain.enumarations.EstadoPagamento;
import br.com.cleberson.modeloconceitual.domain.enumarations.TipoCliente;
import br.com.cleberson.modeloconceitual.repositories.CategoriaRepository;
import br.com.cleberson.modeloconceitual.repositories.CidadeRepository;
import br.com.cleberson.modeloconceitual.repositories.ClienteRepository;
import br.com.cleberson.modeloconceitual.repositories.EnderecoRepository;
import br.com.cleberson.modeloconceitual.repositories.EstadoRepository;
import br.com.cleberson.modeloconceitual.repositories.ItemPedidoRepository;
import br.com.cleberson.modeloconceitual.repositories.PagamentoRepository;
import br.com.cleberson.modeloconceitual.repositories.PedidoRepository;
import br.com.cleberson.modeloconceitual.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoModeloConceitualApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoModeloConceitualApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto prod1 = new Produto(null, "Computador", 2000.0);
		Produto prod2 = new Produto(null, "Impressora", 800.0);
		Produto prod3 = new Produto(null, "Mouse", 80.0);
		
		cat1.add(prod1);
		cat1.add(prod2);
		cat1.add(prod3);
		
		cat2.add(prod2);
		
		prod1.add(cat1);
		
		prod2.add(cat1);
		prod2.add(cat2);
		
		prod3.add(cat1);
		
		
		categoriaRepository.save(Arrays.asList(cat1, cat2));
		produtoRepository.save(Arrays.asList(prod1, prod2, prod3));
		
		List<Estado> estados = criarEstados();
		Estado sp = recuperarEstado(estados, EnumEstadoSigla.SP);
		Estado mg = recuperarEstado(estados, EnumEstadoSigla.MG);
		List<Cidade> cidadesSP = criarCidadesSP(sp);
		List<Cidade> cidadesMG = criarCidadesMG(mg);
		
		List<Cidade> todasCidades = new ArrayList<>();
		todasCidades.addAll(cidadesSP);
		todasCidades.addAll(cidadesMG);
		
		estadoRepository.save(estados);
		cidadeRepository.save(todasCidades);
		
		// Criar cliente, endereco e telefones
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "33366655544", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("1122354687", "1144556632"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "358796546", cli1, recuperarCidade(todasCidades, "Urbelândia"));
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "358542156", cli1, recuperarCidade(todasCidades, "São Paulo"));
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.save(cli1);
		enderecoRepository.save(cli1.getEnderecos());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, prod1, 0.00, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1, prod3, 0.00, 2, 80.0);
		ItemPedido ip3 = new ItemPedido(ped2, prod2, 100.00, 1, 800.0);
		
		itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));
		
		
	}

	private Cidade recuperarCidade(List<Cidade> todasCidades, String cidade) {
		return todasCidades.stream().filter(c -> c.getNome().equalsIgnoreCase(cidade)).findFirst().get();
	}

	private Estado recuperarEstado(List<Estado> estados, EnumEstadoSigla sigla) {
		return estados.stream().filter(e -> e.getSigla().equals(sigla)).findFirst().get();
	}

	private List<Cidade> criarCidadesSP(Estado estado) {
		return Arrays.asList(
				new Cidade(null, "Campinas", estado),
				new Cidade(null, "São Paulo", estado)
				);
	}

	private List<Cidade> criarCidadesMG(Estado estado) {
		return Arrays.asList(new Cidade(null, "Urbelândia", estado));
	}

	private List<Estado> criarEstados() {
		return Arrays.asList(
				
				new Estado(null, EnumEstadoSigla.AC.getNome(), EnumEstadoSigla.AC),
				new Estado(null, EnumEstadoSigla.AL.getNome(), EnumEstadoSigla.AL),
				new Estado(null, EnumEstadoSigla.AP.getNome(), EnumEstadoSigla.AP),
				new Estado(null, EnumEstadoSigla.AM.getNome(), EnumEstadoSigla.AM),
				new Estado(null, EnumEstadoSigla.BA.getNome(), EnumEstadoSigla.BA),
				new Estado(null, EnumEstadoSigla.CE.getNome(), EnumEstadoSigla.CE),
				new Estado(null, EnumEstadoSigla.DF.getNome(), EnumEstadoSigla.DF),
				new Estado(null, EnumEstadoSigla.ES.getNome(), EnumEstadoSigla.ES),
				new Estado(null, EnumEstadoSigla.GO.getNome(), EnumEstadoSigla.GO),
				new Estado(null, EnumEstadoSigla.MA.getNome(), EnumEstadoSigla.MA),
				new Estado(null, EnumEstadoSigla.MG.getNome(), EnumEstadoSigla.MG),
				new Estado(null, EnumEstadoSigla.MS.getNome(), EnumEstadoSigla.MS),
				new Estado(null, EnumEstadoSigla.MG.getNome(), EnumEstadoSigla.MG),
				new Estado(null, EnumEstadoSigla.PA.getNome(), EnumEstadoSigla.PA),
				new Estado(null, EnumEstadoSigla.PB.getNome(), EnumEstadoSigla.PB),
				new Estado(null, EnumEstadoSigla.PR.getNome(), EnumEstadoSigla.PR),
				new Estado(null, EnumEstadoSigla.PE.getNome(), EnumEstadoSigla.PE),
				new Estado(null, EnumEstadoSigla.PI.getNome(), EnumEstadoSigla.PI),
				new Estado(null, EnumEstadoSigla.RJ.getNome(), EnumEstadoSigla.RJ),
				new Estado(null, EnumEstadoSigla.RN.getNome(), EnumEstadoSigla.RN),
				new Estado(null, EnumEstadoSigla.RS.getNome(), EnumEstadoSigla.RS),
				new Estado(null, EnumEstadoSigla.RO.getNome(), EnumEstadoSigla.RO),
				new Estado(null, EnumEstadoSigla.RR.getNome(), EnumEstadoSigla.RR),
				new Estado(null, EnumEstadoSigla.SC.getNome(), EnumEstadoSigla.SC),
				new Estado(null, EnumEstadoSigla.SP.getNome(), EnumEstadoSigla.SP),
				new Estado(null, EnumEstadoSigla.SE.getNome(), EnumEstadoSigla.SE),
				new Estado(null, EnumEstadoSigla.TO.getNome(), EnumEstadoSigla.TO)
				
				);
	}
}
