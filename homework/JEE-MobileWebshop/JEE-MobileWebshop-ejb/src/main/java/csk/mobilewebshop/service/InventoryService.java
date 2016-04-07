/**
 *
 * @author Csaba Kadlecsik <kadlecsik@outlook.com>
 */
package csk.mobilewebshop.service;

import csk.mobilewebshop.dto.MobileDTO;
import csk.mobilewebshop.interceptor.BeanValidation;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@LocalBean
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@BeanValidation
public class InventoryService {

    private final List<MobileDTO> inventory = new LinkedList<>();

    @PostConstruct
    public void initialize() {

        addMobile(new MobileDTO("iPhone 5SE", "Apple", UUID.randomUUID().toString(), 150000, 3));
        addMobile(new MobileDTO("Mi5", "Xiaomi", UUID.randomUUID().toString(), 100000, 12));
        addMobile(new MobileDTO("Two", "OnePlus", UUID.randomUUID().toString(), 110000, 5));
        addMobile(new MobileDTO("Lumia 950", "Microsoft", UUID.randomUUID().toString(), 160000, 0));
    }

    @Lock(LockType.WRITE)
    public void addMobile(MobileDTO product) {

        for (MobileDTO p : inventory) {
            if (p.equals(product)) {
                return;
            }
        }
        inventory.add(product);

    }

    @Lock(LockType.WRITE)
    public void buyMobile(MobileDTO product) {
        for (MobileDTO p : inventory) {
            if (product.equals(p)) {
                if (p.getPiece() > 0) {
                    p.setPiece(p.getPiece() - 1);
                } else {
                    throw new IllegalArgumentException("There is no " + product.toString() + " left in the inventory");
                }
                return;
            }
        }
        throw new IllegalArgumentException("There is no " + product.toString() + " in the inventory");
    }

    @Lock(LockType.READ)
    public boolean isMobileAvaible(MobileDTO product) {
        for (MobileDTO p : inventory) {
            if (p.equals(product) && p.getPiece() > 0) {
                return true;
            }
        }
        return false;
    }

    @Lock(LockType.READ)
    public List<MobileDTO> getMobileList() {
        return inventory;
    }
}
