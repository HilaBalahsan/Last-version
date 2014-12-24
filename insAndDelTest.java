
public class insAndDelTest {

	public static void main(String[] args) {
		WAVLTree T = new WAVLTree();
		//int[] arr = {2,5,21,8,90,10,11,34,7,55,6,3,4};
		int [] arr = {6,5,4};
		for (int num:arr)
		{
			T.insert(num, Integer.toString(num));
		}
		T.printBinaryTree(T.root, 0);
		
//		for (int i = 0; i <= 3; i++)
//			System.out.println();
//		
//		T.delete(2);
//		T.delete(3);
//		T.delete(4);
//		System.out.println(T.NodeForKey(5).rank);
//
//
//		
//		T.printBinaryTree(T.root, 0);

		
	}
	

}
