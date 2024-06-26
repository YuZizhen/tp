package seedu.findvisor.commons.core;

import java.awt.Point;
import java.io.Serializable;
import java.util.Objects;

import seedu.findvisor.commons.util.ToStringBuilder;

/**
 * A Serializable class that contains the GUI settings.
 * Guarantees: immutable.
 */
public class GuiSettings implements Serializable {

    private static final double DEFAULT_WIDTH = 1045;
    private static final double DEFAULT_HEIGHT = 755;
    private static final double DEFAULT_DIVIDER_POSITION = 0.315;

    private final double windowWidth;
    private final double windowHeight;
    private final Point windowCoordinates;
    private final boolean isMaximized;
    private final double dividerPosition;

    /**
     * Constructs a {@code GuiSettings} with the default height, width window position and divider position.
     */
    public GuiSettings() {
        windowWidth = DEFAULT_WIDTH;
        windowHeight = DEFAULT_HEIGHT;
        windowCoordinates = null; // null represent no coordinates
        isMaximized = false;
        dividerPosition = DEFAULT_DIVIDER_POSITION;
    }

    /**
     * Constructs a {@code GuiSettings} with the specified height, width window position and divider position.
     */
    public GuiSettings(double windowWidth, double windowHeight, int xPosition, int yPosition,
                       boolean isMaximized, double dividerPosition) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        windowCoordinates = new Point(xPosition, yPosition);
        this.isMaximized = isMaximized;
        this.dividerPosition = dividerPosition;
    }

    public double getWindowWidth() {
        return windowWidth;
    }

    public double getWindowHeight() {
        return windowHeight;
    }

    public Point getWindowCoordinates() {
        return windowCoordinates != null ? new Point(windowCoordinates) : null;
    }

    public boolean getIsMaximized() {
        return isMaximized;
    }

    public double getDividerPosition() {
        return dividerPosition;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GuiSettings)) {
            return false;
        }

        GuiSettings otherGuiSettings = (GuiSettings) other;
        return windowWidth == otherGuiSettings.windowWidth
                && windowHeight == otherGuiSettings.windowHeight
                && Objects.equals(windowCoordinates, otherGuiSettings.windowCoordinates)
                && isMaximized == otherGuiSettings.isMaximized
                && dividerPosition == otherGuiSettings.dividerPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(windowWidth, windowHeight, windowCoordinates, isMaximized, dividerPosition);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("windowWidth", windowWidth)
                .add("windowHeight", windowHeight)
                .add("windowCoordinates", windowCoordinates)
                .add("isMaximized", isMaximized)
                .add("dividerPosition", dividerPosition)
                .toString();
    }
}
