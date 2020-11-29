package com.compucompare.compucompare.database;

import com.compucompare.compucompare.components.CPUComponent;
import com.compucompare.compucompare.components.GPUComponent;
import com.compucompare.compucompare.computerType.Computer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ComputerRepositoryImpl implements CustomComputerRepository{

    private static final int MAX_KEYWORDS = 5;

    @Autowired
    EntityManager em;

    @Override
    public List<Computer> findComputerByQuery(String query){
        String[] keywords = query.split(" ", MAX_KEYWORDS);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Computer> cq = cb.createQuery(Computer.class);

        Root<Computer> computer = cq.from(Computer.class);
        Path<CPUComponent> cpu = computer.get("processor");
        Path<GPUComponent> gpu = computer.get("graphics");

        List<Predicate> predicates = new ArrayList<>();

        for (String keyword : keywords)
        {
            predicates.add(queryContains(cb, computer.get("brand"), keyword));
            predicates.add(queryContains(cb, computer.get("model"), keyword));
            predicates.add(queryContains(cb, cpu.get("brand"), keyword));
            predicates.add(queryContains(cb, cpu.get("model"), keyword));
            predicates.add(queryContains(cb, gpu.get("brand"), keyword));
            predicates.add(queryContains(cb, gpu.get("model"), keyword));
        }

        cq.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
        return em.createQuery(cq).getResultList();
    }

    private static Predicate queryContains(CriteriaBuilder cb, Expression<String> expression, String keyword)
    {
        return cb.like(cb.lower(expression), "%" + keyword.toLowerCase() + "%");
    }
}