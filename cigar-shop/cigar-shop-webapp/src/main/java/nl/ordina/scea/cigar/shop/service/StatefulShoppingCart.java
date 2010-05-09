package nl.ordina.scea.cigar.shop.service;

import nl.ordina.scea.cigar.shop.domainapi.Money;
import nl.ordina.scea.cigar.shop.domainapi.Product;
import nl.ordina.scea.cigar.shop.domainapi.ShoppingCart;
import nl.ordina.scea.cigar.shop.domainapi.ShoppingCartItem;

import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
@Stateful
public class StatefulShoppingCart implements Serializable, ShoppingCart {
    private Map<Product, Integer> items;

    @PostConstruct
    public void init() {
        items = new HashMap<Product, Integer>();
    }

    @Override
    public void addProduct(Product product) {
        items.put(product, 1);
    }

    @Override
    public void changeQuantity(Product product, int newQuantity) {
        items.put(product, newQuantity);
    }

    @Override
    public List<ShoppingCartItem> getItems() {
        List<ShoppingCartItem> sc = new ArrayList<ShoppingCartItem>();
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            sc.add(new ShoppingCartItemImpl(entry.getKey(), entry.getValue()));
        }
        return sc;
    }

    @Override
    public Money getTotalAmount() {
        Money total = Money.ZERO;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total = total.add(entry.getKey().getPrice().times(entry.getValue()));
        }
        return total;
    }

    @Override
    @Remove
    public void clear() {
    }

    @Override
    public void remove(Product product) {
        items.remove(product);
    }
}

