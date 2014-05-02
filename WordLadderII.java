public class Solution {
	public class Ladder {//Define Ladder class it's important
		public ArrayList<Ladder> parentList;
		public String word;
		public Ladder(String w, Ladder parent) {word=w; parentList = new ArrayList<Ladder>(); parentList.add(parent);}
	}
	ArrayList<ArrayList<String>> ladders=new ArrayList<ArrayList<String>>();;
	public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
		Ladder endLadder = new Ladder(end,null), startLadder=null; 
		Queue<Ladder> q = new ArrayDeque<Ladder>();
		q.add(endLadder);//Here we look from end to start because we need to reverse the output
		int count=1,len = start.length(); //count the number of words for each level
		while(!q.isEmpty() && startLadder==null) {
			HashMap<String, Ladder> map = new HashMap<String, Ladder>();
			int cur=0;
			for(int i=0;i<count;i++) {
				Ladder curLadder = q.poll();
				for(int j=0;j<len;j++) { //change one character to any one of 26 letters
				    char[] wordChar = curLadder.word.toCharArray();
					for(char c='a';c<='z';c++) {
						wordChar[j]=c;
						String s = new String(wordChar);						
						if(s.equals(start)) {//found
							if(startLadder!=null) startLadder.parentList.add(curLadder); // if this is not the first time found
							else startLadder = new Ladder(start, curLadder);
						}
						else if(dict.contains(s) && !s.equals(curLadder.word)) {//filter those not in dict
							Ladder newLadder = new Ladder(s,curLadder);
							q.add(newLadder);	
							map.put(s,newLadder);
							cur++;// increase the number of nodes of the next level
							dict.remove(s); 
						}
						else if(map.containsKey(s)) map.get(s).parentList.add(curLadder); //Same level then reuse the revious existing
					}
				}
			}
			count=cur;
		}
		if(startLadder!=null) buildLadders(new ArrayList<String>(), endLadder, startLadder);
		return ladders;
	}
	public void buildLadders(ArrayList<String> list,Ladder end, Ladder cur) {
	    list.add(cur.word);
	    if(cur==end) ladders.add(new ArrayList<String>(list));
		else for(Ladder l:cur.parentList) buildLadders(list,end,l);
		list.remove(list.size()-1);
	}	
}