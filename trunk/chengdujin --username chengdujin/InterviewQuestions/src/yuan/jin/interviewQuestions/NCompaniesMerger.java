package yuan.jin.interviewQuestions;

/**
 * This is a problem of picking x from y with a bit of induction.
 * 
 * When you have 2 companies, there's one way to merge them (picking 2 from 2),
 * when you have 3 you have to find how many ways there are for picking 2 from 3
 * companies and multiply down the line until the base case (picking 2 from 2).
 * With 4 companies you have to find how many ways there are of picking 2 from
 * 4, multiply that by all the ways of picking 2 from 3 then 2 from 2, and so on
 * and so forth
 * 
 * Picking 2 from x has x! / (2!((x-2)!)) ways of happening Which equals x * x
 * -1 / 2
 * 
 * http://discuss.joelonsoftware.com/default.asp?interview.11.447684.35
 * 
 * @author Yuan
 * 
 */
public class NCompaniesMerger {

	static int waysPicking(int x) {
		if (x == 2)
			return 1;
		else
			return x * (x - 1) * waysPicking(x - 1) / 2;
	}
	
//	static int ways(int n)
//	{
//	    int i, v, w = 0;
//
//	    if (n < 3) 
//	        return 1;
//
//	    for (i = 1; i <= n/2; i++) {
//	        v = (fact(n)/(fact(i)*fact(n - i)))*ways(i)*ways(n - i);
//	        w += ((i == n/2) && (n%2 == 0)) ? v/2 : v;
//	    }
//	  
//	    return w;
//	}

	public static void main(String[] args) {
		System.out.println(waysPicking(4));

	}

}
