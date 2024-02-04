package org.max.home;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.max.demo.EmployeeEntity;

import javax.persistence.PersistenceException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductHibernateTest extends AbstractTest {

    private ProductsEntity getProductEntity(short id, String menuName, String price) {
        ProductsEntity productsEntity = new ProductsEntity();
        productsEntity.setProductId(id);
        productsEntity.setMenuName(menuName);
        productsEntity.setPrice(price);

        return productsEntity;
    }

    @ParameterizedTest
    @CsvSource({"101, Product1, 123.0"
              , "102, Product2, 1.11"
              , "103, Product3, '1,32'"})
    @Order(1)
    void addProduct_whenValid_shouldSave(short id, String menuName, String price) {
        ProductsEntity productsEntity = getProductEntity(id, menuName, price);

        Session session = getSession();
        session.beginTransaction();
        session.persist(productsEntity);
        session.getTransaction().commit();

        Optional<ProductsEntity> result = getSession()
                .createQuery("from ProductsEntity where menuName = :menu_name")
                .setParameter("menu_name", menuName)
                .uniqueResultOptional();

        assertTrue(result.isPresent());
        assertAll(() -> assertEquals(price, result.get().getPrice())
                , () -> assertEquals(id, result.get().getProductId()));
    }

    @ParameterizedTest
    @CsvSource({"101, Product1, 123.0"
              , "104, ,"})
    @Order(2)
    void addProduct_whenNotValid_shouldThrow(short id, String menuName, String price) {
        ProductsEntity productsEntity = getProductEntity(id, menuName, price);
        Session session = getSession();
        session.beginTransaction();
        session.persist(productsEntity);

        Assertions.assertThrows(PersistenceException.class, () -> session.getTransaction().commit());
    }

    @ParameterizedTest
    @CsvSource({"101, Product4, 123.0"
            , "102, Product5, 1.11"
            , "103, Product6, '1,32'"})
    @Order(3)
    void updateProduct_whenValid_shouldSave(short id, String menuName, String price) {
        ProductsEntity productsEntity = getProductEntity(id, menuName, price);
        Session session = getSession();
        session.beginTransaction();
        session.merge(productsEntity);
        session.getTransaction().commit();

        Optional<ProductsEntity> selectResult = getSession()
                .createQuery("from ProductsEntity where productId = :product_id")
                .setParameter("product_id", id)
                .uniqueResultOptional();

        assertTrue(selectResult.isPresent());
        assertAll(() -> assertEquals(menuName, selectResult.get().getMenuName())
                , () -> assertEquals(price, selectResult.get().getPrice()));
    }

    @ParameterizedTest
    @ValueSource(shorts = {101
                       , 102
                       , 103})
    @Order(4)
    void deleteProduct_whenValid_shouldDelete(short id) {
        Session session = getSession();
        session.beginTransaction();
        session.createQuery("delete from ProductsEntity where productId = :product_id")
                .setParameter("product_id", id)
                .executeUpdate();
        session.getTransaction().commit();

        Optional<ProductsEntity> selectResult = getSession()
                .createQuery("from ProductsEntity where productId = :product_id")
                .setParameter("product_id", id)
                .uniqueResultOptional();

        assertFalse(selectResult.isPresent());
    }
}
