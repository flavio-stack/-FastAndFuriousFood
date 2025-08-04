/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.flavio.FastAndFuriousFood1.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import local.flavio.FastAndFuriousFood1.Entities.ItemPedido;
import local.flavio.FastAndFuriousFood1.Entities.Pedido;
import local.flavio.FastAndFuriousFood1.Entities.Produto;
import local.flavio.FastAndFuriousFood1.Entities.StatusPedido;
import local.flavio.FastAndFuriousFood1.Repository.PedidoRepository;
import local.flavio.FastAndFuriousFood1.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ppjata


/**
 *
 * @author ppjata
 */
@Service
public class PedidoService {

    @Autowired 
    private ProdutoRepository produtoRepository;
    
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    // ============================== POST ================================== //
    
    @Transactional
    public Pedido criarPedido (Pedido pedido) {
        pedido.setStatus(StatusPedido.ABERTO);
        
        for (ItemPedido item : pedido.getItens()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
            .orElseThrow(() -> new RuntimeException ("Produto não encontrado"));
                    
            item.setStatus(StatusPedido.ABERTO);
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
            item.setTotal(produto.getPreco() * item.getQuantidade());
            
        }
        pedido.getItens().forEach(item -> {
        item.calcularTudo();
        });
        pedido.calcularValorTotal();
        
        return pedidoRepository.save(pedido);
    }
    
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido salvar(Pedido pedido) {
        pedido.calcularValorTotal(); // Atualiza o valor total antes de salvar
        return pedidoRepository.save(pedido);
    }

    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }
    
    // Atualizar status do pedido
    public Pedido atualizarStatus(Long id, StatusPedido novoStatus) {
        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setStatus(novoStatus);
        return pedidoRepository.save(pedido);
    }
    
    // Listar pedidos por status (exemplo: ABERTO, PRONTO, ENTREGUE)
    public List<Pedido> listarPorStatus(StatusPedido status) {
        // Para isso, crie um método personalizado no PedidoRepository
        return pedidoRepository.findByStatus(status);
    }
}
