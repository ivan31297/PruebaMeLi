/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mutante.app.repositories;

import com.mutante.app.models.DNAModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ivan
 * <p>Interface que hereda los metodos implementados en CrudRepository.
 * <p>Se heredan todos los metodos para realizar las diferentes acciones a la base de datos.
 */
@Repository
public interface DNARepository extends CrudRepository<DNAModel, Integer>
{
}
