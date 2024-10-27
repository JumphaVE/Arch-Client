package cn.enaium.client.gui.clickgui;

import cn.enaium.client.mod.Category;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Enaium
 */
public class ClickGui extends GuiScreen {

    private final List<CategoryPanel> categoryPanels = new ArrayList<>();

    public ClickGui() {
        int y = 0;
        for (Category value : Category.values()) {
            categoryPanels.add(new CategoryPanel(0, y, value));
            y += 50;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        categoryPanels.forEach(it -> it.drawScreen(mouseX, mouseY, partialTicks));
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        categoryPanels.forEach(it -> it.mouseClicked(mouseX, mouseY, mouseButton));
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        categoryPanels.forEach(it -> it.mouseReleased(mouseX, mouseY, state));
        super.mouseReleased(mouseX, mouseY, state);
    }

    public boolean doesGuiPauseGame() {
        return false;
    }
}
