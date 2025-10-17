package org;

import java.util.logging.Logger;

interface Shape {
    void draw();
    void resize();
}

class Square implements Shape {
    private static final Logger LOG = Logger.getLogger(Square.class.getName());

    @Override
    public void draw() {
        LOG.info("Drawing square");
    }

    @Override
    public void resize() {
        LOG.info("Resizing square");
    }
    public static void main(String[] args) {
        Shape s = new Square();   // dùng class để tránh cảnh báo "never used"
        s.draw();
        s.resize();
    }

}
