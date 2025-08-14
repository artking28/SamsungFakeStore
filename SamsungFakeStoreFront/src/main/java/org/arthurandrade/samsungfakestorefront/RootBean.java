package org.arthurandrade.samsungfakestorefront;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.arthurandrade.samsungfakestorefront.services.UserService;
import org.arthurandrade.samsungfakestorefront.utils.Utils;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Named("rootBean")
@ViewScoped
public class RootBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    private UserService userService;

    // Filter fields
    private String name;

    private Date dateFrom;

    private Date dateTo;

    private String orderNumber;

    // List of items
    private List<Cart> items = new ArrayList<>();

    @PostConstruct
    public void init() {
        System.out.println(userService.list(null));
    }

    public void applyFilter() {
        System.out.println(userService.list(null));
        items.clear();

        List<Product> products1 = new ArrayList<>();
        products1.add(new Product("TV", 1));
        products1.add(new Product("Phone", 2));

        List<Product> products2 = new ArrayList<>();
        products2.add(new Product("Tablet", 1));

        items.add(new Cart("John", new Date(), "123", products1));
        items.add(new Cart("Jane", new Date(), "456", products2));
    }

    // Reset action
    public void resetFilter() {
        name = null;
        dateFrom = null;
        dateTo = null;
        orderNumber = null;
        items.clear();
    }

    public String data(Date date) {
        return Utils.formatData(date);
    }

    // Classe interna para a tabela
    @Data
    public static class Cart {
        private String ownerName;
        private Date createdAt;
        private String orderNumber;
        private List<Product> products;

        public Cart(String ownerName, Date createdAt, String orderNumber, List<Product> products) {
            this.ownerName = ownerName;
            this.createdAt = createdAt;
            this.orderNumber = orderNumber;
            this.products = products;
        }
    }

    @Data
    @AllArgsConstructor
    public static class Product {
        private String name;
        private int quantity;
    }
}
