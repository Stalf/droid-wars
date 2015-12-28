package com.droidwars.player.factory;

import com.droidwars.game.GameInstance;
import com.droidwars.game.factory.GameObjectFactory;
import com.droidwars.game.objects.ships.Frigate;
import com.droidwars.game.objects.ships.Ship;
import com.droidwars.player.objects.ships.FrigateSprite;
import com.droidwars.player.objects.ships.ShipSprite;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Фабрика создает графические представления игровых объектов на основе их логических сущностей
 */
@Slf4j
@RequiredArgsConstructor
public class GameObjectSpriteFactory implements GameObjectFactory {

    @NonNull
    @Getter
    protected GameInstance gameInstance;

    private final static Map<Class<? extends Ship>, Class<? extends ShipSprite>> shipDecoratorMap = ImmutableMap.of(
            Ship.class, ShipSprite.class,
            Frigate.class, FrigateSprite.class);

    // TODO возможно стоит реализовать через Reflection и автоматический поиск класса

    public ShipSprite getSpriteObject(Ship subject) {
        Class<? extends ShipSprite> aClass = shipDecoratorMap.get(subject.getClass());
        try {
            Constructor<? extends ShipSprite> constructor = aClass.getConstructor(subject.getClass());

            return constructor.newInstance(subject);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            log.error("Не найден класс-декоратор для {}", subject.getClass(), e);
            throw new RuntimeException("Ошибка при создании игровых объектов");
        }
    }

    public List<ShipSprite> getShipSprites(Collection<? extends Ship> objects) {
        List<ShipSprite> result = Lists.newLinkedList();
        for (Ship next : objects) {
            result.add(getSpriteObject(next));
        }

        return result;
    }

}
