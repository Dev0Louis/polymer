package eu.pb4.polymer.virtualentity.api.elements;

import eu.pb4.polymer.virtualentity.api.tracker.DisplayTrackedData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.Brightness;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.util.math.AffineTransformation;
import net.minecraft.util.math.MatrixUtil;
import org.jetbrains.annotations.Nullable;
import org.joml.*;


public abstract class DisplayElement extends GenericEntityElement {
    @Override
    protected abstract EntityType<? extends DisplayEntity> getEntityType();

    public void setTransformation(AffineTransformation transformation) {
        this.dataTracker.set(DisplayTrackedData.TRANSLATION, transformation.getTranslation());
        this.dataTracker.set(DisplayTrackedData.LEFT_ROTATION, transformation.getLeftRotation());
        this.dataTracker.set(DisplayTrackedData.SCALE, transformation.getScale());
        this.dataTracker.set(DisplayTrackedData.RIGHT_ROTATION, transformation.getRightRotation());
    }

    public void setTransformation(Matrix4f matrix) {
        setTransformation(MatrixUtil.affineTransform(matrix));
    }

    public void setTransformation(Matrix4x3f matrix) {
        var triple = MatrixUtil.svdDecompose((new Matrix3f()).set(matrix));
        this.dataTracker.set(DisplayTrackedData.TRANSLATION, matrix.getTranslation(new Vector3f()));
        this.dataTracker.set(DisplayTrackedData.LEFT_ROTATION, new Quaternionf(triple.getLeft()));
        this.dataTracker.set(DisplayTrackedData.SCALE, new Vector3f(triple.getMiddle()));
        this.dataTracker.set(DisplayTrackedData.RIGHT_ROTATION, new Quaternionf(triple.getRight()));
    }

    public int getInterpolationDuration() {
        return this.dataTracker.get(DisplayTrackedData.INTERPOLATION_DURATION);
    }

    public void setInterpolationDuration(int interpolationDuration) {
        this.dataTracker.set(DisplayTrackedData.INTERPOLATION_DURATION, interpolationDuration);
    }

    public long getInterpolationStart() {
        return this.dataTracker.get(DisplayTrackedData.INTERPOLATION_START);
    }

    public void setInterpolationStart(long interpolationStart) {
        this.dataTracker.set(DisplayTrackedData.INTERPOLATION_START, interpolationStart);
    }

    public DisplayEntity.BillboardMode getBillboardMode() {
        return DisplayEntity.BillboardMode.FROM_INDEX.apply(this.dataTracker.get(DisplayTrackedData.BILLBOARD));
    }

    public void setBillboardMode(DisplayEntity.BillboardMode billboardMode) {
        this.dataTracker.set(DisplayTrackedData.BILLBOARD, (byte) billboardMode.ordinal());
    }

    @Nullable
    public Brightness getBrightness() {
        int i = this.dataTracker.get(DisplayTrackedData.BRIGHTNESS);
        return i != -1 ? Brightness.unpack(i) : null;
    }

    public void setBrightness(@Nullable Brightness brightness) {
        this.dataTracker.set(DisplayTrackedData.BRIGHTNESS, brightness != null ? brightness.pack() : -1);
    }

    public float getViewRange() {
        return this.dataTracker.get(DisplayTrackedData.VIEW_RANGE);
    }

    public void setViewRange(float viewRange) {
        this.dataTracker.set(DisplayTrackedData.VIEW_RANGE, viewRange);
    }

    public float getShadowRadius() {
        return this.dataTracker.get(DisplayTrackedData.SHADOW_RADIUS);
    }

    public void setShadowRadius(float shadowRadius) {
        this.dataTracker.set(DisplayTrackedData.SHADOW_RADIUS, shadowRadius);
    }

    public float getShadowStrength() {
        return this.dataTracker.get(DisplayTrackedData.SHADOW_STRENGTH);
    }

    public void setShadowStrength(float shadowStrength) {
        this.dataTracker.set(DisplayTrackedData.SHADOW_STRENGTH, shadowStrength);
    }

    public float getDisplayWidth() {
        return this.dataTracker.get(DisplayTrackedData.WIDTH);
    }

    public void setDisplayWidth(float width) {
        this.dataTracker.set(DisplayTrackedData.WIDTH, width);
    }

    public void setDisplayHeight(float height) {
        this.dataTracker.set(DisplayTrackedData.HEIGHT, height);
    }

    public int getGlowColorOverride() {
        return this.dataTracker.get(DisplayTrackedData.GLOW_COLOR_OVERRIDE);
    }

    public void setGlowColorOverride(int glowColorOverride) {
        this.dataTracker.set(DisplayTrackedData.GLOW_COLOR_OVERRIDE, glowColorOverride);
    }
}