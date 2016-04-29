package csk.amusementpark.bean.service;

import csk.amusementpark.exception.IllegalRequestException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class ParserService {

    public Integer parseStringId(String id) {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new IllegalRequestException("The ID has to be a number.");
        }
    }
}
