public abstract class User {
    private String name;
    private int age;
    private double weight;
    private double height;
    private String activityLevel;

    public User(String name, int age, double weight, double height, String activityLevel) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.activityLevel = activityLevel;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }
    public String getActivityLevel() { return activityLevel; }
    public void setActivityLevel(String activityLevel) { this.activityLevel = activityLevel; }

    // Abstract method to be implemented in subclasses
    public abstract double calculateCalories();
    public abstract double calculateRecommendedCalories();
    public abstract double calculateCaloriesForLoss();
}
