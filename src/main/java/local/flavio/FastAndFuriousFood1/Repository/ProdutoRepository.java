/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package local.flavio.FastAndFuriousFood1.Repository;

import java.util.List;
import local.flavio.FastAndFuriousFood1.Entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ppjata
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    public List<Produto> findByCategoria(String categoria);
    
  
    
}
