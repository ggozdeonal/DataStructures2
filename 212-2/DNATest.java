import java.io.File;
import java.util.Scanner;

public class DNATest extends DNATree {

	public static <E> void main(String[] args) {

		File file = new File("test-hw2.txt");

		Scanner in = null;
		try {
			in = new Scanner(file);
		} catch (Exception e) {
			System.out.println("File not found!!");
			System.exit(0);
		}
		String l = "";
		DNATree<E> T = new DNATree();
		T.addRoot((E) "leaf");

		while (in.hasNextLine()) {
			l = in.nextLine();
			String[] directive = l.split(" +");

			System.out.println();
			System.out.println(l);

			switch (directive[0]) {
			case ("insert"):
				String sequence = directive[1];
				try {
					T.insertSequence((E) sequence, T, 'i');
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println();
				}
				break;
			case ("display"):
				T.display(T.root(), 0);
				break;
			case ("remove"):
				String removed = directive[1];
				try {
					T = T.removeSequence((E) removed, T);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println();
				}
				break;
			case ("display-lengths"):
				T.display2(T.root(), 0);
				break;
			case ("search"):
				String searched = directive[1];
				if (searched.charAt(searched.length() - 1) == '$')
					try {
						// T.searchSequence((E) searched.substring(0, searched.length() - 1), true);
						T.find(searched.substring(0, searched.length() - 1));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println();
					}
				else
					try {
						T.searchSequence$((E) searched);
						// T.findPrefix(searched, T);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println();
					}

				break;

			}
		}
	}

}
