import java.util.Arrays;

public class insAndDelTest {

	public static void main(String[] args) {
		WAVLTree T = new WAVLTree();
		int mikre = 6;

		switch(mikre)
		{
		case 1:
		{
			// Heavy on the right side. want to delete left child
			int [] arr = {20,5,30,40,50};
			for (int num:arr)
			{
				T.insert(num, Integer.toString(num));
			}
			T.delete(5);
			T.printBinaryTree(T.root, 0);
			int [] keysArr = T.keysToArray();
			String[] infoArr = T.infoToArray();
			System.out.println(Arrays.toString(keysArr));
			System.out.println(Arrays.deepToString(infoArr));
			break;
		} 
		case 2:
		{
			// Heavy on the left side. want to delete right child
			int [] arr = {50 , 40, 60, 30};
			for (int num:arr)
			{
				T.insert(num, Integer.toString(num));
			}
			T.delete(40);
			T.printBinaryTree(T.root, 0);
			int [] keysArr = T.keysToArray();
			String[] infoArr = T.infoToArray();
			System.out.println(Arrays.toString(keysArr));
			System.out.println(Arrays.deepToString(infoArr));
			break;

		}
		case 3:
		{
			// deleting Unary
			int [] arr = {45,20,50,5,40,60,43};
			for (int num:arr)
			{
				T.insert(num, Integer.toString(num));
			}
			T.delete(20);
			T.printBinaryTree(T.root, 0);
			int [] keysArr = T.keysToArray();
			String[] infoArr = T.infoToArray();
			System.out.println(Arrays.toString(keysArr));
			System.out.println(Arrays.deepToString(infoArr));
			break;
		}
		case 4:
		{
			// Heavy on the left. Deleting a root
			int [] arr = {45,20,50,5,40,60,43};
			for (int num:arr)
			{
				T.insert(num, Integer.toString(num));
			}
			T.delete(45);

			T.printBinaryTree(T.root, 0);
			int [] keysArr = T.keysToArray();
			String[] infoArr = T.infoToArray();
			System.out.println(Arrays.toString(keysArr));
			System.out.println(Arrays.deepToString(infoArr));
			break;
		}
		case 5:
		{
			int [] arr = {40, 50 , 20, 10 ,5};
			for (int num:arr)
			{
				T.insert(num, Integer.toString(num));
			}
			
			T.printBinaryTree(T.root, 0);
			int [] keysArr = T.keysToArray();
			String[] infoArr = T.infoToArray();
			System.out.println(Arrays.toString(keysArr));
			System.out.println(Arrays.deepToString(infoArr));
			break;
		}
		case 6:
		{
			int [] arr = {45, 20, 50,5,40,60,43};
			for (int num:arr)
			{
				T.insert(num, Integer.toString(num));
			}
			
			T.printBinaryTree(T.root, 0);
			int [] keysArr = T.keysToArray();
			String[] infoArr = T.infoToArray();
			System.out.println(Arrays.toString(keysArr));
			System.out.println(Arrays.deepToString(infoArr));
			break;
	
		}
	}
}
}



