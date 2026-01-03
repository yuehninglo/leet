import java.math.BigDecimal;

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
		} else {
			int asbN = Math.abs(n);
			BigDecimal bx = new BigDecimal(x);
			BigDecimal res = new BigDecimal(1);
			for (int i = 0; i < asbN; i++) {
				res = res.multiply(bx);
				System.out.println("res: " + res);
			}
			if (n > 0) {
				return Double.valueOf(res.toString());
			} else {
				BigDecimal one = new BigDecimal(1);
				return Double.valueOf(one.divide(res).toString());
			}
		}	
    }	


}



