import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author Zohar Meir {@link "mailto:zoharmeir1@mail.tau.ac.il"}
 * @pre "must implement int[] recSize() and int[] recRank() methods in WAVLTree class,
 * 		for in-order print of nodes size and rank.
 * 		(implementation not included, highly resembles keysToArray()/infoToArray() )"
 * @pre "must include good_results.txt file in project directory"
 * @post "rebalancing steps counter might be incorrect,
 * 		  verification was done manually according to assignment instructions and forum clarifications.
 * 		  might be a good idea to doublecheck the log file yourself..."
 * 
 * simple tester for WAVLTree methods: int insert(), int[] keysToArray(), String[] infoToArray
 * based on qmatica AVL Tree simulator: {@link "http://www.qmatica.com/DataStructures/Trees/AVL/AVLTree.html"}
 * (hence- no test for delete method)
 *
 */
public class Tester {

	public static void main(String[] args) throws IOException {
		PrintWriter fout = new PrintWriter("test_log.txt");
		WAVLTree T = new WAVLTree();
		int[] arr = {2,5,21,8,90,10,11,34,7,55,6,3,4};
		for (int num:arr) {
			fout.printf("%ninsert %d%n",num);
		int rebalance = T.insert(num, Integer.toString(num));
			//fout.printf("keys =\t%s%n", Arrays.toString(T.keysToArray()));
			//fout.printf("items= \t%s%n", Arrays.toString(T.infoToArray()));
			//fout.printf("size =\t%s (total size = %d)%n", Arrays.toString(T.recSize()), T.size());
			//fout.printf("rank =\t%s (root rank = %d)%n", Arrays.toString(T.recRank()), T.rank());
			fout.printf("rebalancing needed = %d%n", rebalance);
			System.out.println(Arrays.toString(T.keysToArray()));
			//System.out.println(T.infoToArray());
		}
		int level =0;
		T.printBinaryTree(T.root , level);	
		Arrays.sort(arr);
		fout.printf("%narr =\t%s%n", Arrays.toString(arr));
		fout.close();	
		Scanner results = new Scanner(new File("good_results.txt"));
		Scanner test = new Scanner(new File("test_log.txt"));
		boolean match = true; int line = 0;
		while (test.hasNextLine() && results.hasNextLine()) {
			String t = test.nextLine();
			String r = results.nextLine();
			if (!r.equals(t)) {
				match = false;
				break;
			}
			line++;
		}
		
		if (!match)
			System.out.println("test failed at line "+line+". check log file");
		else if (test.hasNextLine() || results.hasNextLine())
			System.out.println("test failed, mismatch in log file length");
		else
			System.out.println("test passed without errors");
		
		results.close(); test.close();

	}

}
