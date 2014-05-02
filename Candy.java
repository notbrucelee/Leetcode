
public class Candy {
    public int candy(int[] ratings) {
       	if(ratings==null || ratings.length==0) return 0;
		int sum=1,cur=1,peekindex=0, peek=0;
		for(int i=1;i<ratings.length;i++) {
			if(ratings[i]>ratings[i-1]) { // up hill increase current candy by one
				cur+=1;
				peekindex=i;
				peek=cur;
			}//down hill every step will add a number same to the distance to the peek to the total.
			if(ratings[i]<ratings[i-1]) { //and when down hill distance reach uphill distance  
				sum+=(peek>i-peekindex?i-peekindex:i-peekindex+1); // then increase peek to satisfy the constraints.
				cur=1; // the last down step always one;
				continue;
			}
			if(ratings[i]==ratings[i-1]) {//flat, when encounter flat just put this as one
				cur=1;
				peekindex=i;
				peek=cur;
			}
			sum+=cur;
		}
		return sum;
    }	
}
