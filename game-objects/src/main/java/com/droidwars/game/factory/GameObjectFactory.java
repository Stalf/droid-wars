package com.droidwars.game.factory;

import com.droidwars.game.GameInstance;
import com.droidwars.game.objects.AbstractGameObject;

/**
 * Интерфейс фабрики, предназначенной для создания экземпляров {@link AbstractGameObject}
 */
public interface GameObjectFactory {

    /**
     * @return уникальный идентификатор игрового объекта
     */
    GameInstance getGameInstance();

}
