package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsersWithSameCar(String model, int series) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("FROM User u where u.car.model = :model and u.car.series = :series")
                .setParameter("model", model).setParameter("series", series);
        return query.getResultList();
    }

    @Override
    public User getUserByCar(String model, int series) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM User u where u.car.model = :model and u.car.series = :series")
                .setParameter("model", model).setParameter("series", series);
        return (User) query.getSingleResult();
    }

   /*@Override
public User carList(String model, int series) {
    Session session = sessionFactory.openSession();
    Query query = session.createQuery("from User u where u.car.model=:model " +
            "and u.car.series=:series", User.class);
    query.setParameter("model", model);
    query.setParameter("series", series);
    try {
        return (User) query.getResultList();
    } catch (Exception e) {
        System.out.println("Юзер с таким авто не найден.");
        return null;
    }


    FROM User u JOIN u.car c WHERE c.model=:model " +
            "and c.series=:series"

3:14 PM
насчет JOIN можно поиграться, LEFT JOIN FETCH или ещё чего, пробуй
}*/
}