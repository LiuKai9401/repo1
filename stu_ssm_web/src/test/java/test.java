import org.junit.Test;

import java.math.BigDecimal;

public class test {

    @Test
    public void test(){
        BigDecimal bigDecimal = new BigDecimal("100");
        BigDecimal bigDecimal1 = new BigDecimal("0");
        System.out.println(bigDecimal1.divide(bigDecimal, 2,BigDecimal.ROUND_HALF_UP));
    }
}
