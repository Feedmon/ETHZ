package graph.util;

import java.util.ArrayList;

public class ZHK {

	public int rep;
	public ArrayList<Integer> members;

	public ZHK(int rep) {
		this.rep = rep;
		this.members = new ArrayList<>();
		members.add(rep);
	}

	public void addMember(int newMember) {
		members.add(newMember);
	}
}
