import java.util.Arrays;

public class insAndDelTest {

	public static void main(String[] args) {
		WAVLTree T = new WAVLTree();
		int mikre = 8;

		switch(mikre)
		{
		case 1:
		{
			// Heavy on the right side. want to delete left child
			//checking rotate right
			int [] arr = {20,5,30,40,50};
			for (int num:arr)
			{
				T.insert(num, Integer.toString(num));
			}
			int count = T.delete(50);
			System.out.println("Delet counter" + count);

			T.printBinaryTree(T.root, 0);
			System.out.println();

			for (int num:arr)
			{
				T.Printrelation(T.getNode(num));
			}

			int [] keysArr = T.keysToArray();
			String[] infoArr = T.infoToArray();
			System.out.println(Arrays.toString(keysArr));
			System.out.println(Arrays.deepToString(infoArr));
			break;
		} 
		case 2:
		{
			// Heavy on the left side. want to delete right child
			int [] arr = {50 ,25, 40, 45, 60,70, 30};
			for (int num:arr)
			{
				T.insert(num, Integer.toString(num));
			}
			
			System.out.println();
			
			for (int num:arr)
			{
				T.Printrelation(T.getNode(num));
			}
			int count = T.delete(40);
			T.printBinaryTree(T.root, 0);
			 
		    System.out.println("Delet counter " + count);
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
			int count = T.delete(20);
			System.out.println("Delet counter 0 " + count);
			count = T.delete(40);
			T.printBinaryTree(T.root, 0);

			System.out.println();

			for (int num:arr)
			{
				T.Printrelation(T.getNode(num));
			}
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
			int count = T.delete(45);

			T.printBinaryTree(T.root, 0);
			System.out.println("Delet counter " + count);
			System.out.println();

			for (int num:arr)
			{
				T.Printrelation(T.getNode(num));
			}
			int [] keysArr = T.keysToArray();
			String[] infoArr = T.infoToArray();
			System.out.println(Arrays.toString(keysArr));
			System.out.println(Arrays.deepToString(infoArr));
			break;
		}
		case 5:
		{
			//Checking relations
			int [] arr = {40, 50 , 20, 10 ,5};

			for (int num:arr)
			{
				T.insert(num, Integer.toString(num));
			}

			T.printBinaryTree(T.root, 0);
			System.out.println();

			for (int num:arr)
			{
				T.Printrelation(T.getNode(num));
			}
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

			T.delete(45);
			T.printBinaryTree(T.root, 0);
			System.out.println();

			for (int num:arr)
			{
				T.Printrelation(T.getNode(num));
			}
			break;
		}
		case 7:
		{
			//checking predecessor and Successor
			
			int [] arr = {2,5,21,8,90,10,11,34,7,55,6,3,4};
			for (int num:arr)
			{
				T.insert(num, Integer.toString(num));
			}
			
			T.printBinaryTree(T.root, 0);
			System.out.println();

			
			int [] keysArr = T.keysToArray();
			String[] infoArr = T.infoToArray();
			System.out.println(Arrays.toString(keysArr));
			System.out.println(Arrays.deepToString(infoArr));
			break;
		
		}
		case 8:
		{
			T.doAsymptoticTest();
			break;
		}
		case 9:
		{			
			int [] arr = {5, 4,7,3};
			for (int num:arr)
			{
				T.insert(num, Integer.toString(num));
			}
			int count = T.delete(4);
			
			System.out.println("Delet counter 0 " + count);
	
			T.printBinaryTree(T.root, 0);

			
			break;
		}
		}
	}
}



