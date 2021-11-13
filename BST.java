
class Node {
	int data;
	Node left;
	Node right;
}

class bstMethods {
	
	public Node createNewNode(int k) {
		Node a = new Node();
		a.data = k; 
		a.left = null;
		a.right = null;
		return a;
	}
	public Node insert(Node node, int val) {
		if (node == null) {
			return createNewNode(val);
		}
		if(val < node.data) {
			node.left = insert(node.left, val);
		} else if(val > node.data) {
			node.right = insert(node.right, val);
		}
		return node; 
	}
	
	public Node delete(Node node, int val) {
		if(node == null) {
			return null;
		}
		if(val < node.data) {
			node.left = delete(node.left,val);
		} else if(val > node.data){
			node.right = delete(node.right, val);
		} else {
			if(node.left == null || node.right == null) {
				Node temp = null;
				temp = node.left == null ? node.right : node.left;
				if(temp == null) {
					return null;
				} else {
					return temp;
				}
			} else {
				Node successor = getSuccessor(node);
				node.data = successor.data;
				node.right = delete(node.right, successor.data);
				return node;
			}
		}
		return node;
	}
	
	public Node getSuccessor(Node node) {
		if(node == null) {
			return null;
		}
		Node temp = node.right;
		while(temp.left != null) {
			temp = temp.left;
		}
		return temp;
	}
	
	public void inorder(Node node) {
		if(node == null) {
			return;
		}
		
		inorder(node.left);
		System.out.print(node.data + " ");
		inorder(node.right);
		
	}
	
	public void preorder(Node node) {
		if(node == null) {
			return;
		}
		
		System.out.print(node.data + " ");
		preorder(node.left);
		preorder(node.right);
	}
	
	public void postorder(Node node) {
		if(node == null) {
			return;
		}
		postorder(node.left);
		postorder(node.right);
		System.out.print(node.data + " ");
	}
	
	public boolean isNodePresent(Node node, int val) {
		boolean boolval = false;
		int i = 0;
		if(node == null) {
			boolval = false;
		}
		while(node != null && i == 0) {
			if (val < node.data) {
				node = node.left;
			} else if(val > node.data) {
				node = node.right;
			} else {
				boolval = true;
				i=-1;
			}
		}
		return boolval;
	}
	
	public Node getParentNode(Node node, int val) {
		if(node == null) {
			return null;
		}
		
		Node getParent = null;
		while(node != null) {
			if(val < node.data) {
				getParent = node;
				node = node.left;
			} else if (val > node.data) {
				getParent = node; 
				node = node.right;
			}
			else {
				break;
			}
		}
		
		return node != null ? getParent : null;
	}
	public static Node findMinimum(Node root)
    {
        while (root.left != null) {
            root = root.left;
        }
 
        return root;
    }
	
	// Recursive function to find an inorder successor for the given key in the BST
    public Node findSuccessor(Node root, Node succ, int key)
    {
        // base case
        if (root == null) {
            return null;
        }
 
        // if a node with the desired value is found, the successor is the minimum
        // value node in its right subtree (if any)
        if (root.data == key)
        {
            if (root.right != null) {
                return findMinimum(root.right);
            }
        }
 
        // if the given key is less than the root node, recur for the left subtree
        else if (key < root.data)
        {
            // update successor to the current node before recursing in the
            // left subtree
            succ = root;
            return findSuccessor(root.left, succ, key);
        }
 
        // if the given key is more than the root node, recur for the right subtree
        else {
            return findSuccessor(root.right, succ, key);
        }
 
        return succ;
    }
	
	
	public Node getInorderSuccessor(Node node, int val) {
		if(node == null) {
			return null;
		}
		Node inorderSuccessor = null;
		
		while(node != null) {
			if(val < node.data) {
				inorderSuccessor = node;
				node = node.left;
			}else if (val > node.data) {
				node = node.right;
			} else {
				if(node.right != null) {
					inorderSuccessor = getSuccessor(node);
				}
				break;
			}
		}
		return node != null ? inorderSuccessor : null;
	}
}

public class BST {
	public static void main(String[] args) {
		bstMethods a = new bstMethods();
		Node root = null;
		root = a.insert(root, 8);		
		root = a.insert(root, 3);
		root = a.insert(root, 6);
		root = a.insert(root, 10);
		root = a.insert(root, 4);
		root = a.insert(root, 7);
		root = a.insert(root, 1);
		root = a.insert(root, 14);
		root = a.insert(root, 13);
		
		//root = a.delete(root,3);
		a.inorder(root);
		System.out.println("");
		a.preorder(root);
		System.out.println("");
		a.postorder(root);
		System.out.println("");
		System.out.println(a.isNodePresent(root,3));
		System.out.println(a.getInorderSuccessor(root,1).data);
		System.out.println(a.findSuccessor(root, null, 1).data);
	}

}
