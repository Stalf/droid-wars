package com.droidwars.game.objects.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.objects.AbstractGameObject;
import com.droidwars.game.objects.ships.Ship;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Абстрактный класс снаряда, который может наносить урон
 */
public class Projectile extends AbstractGameObject {

    /**
     * Величина наносимого урона
     */
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private float damage = 0;

    /**
     * Максимальное время жизни снаряда (0 - не ограничено)
     */
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private float maxAliveTime = 0;

    /**
     * Максимальная дальность (0 - не ограничена)
     */
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private float maxDistance = 0;

    /**
     * Корабль, который произвел выстрел
     */
    @Getter(AccessLevel.PACKAGE)
    @Setter(AccessLevel.PROTECTED)
    private Ship owner;

    public Projectile(long id, Vector2 position, Vector2 facing) {
        super(id, position, facing);
    }

    @Override
    public void update(float delta) {
        if (!isAlive()) {
            return;
        }

        super.update(delta);

        // Проверяем условия дальности и длительности полета
        if ((this.maxAliveTime > 0) && (this.getAliveTime() > this.maxAliveTime)) {
            this.destroy();
        }
        if ((this.maxDistance > 0) && (this.getTravelDistance() > this.maxDistance)) {
            this.destroy();
        }

    }

}
