package com.compucompare.compucompare.database;

import com.compucompare.compucompare.components.CPUComponent;
import com.compucompare.compucompare.components.DisplayComponent;
import com.compucompare.compucompare.components.GPUComponent;
import com.compucompare.compucompare.computerType.Computer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ComputerRepositoryImpl implements CustomComputerRepository{

    @Autowired
    EntityManager em;

    @Override
    public List<Computer> findComputerByQuery(String query){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Computer> cq = cb.createQuery(Computer.class);

        Root<Computer> computer = cq.from(Computer.class);
        Path<CPUComponent> cpu = computer.get("processor");
        Path<GPUComponent> gpu = computer.get("graphics");
        Path<DisplayComponent> display = computer.get("display");

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.like(cb.lower(computer.get("brand")), "%" + query.toLowerCase() + "%"));
        predicates.add(cb.like(cb.lower(computer.get("model")), "%" + query.toLowerCase() + "%"));
        predicates.add(cb.like(cb.lower(computer.get("brand")), "%" + query.toLowerCase() + "%"));
        predicates.add(cb.like(cb.lower(cpu.get("model")), "%" + query.toLowerCase() + "%"));
        predicates.add(cb.like(cb.lower(gpu.get("brand")), "%" + query.toLowerCase() + "%"));
        predicates.add(cb.like(cb.lower(gpu.get("model")), "%" + query.toLowerCase() + "%"));
        predicates.add(cb.like(cb.lower(display.get("brand")), " %" + query.toLowerCase() + "%"));

        cq.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
        return em.createQuery(cq).getResultList();
    }
}