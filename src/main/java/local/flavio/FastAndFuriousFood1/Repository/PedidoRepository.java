/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package local.flavio.FastAndFuriousFood1.Repository;

import java.util.List;
import local.flavio.FastAndFuriousFood1.Entities.Pedido;
import local.flavio.FastAndFuriousFood1.Entities.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aluno
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    public List<Pedido> findByStatus(StatusPedido status);
    
}