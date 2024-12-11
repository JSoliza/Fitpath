public class WeightLossUser extends User {
    private double goalWeight;

    public WeightLossUser(String name, int age, double weight, double height, String activityLevel, double goalWeight) {
        super(name, age, weight, height, activityLevel);
        this.goalWeight = goalWeight;
    }

    public double getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(double goalWeight) {
        this.goalWeight = goalWeight;
    }

    // Calculate maintenance calories using the BMR formula
    @Override
    public double calculateCalories() {
        double bmr = 10 * getWeight() + 6.25 * getHeight() - 5 * getAge() + 5;
        double activityMultiplier = switch (getActivityLevel().toLowerCase()) {
            case "very active" -> 1.725;
            case "active" -> 1.55;
            case "rarely" -> 1.375;
            default -> 1.2; // Default for sedentary lifestyle
        };
        return bmr * activityMultiplier; // Maintenance calories
    }

    // Recommended calories for weight loss (0.5 kg/week deficit)
    @Override
    public double calculateRecommendedCalories() {
        double maintenanceCalories = calculateCalories();
        double dailyDeficit = 550; // Daily deficit for 0.5 kg weight loss per week
        return maintenanceCalories - dailyDeficit; // Calculate calories after deficit
    }

    // Alternative calorie calculation method for weight loss (can reuse the same method)
    @Override
    public double calculateCaloriesForLoss() {
        return calculateRecommendedCalories(); // Reuse the same method for consistency
    }
}
