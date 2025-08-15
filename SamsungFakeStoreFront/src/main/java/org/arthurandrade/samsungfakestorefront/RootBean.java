package org.arthurandrade.samsungfakestorefront;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.arthurandrade.samsungfakestoreapi.domain.dto.CartDTO;
import org.arthurandrade.samsungfakestorefront.services.CartService;
import org.arthurandrade.samsungfakestorefront.utils.Utils;

import java.io.Serial;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private CartService cartService;

    // Filter fields
    private String name;

    private Date dateFrom;

    private Date dateTo;

    private String orderNumber;

    // List of items
    private List<CartDTO> items = new ArrayList<>();

    @PostConstruct
    public void init() {
        this.applyFilter();
    }

    public void applyFilter() {
        this.items = cartService.list(null);
        System.out.println(this.items);
//        items.clear();
//
//        List<Product> products1 = new ArrayList<>();
//        products1.add(new Product("TV", 1));
//        products1.add(new Product("Phone", 2));
//
//        List<Product> products2 = new ArrayList<>();
//        products2.add(new Product("Tablet", 1));
//
//        items.add(new Cart("John", new Date(), "123", products1));
//        items.add(new Cart("Jane", new Date(), "456", products2));
    }

    // Reset action
    public void resetFilter() {
        name = null;
        dateFrom = null;
        dateTo = null;
        orderNumber = null;
        items.clear();
    }

    public String data(Object obj) {
        if (obj instanceof Date d) {
            return Utils.formatData(d);
        }

        if (obj instanceof String str) {
            try {
                var ret = new SimpleDateFormat("yyyy-MM-dd").parse(str);
                return Utils.formatData(ret);
            } catch (ParseException e) {
                return str;
            }
        }

        return "null";
    }
}
