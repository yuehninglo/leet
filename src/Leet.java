import java.math.BigDecimal;
import java.math.RoundingMode;

public class Leet {
    static void main() {
		var leet = new Leet();
		leet.myPow(2, 10);
	}

	public double myPow(double x, int n) {
		if (n == 1) {
			return x;
		} else if (n == -1) {
			return 1/x;
		} else if (n >= 31) {
			return Double.MAX_VALUE;
		} else if (n <= -31) {
			return Double.MIN_VALUE;
		} else {
			int asbN = Math.abs(n);
			BigDecimal bx = new BigDecimal(x);
			BigDecimal res = new BigDecimal(1);
			BigDecimal limit = new BigDecimal(10000);
			for (int i = 0; i < asbN; i++) {
				res = res.multiply(bx);
				if (res.compareTo(limit) > 0) {
					break;
				}
			}
			if (n > 0) {
				return Double.valueOf(res.toString());
			} else {
				BigDecimal one = new BigDecimal(1);
				return Double.valueOf(one.divide(res, 10, RoundingMode.UNNECESSARY).toString());
			}
		}	
    }	


}



