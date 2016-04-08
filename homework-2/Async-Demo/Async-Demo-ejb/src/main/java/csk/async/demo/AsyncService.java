package csk.async.demo;

import java.util.Random;
import java.util.concurrent.Future;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
@Asynchronous
public class AsyncService {

    @Resource
    private SessionContext context;

    public Future<Integer> blackMagic() {
        Random randomGenerator = new Random();
        Integer rInt = randomGenerator.nextInt(100000000);
        Integer nInt;
        int i = 0;
        do {
            nInt = randomGenerator.nextInt(100000000);
            ++i;
        } while (!nInt.equals(rInt));

        if (context.wasCancelCalled()) {
            return new AsyncResult<>(-1);
        }
        
        do {
            nInt = randomGenerator.nextInt(100000000);
            ++i;
        } while (!nInt.equals(rInt));
        return new AsyncResult<>(i);
    }
}
