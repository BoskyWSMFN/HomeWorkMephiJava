package sorting;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class main {

	public static void main(String[] args) {
		LinkedList<Integer> buf = new LinkedList<>();
		LinkedList<Integer> bufsort = new LinkedList<>();
		Random rand = new Random();
		for (int i = 0; i < 100; i++) {
			fifo(buf, bufsort, rand.nextInt(10));
		}
	}

	public static void fifo(LinkedList<Integer> buf, LinkedList<Integer> bufsort, int add) {
		Float mid;
		Integer a = add;
		buf.add(a);
		bufsort.add(a);

		if (buf.size() > 8) {
			bufsort.remove(buf.get(0));
			buf.remove(0);
		}
		Collections.sort(bufsort);
		if (bufsort.size() == 8) {
			mid = (float) (bufsort.get(3) + bufsort.get(4)) / 2;
		} else
			mid = bufsort.size() % 2 == 0
					? ((float) (bufsort.get((int) bufsort.size() / 2 - 1) + bufsort.get((int) bufsort.size() / 2)) / 2)
					: (float) bufsort.get((int) (bufsort.size() / 2));
		System.out.println(buf.toString() + " -> " + bufsort.toString() + " -> " + mid.toString());
	}
	
}
