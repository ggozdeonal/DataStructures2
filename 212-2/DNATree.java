import java.util.*;

public class DNATree<E> extends AbstractBinaryTree<E> {

	private ArrayList<E> sequences = new ArrayList();

	protected static class Node<E> implements Position<E> {
		private E element;
		// private String path;
		private Node<E> parent;
		private Node<E> ChildA;
		private Node<E> ChildT;
		private Node<E> ChildG;
		private Node<E> ChildC;
		private Node<E> Child$;

		public Node(E dna, String p, Node<E> above, Node<E> AChild, Node<E> TChild, Node<E> GChild, Node<E> CChild,
				Node<E> $Child) {
			element = dna;
			// path=p;
			parent = above;
			ChildA = AChild;
			ChildT = TChild;
			ChildG = GChild;
			ChildC = CChild;
			Child$ = $Child;
		}

		public E getElement() {
			return element;
		}

		public Node<E> getParent() {
			return parent;
		}

		public Node<E> getChildA() {
			return ChildA;
		}

		public Node<E> getChildT() {
			return ChildT;
		}

		public Node<E> getChildG() {
			return ChildG;
		}

		public Node<E> getChildC() {
			return ChildC;
		}

		public Node<E> getChild$() {
			return Child$;
		}

		public void setElement(E dna) {
			element = dna;
		}

		public void setParent(Node<E> parentNode) {
			parent = parentNode;
		}

		public void setChildA(Node<E> ChildA2) {
			ChildA = ChildA2;
		}

		public void setChildT(Node<E> ChildT2) {
			ChildT = ChildT2;
		}

		public void setChildG(Node<E> ChildG2) {
			ChildG = ChildG2;
		}

		public void setChildC(Node<E> ChildC2) {
			ChildC = ChildC2;
		}

		public void setChild$(Node<E> Child$2) {
			Child$ = Child$2;
		}

	}

	public int depth(Position<E> p) {
		if (isRoot(p))
			return 0;

		return 1 + depth(parent(p));
	}

	// Agactaki parcalama ve yerlestirme islemlerini yapar
	public void insertSequence(E dna, DNATree<E> T, char process) throws Exception {

		if ((root.getElement() != null && root.getElement().equals("leaf")) || (root.ChildA == null
				&& root.ChildC == null && root.ChildG == null && root.ChildT == null && root.Child$ == null)) {

			if (root.getElement().equals("leaf")) {
				T.root.setElement(dna);
				if (process == 'i')
					System.out.println(dna + " is inserted in level 0!");
			}

			else {
				T.addA(root, null);
				T.addT(root, null);
				T.addG(root, null);
				T.addC(root, null);
				T.add$(root, null);

				if (((String) root.element).charAt(0) == 'A')
					((Node<E>) ChildA(root)).setElement(root.element);
				else if (((String) root.element).charAt(0) == 'C')
					((Node<E>) ChildC(root)).setElement(root.getElement());
				else if (((String) root.element).charAt(0) == 'G')
					((Node<E>) ChildG(root)).setElement(root.getElement());
				else if (((String) root.element).charAt(0) == 'T')
					((Node<E>) ChildT(root)).setElement(root.getElement());

				root.setElement(null);
			}

		}

		if (root.getElement() == null) {
			int j = 0;
			String s = "";
			String b = "";
			Position<E> current = null;
			if (T.searchCheck(sequences, (String) dna) == false) {
				System.out.println("duplicate");
				return;
			}

			// else
			for (int i = ((String) dna).length(); i > 0; i--) {
				b = search(T.sequences, ((String) dna).substring(0, i));
				if (b != null) {
					s = ((String) dna).substring(0, i);
					i = -1;

				}

			}
			if (s.length() == 0) {

				if (((String) dna).charAt(0) == 'A') {
					if (root.getElement() == null)
						root.ChildA.setElement(dna);
				} else if (((String) dna).charAt(0) == 'T') {
					if (root.getElement() == null)
						root.ChildT.setElement(dna);
				} else if (((String) dna).charAt(0) == 'G') {
					if (root.getElement() == null)
						root.ChildG.setElement(dna);
				} else if (((String) dna).charAt(0) == 'C') {
					if (root.getElement() == null)
						root.ChildC.setElement(dna);
				}
				if (process == 'i')
					System.out.println(dna + " is inserted in level 1!");
			} else {
				/*
				 * current= find((String) b); int d=depth(current);
				 * 
				 * T.addA(current, null); T.addT(current, null); T.addG(current, null);
				 * T.addC(current, null); T.add$(current, null);
				 * 
				 * if(((String) current.getElement()).charAt(d)=='A') {
				 * current.ChildA.setElement(dna); }
				 * 
				 * }
				 */

				if (s.charAt(0) == 'A')
					current = ChildA(root);
				else if (s.charAt(0) == 'C')
					current = ChildC(root);
				else if (s.charAt(0) == 'G')
					current = ChildG(root);
				else if (s.charAt(0) == 'T')
					current = ChildT(root);

				for (int i = 0; i < s.length(); i++) {

					if (s.charAt(i) == 'A') {
						T.addA(current, null);
						T.addT(current, null);
						T.addG(current, null);
						T.addC(current, null);
						T.add$(current, null);

						if (i + 1 == s.length())
							break;

						if (s.charAt(i + 1) == 'A')
							current = ChildA(current);
						else if (s.charAt(i + 1) == 'C')
							current = ChildC(current);
						else if (s.charAt(i + 1) == 'G')
							current = ChildG(current);
						else if (s.charAt(i + 1) == 'T')
							current = ChildT(current);
					}

					else if (s.charAt(i) == 'C') {
						T.addA(current, null);
						T.addT(current, null);
						T.addG(current, null);
						T.addC(current, null);
						T.add$(current, null);

						if (i + 1 == s.length())
							break;

						if (s.charAt(i + 1) == 'A')
							current = ChildA(current);
						else if (s.charAt(i + 1) == 'C')
							current = ChildC(current);
						else if (s.charAt(i + 1) == 'G')
							current = ChildG(current);
						else if (s.charAt(i + 1) == 'T')
							current = ChildT(current);
					}

					else if (s.charAt(i) == 'G') {
						T.addA(current, null);
						T.addT(current, null);
						T.addG(current, null);
						T.addC(current, null);
						T.add$(current, null);

						if (i + 1 == s.length())
							break;

						if (s.charAt(i + 1) == 'A')
							current = ChildA(current);
						else if (s.charAt(i + 1) == 'C')
							current = ChildC(current);
						else if (s.charAt(i + 1) == 'G')
							current = ChildG(current);
						else if (s.charAt(i + 1) == 'T')
							current = ChildT(current);
					}

					else if (s.charAt(i) == 'T') {
						T.addA(current, null);
						T.addT(current, null);
						T.addG(current, null);
						T.addC(current, null);
						T.add$(current, null);

						if (i + 1 == s.length())
							break;

						if (s.charAt(i + 1) == 'A')
							current = ChildA(current);
						else if (s.charAt(i + 1) == 'C')
							current = ChildC(current);
						else if (s.charAt(i + 1) == 'G')
							current = ChildG(current);
						else if (s.charAt(i + 1) == 'T')
							current = ChildT(current);
					}

				}
				if (((String) dna).length() == (s.length())) {
					((Node<E>) Child$(current)).setElement(dna);
					if (process == 'i')
						System.out.println(dna + " is inserted in level " + (s.length() + 1) + "!");
				}

				else if (((String) dna).charAt(s.length()) == 'A') {
					((Node<E>) ChildA(current)).setElement(dna);
					if (process == 'i')
						System.out.println(dna + " is inserted in level " + (s.length() + 1) + "!");
				}

				else if (((String) dna).charAt(s.length()) == 'C') {
					((Node<E>) ChildC(current)).setElement(dna);
					if (process == 'i')
						System.out.println(dna + " is inserted in level " + (s.length() + 1) + "!");
				}

				else if (((String) dna).charAt(s.length()) == 'G') {
					((Node<E>) ChildG(current)).setElement(dna);
					if (process == 'i')
						System.out.println(dna + " is inserted in level " + (s.length() + 1) + "!");
				}

				else if (((String) dna).charAt(s.length()) == 'T') {
					((Node<E>) ChildT(current)).setElement(dna);
					if (process == 'i')
						System.out.println(dna + " is inserted in level " + (s.length() + 1) + "!");
				}

				if (((String) b).charAt(s.length()) == 'A')
					((Node<E>) ChildA(current)).setElement((E) b);

				else if (((String) b).charAt(s.length()) == 'C')
					((Node<E>) ChildC(current)).setElement((E) b);

				else if (((String) b).charAt(s.length()) == 'G')
					((Node<E>) ChildG(current)).setElement((E) b);

				else if (((String) b).charAt(s.length()) == 'T')
					((Node<E>) ChildT(current)).setElement((E) b);

			}
		}

		sequences.add(dna);

	}

	// uygun görülürse silme islemini yapar tree yeniden duzenlenir
	public DNATree<E> removeSequence(E dna, DNATree<E> T) throws Exception {

		boolean b = false;
		for (int i = 0; i < sequences.size(); i++) {
			if (sequences.get(i).equals(dna)) {
				System.out.println(dna + " is removed!");
				b = true;
				break;
			}
		}
		if (b == false)
			System.out.println(dna + " is not in the tree.");

		T.sequences.remove(dna);
		DNATree<E> tempT = new DNATree();
		tempT.addRoot((E) "leaf");

		for (int i = 0; i < sequences.size(); i++) {
			tempT.insertSequence(sequences.get(i), tempT, 'r');
		}

		T = tempT;

		return T;
	}

	// kullanmıyorum fakat ödev itirazında lazim olabilir düsüncesiyle tutuyorum
	/*
	 * public void findPrefix(String dna, DNATree<E> T) throws Exception {
	 * Position<E> current = null; System.out.println("Start with: " + dna); for
	 * (int i = 0; i < T.sequences.size(); i++) { if ((((String)
	 * sequences.get(i)).substring(0, dna.length())).equals(dna)) find((String)
	 * sequences.get(i)); } System.out.println("End");
	 * 
	 * }
	 */

	public Position<E> find(String dna) throws Exception {

		Position<E> current = findAux(dna, root(), 0);

		if (current == null) {
			System.out.println(dna + " is not found.");
			searchSequence((E) dna, true);
			// throw new Exception("FİND EXCEPTION");
			return null;
		}

		else {
			System.out.println(dna + " is found.");
			searchSequence((E) dna, true);
			return current;
		}

	}

	// yedek method
	private Position<E> findAux(String target, Position<E> next, int i) {
		if (next == null) {
			return null;
		}
		if (((Node<E>) next).element != null && ((Node<E>) next).element.equals(target)) {
			return next;
		}
		Position<E> temp = findAux(target, ChildA(next), i + 1);

		if (temp == null) {

			temp = findAux(target, ChildC(next), i + 1);
		}

		if (temp == null) {

			temp = findAux(target, ChildG(next), i + 1);
		}

		if (temp == null) {

			temp = findAux(target, ChildT(next), i + 1);
		}

		if (temp == null) {

			temp = findAux(target, Child$(next), i + 1);
		}
		return temp;
	}

	public void searchSequence$(E dna) throws Exception {
		int j = 0;
		if (searchCheck(sequences, (String) dna) == true)
			j += searchSequence((E) dna, false) - 1;

		Position<E> current = null;
		System.out.println("Start with: " + dna);
		for (int i = 0; i < sequences.size(); i++) {
			if ((((String) sequences.get(i)).substring(0, ((String) dna).length())).equals(dna)) {
				j += searchSequence((E) sequences.get(i), false);
				System.out.println(sequences.get(i));
			}
		}
		System.out.println("End of the serach total number o visited node()s " + j);

	}

	public int searchSequence(E dna, boolean yaz) {

		if (root.getElement() != null && root.getElement().equals(dna)) {
			System.out.println(dna + " is found.");
			System.out.println("Number of visited node(s): " + 1);
			return 1;
		}
		Position<E> current = root;
		int i = 0;
		int j = 1;
		boolean b = false;
		while (ChildA(current) != null || ChildC(current) != null || ChildG(current) != null || ChildT(current) != null
				|| Child$(current) != null) {
			if (((String) dna).charAt(i) == 'A') {
				current = ChildA(current);
				j++;
			} else if (((String) dna).charAt(i) == 'C') {
				current = ChildC(current);
				j++;
			} else if (((String) dna).charAt(i) == 'T') {
				current = ChildT(current);
				j++;
			} else if (((String) dna).charAt(i) == 'G') {
				current = ChildG(current);
				j++;
			}
			i++;
			if (((String) dna).length() == i) {
				b = true;
				break;

			}
		}
		if (current.getElement() != null && current.getElement().equals(dna)) {
			if (yaz == true) {
				// System.out.println(dna + " is found.");
				System.out.println("Number of visited node(s): " + j);
			}
			return j;
		}

		if (b == true) {
			if (current.getElement() != null && Child$(current).getElement().equals(dna)) {
				j++;
				if (yaz == true) {
					// System.out.println(dna + " is found.");
					System.out.println("Number of visited node(s): " + j);
				}
				return j;
			} else {
				j++;
				if (yaz == true) {
					// System.out.println(dna + " is not found.");
					System.out.println("Number of visited node(s): " + j);
				}
				return j;
			}

		}
		return j;

	}

	// kullanmıyorum fakat ödev itirazında lazim olabilir düsüncesiyle tutuyorum
	public int visitedNode(E dna, DNATree<E> T) {
		int sayac = 0;
		Position<E> current = null;
		if (((String) dna).charAt(0) == 'A' && ChildA(root) != null) {
			current = ChildA(root);
			sayac++;
		} else if (((String) dna).charAt(0) == 'C' && ChildA(root) != null) {
			current = ChildC(root);
			sayac++;
		} else if (((String) dna).charAt(0) == 'G' && ChildA(root) != null) {
			current = ChildG(root);
			sayac++;
		} else if (((String) dna).charAt(0) == 'T' && ChildA(root) != null) {
			current = ChildT(root);
			sayac++;
		}

		int k = 0;

		for (int i = 0; i < ((String) dna).length(); i++) {
			if (i + 1 == ((String) dna).length())
				break;

			if (((String) dna).charAt(i + 1) == 'A' && ChildA(current) != null) {
				System.out.println("A");
				current = ChildA(current);

				sayac++;
			}

			else if (((String) dna).charAt(i + 1) == 'C' && ChildC(current) != null) {
				System.out.println("C");
				current = ChildC(current);
				sayac++;
			}

			if (((String) dna).charAt(i + 1) == 'G' && ChildG(current) != null) {
				System.out.println("G");
				current = ChildG(current);
				sayac++;
			}

			if (((String) dna).charAt(i) == 'T' && ChildT(current) != null) {
				System.out.println("T");
				current = ChildT(current);
				sayac++;
			}
		}
		String dnas = (String) dna;
		if (searchCheck(sequences, (String) dna) == true) {
			if (dnas.charAt(sayac - 1) == 'A') {
				sayac = sayac + 5;
			}
			if (dnas.charAt(sayac - 1) == 'C') {
				sayac = sayac + 4;
			}
			if (dnas.charAt(sayac - 1) == 'G') {
				sayac = sayac + 3;
			}
			if (dnas.charAt(sayac - 1) == 'T') {
				sayac = sayac + 2;
			}

		} else
			sayac++;

		return sayac;
	}

	public void display(Position<E> next, int k) {
		if (next == null) {

			return;
		}

		for (int i = 0; i < k; i++)
			System.out.print(".");

		if (ChildA(next) != null || ChildA(next) != null || ChildC(next) != null || ChildG(next) != null
				|| ChildT(next) != null || Child$(next) != null)
			System.out.println("I");
		else if (((String) next.getElement()) != null && ((String) next.getElement()).length() > 0
				&& !((String) next.getElement()).equals("leaf"))
			System.out.println("[" + next.getElement() + "]");

		else
			System.out.println("E");

		display(ChildA(next), k + 1);

		display(ChildC(next), k + 1);

		display(ChildG(next), k + 1);

		display(ChildT(next), k + 1);

		display(Child$(next), k + 1);

	}

	public void display2(Position<E> next, int k) {
		if (next == null) {

			return;
		}

		for (int i = 0; i < k; i++)
			System.out.print(".");

		if (ChildA(next) != null || ChildA(next) != null || ChildC(next) != null || ChildG(next) != null
				|| ChildT(next) != null || Child$(next) != null)
			System.out.println("I");
		else if (((String) next.getElement()) != null && ((String) next.getElement()).length() > 0
				&& !((String) next.getElement()).equals("leaf"))
			System.out.println("[" + next.getElement() + "]" + " " + ((String) next.getElement()).length());

		else
			System.out.println("E");

		display2(ChildA(next), k + 1);

		display2(ChildC(next), k + 1);

		display2(ChildG(next), k + 1);

		display2(ChildT(next), k + 1);

		display2(Child$(next), k + 1);

	}

	public String search(ArrayList sequences, String dna) {
		String compare = "";
		String compare2 = "";
		for (int i = 0; i < sequences.size(); i++) {
			if (((String) sequences.get(i)).length() >= dna.length())
				compare = ((String) sequences.get(i)).substring(0, dna.length());
			compare2 = (String) sequences.get(i);
			if (compare.equals(dna) || compare2.equals(dna))
				return compare2;
		}
		return null;

	}

	public boolean searchCheck(ArrayList sequences, String dna) {
		for (int i = 0; i < sequences.size(); i++)
			if (sequences.get(i).equals(dna))
				return false;
		return true;
	}

	protected Node<E> createNode(E e, String p, Node<E> parent, Node<E> AChild, Node<E> TChild, Node<E> GChild,
			Node<E> CChild, Node<E> $Child) {
		return new Node<E>(e, p, parent, AChild, TChild, GChild, CChild, $Child);
	}

	protected Node<E> root = null; // root of the tree

	private int size = 0; // number of nodes in the tree

	// constructor

	public DNATree() {
	}

	protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node))
			throw new IllegalArgumentException("Not valid position type");
		Node<E> node = (Node<E>) p; // safe cast
		if (node.getParent() == node) // our convention for defunct node
			throw new IllegalArgumentException("p is no longer in the tree");
		return node;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Position<E> root() {
		return root;
	}

	@Override
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getParent();
	}

	@Override
	public Position<E> ChildA(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getChildA();
	}

	@Override
	public Position<E> ChildT(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getChildT();
	}

	public Position<E> ChildG(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getChildG();
	}

	public Position<E> ChildC(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getChildC();
	}

	public Position<E> Child$(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return node.getChild$();
	}

	public Position<E> addRoot(E e) throws IllegalStateException {
		if (!isEmpty())
			throw new IllegalStateException("Tree is not empty");
		root = createNode(e, null, null, null, null, null, null, null);
		size = 1;
		return root;
	}

	public Position<E> addA(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getChildA() != null)
			return null;

		Node<E> child = createNode(e, null, parent, null, null, null, null, null);
		parent.setChildA(child);
		size++;
		return child;
	}

	public Position<E> addT(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getChildT() != null)
			return null;

		Node<E> child = createNode(e, null, parent, null, null, null, null, null);
		parent.setChildT(child);
		size++;
		return child;
	}

	public Position<E> addG(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getChildG() != null)
			return null;

		Node<E> child = createNode(e, null, parent, null, null, null, null, null);
		parent.setChildG(child);
		size++;
		return child;
	}

	public Position<E> addC(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getChildC() != null)
			return null;

		Node<E> child = createNode(e, null, parent, null, null, null, null, null);
		parent.setChildC(child);
		size++;
		return child;
	}

	public Position<E> add$(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		if (parent.getChild$() != null)
			return null;

		Node<E> child = createNode(e, null, parent, null, null, null, null, null);
		parent.setChild$(child);
		size++;
		return child;
	}

	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E temp = node.getElement();
		node.setElement(e);
		return temp;
	}

	@Override
	public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
