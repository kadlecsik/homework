package csk.mobilewebshop.service;

import csk.mobilewebshop.dto.MobileDTO;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Remove;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.inject.Inject;

@Stateful
public class CartService {

    @Resource
    private SessionContext context;

    @Inject
    private InventoryService inventoryService;

    private final List<MobileDTO> cart = new LinkedList<>();

    public List<MobileDTO> addToCart(MobileDTO mobile) {
        cart.add(mobile);
        return Collections.unmodifiableList(cart);
    }

    @Remove
    public void checkout() {
        cart.stream().forEach(inventoryService::buyMobile);
        cart.clear();
    }

    public List<MobileDTO> getProducts() {
        return Collections.unmodifiableList(cart);
    }
}
