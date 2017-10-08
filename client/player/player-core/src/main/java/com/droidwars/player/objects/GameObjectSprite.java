package com.droidwars.player.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.droidwars.game.objects.AbstractGameObject;
import lombok.Getter;

/**
 * Графическое представление игрового объекта
 * @param <T> реальный класс объекта
 */
public class GameObjectSprite<T extends AbstractGameObject> extends Sprite{

    /**
     * Реальный объект игрового мира, который отрисовывается с помощью данного спрайта
     */
    @Getter
    protected T subject;

    public GameObjectSprite(T subject) {
        this.subject = subject;
    }

    /**
     * Метод отрисовывает игровой объект
     * @param batch пакет для отрисовки
     */
    public void draw(Batch batch) {
        this.setRotation(subject.getFacing().angle());
        this.setPosition(subject.getPosition().x, subject.getPosition().y);

        super.draw(batch);
    }

    /**
     * @param sprite спрайт для отрисовки этого объекта
     */
    public void setSprite(Sprite sprite) {
        this.set(sprite);
        this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
    }
}
