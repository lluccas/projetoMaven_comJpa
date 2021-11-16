package modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ProdutosHibernate")
public class Produto implements Serializable {
		private static final long serialVersionUID = 1L;
	//atributos
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private int quantidade;
	private Double preco;
	
	//metodos especiais
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", quantidade=" + quantidade + ", preco=" + preco + "]";
	}
	
	
	
	

}
