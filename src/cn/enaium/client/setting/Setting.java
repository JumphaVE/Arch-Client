package cn.enaium.client.setting;

/**
 * @author Enaium
 * @since 1.0
 */
public class Setting<T> {
    private final String name;
    private T value;

    public Setting(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}