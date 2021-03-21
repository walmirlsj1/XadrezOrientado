package pecas;

import regras.Status;

import java.awt.*;

public class PointStatus extends Point {
    public Status status;

    public PointStatus(Point p, Status status) {
        super(p.x, p.y);
        this.status = status;
    }
}
