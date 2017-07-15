package com.kalita_ivan.balls.engine.objects;

import com.kalita_ivan.balls.engine.geometry.Vector2;
import com.kalita_ivan.balls.engine.modifiers.ModifierInterface;
import com.kalita_ivan.balls.engine.modifiers.RigidBody;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Ball extends AbstractGameObject {
    private double radius;
    private Color color;

    private Ball(Vector2 position, Vector2 acceleration, Vector2 speed, double radius, Color color, double mass) {
        this.modifiers = new ModifierInterface[1];
        this.modifiers[0] = new RigidBody("physics", position, acceleration, speed, mass);
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void render(Graphics g) {
        RigidBody r = this.getModifierByType(RigidBody.class);
        if (r == null) {
            return;
        }
        Vector2 pos = r.getPosition();
        int x = (int) (pos.getX() - radius);
        int y = (int) (pos.getY() - radius);

        g.setColor(this.color);
        g.fillOval(x, y, (int) radius * 2, (int) radius * 2);
    }

    static public Ball random(double maxX, double maxY) {
        double x = ThreadLocalRandom.current().nextDouble(0, maxX);
        double y = ThreadLocalRandom.current().nextDouble(0, maxY);
        double ax =ThreadLocalRandom.current().nextDouble(-30, 30);
        double ay =ThreadLocalRandom.current().nextDouble(-30, 30);
        double radius = ThreadLocalRandom.current().nextDouble(0, 30);
        int r = ThreadLocalRandom.current().nextInt(0, 255);
        int g = ThreadLocalRandom.current().nextInt(0, 255);
        int b = ThreadLocalRandom.current().nextInt(0, 255);
        double vx = ThreadLocalRandom.current().nextDouble(-30f, 30f);
        double vy = ThreadLocalRandom.current().nextDouble(-30f, 30f);

        return new Ball(
                new Vector2(x, y),
                new Vector2(ax, ay),
                new Vector2(vx, vy),
                radius,
                new Color(r, g, b),
                1
        );
    }

    @Override
    public ModifierInterface[] getModifiers() {
        return this.modifiers;
    }
}