package com.compucompare.compucompare.database;

import com.compucompare.compucompare.components.CPUComponent;
import com.compucompare.compucompare.components.DisplayComponent;
import com.compucompare.compucompare.components.GPUComponent;
import com.compucompare.compucompare.computerType.Computer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ComputerRepoImpl implements CustomComputerRepository{

    EntityManager em;

    @Override
    public List<Computer> findComputerByQuery(String query){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Computer> cq = cb.createQuery(Computer.class);

        Root<Computer> computer = cq.from(Computer.class);
        Root<CPUComponent> cpu = cq.from(CPUComponent.class);
        Root<GPUComponent> gpu = cq.from(GPUComponent.class);
        Root<DisplayComponent> display = cq.from(DisplayComponent.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.like(computer.get("brand"), "%" + query + "%"));
        predicates.add(cb.like(computer.get("model"), "%" + query + "%"));
        predicates.add(cb.like(cpu.get("brand"), "%" + query + "%"));
        predicates.add(cb.like(cpu.get("model"), "%" + query + "%"));
        predicates.add(cb.like(gpu.get("brand")," %" + query + "%"));
        predicates.add(cb.like(gpu.get("model")," %" + query + "%"));
        predicates.add(cb.like(display.get("brand"), " %" + query + "%"));

        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();

    }
}
