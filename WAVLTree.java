import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * WAVLTree
 *
 * An implementation of a WAVL Tree with
 * distinct integer keys and info
 *
 */

public class WAVLTree{
	public WAVLNode root;
	public WAVLNode minimum;
	public WAVLNode maximum;
	public int treeSize;
	public int countBalance;
	public int countDeleteBalance;

	/** Constructor; creates a tree with empty Node */
	public WAVLTree() 
	{
		this.root = null;
	}
	/**
	 * public boolean empty()
	 * returns true if and only if the tree is empty
	 */
	public boolean empty() {
		return (this.root == null);
	}
	/**
	 *  public String search(WAVLTree T , int k) 
	 *  recursive search
	 */
	public String recSearch(WAVLNode node , int k)
	{
		if(node.key == k)
		{
			return node.info; 
		}
		else if((node.key < k) && (node.right != null))
		{
			return recSearch(node.right, k);
		}
		else if(node.left != null)
		{
			return recSearch(node.left , k);
		}

		return null;
	}
	/**
	 * public String search(int k)
	 *
	 * returns the info of an item with key k if it exists in the tree
	 * otherwise, returns null
	 */
	public String search(int k)	{

		if (this.root != null)
		{
			if(this.root.key == k)
			{
				return this.root.info;
			}
			else if((this.root.key < k) && (this.root.right != null)) 
			{
				return recSearch(this.root.right , k); 
			}
			else if((this.root.key > k) && (this.root.left != null)) 
			{
				return recSearch(this.root.left , k); 
			}
		}
		return null;  
	}
	/** 
	 * public int[] getRankDiff(WAVLNode x)
	 * returns the rank differences between the given node and his children.
	 * if x is a leaf returns (1,1)
	 */
	public int[] getRankDiff(WAVLNode x){

		int[] rankDiff = new int [2]; 
		if((x.left == null) && (x.right == null))
		{
			rankDiff[0] = 1;
			rankDiff[1] = 1;
		}
		else if((x.left != null) && (x.right == null))
		{
			rankDiff[0] = (x.rank - x.left.rank);
			rankDiff[1] = x.rank + 1;
		}
		else if((x.left == null) && (x.right != null))
		{
			rankDiff[1] = (x.rank - x.right.rank);
			rankDiff[0] = x.rank + 1;
		}
		else
		{
			rankDiff[0] = (x.rank - x.left.rank);
			rankDiff[1] = (x.rank - x.right.rank);
		}
		return rankDiff;
	}
	/**
	 * public WAVLNode getParent ( WAVLNode n , int k)
	 * return the father of the inserted Node 
	 */
	public WAVLNode getParent(WAVLNode root , int k){

		if((root.key < k) && (root.right == null))
		{
			return root;
		}
		else if((root.key > k) && (root.left == null))
		{
			return root;
		}

		if (root.key < k)
		{
			return getParent(root.right , k);
		}

		else
		{
			return getParent(root.left , k);
		}

	}	


	public WAVLNode rotateWithLeftChild (WAVLNode node){                  // Right Rotation
		WAVLNode temp = node.left;
		temp.parent = node.parent;
		node.left = temp.right;
		//node.left.parent = node;

		if(temp.right != null)
		{
			temp.right.parent = node;
		}

		temp.right = node;
		//node.rank --; maybe
		temp.right.parent = temp;

		if(temp.left != null)
			temp.left.parent = temp;

		if ((node.right != null) &&  (node.left != null))
			node.rank = Math.max(node.right.rank, node.left.rank) +1 ;
		else if ((node.right != null) &&  (node.left == null))
			node.rank = Math.max(node.right.rank, -1) +1 ;
		else if ((node.right == null) &&  (node.left != null))
			node.rank = Math.max(-1, node.left.rank) +1 ;
		else
			node.rank = 0;

		if (temp.left != null)
			temp.rank = Math.max(temp.left.rank, node.rank) +1 ;
		else
			temp.rank = Math.max(-1, node.rank) +1 ;

		return temp;

	}

	public WAVLNode rotateWithRightChild (WAVLNode node){              // Left Rotation   
		WAVLNode temp = node.right;
		temp.parent = node.parent;

		node.right = temp.left;
		if(temp.left != null)
		{
			node.right.parent = node;
		}

		temp.left = node;
		temp.left.parent = temp;
		if(temp.right != null)
			temp.right.parent = temp;


		if ((node.right != null) &&  (node.left != null))
			node.rank = Math.max(node.right.rank, node.left.rank) +1 ;
		else if ((node.right != null) &&  (node.left == null))
			node.rank = Math.max(node.right.rank, -1) +1 ;
		else if ((node.right == null) &&  (node.left != null))
			node.rank = Math.max(-1, node.left.rank) +1 ;
		else
			node.rank = 0;

		if (temp.right != null)
			temp.rank = Math.max(temp.right.rank, node.rank) +1 ;
		else
			temp.rank = Math.max(-1, node.rank) +1 ;
		//OLD				
		//
		//node.rank --;
		//	temp.rank = node.rank +1;



		return temp;
	}	

	public WAVLNode doubleWithLeftChild(WAVLNode node){              // Left-Right Rotation
		node.left = rotateWithRightChild(node.left);
		node.left.parent = node;
		return rotateWithLeftChild(node);
	}

	public WAVLNode doubleWithRightChild(WAVLNode node){            // Right-left Rotation
		node.right = rotateWithLeftChild(node.right);
		node.right.parent = node;
		return rotateWithRightChild(node);
	}

	/** public int insert(int k, String i)
	 * inserts an item with key k and info i to the WAVL tree.
	 * the tree must remain valid (keep its invariants).
	 * returns the number of rebalancing operations, or 0 if no rebalancing operations were necessary.
	 * returns -1 if an item with key k already exists in the tree.
	 */
	public int insert(int k, String i) {

		if(this.search(k) != null)
		{
			return -1;
		}

		this.countBalance = 0;
		try {
			this.root = insert_helper(k,i,this.root);
			if(this.treeSize == 0)
			{
				this.maximum = this.root;
				this.minimum = this.root;
			}
			this.treeSize++;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("insertion number or rotation = " + this.countBalance);
		return this.countBalance;

	}
	public WAVLNode insert_helper (int key,String i,WAVLNode t) throws Exception{
		if (t == null)
		{
			t = new WAVLNode(key,i);
			if(this.treeSize > 0 )
			{
				if(t.key > this.maximum.key)
				{
					this.maximum = t;
				}
				else if (t.key < this.minimum.key)
				{
					this.minimum = t;
				}
			}
		}
		else if (key < t.key)
		{
			t.left = insert_helper (key, i, t.left);
			t.left.parent = t;
			int[] rankdiff = getRankDiff(t);
			if ((rankdiff[1] - rankdiff[0]) == 2)
			{
				if (key < t.left.key)
				{
					t = rotateWithLeftChild (t);
					this.countBalance++;
				}
				else 
				{
					t = doubleWithLeftChild (t);
					this.countBalance+=2;
				}
			}
			else if((rankdiff[1]==1) && (rankdiff[0]==0)) 
			{
				t.rank++;
				this.countBalance++;
			}
		}
		else if (key > t.key)
		{
			t.right = insert_helper(key, i, t.right);
			t.right.parent = t;
			
			int[] rankdiff = getRankDiff(t);
			if ((rankdiff[0] - rankdiff[1]) == 2) 
			{
				if (key > t.right.key)
				{
					t = rotateWithRightChild (t);
					this.countBalance++;
				}
				else
				{
					t = doubleWithRightChild (t);
					this.countBalance+=2;
				}
			}
			else if((rankdiff[0]==1) && (rankdiff[1]==0)) 
			{
				t.rank++;
				this.countBalance++;
			}
		}
		else
		{
			throw new Exception("-1");
		}
		return t;
	}
	//find predecessor
	public WAVLNode FindPredecessor(WAVLNode node)
	{
		WAVLNode temp = node;
		if (temp.left!=null)
		{
			while (temp.left.right!=null)
			{
				temp.left=temp.left.right;
			}
			return temp.left;
		}
		WAVLNode predecessor = node.parent;
		while ((predecessor != null) && (temp == predecessor.left))
		{
			temp = predecessor;
			predecessor = this.getParent(this.root,temp.parent.key);
		}
		return predecessor;
	}
	//find successor
	public WAVLNode FindSuccessor(WAVLNode node)
	{
		WAVLNode temp = node;
		int k;
		int flag = 0;
		if (temp.right != null)
		{
			k = temp.right.key;
			while (flag == 0)
				if (this.NodeForKey(k).left != null)
					k = this.NodeForKey(k).left.key;
				else
					flag = 1;

			return this.NodeForKey(k);
		}
		WAVLNode successor = node;
		k = successor.key;
		while (flag == 0)
		{
			if (successor.parent != null)
			{
				if (k > successor.parent.key)
				{
					k = this.NodeForKey(k).parent.key;
					successor = successor.parent;
				}
				else
					flag = 1;
			}				
		}
		return this.NodeForKey(successor.parent.key);
		//while ((successor != null)&&(temp == successor.right))
		//{
		//	temp=successor;
		//	successor=this.getParent(this.root,temp.parent.key);;
		//}
		//return successor;
	}
	//return WAVLNode for key
	public WAVLNode NodeForKey(int i)
	{
		if (this.root.key==i)
		{
			return root;
		}
		else if((this.root.key < i) && (this.root.right != null)) 
		{
			return recNodeForKey(this.root.right , i); 
		}
		else if((this.root.key > i) && (this.root.left != null)) 
		{
			return recNodeForKey(this.root.left , i); 
		}
		return null;
	}
	public WAVLNode recNodeForKey(WAVLNode node,int i)
	{
		if(node.key == i)
		{
			return node; 
		}
		else if((node.key < i) && (node.right != null))
		{
			return recNodeForKey(node.right, i);
		}
		else if(node.left != null)
		{
			return recNodeForKey(node.left , i);
		}

		return null;
	}	
	// delete a leaf
	public void DeleteLeaf(WAVLNode node,int k)
	{
		countDeleteBalance = 0;
		if (this.treeSize == 1)
		{
			this.root = null;
		}
		else
		{
			int[] rankDiff = this.getRankDiff(node.parent);
			if ((this.getRankDiff(node.parent)[0] == 1) &&(this.getRankDiff(node.parent)[1] == 1))
				// The father is full. the rank of the children is 0 (leafs)
			{
				if (node.parent.key > k)
				{
					node.parent.left = null;
					node = null;
				}
				else
				{
					node.parent.right = null;
					node = null;
				}
			}
			else if ((rankDiff[0] == 1) &&(rankDiff[1] == 2))
				// The father is heavy on the left size. two nodes on the left. One on the right. 
			{
				if (node.parent.key > k) // Want to delete left leaf.
				{
					node.parent.left = null;
					node.parent.rank--;                             //demote
					countDeleteBalance++;
					if (node.parent.parent != null)
						node.parent.parent = this.deletebalance(node.parent.parent);
					node = null;
				}
				else
				{
					node.parent.right = null;
					node.parent = this.deletebalance(node.parent);
					// Here we not have to call another function. we can call rotate with left child.
					//why there is a difference between this suction and the suction starting at line 483. Is the symmetry case.
				}
			}
			else if((rankDiff[0] == 2) &&(rankDiff[1] == 1))
				// The father is heavy on the right size. two nodes on the right. One on the left. 

			{
				if (node.parent.key > k)
				{
					node.parent.left = null;
					WAVLNode grand = node.parent.parent;
					node.parent = this.deletebalance(node.parent);
					if (grand != null)
					{
						if (grand.key > node.parent.key)
							grand.left = node.parent;
						else
							grand.right = node.parent;
					}
					node = null;
				}
				else
				{
					node.parent.right = null;
					node.parent.rank--;//demote
					countDeleteBalance++;
					node.parent=this.deletebalance(node.parent);
				}
			}
		}
	}
	public void DeleteUnaryNode(WAVLNode node,int k)
	{
		countDeleteBalance=0;
		if (this.treeSize == 2)
		{
			if (this.root.right!= null)
			{
				node = node.right;
			}
			else
			{
				node = node.left;
			}
		}
		else
		{
			if (node.parent.rank == 2)
			{
				if (node.left != null)
				{
					if (node.left.key > node.parent.key)
					{
						node.parent.right = node.left;
						node.left.parent=node.parent;
					}
					else
					{
						node.parent.left = node.left;
						node.left.parent=node.parent;
					}
				}
				else
				{
					if (node.right.key > node.parent.key)
					{
						node.parent.right = node.right;
						node.right.parent=node.parent;
					}
					else
					{
						node.parent.left = node.right;
						node.right.parent=node.parent;
					}				
				}
			}
			else
			{
				if (node.left!=null)
				{
					if (node.left.key>node.parent.key)
					{
						node.parent.right=node.left;
						node.left.parent=node.parent;
					}
					else
					{
						node.parent.left=node.left;
						node.left.parent=node.parent;
					}
					this.deletebalance(node);

				}
				else
				{
					if (node.right.key>node.parent.key)
					{
						node.parent.right=node.right;
						node.right.parent=node.parent;
					}
					else
					{
						node.parent.left=node.right;
						node.right.parent=node.parent;
					}				
					this.deletebalance(node);
				}
			}
		}

	}
	public WAVLNode deletebalance(WAVLNode node)
	{
		if(node != this.root)
		{
			int[] rankDiff = this.getRankDiff(node);
			// case 1 demote + symecric
			if (((rankDiff[0]==3)&&(rankDiff[1]==2))||((rankDiff[0]==2)&&(rankDiff[1]==3)))
			{
				node.rank--;
				countDeleteBalance++;
				return deletebalance(node.parent);
			}
			//case 2+3+4 double demote
			else if ((rankDiff[0]==3) && (rankDiff[1]==1))
			{
				//case 2 double demote
				int[] rankDiffright = this.getRankDiff(node.right);
				if ((rankDiffright[0] == 2)&&(rankDiffright[1]==2))
				{
					node.right.rank--;
					countDeleteBalance++;
					node.rank--;
					countDeleteBalance++;
					return deletebalance(node.right);
				}
				//case 3 rotate
				else if(((rankDiffright[0]==2)||(rankDiffright[0]==1))&&(rankDiffright[1]==1))
				{
					node = this.rotateWithRightChild(node);
					countDeleteBalance++;

					if ((rankDiff[0]==2)&&(rankDiff[1]==2))
					{
						node.rank--;
						countDeleteBalance++;
					}
					return node;
				}
				//case 4 double rotate
				else if ((rankDiffright[0] == 1)&&(rankDiffright[1] == 2))
				{
					node = this.doubleWithRightChild(node);
					countDeleteBalance += 2;
					return node;
				}
			}
			else
			{
				//case 2+3+4  symetry
				if ((rankDiff[0]==1)&&(rankDiff[1]==3))
				{
					//case 2 double demote
					int[] rankDiffleft = this.getRankDiff(node.left);

					if ((rankDiffleft[0]==2)&&(rankDiffleft[1]==2))
					{
						node.left.rank--;
						countDeleteBalance++;
						node.rank--;
						countDeleteBalance++;
						return deletebalance(node.left);

					}
					//case 3 rotate
					else if((rankDiffleft[0]==1)&&((rankDiffleft[1]==1)||(this.getRankDiff(node.left)[1]==2)))
					{
						node = this.rotateWithLeftChild(node);
						countDeleteBalance++;
						if ((rankDiff[0]==2)&&(rankDiff[1]==2))
						{
							node.rank--;
							countDeleteBalance++;
						}
						return node;
					}
					//case 4 double rotate
					else if ((rankDiffleft[0]==2)&&(rankDiffleft[1]==1))
					{
						node= this.doubleWithLeftChild(node);
					}						
					countDeleteBalance++;
					countDeleteBalance++;
					return node;
				}
			}
			return node;

		}
		else
		{
			this.root = this.deleteBalanceRoot(this.root);
			return this.root;
		}
	}

	public int delete(int k)
	{
		countDeleteBalance = 0;
		//if the key doesnt exist in the tree
		if (this.search(k) == null)
			return -1;

		// find the node that contains the key
		WAVLNode node = NodeForKey(k);
		
		if ((node == this.minimum) && (this.treeSize > 1))
		{
			WAVLNode sucessor = this.FindSuccessor(node);
			this.minimum = sucessor;
			if(node.rank == 0)
				DeleteLeaf(node,k);
			else
				DeleteUnaryNode(node, k);
		}
		
		else if ((node == this.maximum)&&(this.treeSize > 1))
		{
			WAVLNode predecessor = this.FindPredecessor(node);
			this.maximum = predecessor;
			if(node.rank == 0)
				DeleteLeaf(node,k);
			else
				DeleteUnaryNode(node, k);		}
		
		// if k is a leaf
		else if (node.rank == 0)
		{
			DeleteLeaf(node,k);
		}
		//if k is unary node
		else if (((node.right == null)&&(node.left != null))||((node.left == null)&&(node.right!=null)))
		{
			DeleteUnaryNode(node,k);

		}
		else
		{

			WAVLNode sucessor = this.FindSuccessor(node);
			int tempKey = sucessor.key;
			String tempInfo = sucessor.info;			
			if( sucessor.rank == 0)
			{
				this.DeleteLeaf(sucessor, sucessor.key);
			}
			else
			{
				DeleteUnaryNode(sucessor,sucessor.key);
			}
			node.key = tempKey;
			node.info = tempInfo;
 
		}
		this.treeSize--;
		return 	countDeleteBalance;
	}


	public WAVLNode deleteBalanceRoot(WAVLNode node){
		int[] rankDiff = this.getRankDiff(node);

		// case 1 demote + symecric
		if (((rankDiff[0]==3)&&(rankDiff[1]==2))||((rankDiff[0]==2)&&(rankDiff[1]==3)))
		{
			node.rank--;
			countDeleteBalance++;
			return node;
		}
		//case 2+3+4 double demote
		else if ((rankDiff[0]==3) && (rankDiff[1]==1))
		{
			//case 2 double demote

			if ((this.getRankDiff(node.right)[0] == 2)&&(this.getRankDiff(node.right)[1]==2))
			{
				node.right.rank--;
				countDeleteBalance++;
				node.rank--;
				countDeleteBalance++;
				return deletebalance(node.right);
			}
			//case 3 rotate
			else if(((this.getRankDiff(node.right)[0]==2)||(this.getRankDiff(node.right)[0]==1))&&(this.getRankDiff(node.right)[1]==1))
			{
				System.out.println("case 3 , " + node.key );
				System.out.println();
				
				node = this.rotateWithRightChild(node);
				this.root = node;
				countDeleteBalance++;

				if ((this.getRankDiff(node)[0]==2)&&(this.getRankDiff(node)[1]==2))
				{
					node.rank--;
					countDeleteBalance++;
				}
				return node;
			}
			//case 4 double rotate
			else if ((this.getRankDiff(node.right)[0]==1)&&(this.getRankDiff(node.right)[1]==2))
			{
				node=this.doubleWithRightChild(node);
				countDeleteBalance++;
				countDeleteBalance++;
				return node;
			}
		}
		else
		{
			//case 2+3+4  symetry
			if ((this.getRankDiff(node)[0]==1)&&(this.getRankDiff(node)[1]==3))
			{
				//case 2 double demote
				if ((this.getRankDiff(node.left)[0]==2)&&(this.getRankDiff(node.left)[1]==2))
				{
					node.left.rank--;
					countDeleteBalance++;
					node.rank--;
					countDeleteBalance++;
					return deletebalance(node.left);

				}
				//case 3 rotate
				else if((this.getRankDiff(node.left)[0]==1)&&((this.getRankDiff(node.left)[1]==1)||(this.getRankDiff(node.left)[1]==2)))
				{
					node = this.rotateWithLeftChild(node);
					countDeleteBalance++;
					if ((this.getRankDiff(node)[0]==2)&&(this.getRankDiff(node)[1]==2))
					{
						node.rank--;
						countDeleteBalance++;
					}
					return node;
				}
				//case 4 double rotate
				else if ((this.getRankDiff(node.left)[0]==2)&&(this.getRankDiff(node.left)[1]==1))
				{
					node=this.doubleWithLeftChild(node);
					countDeleteBalance++;
					countDeleteBalance++;
					return node;
				}
			}

		}
		return node;
	}



	public void reckeysToArray(WAVLNode node, List<Integer> keys){
		if(node != null)
		{
			reckeysToArray(node.left , keys);
			keys.add(node.key);
			reckeysToArray(node.right , keys);
		}

	}
	/** public int[] keysToArray()
	 * Returns a sorted array which contains all keys in the tree,
	 * or an empty array if the tree is empty.
	 */
	public int[] keysToArray(){
		List<Integer> keys = new ArrayList<Integer>();
		if(this.empty())
		{
			int[] keyArray = new int[0]; 
			return keyArray;
		}
		else 
		{
			reckeysToArray(this.root , keys);
		}

		// Converting
		int[] keysArray = new int[keys.size()];
		java.util.Iterator<Integer> iter = keys.iterator();
		for(int i = 0; iter.hasNext(); i++)
		{
			keysArray[i] = iter.next();
		}
		return keysArray;
	}


	public void recinfoToArray(WAVLNode node, List<String> inf){
		if(node != null)
		{
			recinfoToArray(node.left , inf);
			inf.add(node.info);
			recinfoToArray(node.right , inf);
		}

	}

	/**
	 * public String[] infoToArray()
	 *
	 * Returns an array which contains all info in the tree,
	 * sorted by their respective keys,
	 * or an empty array if the tree is empty.
	 */
	public String[] infoToArray() {
		List<String> info = new ArrayList<String>();
		if(this.empty())
		{
			String[] infoArray = new String[0]; 
			return infoArray;
		}
		else
		{
			recinfoToArray(this.root , info);	
		}

		String[] infoToArr = new String[this.size()];
		for(int i = 0 ; i < info.size(); i++)
		{
			infoToArr[i] = info.get(i);
		}

		return infoToArr;
	}

	/**
	 * public int size()
	 *
	 * Returns the number of nodes in the tree.
	 *
	 * precondition: none
	 * postcondition: none
	 */
	public int size()  {
		return this.treeSize;	
	}






	public void printBinaryTree(WAVLNode root, int level){
		if(root == null)
			return;
		printBinaryTree(root.right, level+1);
		if(level!=0)
		{
			for(int i=0;i<level-1;i++)
				System.out.print("|\t");
			System.out.println("|-------------------------"+root.key +" " + Arrays.toString(this.getRankDiff(root)) + " " + " Rank = " + root.rank );
		}
		else
			System.out.println(root.key +" " + Arrays.toString(this.getRankDiff(root))+ " " + " Rank = " + root.rank);
		printBinaryTree(root.left, level+1);
	}

	/**
	 * public class WAVLNode
	 *
	 * If you wish to implement classes other than WAVLTree
	 * (for example WAVLNode), do it in this file, not in 
	 * another file.
	 * This is an example which can be deleted if no such classes are necessary.
	 */
	public class WAVLNode {

		/** Node data */
		public String info;

		/** Key */
		public int key;

		/** Left child */
		public WAVLNode left;

		/** Right child */
		public WAVLNode right;

		/** Height of node */
		public int height;

		/** Rank of node */
		public int rank;

		/** Parent of node */
		public WAVLNode parent;

		public WAVLNode(int key,String info){
			this.key = key;
			this.info = info;
			this.left = null;
			this.right = null;
			this.parent = null;
			this.rank = 0;
		}
	}
}