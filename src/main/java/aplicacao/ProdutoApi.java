package aplicacao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.Produto;

public class ProdutoApi {

	public static void main(String[] args) {

		pesquisarPreco();
	}

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaHib");

	static EntityManager em = emf.createEntityManager();

	static Scanner s = new Scanner(System.in);

	static Produto p = new Produto();

	public static void inserir(String nome, int qtd, double preco) {

		Produto p = new Produto();

		p.setNome(nome);
		p.setQuantidade(qtd);
		p.setPreco(preco);

		em.getTransaction().begin();

		em.persist(p);

		em.getTransaction().commit();
		em.close();

	}

	public static void cadastrar() {

		System.out.println("Digite a quantidade de produtos que deseja cadastrar: ");
		int qtd = s.nextInt();
		s.nextLine();

		for (int i = 0; i < qtd; i++) {
			Produto p = new Produto();

			System.out.println("Qual o nome do produto: ");
			p.setNome(s.next());

			System.out.println("Qual a quantidade: ");
			p.setQuantidade(s.nextInt());
			s.nextLine();

			System.out.println("Qual o preço: ");
			p.setPreco(s.nextDouble());

			em.getTransaction().begin();

			em.persist(p);

			em.getTransaction().commit();

		}

		em.close();

	}

	public static void excluir() {

		int opp;
		do {

			System.out.println("Digite o ID a ser excluido: ");
			int id = s.nextInt();
			s.nextLine();
			p.setId(id - 1);

			Produto p = em.find(Produto.class, id);

			em.getTransaction().begin();

			em.remove(p);

			em.getTransaction().commit();

			System.out.println("ID excluido com sucesso!!!!!");
			System.out.println("**************************************");
			System.out.println("Digite 1 para continuar ou 2 para sair: ");
			opp = s.nextInt();
			s.nextLine();

		} while (opp == 1);
		System.out.println("Saindo do sistema....");
		em.close();
	}

	public static void excluirID(int id) {
		Produto p = em.find(Produto.class, id);

		em.getTransaction().begin();

		em.remove(p);

		em.getTransaction().commit();

		em.close();
	}

	public static void mostrar(Object a) {
		System.out.println(a);
	}

	public static Produto localizarID(int id) {
		Produto p = em.find(Produto.class, id);

		if (p == null) {
			System.out.println("ID não encontrado");
		} else {
			mostrar(p);
		}

		return p;
	}

	public static Produto localizarid() {
		int opp;

		do {
			System.out.println("Digite o ID a ser localizado: ");
			int id = s.nextInt();
			s.nextLine();
			Produto p = em.find(Produto.class, id);

			if (p == null) {
				System.out.println("ID não encontrado");
			} else {
				mostrar(p);
			}

			System.out.println("Digite 1 para localizar outro ID ou 2 para sair: ");
			opp = s.nextInt();
			s.nextLine();
		} while (opp == 1);
		System.out.println("Saindo.........");
		return p;
	}

	public static void atualizar(int id) {
		Produto p = localizarID(id);

		em.getTransaction().begin();

		p.setNome("Lapiseira");
		p.setPreco(5.99);
		p.setQuantidade(20);

		em.getTransaction().commit();

		em.close();

		System.out.println("Produto atualizado!!!!!!");
		mostrar(p);
	}

	public static void atualizarID() {
		String opp;
		int op;
		do {
			System.out.println("Digite a ID a ser atualizada: ");
			int id = s.nextInt();
			s.nextLine();
			Produto p = localizarID(id);

			em.getTransaction().begin();

			System.out.println("Deseja alterar o nome: " + p.getNome() + " [s] ou [n]");
			opp = s.next();
			if (opp.equalsIgnoreCase("s")) {
				System.out.println("Qual o nome: ");
				p.setNome(s.next());
			}
			System.out.println("Deseja alterar o  preço: " + p.getPreco() + " [s] ou [n]?");
			opp = s.next();
			if (opp.equalsIgnoreCase("s")) {
				System.out.println("Qual o preço: ");
				p.setPreco(s.nextDouble());
			}
			System.out.println("Deseja alterar a quantidade: " + p.getQuantidade() + " [s] ou [n]?");
			opp = s.next();
			if (opp.equalsIgnoreCase("s")) {
				System.out.println("Qual a quantidade: ");
				p.setQuantidade(s.nextInt());
				s.nextLine();
			}

			System.out.println("Produto atualizado!!!!!!");
			mostrar(p);
			em.getTransaction().commit();
			System.out.println("Deseja continuar 1 sim e 2 sair: ");
			op = s.nextInt();
			s.nextLine();

		} while (op == 1);

		em.close();
		System.out.println("Saindo..........");

	}

	@SuppressWarnings("unchecked")
	public static void mostrarTudo() {
		Query q = em.createQuery("SELECT p FROM Produto p");
		List<Produto> prod = q.getResultList();
		/*
		 * for(int i = 0; i< prod.size(); i++) { System.out.println(prod.get(i)); }
		 */

		prod.forEach(System.out::println);
	}

	@SuppressWarnings("unchecked")
	public static void buscarNome() {
		String nome;

		System.out.println("Digite o nome que deseja pesquisar: ");
		nome = s.next();

		Query q = em.createQuery("SELECT p FROM Produto p WHERE nome LIKE '" + nome + "%'");
		List<Produto> prod = q.getResultList();

		prod.forEach(System.out::println);
	}

	@SuppressWarnings("unchecked")
	public static void buscarID() {
		int id;

		System.out.println("Digite o ID que deseja pesquisar: ");
		id = s.nextInt();
		s.nextLine();

		Query q = em.createQuery("SELECT p FROM Produto p WHERE id LIKE " + id);
		List<Produto> prod = q.getResultList();

		prod.forEach(System.out::println);
	}

	@SuppressWarnings("unchecked")
	public static void pesquisarPreco() {
		double preco1, preco2;
		Query q = em.createQuery("SELECT p FROM Produto p ");

		List<Produto> prod = q.getResultList();

		System.out.println("Digite a faixa de preço que deseja pesquisar ");
		System.out.println("De: ");
		preco1 = s.nextDouble();

		System.out.println("Até: ");
		preco2 = s.nextDouble();

		for (int i = 0; i < prod.size(); i++) {
			if (preco1 <= prod.get(i).getPreco() && preco2 >= prod.get(i).getPreco()) {

				System.out.println(prod.get(i));
			}

		}

	}
}
