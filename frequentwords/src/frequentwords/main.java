package frequentwords;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {

	public static void main(String[] args) {
		downloadFile("https://drive.google.com/uc?export=download&confirm=no_antivirus&id=1NhdCa1gy7UiZyOkHnBDYsdlJu1m2Ydd2", "vim.txt");
		try {
			FileInputStream FIS = new FileInputStream("vim.txt");
			BufferedReader BR = new BufferedReader(new InputStreamReader(FIS, "UTF-8"));
			String str;
			int j = 0;
			char chars[];
			ArrayList<List<Map.Entry<Character, Integer>>> List = new ArrayList<List<Map.Entry<Character, Integer>>>();
			Map<Character, Integer> charToCount = new HashMap<>();
			while ((str = BR.readLine()) != null) {
				str = str.replaceAll("[^а-яА-Яa-zA-Z+[ёЁ]]", "");
				chars = str.toCharArray();
				for (int i = 0; i < chars.length; i++) {
					for (char ch : chars) {

						if (!charToCount.containsKey(Character.toLowerCase(ch))) {
							charToCount.put(Character.toLowerCase(ch), 0);
						}
						charToCount.put(Character.toLowerCase(ch), charToCount.get(Character.toLowerCase(ch)) + 1);
						j++;
						if (j > 10000) {
							List<Map.Entry<Character, Integer>> a = new ArrayList<>(charToCount.entrySet());
							sort(a);
							List.add(a);
							j = 0;
							charToCount = new HashMap<>();
						}
					}
				}

			}

			for (int l = 0; l < 10; l++) {
				for (int k = 0; k < 10; k++)
					System.out.print(List.get(k).get(l).getKey() + " " + List.get(k).get(l).getValue() + " ");
				System.out.print("\n");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void downloadFile(String host, String fileName) {
        try {
            URL url = new URL(host);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
 
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
 
            File f1 = new File(fileName);
            FileOutputStream fw = new FileOutputStream(f1);
 
            byte[] b = new byte[1024];
            int count;
 
            while ((count=bis.read(b)) != -1)
                fw.write(b,0,count);
 
            fw.close();
        } catch (IOException ex) {
            System.err.println("Error " + ex.getMessage());
        }
        }
	
	private static void sort(List<Map.Entry<Character, Integer>> a) {
		Collections.sort(a, new Comparator() {
			public int compare(Object o1, Object o2) {
				Map.Entry e1 = (Map.Entry) o1;
				Map.Entry e2 = (Map.Entry) o2;
				return ((Comparable) e2.getValue()).compareTo(e1.getValue());
			}
		});
	}
	
}
