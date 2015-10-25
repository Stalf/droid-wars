package com.droidwars.game.objects.ships;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.objects.AbstractGameObject;
import com.droidwars.game.weaponry.Weapon;
import com.google.common.collect.ImmutableList;
import lombok.Getter;

import java.util.List;

/**
 * Абстрактный корабль
 */
public abstract class Ship extends AbstractGameObject {

    /**
     * Скорость поворота вокруг центральной оси - градусов в секунду
     */
    @Getter
    private float turnSpeed = 0.0f;

    /**
     * Ускорение в боковом направлении - м / с^2 <br/>
     * Вектор ускорения применяется в направлении, перпендикулярном {@link AbstractGameObject#facing}
     */
    @Getter
    private float strafe = 0.0f;

    /**
     * Текущие единицы прочности корпуса корабля
     */
    @Getter
    private float hullPoints = 0;

    /**
     * Максимальные единицы прочности корпуса корабля
     */
    @Getter
    private float maxHullPoints = 0;

    /**
     * Список оружейных слотов
     */
    @Getter
    protected List<Weapon> weaponSlots = ImmutableList.of();

    public Ship(long id, Vector2 position, Vector2 facing) {
        super(id, position, facing);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (this.getHullPoints() <= 0) {
            this.destroy();
        }

    }

    /**
     * Выполняет выстрел из всех доступных орудий
     */
    public void shoot() {
        for (Weapon next : weaponSlots) {
            next.shoot();
        }
    }

    /**
     * Выполняет выстрел
     * @param slot номер орудийного слота
     */
    public void shoot(int slot) {
        if (weaponSlots.size() < slot) {
            weaponSlots.get(slot).shoot();
        }
    }

    /**
     * Поворачивает корабль в указанном направлении на максимально возможный за такт угол
     *
     * @param direction направление поворота: 1 - против часовой стрелки; -1 - по часовой стрелке
     */
    public void turn(int direction) {
        this.getFacing().rotate(direction * this.turnSpeed * this.delta).nor();
    }

    /**
     * Поворачивает корабль в направлении вектора. Учитывает максимальную скорость поворота корабля.
     *
     * @param direction требуемое направление корабля
     */
    public void turn(Vector2 direction) {
        float angle = this.getFacing().angle(direction);

        this.getFacing().rotate(Math.signum(angle) * Math.min(this.turnSpeed * this.delta, Math.abs(angle))).nor();
    }

    /**
     * Придает кораблю ускорение (в том направлении, в котором он в данный момент расположен)
     */
    public void thrust() {
        this.getVelocity().add(this.getFacing().x * this.getAcceleration() * this.delta, this.getFacing().y * this.getAcceleration() * this.delta);
    }

    /**
     * Придает кораблю боковое ускорение (в направлении, перпендикулярном текущему расположению)
     *
     * @param direction направление смещения. -1 - влево, 1 - вправо
     */
    public void strafe(int direction) {
        this.getVelocity().add(this.getFacing().y * this.getStrafe() * direction * this.delta, - this.getFacing().x * this.getStrafe() * direction * this.delta);
    }

    /**
     * @return случайную точку на корабле
     */
    public Vector2 randomPointOnShip() {
        return new Vector2(this.getPosition().x + MathUtils.random(-this.getWidth() / 2, this.getWidth() / 2), this.getPosition().y
                + MathUtils.random(-this.getHeight() / 2, this.getHeight() / 2));
    }

    /**
     * @return прочность корпуса корабля в процентах
     */
    public float healthPercentage() {
        return Math.max(this.getHullPoints() / this.getMaxHullPoints(), 0);
    }

    /**
     * Уменьшает прочность корпуса корабля на переданную величину
     *
     * @param amount величина полученных повреждений
     */
    public void applyDamage(float amount) {
        this.hullPoints = Math.max(this.hullPoints - amount, 0);
    }

}
