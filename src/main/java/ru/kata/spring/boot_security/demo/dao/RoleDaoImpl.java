package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("select r from Role r ", Role.class).getResultList();
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery(
                "SELECT r from Role r where r.name=:name", Role.class
        ).setParameter("name", name).getSingleResult();
    }

    @Override
    public List<Role> getListOfRoles(String[] roleNames) {
        List<Role> roleList = new ArrayList<>();
        for (String role : roleNames) {
            roleList.add(getRoleByName(role));
        }
        return roleList;
    }

    @Override
    public void add(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void edit(Role role) {
        entityManager.merge(role);
    }

    @Override
    public Role getById(int id) {
        return entityManager.find(Role.class, id);
    }
}