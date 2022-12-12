import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import tree.AbstractNode;
import tree.OrderedNode;
import tree.OrderedTree;
import acm.graphics.GArc;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * FileSizesGraphical
 * 
 * Compute space of files in directory and subdirectories and display
 * graphically as arcs.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class FileSizesGraphical extends GraphicsProgram {

	private static final int DISK_THICKNESS = 70;

	class FileAndSize {
		public File file;
		public long size;

		public FileAndSize(File file, long size) {
			this.file = file;
			this.size = size;
		}
	}

	private int centerX;
	private int centerY;
	private RandomGenerator rgen = new RandomGenerator();
	private GLabel hintLabel = new GLabel("");

	public void run() {
		setSize(400, 400);

		add(hintLabel);

		OrderedTree<FileAndSize> fileTree = createFileTree(new File(
				"/home/ralph/Desktop/"));

		postOrder(fileTree.root());

		traverseAndDrawRects(fileTree.root(), 0, 0, getWidth(), getHeight(),
				true, 5);

		// centerX = getWidth() / 2;
		// centerY = getHeight() / 2 - 20;
		// traverseAndDrawArcs(fileTree.root(), 0, 360, 0);
	}

	private void traverseAndDrawArcs(AbstractNode<FileAndSize> tree,
			double alpha, double sweep, int depth) {
		if (depth > 8)
			return;
		depth++;
		long parentSize = tree.getElement().size;
		for (AbstractNode<FileAndSize> child : tree.getChildren()) {
			final long childSize = child.getElement().size;
			final String fileName = child.getElement().file.getName();
			double deltaAlpha = sweep * childSize / parentSize;

			if (deltaAlpha > 1) {
				traverseAndDrawArcs(child, alpha, deltaAlpha, depth);
				int radius = depth * DISK_THICKNESS;
				GArc arc = new GArc(centerX - radius / 2, centerY - radius / 2,
						radius, radius, alpha, deltaAlpha);
				arc.setFilled(true);
				arc.setColor(rgen.nextColor());
				arc.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseExited(MouseEvent e) {
						hintLabel.setVisible(false);
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						hintLabel.setLocation(e.getX(), e.getY());
						// hintLabel.setLabel("" + childSize / 1000000);
						hintLabel.setLabel(fileName);
						hintLabel.setFont("Times New Roman-bold-18");
						hintLabel.sendToFront();
						hintLabel.setVisible(true);
					}
				});
				add(arc);
			}
			// traverseAndDrawRects(child, x, y, w, height, !horizontal, depth);
			alpha += deltaAlpha;
			// println(alpha);
		}
	}

	private void traverseAndDrawRects(AbstractNode<FileAndSize> tree, double x,
			double y, double width, double height, boolean horizontal, int depth) {
		if (depth < 0)
			return;
		depth--;
		long parentSize = tree.getElement().size;
		println(parentSize);
		// double x = 0;
		// double y = 0;
		for (AbstractNode<FileAndSize> child : tree.getChildren()) {
			long childSize = child.getElement().size;
			String fileNmae = child.getElement().file.getName();
			if (horizontal) {
				double w = width * childSize / parentSize;
				GRect r = new GRect(x, y, w, height);
				addRect(r, childSize, fileNmae);
				// traverse(child, x, y, w-1, height-1, !horizontal,depth);
				traverseAndDrawRects(child, x, y, w, height, !horizontal, depth);
				x += w;
			} else {
				double h = height * childSize / parentSize;
				GRect r = new GRect(x, y, width, h);
				addRect(r, childSize, fileNmae);
				// traverse(child, x, y, width-1, h-1, !horizontal, depth);
				traverseAndDrawRects(child, x, y, width, h, !horizontal, depth);
				y += h;
			}
			// traverse(child);
			println(childSize);
		}
	}

	private void addRect(GRect r, final long childSize, final String fileName) {
		r.setFilled(true);
		r.setFillColor(rgen.nextColor());
		r.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				hintLabel.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				hintLabel.setLocation(e.getX(), e.getY());
				// hintLabel.setLabel("" + childSize / 1000000);
				hintLabel.setLabel(fileName);
				hintLabel.setFont("Times New Roman-bold-18");
				hintLabel.sendToFront();
				hintLabel.setVisible(true);
			}
		});
		add(r);
	}

	private long postOrder(AbstractNode<FileAndSize> pos) {
		long sizeOfFilesInDir = 0;

		if (pos.getChildren() != null) {
			for (AbstractNode<FileAndSize> child : pos.getChildren()) {
				sizeOfFilesInDir += postOrder(child);
			}
		}
		sizeOfFilesInDir += visit(pos);

		FileAndSize fas = pos.getElement();
		fas.size = sizeOfFilesInDir;

		return sizeOfFilesInDir;
	}

	private long visit(AbstractNode<FileAndSize> pos) {
		long sizeOfFilesInDir = 0;
		File f = pos.getElement().file;
		if (f.isDirectory()) {
			for (File ff : f.listFiles()) {
				if (ff.isFile()) {
					sizeOfFilesInDir += ff.length();
				}
			}
		} else {
			println("we should never get here!");
		}
		return sizeOfFilesInDir;
	}

	private OrderedTree<FileAndSize> createFileTree(File startFile) {
		OrderedNode<FileAndSize> directories = new OrderedNode<FileAndSize>(
				new FileAndSize(startFile, -1));
		buildFileTreeRecursive(directories);
		return new OrderedTree<FileAndSize>(directories);
	}

	private void buildFileTreeRecursive(OrderedNode<FileAndSize> node) {
		File file = node.getElement().file;
		if (file.listFiles() != null) {
			for (File child : file.listFiles()) {
				// we are only interested in directories
				if (child.isDirectory()) {
					OrderedNode<FileAndSize> newDir = new OrderedNode<FileAndSize>(
							new FileAndSize(child, -1));
					node.addChild(newDir);
					buildFileTreeRecursive(newDir);
				}
			}
		}
	}
}
