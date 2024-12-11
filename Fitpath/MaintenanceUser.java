public class MaintenanceUser extends User {

    public MaintenanceUser(String name, int age, double weight, double height, String activityLevel) {
        super(name, age, weight, height, activityLevel);
    }

    // Override the calculateCalories method for maintenance
    @Override
    public double calculateCalories() {
        double bmr = 10 * getWeight() + 6.25 * getHeight() - 5 * getAge() + 5;
        double activityMultiplier = switch (getActivityLevel().toLowerCase()) {
            case "Veary Active" -> 1.725;
            case "Active" -> 1.55;
            case "Rarely" -> 1.375;
            default -> 1.2;
        };
        return bmr * activityMultiplier; // No deficit for maintenance
    }

    @Override
    public double calculateRecommendedCalories() {
        return calculateCalories(); // No deficit for maintenance
    }

    @Override
    public double calculateCaloriesForLoss() {
        return calculateCalories(); // This is for maintenance, so no calorie loss
    }
}
