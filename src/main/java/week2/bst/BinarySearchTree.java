package week2.bst;


public class BinarySearchTree<E extends Comparable<E>> {
	class Node {
		E value;
		Node leftChild = null;
		Node rightChild = null;
		Node(E value) {
			this.value = value;
		}
		@Override
		public boolean equals(Object obj) {
			if ((obj instanceof BinarySearchTree.Node) == false)
				return false;
			@SuppressWarnings("unchecked")
			Node other = (BinarySearchTree<E>.Node)obj;
			return other.value.compareTo(value) == 0 &&
					other.leftChild == leftChild && other.rightChild == rightChild;
		}
	}
	
	protected Node root = null;
	
	protected void visit(Node n) {
		System.out.println(n.value);
	}
	
	public boolean contains(E val) {
		return contains(root, val);
	}
	
	protected boolean contains(Node n, E val) {
		if (n == null) return false;
		
		if (n.value.equals(val)) {
			return true;
		} else if (n.value.compareTo(val) > 0) {
			return contains(n.leftChild, val);
		} else {
			return contains(n.rightChild, val);
		}
	}
	
	public boolean add(E val) {
		if (root == null) {
			root = new Node(val);
			return true;
		}
		return add(root, val);
	}
	
	protected boolean add(Node n, E val) {
		if (n == null) {
			return false;
		}
		int cmp = val.compareTo(n.value);
		if (cmp == 0) {
			return false; // this ensures that the same value does not appear more than once
		} else if (cmp < 0) {
			if (n.leftChild == null) {
				n.leftChild = new Node(val);
				return true;
			} else {
				return add(n.leftChild, val);
			} 	
		} else {
			if (n.rightChild == null) {
				n.rightChild = new Node(val);
				return true;
			} else {
				return add(n.rightChild, val);
			} 	
		}
	}	
	
	public boolean remove(E val) {
		return remove(root, null, val);
	}
	
	protected boolean remove(Node n, Node parent, E val) {
		if (n == null) return false;

		if (val.compareTo(n.value) == -1) {
				return remove(n.leftChild, n, val);
		} else if (val.compareTo(n.value) == 1) {
				return remove(n.rightChild, n, val);
		} else {
			if (n.leftChild != null && n.rightChild != null){
				n.value = maxValue(n.leftChild);
				remove(n.leftChild, n, n.value);
			} else if (parent == null) {
				root = n.leftChild != null ? n.leftChild : n.rightChild;
			} else if (parent.leftChild == n){
				parent.leftChild = n.leftChild != null ? n.leftChild : n.rightChild;
			} else {
				parent.rightChild = n.leftChild != null ? n.leftChild : n.rightChild;
			}
			return true;
		}
	}

	protected E maxValue(Node n) {
		if (n.rightChild == null) {
			return n.value;
	    } else {
	       return maxValue(n.rightChild);
	    }
	}

	
	/*********************************************
	 * 
	 * IMPLEMENT THE METHODS BELOW!
	 *
	 *********************************************/
	
	
	// Method #1.
	public Node findNode(E val) {
		if (val == null) return null;

		return findNode(root, val);
	}

	protected Node findNode(Node n, E val) {
		if (n == null) return null;

		if (n.value.equals(val)) {
			return n;
		} else if (n.value.compareTo(val) > 0) {
			return findNode(n.leftChild, val);
		} else {
			return findNode(n.rightChild, val);
		}
	}
	
	// Method #2.
	protected int depth(E val) {
		if (val == null) return -1;

		return depth(root, val, 0);
	}

	protected int depth(Node n, E val, int d) {
		if (n == null) return -1;

		if (n.value.equals(val)) {
			return d;
		} else if (n.value.compareTo(val) > 0) {
			d++;
			return depth(n.leftChild, val, d);
		} else {
			d++;
			return depth(n.rightChild, val, d);
		}
	}
	
	// Method #3.
	protected int height(E val) {
		if (val == null) return -1;

		return height(root, val, 0, false);
	}

	protected int height(Node n, E val, int d, boolean found) {
		if (n == null) return -1;

		d++;
		if (n.value.equals(val)) {
			if (n.leftChild == null && n.rightChild == null) return 0;

			int tmp1 = 0;
			if (n.leftChild != null) {
				tmp1 = height(n.leftChild, val, d, true);
			}

			int tmp2 = 0;
			if (n.rightChild != null) {
				tmp2 =height(n.rightChild, val, d, true);
			}

			// It... works?
			int tmp3 = Math.max(tmp1, tmp2);
			return Math.max(d, tmp3);
		}

		if (!found) {
			if (n.value.compareTo(val) > 0) {
				return height(n.leftChild, val, d, false);
			} else {
				return height(n.rightChild, val, d, false);
			}
		} else {
			int tmp1 = d;
			int tmp2 = d;
			if (n.rightChild != null) {
				tmp1 = height(n.rightChild, val, d, true);
			} else {
				tmp1--;
			}

			if (n.leftChild != null) {
				tmp2 = height(n.leftChild, val, d, true);
			} else {
				tmp2--;
			}

			return Math.max(tmp1, tmp2);
		}
	}


	// Method #4.
	protected boolean isBalanced(Node n) {
		if (n == null) return false;

		if (!contains(n.value)) return false;

		int left;
		int right;

		if (n.leftChild != null) {
			left = height(n.leftChild, n.value, 0, true);
		} else {
			left = -1;
		}

		if (n.rightChild != null) {
			right = height(n.rightChild, n.value, 0, true);
		} else {
			right = -1;
		}

		return Math.abs(left - right) <= 1;
	}
	
	// Method #5. .
	public boolean isBalanced() {
		Node n = root;

		// Simple solution that works for this homework
		while (n != null) {
			if (isBalanced(n)) {
				n = n.leftChild;
			} else {
				return false;
			}
		}

		// It may be too simple for real problems
		n = root;
		while (n != null) {
			if (isBalanced(n)) {
				n = n.rightChild;
			} else {
				return false;
			}
		}
		
		return true;

	}
}
