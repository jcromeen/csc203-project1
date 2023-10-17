/**
 * An action that can be taken by an entity
 */
public final class Animation implements Action {
    public ActionKind kind;
    public Entity entity;
    public WorldModel world;
    public ImageStore imageStore;
    public int repeatCount;

    public Animation(ActionKind kind, Entity entity, WorldModel world, ImageStore imageStore, int repeatCount) {
        this.kind = kind;
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
        this.repeatCount = repeatCount;
    }

    public static Animation createAnimationAction(Entity entity, int repeatCount) {
        return new Animation(ActionKind.ANIMATION, entity, null, null, repeatCount);
    }


    public void executeAction(EventScheduler scheduler) {
        this.executeAnimationAction(scheduler);
    }

    private void executeAnimationAction(EventScheduler scheduler) {
        entity.nextImage();

        if (this.repeatCount != 1) {
            scheduler.scheduleEvent(this.entity, createAnimationAction(this.entity, Math.max(this.repeatCount - 1, 0)), entity.getAnimationPeriod());
        }
    }
}
