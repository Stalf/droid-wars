package com.droidwars.game.objects.ships;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.droidwars.game.GameInstance;
import com.droidwars.game.command.Command;
import com.droidwars.game.command.CommandExecutor;
import com.droidwars.game.command.CommandExecutorImpl;
import com.droidwars.game.command.CommandType;
import com.droidwars.game.command.Manageable;
import com.droidwars.game.objects.AbstractGameObject;
import com.droidwars.game.weaponry.Weapon;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Abstract ship
 */
@Getter
@Setter(AccessLevel.PROTECTED)
public abstract class Ship extends AbstractGameObject implements Manageable<Ship> {

    /**
     * Rotation rate around ship central axis - grads per second
     */
    private float turnSpeed = 10.0f;

    /**
     * Acceleration in strafe direction - m / s^2 <br/>
     * Acceleration vector is applied in perpendicular to {@link AbstractGameObject#facing} direction
     */
    private float strafe = 5.0f;

    /**
     * Current ship hull points
     */
    private float hullPoints;

    /**
     * Maximum ship hull points
     */
    private float maxHullPoints = 100;

    /**
     * Team number. Used for friend or foe identification and ship coloring
     */
    private int teamNumber;

    /**
     * Weapon slots list
     */
    @Setter(AccessLevel.NONE)
    private List<Weapon> weaponSlots = Lists.newArrayList();

    /**
     * Ship commands executor
     */
    @Setter(AccessLevel.NONE)
    private CommandExecutor<Ship> commandExecutor = new CommandExecutorImpl<>(this);

    public Ship(GameInstance gameInstance, Vector2 position, Vector2 facing, int teamNumber) {
        super(gameInstance, position, facing);

        this.hullPoints = this.maxHullPoints;
        this.teamNumber = teamNumber;
    }

    protected final void setMaxHullPoints(float maxHullPoints) {
        this.maxHullPoints = maxHullPoints;
        this.hullPoints = maxHullPoints;
    }

    public List<Weapon> getWeaponSlots() {
        return ImmutableList.copyOf(weaponSlots);
    }

    protected final void setWeaponSlots(int number) {
        //TODO use fixedSize list
    }

    protected final void putWeapon(Weapon weapon, int slotNumber) {
        // TODO respect weaponSlotsSize
        weaponSlots.add(slotNumber, weapon);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (this.getHullPoints() <= 0) {
            this.destroy();
        }

        commandExecutor.execute(delta);
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
     *
     * @param slot номер орудийного слота
     */
    public void shoot(int slot) {
        if (weaponSlots.size() < slot) {
            weaponSlots.get(slot).shoot();
        } else {
            throw new IllegalArgumentException("Неверный номер оружейного слота");
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
        this.getVelocity().add(this.getFacing().y * this.getStrafe() * direction * this.delta, -this.getFacing().x * this.getStrafe() * direction * this.delta);
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
        this.hullPoints = Math.max(this.getHullPoints() - amount, 0);
    }

    @Override
    public void command(Command<Ship> command) {
        commandExecutor.add(command);
    }

    @Override
    public void command(Map<CommandType, Command<Ship>> commands) {
        for (Map.Entry<CommandType, Command<Ship>> command : commands.entrySet()) {
            command(command.getValue());
        }
    }
}
