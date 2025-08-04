/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.flavio.FastAndFuriousFood1.Controller;

import java.util.List;
import local.flavio.FastAndFuriousFood1.Entities.Pedido;
import local.flavio.FastAndFuriousFood1.Entities.StatusPedido;
import local.flavio.FastAndFuriousFood1.Service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ppjata
 */
@RestController
@RequestMapping("/FastFurious/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    @PostMapping
    public ResponseEntity<?> criarPedido(@RequestBody Pedido itemPedido) {
        try{
            Pedido pedido = pedidoService.criarPedido(itemPedido); 
            return ResponseEntity.ok(pedido);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }

    @DeleteMapping("/{id}")
    public void deletarPedido(@PathVariable Long id) {
        pedidoService.deletar(id);
    }

    @PutMapping("/status/{id}")
    public Pedido atualizarStatus(@PathVariable Long id, @RequestParam StatusPedido status) {
        return pedidoService.atualizarStatus(id, status);
    }

    @GetMapping("/status/{status}")
    public List<Pedido> listarPorStatus(@PathVariable StatusPedido status) {
        return pedidoService.listarPorStatus(status);
    }
}