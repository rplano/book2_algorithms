package graph;
import java.awt.*;
import java.awt.geom.*;

import acm.graphics.GLine;

public class GArrow extends GLine {
	
	private int size;
	private boolean filled;

	private AffineTransform tx;
	private Polygon arrowHead;

	public GArrow(double x0, double y0, double x1, double y1, int size, boolean filled) {
		super(x0, y0, x1, y1);

		this.tx = new AffineTransform();
		this.size = size;
		this.filled = filled;
		this.arrowHead = createSimpleArrowHead();
	}

	public GArrow(double x0, double y0, double x1, double y1) {
		this(x0, y0, x1, y1, 5, true);
	}
	
	private Polygon createSimpleArrowHead() {
		Polygon ah = new Polygon();
		ah.addPoint(0, size);
		ah.addPoint(-size, -size);
		ah.addPoint(size, -size);
		return ah;
	}

	public void paint(Graphics g) {
		super.paint(g);

		double x0 = getX();
		double y0 = getY();
		double x1 = getEndPoint().getX();
		double y1 = getEndPoint().getY();

		tx.setToIdentity();
		double angle = Math.atan2(y1 - y0, x1 - x0);
		tx.translate(x1, y1);
		tx.rotate((angle - Math.PI / 2d));

		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setTransform(tx);
		if (filled) {
			g2d.fill(arrowHead);			
		} else {
			g2d.draw(arrowHead);						
		}
		g2d.dispose();
	}
}
