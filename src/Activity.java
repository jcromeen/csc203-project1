/**
 * An action that can be taken by an entity
 */
public final class Activity implements Action {

    public Entity entity;
    public WorldModel world;
    public ImageStore imageStore;


    public Activity(Entity entity, WorldModel world, ImageStore imageStore) {
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
    }

    public static Activity createActivityAction(Entity entity, WorldModel world, ImageStore imageStore) {
        return new Activity(entity, world, imageStore);
    }

    public void executeAction(EventScheduler scheduler) {
        this.executeActivityAction(scheduler);
    }

    private void executeActivityAction(EventScheduler scheduler) {
        switch (this.entity.kind) {
            case SAPLING:
                entity.executeSaplingActivity(this.world, this.imageStore, scheduler);
                break;
            case TREE:
                entity.executeTreeActivity(this.world, this.imageStore, scheduler);
                break;
            case FAIRY:
                entity.executeFairyActivity(this.world, this.imageStore, scheduler);
                break;
            case DUDE_NOT_FULL:
                entity.executeDudeNotFullActivity(this.world, this.imageStore, scheduler);
                break;
            case DUDE_FULL:
                entity.executeDudeFullActivity(this.world, this.imageStore, scheduler);
                break;
            default:
                throw new UnsupportedOperationException(String.format("executeActivityAction not supported for %s", this.entity.kind));
        }
    }
}
