
package payrollsystem;

import java.time.LocalDate;
import java.util.*;

public class PayrollSystem {

    public static void main(String[] args) {

        // 1) إنشاء القائمة وتعبئتها ببيانات متنوعة
        List<Employee> staff = new ArrayList<>();
        staff.add(new FullTimeEmployee("FT-101", "mohammed fares", 4200, LocalDate.of(2019, 3, 12), 650));
        staff.add(new FullTimeEmployee("FT-102", "Omar fares", 3100, LocalDate.of(2022, 9, 1), 400));
        staff.add(new FullTimeEmployee("FT-103", "rawad bh", 5000, LocalDate.of(2016, 1, 20), 900));
        staff.add(new ContractorEmployee("CT-201", "sohib dr", LocalDate.of(2023, 6, 15), 45, 120));
        staff.add(new ContractorEmployee("CT-202", "adel fares", LocalDate.of(2021, 11, 5), 60, 150));
        staff.add(new ContractorEmployee("CT-203", "Karim mh", LocalDate.of(2024, 2, 10), 30, 80));

        // تقييم الأداء لكل موظف (حسب الـ id)
        Map<String, Double> scores = new HashMap<>();
        scores.put("FT-101", 4.5);
        scores.put("FT-102", 3.0);
        scores.put("FT-103", 5.0);
        scores.put("CT-201", 4.2);
        scores.put("CT-202", 3.8);
        scores.put("CT-203", 4.0); // 4.0 ليست أعلى من 4.0، فلا مكافأة

        // 2) الفرز الطبيعي (Comparable): صافي الراتب تنازلياً
        Collections.sort(staff);
        System.out.println("=== Sorted by net salary (high -> low) ===");
        for (Employee e : staff) {
            System.out.println(e);
        }

        // 3) الفرز بـ Comparator مخصص: الأقدمية (الأقدم أولاً)
        Collections.sort(staff, new Comparator<Employee>() {
            @Override
            public int compare(Employee a, Employee b) {
                return a.getJoinDate().compareTo(b.getJoinDate());
            }
        });
        System.out.println("\n=== Sorted by seniority (oldest first) ===");
        for (Employee e : staff) {
            System.out.println(e);
        }

        // 4) مجموع الضرائب والمكافآت بحلقة واحدة (بدون instanceof أو casting)
        double totalTax = 0, totalBonus = 0;
        System.out.println("\n=== Tax & bonus per employee ===");
        for (Employee e : staff) {
            double tax = e.calculateTax();
            double bonus = e.calculateBonus(scores.get(e.getId()));
            totalTax += tax;
            totalBonus += bonus;
            System.out.printf(Locale.US, "%-14s | tax: %9.2f | bonus: %9.2f%n",
                    e.getName(), tax, bonus);
        }

        System.out.println("\n=== Company totals ===");
        System.out.printf(Locale.US, "Total tax   : %,.2f%n", totalTax);
        System.out.printf(Locale.US, "Total bonus : %,.2f%n", totalBonus);
    }
}
