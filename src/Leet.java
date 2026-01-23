import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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
	// The logic is like this (use x == 2 & n == 11 as an example)
	// first: 2^11
	// become: 2 * 2^10
	// become 2, (2^2 which is 4)^5, now pow is 2, x is 4 and n is 5
	// become 2*4, (4^2 which is 16)^2, now pow is 8, x is 16 and n is 2
    // become 2*4, (16^2 which is 256), now pow is 8, x is 256 and n is 1
	// become 2*4*256, (256^2 which is 65536), now pow is 2048, x is 65536 and n is 0
	// loop is terminated now, return pow which is the answer
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
		if(n%2 == 0) return n<0 ? 1/x*t*t : x*t*t;
		else return t*t;
	}
	
	// https://leetcode.com/problems/simplify-path/
	public String simplifyPathFirst(String path) {
		StringBuffer sb = new StringBuffer("/");
		String[] sArr = path.split("/");
		for(int i = 0; i < sArr.length; i++) {
			if(sArr[i].equals("..") || sArr[i].equals(".") || sArr[i].equals("")) continue;
			if(i != (sArr.length - 1) && sArr[i+1].equals("..")) continue;
			sb.append(sArr[i]);
			sb.append("/");
		}
		String res = sb.toString();
		return res.length() == 1? res: res.substring(0, res.length() - 1);
    }
	
	public String simplifyPath(String path) {
		String[] sArr = path.split("/");
		Deque<String> deque = new ArrayDeque<>();
		for(int i = 0; i < sArr.length; i++) {
			if(sArr[i].equals("..")) {
                if(deque.size() > 0) deque.removeLast();
                continue;
            }
			if(sArr[i].equals(".") || sArr[i].equals("")) continue;
			deque.add(sArr[i]);
		}
		StringBuffer sb = new StringBuffer("/");
		while(deque.size() > 0) {
			sb.append(deque.removeFirst());
			sb.append("/");
		}
		String res = sb.toString();
		return res.length() == 1? res: res.substring(0, res.length() - 1);
    }
	
	// https://leetcode.com/problems/gray-code/
	public List<Integer> grayCode(int n) {
		List<Integer> rs = new ArrayList<Integer>();
		rs.add(0);
		for(int i = 0; i < n; i++){
			int size = rs.size();
			for(int k = size-1; k >= 0; k--)
				rs.add(rs.get(k) | 1 << i);
		}
		return rs;
	}
	
	// https://leetcode.com/problems/unique-paths/
	public int uniquePaths(int m, int n) {
        if(m == 1) return 1;
		int[] base = new int[n];
		for(int i = 0; i < n; i++) base[i] = 1;
		m--;
		return dpUP(m, base)[n-1];
    }
	
	int[] dpUP(int m, int[] base) {
		m--;
		int[] n = new int[base.length];
		n[0] = 1;
		for(int i = 1; i < n.length; i++) {
			n[i] = n[i-1] + base[i];
		}
		if(m > 0) {
			return dpUP(m, n);
		} else {
			return n;
		}
	}
	
	// https://leetcode.com/problems/count-and-say/
	public String countAndSay(int n) {
		if(n == 1) return "1";
        return casLoop(n-1, "11");
    }
	
	String casLoop(int n, String str) {
		n--;
		if(n == 0) return str;
		else {
			char[] cArr = str.toCharArray();
			char currentChar = cArr[0];
			int c = 1;
			StringBuilder sb = new StringBuilder();
			for(int i = 1; i < cArr.length; i++) {
				if(cArr[i-1] != cArr[i]) {
					sb.append(c);
					c = 0;
					sb.append(cArr[i-1]);
				}
				if(currentChar != cArr[i]) currentChar = cArr[i];
				c++;
			}
			sb.append(c);
			sb.append(currentChar);
			return casLoop(n, sb.toString());
		}
	}
	
	// https://leetcode.com/problems/sort-colors/
	public void sortColors(int[] nums) {
		if(nums.length > 1) {
            int len = nums.length;
			int[] copy = Arrays.copyOf(nums, len);
            for (int i = 0; i < len; i++) {
                nums[i] = 1;
            }
            int l = 0;
            int r = len - 1;
            for (int i = 0; i < len; i++) {
                if(copy[i] == 0) {
                    nums[l] = 0;
                    l++;
                }
                if(copy[i] == 2) {
                    nums[r] = 2;
                    r--;
                }
            }
		}			
    }

    // https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
    public Node connect(Node root) {
        if(root != null) {
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            while(!q.isEmpty()) {
                int len = q.size();
                for (int i = 0; i < len; i++) {
                    Node n = q.poll();
                    if(i != len-1) n.next = q.peek();
                    if(n.left != null) q.add(n.left);
                    if(n.right != null) q.add(n.right);
                }
            }
        }
        return root;
    }

    public Node connect2(Node root) {
        if (root == null) return root;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int level = q.size();
            while (level-- > 0) {
                Node curr = q.poll();
                if (level != 0) curr.next = q.peek();
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }
        }
        return root;
    }

    // https://leetcode.com/problems/insert-interval/description/
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals.length == 0) {
            int[][] res = new int[2][1];
            res[0] = newInterval;
            return res;
        }
        int l = newInterval[0];
        int r = newInterval[1];
        Stack<Integer> stack = new Stack<>();
        stack.push(l);
        stack.push(r);
        for (int i = 0; i < intervals.length; i++) {
            int e1 = intervals[i][0];
            int e2 = intervals[i][1];
            if(e1 >= stack.peek()) {
                stack.push(e1);
                stack.push(e2);
            } else {
                compareAndPush(stack, e1);
                compareAndPush(stack, e2);
            }
        }

        return null;

    }

    private void compareAndPush(Stack<Integer> stack, int num) {
        Stack<Integer> tempStack = new Stack<>();
        while(!stack.isEmpty()) {
            Integer ele = stack.peek();
            if(num > ele) break;
            else {
                tempStack.push(stack.pop());
            }
        }
        stack.push(num);
        while(!tempStack.isEmpty()) stack.push(tempStack.pop());
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;
    public Node() {}

    public Node(int _val) {
        val = _val;
    }
    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}


