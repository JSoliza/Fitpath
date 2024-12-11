public class Meal {
    private double protein;
    private double fats;
    private double carbs;
    private double calories;

    public Meal(double protein, double fats, double carbs, double calories) {
        this.protein = protein;
        this.fats = fats;
        this.carbs = carbs;
        this.calories = calories;
    }

    // Getters and Setters
    public double getProtein() { return protein; }
    public void setProtein(double protein) { this.protein = protein; }
    public double getFats() { return fats; }
    public void setFats(double fats) { this.fats = fats; }
    public double getCarbs() { return carbs; }
    public void setCarbs(double carbs) { this.carbs = carbs; }
    public double getCalories() { return calories; }
    public void setCalories(double calories) { this.calories = calories; }

    // Calculate total calories for the meal
    public double calculateMealCalories() {
        return this.calories; // Use the user inputted calories directly
    }
}
