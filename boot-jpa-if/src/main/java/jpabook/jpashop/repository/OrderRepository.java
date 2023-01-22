package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.dto.OrderQueryDto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class OrderRepository {

    private final EntityManager em;


    public OrderRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll() {
//        return em.createQuery("select o from Order o join o.member m " +
//                                "where o.status = :status " +
//                                "and m.name like :name "
//                        , Order.class)
//                .setParameter("name", orderSearch.getMemberName())
//                .setParameter("status", orderSearch.getOrderStatus())
//                .setMaxResults(1000)
//                .getResultList();
        return em.createQuery("select o from Order o", Order.class).getResultList();
    }

    public List<Order> findAndWithMemberDelivery() {
        return em.createQuery("select o from Order o join fetch o.member m join fetch o.delivery d", Order.class).getResultList();
    }

    public List<Order> findAllWithItem() {
        return em.createQuery("select distinct o from Order o join fetch o.member m join fetch o.orderItems oi join fetch oi.item i", Order.class).getResultList();

    }

    public List<Order> findAndWithMemberDelivery(int limit, int offset) {
        return em.createQuery("select o from Order o join fetch o.member m join fetch o.delivery d", Order.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
