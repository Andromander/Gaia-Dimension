package androsa.gaiadimension.honing;

public record SkillSlots(int maxSlots, int badSlots) {

    public int getTotalSlots() {
        return this.maxSlots() + this.badSlots();
    }
}
