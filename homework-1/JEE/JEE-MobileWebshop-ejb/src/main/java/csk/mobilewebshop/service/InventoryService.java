package csk.mobilewebshop.service;

import csk.mobilewebshop.dto.MobileDTO;
import csk.mobilewebshop.exception.IllegalRequestException;
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
@LocalBean
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
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
    public MobileDTO addMobile(MobileDTO product) {

        for (MobileDTO p : inventory) {
            if (p.equals(product)) {
                throw new IllegalRequestException("Phone with this ID already exists.");
            }
        }
        inventory.add(product);
        return product;
    }

    @Lock(LockType.WRITE)
    public MobileDTO buyMobile(MobileDTO product) {
        for (MobileDTO p : inventory) {
            if (product.equals(p)) {
                if (p.getPiece() > 0) {
                    p.setPiece(p.getPiece() - 1);
                    return p;
                } else {
                    throw new IllegalRequestException("There is no " + product.getManufacturer() + " " + product.getType() + " left in the inventory.");
                }
            }
        }
        throw new IllegalRequestException("Product does not exists.");
    }

    @Lock(LockType.READ)
    public boolean isMobileAvaible(MobileDTO product) {
        return inventory.stream().anyMatch(p -> p.equals(product) && p.getPiece() > 0);
    }

    @Lock(LockType.READ)
    public List<MobileDTO> getMobileList() {
        return inventory;
    }
}
