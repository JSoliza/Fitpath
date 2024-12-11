import java.util.ArrayList;
import java.util.Scanner;

public class Fitpath {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        User user = null;
        ArrayList<Meal> meals = new ArrayList<>();
        
        while (true) {
            System.out.println("\n:-------------------------------------------------------:");
            System.out.println(":    FitPath - Main Menu                                :");
            System.out.println(":-------------------------------------------------------:");
            System.out.println(": [Select choices from 1-5]                             :");
            System.out.println(":-------------------------------------------------------:");
            System.out.println(": 1. Enter/Update Personal Information                  :");
            System.out.println(": 2. Add/Update Meals (Breakfast, Lunch, Dinner, Snacks):");
            System.out.println(": 3. View User Information                              :");
            System.out.println(": 4. View Macros and Calories                           :");
            System.out.println(": 5. Exit                                               :");
            System.out.println(":-------------------------------------------------------:");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Personal Information Input
                    scanner.nextLine(); // Consume newline
                    System.out.println("\n:---------------------------------:");
                    System.out.println(": Enter your personal information :");
                    System.out.println(":---------------------------------:");
                    System.out.print("\nEnter your name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your age: ");
                    int age = scanner.nextInt();
                    System.out.print("Enter your weight (kg): ");
                    double weight = scanner.nextDouble();
                    System.out.print("Enter your height (cm): ");
                    double height = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter your activity level (Never, Rarely, Active, Very Active): ");
                    String activityLevel = scanner.nextLine();
                    System.out.print("Enter your fitness goal (1 - Weight Loss, 2 - Maintenance): ");
                    int goalType = scanner.nextInt();

                    if (goalType == 1) {
                        System.out.print("Enter your goal weight (kg): ");
                        double goalWeight = scanner.nextDouble();
                        user = new WeightLossUser(name, age, weight, height, activityLevel, goalWeight);
                    } else {
                        user = new MaintenanceUser(name, age, weight, height, activityLevel);
                    }
                    System.out.println("Personal information updated successfully!");
                    System.out.println("---------------------------------------------------------");
                    break;

                case 2:
                    // Add/Update Meals
                    if (user == null) {
                        System.out.println("Please enter your personal information first.");
                        break;
                    }

                    System.out.println("---------------------------------------------------------");
                    System.out.println("|                 Enter Meal Details                    |");
                    System.out.println("---------------------------------------------------------");

                    // Meal types
                    String[] mealTypes = {"Breakfast", "Lunch", "Dinner", "Snacks"};
                    
                    for (String mealType : mealTypes) {
                        System.out.printf("\nEnter details for %s:\n", mealType);

                        // Initialize variables for each macro and calorie input
                        double protein = 0, fats = 0, carbs = 0, calories = 0;

                        // Loop for manual input of each macro and calorie
                        boolean done = false;
                        while (!done) {
                            // Input for protein
                            System.out.print("Enter Protein (g): ");
                            protein = scanner.nextDouble();

                            // Input for fats
                            System.out.print("Enter Fats (g): ");
                            fats = scanner.nextDouble();

                            // Input for carbs
                            System.out.print("Enter Carbs (g): ");
                            carbs = scanner.nextDouble();

                            // Input for calories
                            System.out.print("Enter Calories: ");
                            calories = scanner.nextDouble();

                            // Add meal
                            meals.add(new Meal(protein, fats, carbs, calories));
                            System.out.println("Meal details added successfully!");

                            // Ask if the user wants to update this meal or continue
                            System.out.print("\nWould you like to update this meal again? (yes/no): ");
                            String updateChoice = scanner.next();
                            if (updateChoice.equalsIgnoreCase("no")) {
                                done = true; // Exit the loop for this meal
                            }
                        }
                    }

                    System.out.println("Meals updated successfully!");
                    System.out.println("------------------------");
                    break;

                case 3:
                    // View User Information
                    if (user == null) {
                        System.out.println("Please Create a Account of Information First!");
                    } else {
                        System.out.println("---------------------------------------------------------");
                        System.out.println("|                    User Information                   |");
                        System.out.println("---------------------------------------------------------");
                        System.out.printf("| %-20s | %s                         |\n", "Name", user.getName());
                        System.out.printf("| %-20s | %d                             |\n", "Age", user.getAge());
                        System.out.printf("| %-20s | %.2f kg                       |\n", "Weight", user.getWeight());
                        System.out.printf("| %-20s | %.2f cm                      |\n", "Height", user.getHeight());
                        System.out.printf("| %-20s | %s                         |\n", "Activity Level", user.getActivityLevel());
                        System.out.printf("| %-20s | %s                    |\n", "Goal", (user instanceof WeightLossUser ? "Weight Loss" : "Maintenance"));
                        System.out.println("---------------------------------------------------------");
                    }
                    break;

                    case 4:
                    // View Macros and Calories
                    if (user == null) {
                        System.out.println("Please enter your personal information first.");
                        break;
                    }
                
                    // Calculate recommended calories based on the user's goal
                    double recommendedCalories = user.calculateRecommendedCalories();
                    double totalProtein = 0, totalFats = 0, totalCarbs = 0, totalCalories = 0;
                
                    // Loop through each meal and sum up the macros
                    for (Meal meal : meals) {
                        totalProtein += meal.getProtein();
                        totalFats += meal.getFats();
                        totalCarbs += meal.getCarbs();
                        totalCalories += meal.calculateMealCalories();
                    }
                
                    // Display recommended macros and actual consumption
                    System.out.println("---------------------------------------------------------");
                    System.out.println("|      Recommended vs. Consumed Macros and Calories     |");
                    System.out.println("---------------------------------------------------------");
                    System.out.printf("| %-25s| %.2f                    |\n", "Recommended Daily Calories", recommendedCalories);
                    System.out.printf("| %-25s | %.2f                    |\n", "Total Calories from Meals", totalCalories);
                
                    // Recommended Macros (adjusted based on goal)
                    double recommendedProtein = recommendedCalories * 0.3 / 4;  // 30% protein, 1g protein = 4 calories
                    double recommendedFats = recommendedCalories * 0.3 / 9;  // 30% fats, 1g fat = 9 calories
                    double recommendedCarbs = recommendedCalories * 0.4 / 4;  // 40% carbs, 1g carbs = 4 calories
                
                    // Display recommended and consumed macros
                    System.out.printf("| %-25s | %.2f g                   |\n", "Recommended Protein", recommendedProtein);
                    System.out.printf("| %-25s | %.2f g                   |\n", "Recommended Fats", recommendedFats);
                    System.out.printf("| %-25s | %.2f g                   |\n", "Recommended Carbs", recommendedCarbs);
                
                    System.out.printf("| %-25s | %.2f g                   |\n", "Total Protein Consumed", totalProtein);
                    System.out.printf("| %-25s | %.2f g                   |\n", "Total Fats Consumed", totalFats);
                    System.out.printf("| %-25s | %.2f g                   |\n", "Total Carbs Consumed", totalCarbs);
                    System.out.println("---------------------------------------------------------");
                    
                    break;
                
                case 5:
                    // Exit
                    System.out.println("Exiting FitPath.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

