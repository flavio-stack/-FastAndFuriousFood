/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.flavio.FastAndFuriousFood1.Entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;



@Entity
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column (name = "nome_cliente")
    private String nomeCliente;
    
    @Column 
    private double valorTotal;
    

    
    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.ABERTO;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;

   
    public long getId() {
        return id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }


    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
        for (ItemPedido item : itens) {
            item.setPedido(this);
        }
    }


    public void adicionarItem(ItemPedido item) {
        itens.add(item);
        item.setPedido(this);
    }
    

    public double calcularValorTotal() {
        double total = itens.stream()
                            .mapToDouble(ItemPedido::getSubtotal)
                            .sum();
        this.valorTotal = total;
        return total;
    }
}