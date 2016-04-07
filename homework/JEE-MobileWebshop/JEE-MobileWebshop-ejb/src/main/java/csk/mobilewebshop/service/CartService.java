package csk.mobilewebshop.service;


import csk.mobilewebshop.dto.MobileDTO;
import csk.mobilewebshop.interceptor.BeanValidation;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Csaba Kadlecsik <kadlecsik@outlook.com>
 */
@Stateful
@SessionScoped
@BeanValidation
public class CartService {
    
    @Resource
    SessionContext context;
    
    @EJB
    InventoryService inventoryService;
    
    private final List<MobileDTO> products = new LinkedList<>();
    
    public CartService() {
    }
    
    public int addToCart(MobileDTO product) {
        products.add(product);
        return products.size();
    }
    
    @Remove
    public void checkout() {
        products.stream().forEach((p) -> {
            inventoryService.buyMobile(p);
        });
        products.clear();
    }

    public List<MobileDTO> getProducts() {
        return products;
    }
    
}
