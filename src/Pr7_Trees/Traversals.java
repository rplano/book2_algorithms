import javax.swing.JScrollPane;

import tree.AbstractNode;
import tree.OrderedNode;
import tree.OrderedTree;
import tree.OrderedTreeDrawerCanvas;
import tree.OrderedTreeParser;
import tree.VisitorInterface;
import acm.program.Program;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Traversals
 * 
 * Visualizes the different forms of tree traversal.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Traversals extends Program {

	public void init() {
		setSize(400, 200);

		String str = "A{B{C,D},E{F,G,H},I}";
		OrderedTree<String> tree = new OrderedTreeParser().parseTree(str);

		tree.levelOrder(new VisitorInterface<String>() {
			// tree.postOrder(new VisitorInterface<String>() {
			// tree.preOrder(new VisitorInterface<String>() {
			int counter = 1;

			public void visit(AbstractNode<?> node) {
				((OrderedNode<String>) node).setElement("" + counter++);
			}
		});

		OrderedTreeDrawerCanvas canvas = new OrderedTreeDrawerCanvas(tree);
		canvas.setShapeNode(OrderedTreeDrawerCanvas.SHAPE_OVAL);
		canvas.setOrientation(OrderedTreeDrawerCanvas.VERTICAL);
		canvas.setEdgeStyle(OrderedTreeDrawerCanvas.EDGE_STYLE_DIRECT);
		canvas.setNodeSeparationX(48);
		canvas.setNodeSeparationY(35);
		canvas.setFont("Courier new-bold-18");
		add(new JScrollPane(canvas), CENTER);
	}

}