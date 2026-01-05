import java.math.BigDecimal;
import java.math.RoundingMode;

public class Leet {
    static void main() {
		var leet = new Leet();
		leet.myPow(2, 10);
	}

	public double myPowWrong(double x, int n) {
		if (n == 1) {
			return x;
		} else if (n == -1) {
			return 1/x;
		} else if (n >= 31) {
			return Double.MAX_VALUE;
		} else if (n <= -31) {
			return Double.MIN_VALUE;
		} else {
            Math.pow(1,1);
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

	// https://leetcode.com/problems/powx-n/solutions/1337794/java-c-simple-o-logn-easy-faster-than-10-tr07/
    public double myPow(double x, int n) {
        if(n < 0) {
            n = -n;
            x = 1 / x;
        }
        double pow = 1;
        while(n != 0) {
            if((n & 1) != 0) { // means n%2 != 0
                pow *= x;
            } 
            x *= x;
            n >>>= 1; // n/2
        }
        return pow;
    }

	double myPowNest(double x, int n) {
		if(n < 0) return 1/x * myPowNest(1/x, -(n+1));
		if(n == 0) return 1;
		if(n == 2) return x*x;
		if(n%2 == 0) return myPowNest(myPowNest(x, n/2), 2);
		else return x*myPowNest(myPowNest(x, n/2), 2);
	}

	double myPowDouble(double x, int n) {
		if(n == 0) return 1;
		double t = myPowDouble(x,n/2);
		if(n%2) return n<0 ? 1/x*t*t : x*t*t;
		else return t*t;
	}
}



