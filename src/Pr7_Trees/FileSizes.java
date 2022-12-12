import java.io.File;

import tree.AbstractNode;
import tree.OrderedNode;
import tree.OrderedTree;
import tree.VisitorInterface;
import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * FileSizes
 * 
 * Compute space of files in directory and subdirectories using Postorder
 * Traversal
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class FileSizes extends ConsoleProgram {

	private long totalSize = 0;

	public void run() {
		setSize(400, 200);
		setFont("Times New Roman-bold-18");

		String startDirectory = "/home/ralph/Desktop/";
		OrderedTree<File> fileTree = createFileTree(new File(startDirectory));

		fileTree.postOrder(new VisitorInterface<File>() {
			@Override
			public void visit(AbstractNode<?> node) {
				long sizeOfFilesInDir = 0;
				File f = (File) node.getElement();
				if (f.isDirectory()) {
					for (File ff : f.listFiles()) {
						if (ff.isFile()) {
							sizeOfFilesInDir += ff.length();
						}
					}
				} else {
					println("we should never get here!");
				}
				totalSize += sizeOfFilesInDir;
				// println(node +": "+sizeOfFilesInDir);
			}
		});
		println("Total size of " + startDirectory + "\n" + totalSize + " bytes");
	}

	private OrderedTree<File> createFileTree(File startFile) {
		OrderedNode<File> directories = new OrderedNode<File>(startFile);
		buildFileTreeRecursive(directories);

		return new OrderedTree<File>(directories);
	}

	private void buildFileTreeRecursive(OrderedNode<File> node) {
		File file = node.getElement();
		if (file.listFiles() != null) {
			for (File child : file.listFiles()) {
				// we are only interested in directories
				if (child.isDirectory()) {
					OrderedNode<File> newDir = new OrderedNode<File>(child);
					node.addChild(newDir);
					buildFileTreeRecursive(newDir);
				}
			}
		}
	}
}
