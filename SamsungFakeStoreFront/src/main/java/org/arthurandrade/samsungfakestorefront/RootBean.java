package org.arthurandrade.samsungfakestorefront;

import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.arthurandrade.samsungfakestoreapi.domain.dto.CartDTO;
import org.arthurandrade.samsungfakestorefront.services.CartService;
import org.arthurandrade.samsungfakestorefront.utils.Utils;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Data
@Named("rootBean")
@SessionScoped
public class RootBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    private CartService cartService;

    private String name;

    private Date dateFrom;

    private Date dateTo;

    private String orderNumber;

    private List<CartDTO> items = new ArrayList<>();

    @PostConstruct
    public void init() {
        this.applyFilter();
    }

    public void applyFilter() {
        this.items = cartService.list(null);
    }

    // Reset action
    public void resetFilter() {
        name = null;
        dateFrom = null;
        dateTo = null;
        orderNumber = null;
        items.clear();
    }


    public String getOrderDate(CartDTO order) {
        return Utils.formatData(order.getDate());
    }

    public String getOrderSum(CartDTO order) {
        return order.getCartProducts().stream().map((cp) -> {
            return cp.getQuantity() * cp.getProduct().getPrice();
        }).reduce(0f, Float::sum).toString();
    }
}
